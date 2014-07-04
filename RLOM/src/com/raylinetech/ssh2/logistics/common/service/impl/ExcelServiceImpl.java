package com.raylinetech.ssh2.logistics.common.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.raylinetech.ssh2.logistics.common.config.PageConfig;
import com.raylinetech.ssh2.logistics.common.dao.SkuDao;
import com.raylinetech.ssh2.logistics.common.entity.OrderFile;
import com.raylinetech.ssh2.logistics.common.entity.PrintLable;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;
import com.raylinetech.ssh2.logistics.common.entity.Sku;
import com.raylinetech.ssh2.logistics.common.exception.ExcelException;
import com.raylinetech.ssh2.logistics.common.service.ExcelService;
import com.raylinetech.ssh2.logistics.common.util.ExcelUtil;
import com.raylinetech.ssh2.logistics.common.util.StringUtil;

public class ExcelServiceImpl implements ExcelService {

	Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);

	private SkuDao skuDao;

	public SkuDao getSkuDao() {
		return skuDao;
	}

	public void setSkuDao(SkuDao skuDao) {
		this.skuDao = skuDao;
	}

	private String getEquickAddress(String shipAdress1, String shipAdress2,
			String shipCity) {
		String address = "";
		if (null != shipAdress1 && !"".equals(shipAdress1)) {
			address = address + shipAdress1;
		}
		if (null != shipAdress2 && !"".equals(shipAdress2)) {
			address = address + ", " + shipAdress2;
		}
		if (null != shipCity && !"".equals(shipCity)) {
			address = address + ", " + shipCity;
		}
		// 获取截取出来的adress
		int[] flag = { 0, 0, 0, 0, 0 };
		String[] line = { "", "", "", "" };
		String tempString = address;
		for (int i = 0; i < line.length; i++) {
			// 根据条件获取flag[i+1]
			if (i < 2) {
				flag[i + 1] = this.getPostLine(18, tempString);
			} else {
				flag[i + 1] = this.getPostLine(13, tempString);
			}
			// 将flag[i+1] 与flag[i]相加，得到字符串的endIndex
			flag[i + 1] = flag[i + 1] + flag[i];
			// 如果endindex > startindex,则直接截取字符串
			if (flag[i + 1] > flag[i]) {
				line[i] = address.substring(flag[i], flag[i + 1]);

			}
			// 如果相等，则从flag[i+1]开始，截取后边所有的字符
			else if (flag[i + 1] == flag[i]) {
				line[i] = address.substring(flag[i + 1]);
				break;
			}
			// 如果小于，则证明返回了-1，此列没有字符
			else {
				line[i] = "";
			}
			tempString = address.substring(flag[i + 1]);
		}
		return line[0] + "\r\n" + line[1] + "\r\n" + line[2] + "\r\n" + line[3];
	}

	/**
	 * case 1 如果ts > length
	 * 
	 * @param length
	 * @param targetString
	 * @return
	 */
	private int getPostLine(int length, String targetString) {
		if (null == targetString || 0 == targetString.length()) {
			return -1;
		}

		else if (targetString.length() > 0 && targetString.length() <= length) {
			return 0;
		}

		else {
			char char20 = targetString.charAt(length - 1);
			if (" ".equals(char20) || ",".equals(char20)) {
				return length;
			} else {
				String sub20 = targetString.substring(0, length);
				int lastSpace = sub20.lastIndexOf(" ");
				int lastComma = sub20.lastIndexOf(",");
				int lastUnchar = Math.max(lastSpace, lastComma);
				if (-1 != lastUnchar) {
					return lastUnchar + 1;
				} else {
					System.out.println("error , long world , please input");
					return length;
				}
			}
		}
	}

	/**
	 * 通过地址拼接出详细地址
	 * 
	 * @param shipAdress1
	 * @param shipAdress2
	 * @param shipCity
	 * @param shipState
	 * @return
	 */
	private String getShipAdress(String shipAdress1, String shipAdress2,
			String shipCity, String shipState) {
		String adress = "";
		if (null != shipAdress1 && !"".equals(shipAdress1)) {
			adress = adress + shipAdress1;
		}
		if (null != shipAdress2 && !"".equals(shipAdress2)) {
			adress = adress + ", " + shipAdress2;
		}
		if (null != shipCity && !"".equals(shipCity)) {
			adress = adress + ", " + shipCity;
		}
		if (null != shipState && !"".equals(shipState)) {
			adress = adress + ", " + shipState;
		}
		return adress;
	}

	@Override
	public List<RLOrder> excelToRLORder(String path, OrderFile orderFile)
			throws ExcelException {
		String fileName = orderFile.getOriginalfilename();
		int index = fileName.lastIndexOf(".");
		if (index > 0) {
			fileName = fileName.substring(0, index);
		}
		String[] datas = fileName.split("-");
		String pingtai = "";
		String zhanghao = "";
		String riqi = "";
		if (datas.length == 3) {
			pingtai = datas[0];
			zhanghao = datas[1];
			riqi = datas[2];
		}
		if (pingtai.equalsIgnoreCase("ebay")) {
			return ebayToRLORder(path, orderFile);
		} else {
			return RLExcelToRLORder(path, orderFile);
		}
	}

	private List<RLOrder> RLExcelToRLORder(String path, OrderFile orderFile)
			throws ExcelException {
		ExcelUtil excelUtil = null;
		try {
			excelUtil = new ExcelUtil(path + orderFile.getFilename());
			System.out.println(path + orderFile.getFilename());
		} catch (BiffException e) {
			throw new ExcelException("文件格式不正确");
		} catch (IOException e) {
			throw new ExcelException("找不到文件" + path + orderFile.getFilename());
		}
		Sheet sheet = excelUtil.getSheet(0);
		int rows = excelUtil.getRows(sheet);
		if (rows < 2) {
			throw new ExcelException("文件没有内容");
		}
		int cell = excelUtil.getCells(sheet);
		int mainRowFlag = 0;
		List<RLOrder> rlOrders = new LinkedList<RLOrder>();
		for (int i = 1; i < rows; i++) {
			// 从第二行开始将一行的元素写入list
			List<String> list = new LinkedList<String>();
			for (int j = 0; j < cell; j++) {
				String string = sheet.getCell(j, i).getContents();
				list.add(string.trim());
			}
			// 判断，如果此行为空行，则直接进入下一行。
			boolean flag = false;
			for (String data : list) {
				flag = flag || !"".equals(data.trim());
			}
			System.out.println(flag);
			if (!flag) {
				continue;
			}

			// 如果此行的第二个字符串为空，且i==1 ，则证明这是个问题表。
			if (null == list.get(1) || "".equals(list.get(1))) {
				if (i == 1) {
					logger.error("no data in the first row");
					break;
				} else {
					// 此时将数据写入mainRowFlag，表示此行为物料行
					if (null == list.get(3) || "".equals(list.get(3).trim())) {
						break;
					}
					RLOrderItem item = new RLOrderItem();
					RLOrder order = rlOrders.get(mainRowFlag);
					// item.setSkuno(list.get(3).trim());
					// item.setItemname(list.get(4).trim());
					// item.setPinming(list.get(5).trim());
					String skuno = list.get(3).trim();
					if (skuno.indexOf(ExcelService.BZ) > 0) {
						skuno = skuno.substring(0,
								skuno.indexOf(ExcelService.BZ));
					}
					item.setSku(new Sku(skuno));
					item.setQuantity(list.get(6).trim());
					item.setDescription(list.get(7).trim());
					order.getRlorderitems().add(item);
				}
			} else {
				RLOrder order = new RLOrder();
				order.setRlordernumber(list.get(0).trim());
				order.setOrdernumber(list.get(1).trim());
				order.setVendor(list.get(2).trim().substring(0, 6));
				order.setSkuno(list.get(3).trim());
				order.setItemname(list.get(4).trim());
				order.setPinming(list.get(5).trim());
				order.setQuantity(list.get(6).trim());
				order.setDescription(list.get(7).trim());
				order.setBuyername(list.get(8).trim());
				order.setShipaddress1(list.get(9).trim());
				order.setShipaddress2(list.get(10).trim());
				order.setShipcity(list.get(11).trim());
				order.setShipstate(list.get(12).trim());
				order.setPostalcode(list.get(13).trim());
				order.setShipcountry(list.get(14).trim());
				order.setBuyerphonenumber(list.get(15).trim());
				order.setDate(list.get(16).trim());
				order.setAmount(list.get(17).trim());
				order.setTrackingno(list.get(18).trim());
				order.setGuojia(list.get(19).trim());
				order.setMarketplace(list.get(20).trim());
				order.setAccount(list.get(21).trim());
				order.setCurrency(list.get(22).trim());
				order.setFileid(orderFile.getId());
				order.setUid(orderFile.getUid());
				List<RLOrderItem> items = new LinkedList<RLOrderItem>();
				if (null == list.get(3) || "".equals(list.get(3).trim())) {
				} else {
					RLOrderItem item = new RLOrderItem();
					// item.setSkuno(list.get(3).trim());
					// item.setItemname(list.get(4).trim());
					// item.setPinming(list.get(5).trim());
					item.setSku(new Sku(list.get(3).trim()));
					item.setQuantity(list.get(6).trim());
					item.setDescription(list.get(7).trim());
					// item.setOrdernumber(order.getOrdernumber());
					items.add(item);
				}
				order.setRlorderitems(items);
				rlOrders.add(order);
				mainRowFlag = rlOrders.size() - 1;
			}
		}
		return rlOrders;
	}

	private List<RLOrder> ebayToRLORder(String path, OrderFile orderFile)
			throws ExcelException {
		List<List> lists = this.excelToList(path, orderFile);
		String fileName = orderFile.getOriginalfilename();
		int index = fileName.lastIndexOf(".");
		if (index > 0) {
			fileName = fileName.substring(0, index);
		}
		String[] datas = fileName.split("-");
		String pingtai = "";
		String zhanghao = "";
		String riqi = "";
		if (datas.length != 3) {
			throw new ExcelException("文件命名错误，请按规定命名pingtai-zhanghao-riqi");
		} else {
			pingtai = datas[0];
			zhanghao = datas[1];
			riqi = datas[2];
		}

		Map<String, List<List<String>>> orderMap = new LinkedHashMap<String, List<List<String>>>();
		for (List<String> oneList : lists) {
			String buyerfullname = oneList.get(2).trim().toUpperCase();
			String buyeraddress1 = oneList.get(5).trim().toUpperCase();
			String nameAdressKey = buyerfullname+buyeraddress1;
			List<List<String>> orders = orderMap.get(nameAdressKey);
			if (orders == null) {
				orders = new ArrayList<List<String>>();
				orders.add(oneList);
				orderMap.put(nameAdressKey, orders);
			} else {
				orderMap.get(nameAdressKey).add(oneList);
			}
		}
		List<RLOrder> orders = new ArrayList<RLOrder>();
		for (Entry<String, List<List<String>>> oneOrder : orderMap.entrySet()) {
			List<List<String>> value = oneOrder.getValue();
			RLOrder order = new RLOrder();
			String[] ordernumbers = new String[value.size()];
			for (int i = 0; i < ordernumbers.length; i++) {
				ordernumbers[i] = value.get(i).get(0).trim();
			}
			String ordernumber = StringUtil.linkString("-", ordernumbers);
			order.setOrdernumber(ordernumber);
			order.setBuyerphonenumber(value.get(0).get(3).trim());

			order.setSkuno(value.get(0).get(31).trim());
			List<RLOrderItem> items = new ArrayList<RLOrderItem>();
			for (List<String> list : value) {
				RLOrderItem item = new RLOrderItem();
				String skuno = list.get(31).trim();
				if (skuno.indexOf(ExcelService.BZ) > 0) {
					skuno = skuno.substring(0, skuno.indexOf(ExcelService.BZ));
				}
				/* 暂时使用，因为之前的版本有些sku有和库中不一致，且没有下架TOBE UPDATE */
				String temSkuno = PageConfig.getTempSku(skuno.toUpperCase());
				if (temSkuno != null) {
					skuno = temSkuno;
				}
				item.setItemno(list.get(12).trim());
				item.setOrderno(list.get(0).trim());
				item.setSku(new Sku(skuno));
				item.setDescription(list.get(14).trim());
				item.setQuantity(list.get(15).trim());
				items.add(item);
			}
			order.setRlorderitems(items);
			order.setShipaddress1(value.get(0).get(5).trim());
			order.setShipaddress2(value.get(0).get(6).trim());
			order.setShipcity(value.get(0).get(7).trim());
			order.setShipstate(value.get(0).get(8).trim());
			order.setPostalcode(value.get(0).get(9).trim());
			order.setShipcountry(value.get(0).get(10).toUpperCase().trim());
			order.setBuyername(value.get(0).get(2).trim());
			String amount = value.get(0).get(16).trim();
			order.setAmount(StringUtil.getDoubleFromAmount(amount));
			String country = order.getShipcountry();
			String guojia = PageConfig.getGuojia(country);
			if (guojia == null || guojia.equals("")) {
				guojia = "";
			}
			order.setGuojia(guojia);
			String currency = PageConfig.getCurrency(guojia);
			if (currency == null || currency.equals("")) {
				currency = "";
			}

			order.setCurrency(currency);
			order.setDate(riqi);
			order.setAccount(zhanghao.toUpperCase());
			order.setMarketplace(pingtai.toUpperCase());
			order.setTrackingno("");
			order.setVendor("");
			order.setRlordernumber("");
			order.setFileid(orderFile.getId());
			order.setItemname("");
			order.setPinming("");
			order.setDescription("");
			order.setQuantity("");
			order.setUid(orderFile.getUid());
			orders.add(order);

		}
		return orders;
	}

	@Override
	public List<List> validateExcel(List<List> lists) {
		if(lists == null||lists.get(0)==null){
			return null;
		}else{
		if(lists.get(0).size()==39){
			return validateEbay(lists);
		}else{
			return validateRLExcel(lists);
		}
		}
	}
	
	private List<List> validateRLExcel(List<List> lists) {
		List<List> ls = new LinkedList<List>();

		for (int i = 0; i < lists.size(); i++) {
			List datas = lists.get(i);
			List val = new LinkedList();
			// 第0位
			val.add(true);
			// 第一位 如果为空，并且第3位也为空，则说明表有问题，停止并且退出循环
			// 如果为空且第8位不为空，则判断为false
			boolean mark1 = true;
			if (null == datas.get(1)
					|| "".equals(datas.get(1).toString().trim())) {
				if (null == datas.get(3)
						|| "".equals(datas.get(3).toString().trim())) {
					break;
				}
				if (null == datas.get(8)
						|| "".equals(datas.get(8).toString().trim())) {
				} else {
					mark1 = false;
				}
			}
			val.add(mark1);
			// 第二位 和第一位相同
			boolean mark2 = true;
			if (null == datas.get(2)
					|| "".equals(datas.get(2).toString().trim())) {
				if (null == datas.get(3)
						|| "".equals(datas.get(3).toString().trim())) {
					break;
				}
				if (null == datas.get(8)
						|| "".equals(datas.get(8).toString().trim())) {
				} else {
					mark2 = false;
				}
			}
			val.add(mark2);
			// 第三位 SKUNO 查询得出是否此sku不存在，如果不存在则false
			boolean mark3 = true;
			if (null != datas.get(3)
					&& !"".equals(datas.get(3).toString().trim())) {
				String skuno = datas.get(3).toString().trim();
				if (skuno.indexOf(ExcelService.BZ) > 0) {
					skuno = skuno.substring(0, skuno.indexOf(ExcelService.BZ));
				}
				Sku sku = this.skuDao.find(skuno);
				if (null == sku) {
					mark3 = false;
				}
			}
			val.add(mark3);
			// 第四位 暂时不做处理
			val.add(true);
			// 第五位 暂时不做处理
			val.add(true);
			// 第六位 判断字符类型,并且判断是否为空
			boolean mark6 = true;
			if (null == datas.get(6)
					|| "".equals(datas.get(6).toString().trim())) {
				if (null == datas.get(3)
						|| "".equals(datas.get(3).toString().trim())) {
				} else {
					mark6 = false;
				}
				val.add(mark6);
			} else {
				String pattern = "[0-9]*";
				Pattern pat = Pattern.compile(pattern);
				Matcher mat = pat.matcher(datas.get(6).toString());
				val.add(mat.matches());
			}
			// 第七位 暂时不做处理
			val.add(true);
			// 第八位 buyername需要判空
			boolean mark8 = true;
			if (null == datas.get(8)
					|| "".equals(datas.get(8).toString().trim())) {
				if (null == datas.get(1)
						|| "".equals(datas.get(1).toString().trim())) {
				} else {
					mark8 = false;
				}
			}
			val.add(mark8);
			// 第九位 address1 需要判断空
			boolean mark9 = true;
			if (null == datas.get(9)
					|| "".equals(datas.get(9).toString().trim())) {
				if (null == datas.get(1)
						|| "".equals(datas.get(1).toString().trim())) {
				} else {
					mark9 = false;
				}
			}
			val.add(mark9);
			// 第十位 address2 不做处理
			val.add(true);
			// 第十一位 city不为空
			boolean mark11 = true;
			if (null == datas.get(11)
					|| "".equals(datas.get(11).toString().trim())) {
				if (null == datas.get(1)
						|| "".equals(datas.get(1).toString().trim())) {
				} else {
					mark11 = false;
				}
			}
			val.add(mark11);
			// 第十二位 state不做处理
			val.add(true);
			// 第十三位 postcode 不为空
			boolean mark13 = true;
			if (null == datas.get(13)
					|| "".equals(datas.get(13).toString().trim())) {
				if (null == datas.get(1)
						|| "".equals(datas.get(1).toString().trim())) {
				} else {
					mark13 = false;
				}
			}
			val.add(mark13);
			// 第十四位 country 不为空
			boolean mark14 = true;
			if (null == datas.get(14)
					|| "".equals(datas.get(14).toString().trim())) {
				if (null == datas.get(1)
						|| "".equals(datas.get(1).toString().trim())) {
				} else {
					mark14 = false;
				}
			}
			val.add(mark14);
			// 第15位，可以为空，暂时不做判断
			val.add(true);
			// 第16位，date不为空
			boolean mark16 = true;
			if (null == datas.get(16)
					|| "".equals(datas.get(16).toString().trim())) {
				if (null == datas.get(1)
						|| "".equals(datas.get(1).toString().trim())) {
				} else {
					mark16 = false;
				}
			}
			val.add(mark16);

			// 第十七位 不为空，且符合正则表达式
			boolean mark17 = true;
			if (null == datas.get(17)
					|| "".equals(datas.get(17).toString().trim())) {
				if (null == datas.get(1)
						|| "".equals(datas.get(1).toString().trim())) {
				} else {
					mark17 = false;
				}
				val.add(mark17);
			} else {
				String pattern = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$";
				Pattern pat = Pattern.compile(pattern);
				Matcher mat = pat.matcher(datas.get(17).toString());
				val.add(mat.matches());
			}
			// 第十八位 trackingno，不判断
			val.add(true);
			// 第十九位，国名，不为空
			boolean mark19 = true;
			if (null == datas.get(19)
					|| "".equals(datas.get(19).toString().trim())) {
				if (null == datas.get(1)
						|| "".equals(datas.get(1).toString().trim())) {
				} else {
					mark19 = false;
				}
			}
			val.add(mark19);
			// 第二十位 marketplace，不为空
			boolean mark20 = true;
			if (null == datas.get(20)
					|| "".equals(datas.get(20).toString().trim())) {
				if (null == datas.get(1)
						|| "".equals(datas.get(1).toString().trim())) {
				} else {
					mark20 = false;
				}
			}
			val.add(mark20);
			// 第二十一位 account，不为空
			boolean mark21 = true;
			if (null == datas.get(21)
					|| "".equals(datas.get(21).toString().trim())) {
				if (null == datas.get(1)
						|| "".equals(datas.get(1).toString().trim())) {
				} else {
					mark21 = false;
				}
			}
			val.add(mark21);
			// 第22位 currency 不为空
			boolean mark22 = true;
			if (null == datas.get(22)
					|| "".equals(datas.get(22).toString().trim())) {
				if (null == datas.get(1)
						|| "".equals(datas.get(1).toString().trim())) {
				} else {
					mark22 = false;
				}
			}
			val.add(mark22);
			// System.out.println(val);
			ls.add(val);
		}
		return ls;
	}

	@Override
	public List<List> excelToList(String path, OrderFile orderFile)
			throws ExcelException {
		ExcelUtil excelUtil = null;
		try {
			excelUtil = new ExcelUtil(path + orderFile.getFilename());
			System.out.println(path + orderFile.getFilename());
		} catch (BiffException e) {
			throw new ExcelException("文件格式不正确");
		} catch (IOException e) {
			throw new ExcelException("找不到文件" + path + orderFile.getFilename());
		}
		Sheet sheet = excelUtil.getSheet(0);
		int rows = excelUtil.getRows(sheet);
		if (rows < 2) {
			throw new ExcelException("文件没有内容");
		}
		int cell = excelUtil.getCells(sheet);
		List<List> lists = new LinkedList<List>();
		for (int i = 1; i < rows; i++) {
			// 从第二行开始将一行的元素写入list
			List<String> list = new LinkedList<String>();
			for (int j = 0; j < cell; j++) {
				String string = sheet.getCell(j, i).getContents();
				list.add(string.trim());
			}
			System.out.println(list);
			// 判断，如果全部为false，则证明这是个空列，不添加
			boolean flag = false;
			for (String data : list) {
				flag = flag || !"".equals(data.trim());
			}
			System.out.println(flag);
			if (flag) {
				lists.add(list);
			}
		}
		return lists;
	}

	@Override
	public List<List> validateTrackingnoExcel(List<List> stringList) {
		List<List> ls = new LinkedList<List>();
		for (int i = 0; i < stringList.size(); i++) {
			List datas = stringList.get(i);
			List val = new LinkedList();
			// 第1位 需要判断空
			if (i != datas.size() - 1) {
				boolean mark1 = true;
				if (null == datas.get(0)
						|| "".equals(datas.get(0).toString().trim())) {
					mark1 = false;
				}
				val.add(mark1);
				// 第1位 需要判断空
				boolean mark2 = true;
				if (null == datas.get(1)
						|| "".equals(datas.get(1).toString().trim())) {
					mark2 = false;
				}
				val.add(mark2);
				ls.add(val);
			}
		}
		return ls;
	}

	private List<List> validateEbay(List<List> lists) {
		List<List> ls = new LinkedList<List>();
		for (int i = 0; i < lists.size(); i++) {
			List<String> datas = lists.get(i);
			List val = new LinkedList();
			// 第0位 ordernumber
			val.add(StringUtil.validateNull(datas.get(0)));
			val.add(StringUtil.validateNull(datas.get(1)));
			val.add(StringUtil.validateNull(datas.get(2)));
			val.add(StringUtil.validateNull(datas.get(3)));
			val.add(true);
			val.add(StringUtil.validateNull(datas.get(5)));
			val.add(true);
			val.add(StringUtil.validateNull(datas.get(7)));
			val.add(true);
			val.add(StringUtil.validateNull(datas.get(9)));
			val.add(StringUtil.validateNull(datas.get(10)));
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(StringUtil.validateNull(datas.get(15)));
			val.add(StringUtil.validateNull(datas.get(16)));
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			boolean mark31 = true;
			if(null != datas.get(10)&& !"".equals(datas.get(10).toString().trim())){
				String skuno = datas.get(10).toString().trim();
				if(skuno.indexOf(ExcelService.BZ)>0){
				skuno= skuno.substring(0, skuno.indexOf(ExcelService.BZ));
				}
				/*暂时使用，因为之前的版本有些sku有和库中不一致，且没有下架TOBE UPDATE */
				String temSkuno = PageConfig.getTempSku(skuno.toUpperCase());
				if(temSkuno!=null){
					skuno = temSkuno;
				}
				Sku sku = this.skuDao.find(skuno);
				if(null==sku){
					mark31 = false;
				}
			}
			val.add(mark31);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			ls.add(val);
		}
		return ls;
	}

	public static void main(String[] args) {
		// String pattern = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$";
		// Pattern pat = Pattern.compile(pattern);
		// Matcher mat = pat.matcher("11111.00");
		// System.out.println(mat.matches());
		String skuno = "sldjfoisudfo--1";
		if (skuno.indexOf(ExcelService.BZ) > 0) {
			skuno = skuno.substring(0, skuno.indexOf(ExcelService.BZ));
		}
		System.out.println(skuno);
	}

}