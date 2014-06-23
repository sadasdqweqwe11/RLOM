package com.raylinetech.ssh2.logistics.common.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raylinetech.ssh2.logistics.common.config.PageConfig;
import com.raylinetech.ssh2.logistics.common.dao.SkuDao;
import com.raylinetech.ssh2.logistics.common.entity.OrderFile;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;
import com.raylinetech.ssh2.logistics.common.entity.Sku;
import com.raylinetech.ssh2.logistics.common.exception.ExcelException;
import com.raylinetech.ssh2.logistics.common.service.ExcelService;
import com.raylinetech.ssh2.logistics.common.service.LogisticsService;
import com.raylinetech.ssh2.logistics.common.service.TxtService;
import com.raylinetech.ssh2.logistics.common.util.DateUtil;

public class TxtServiceImpl implements TxtService{

	Logger logger = LoggerFactory.getLogger(TxtServiceImpl.class);

	private SkuDao skuDao;

	public SkuDao getSkuDao() {
		return skuDao;
	}

	public void setSkuDao(SkuDao skuDao) {
		this.skuDao = skuDao;
	}
	
	@Override
	public List<List> validateTxt(List<List> lists) {
		List<List> ls = new LinkedList<List>();
		
		
		for (int i = 0; i < lists.size(); i++) {
			List datas = lists.get(i);
			List val = new LinkedList();
			//第0位 ordernumber
			boolean mark0 = true;
			if(null == datas.get(0)|| "".equals(datas.get(0).toString().trim())){
				mark0 = false;
			}
			val.add(mark0);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			val.add(true);
			//第9位 phone number
			boolean mark9 = true;
			if(null == datas.get(9)|| "".equals(datas.get(9).toString().trim())){
				mark9 = false;
			}
			val.add(mark9);

			//第三位 SKUNO 查询得出是否此sku不存在，如果不存在则false
			boolean mark10 = true;
			if(null != datas.get(10)&& !"".equals(datas.get(10).toString().trim())){
				String skuno = datas.get(10).toString().trim();
				if(skuno.indexOf(ExcelService.BZ)>0){
				skuno= skuno.substring(0, skuno.indexOf(ExcelService.BZ));
				}
				System.out.println(this.skuDao + "sku dao is null?");
				Sku sku = this.skuDao.find(skuno);
				if(null==sku){
					mark10 = false;
				}
			}
			val.add(mark10);
			
			//第11位 
			boolean mark11 = true;
			if(null == datas.get(11)|| "".equals(datas.get(11).toString().trim())){
				mark11 = false;
			}
			val.add(mark11);
			
			//第12位 
			boolean mark12 = true;
			if(null == datas.get(12)|| "".equals(datas.get(12).toString().trim())){
				mark12 = false;
			}
			val.add(mark12);
			

			val.add(true);
			val.add(true);
			val.add(true);
			
			
			//第16位 rece name
			boolean mark16 = true;
			if(null == datas.get(16)|| "".equals(datas.get(16).toString().trim())){
				mark16 = false;
			}
			val.add(mark16);
			
			//第17位 address1
			boolean mark17 = true;
			if(null == datas.get(17)|| "".equals(datas.get(17).toString().trim())){
				mark17 = false;
			}
			val.add(mark17);
			
			
			//第18位 暂时不做处理
			val.add(true);
			//第19位 暂时不做处理
			val.add(true);
			
			//第20位 phone number
			boolean mark20 = true;
			if(null == datas.get(20)|| "".equals(datas.get(20).toString().trim())){
				mark20 = false;
			}
			val.add(mark20);
			
			//第21位 phone number
			val.add(true);
			
			//第22位 phone number
			boolean mark22 = true;
			if(null == datas.get(22)|| "".equals(datas.get(22).toString().trim())){
				mark22 = false;
			}
			val.add(mark22);
			
			//第23位 phone number
			boolean mark23 = true;
			if(null == datas.get(23)|| "".equals(datas.get(23).toString().trim())){
				mark23 = false;
			}
			val.add(mark23);
			
			ls.add(val);
		}
		return ls;
	}

	@Override
	public List<List> txtToList(String path, OrderFile orderFile)
			throws ExcelException {
	    try {
			BufferedReader br = new BufferedReader(new FileReader(path + orderFile.getFilename()));  
			StringBuilder sb = new StringBuilder();
			List<List> lists = new LinkedList<List>();
			String data = "";//一次读入一行，直到读入null为文件结束  
			while( data!=null){  
			      data = br.readLine(); //接着读下一行  
			      if(data!=null){
			    	  ArrayList<String> l = new ArrayList<String>();
			    	  String[] strs = data.split("	");
			    	  for (int i = 0; i < strs.length; i++) {
						l.add(strs[i]);
			    	  }
			    	  System.out.println(l);
			    	  lists.add(l);
			      }
			}
			System.out.println(sb.toString());
			if(lists!=null){
				lists.remove(0);
			}
			return lists;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;			
		}  
	}

	@Override
	public List<RLOrder> txtToRLORder(String path, OrderFile orderFile)
			throws ExcelException {
		List<List> lists = this.txtToList(path, orderFile);
		String fileName = orderFile.getOriginalfilename();
		int index = fileName.lastIndexOf(".");
		if(index>0){
			fileName = fileName.substring(0, index);
		}
		String[] datas = fileName.split("-");
		String pingtai = "";
		String zhanghao = "";
		String riqi = "";
		if(datas.length!=3){
			throw new ExcelException("文件命名错误，请按规定命名pingtai-zhanghao-riqi");
		}else{
			pingtai = datas[0];
			zhanghao = datas[1];
			riqi = datas[2];
		}
		
		
		Map<String, List<List<String>>> orderMap = new HashMap<String, List<List<String>>>();
		for (List<String> oneList : lists) {
			String orderNo = oneList.get(0);
			List<List<String>> orders = orderMap.get(orderNo);
			if(orders == null){
				orders = new ArrayList<List<String>>();
				orders.add(oneList);
				orderMap.put(orderNo, orders);
			}else{
				orderMap.get(orderNo).add(oneList);
			}
		}
		List<RLOrder> orders = new ArrayList<RLOrder>();
		for (Entry<String, List<List<String>>>  oneOrder: orderMap.entrySet()) {
			String ordernumber = oneOrder.getKey();
			List<List<String>>  value = oneOrder.getValue();
			RLOrder order = new RLOrder();
			order.setOrdernumber(ordernumber);
			//设置date为系统时间
			order.setBuyerphonenumber(value.get(0).get(9).trim());
			order.setSkuno(value.get(0).get(10).trim());
			List<RLOrderItem> items = new ArrayList<RLOrderItem>();
			for (List<String> list : value) {
				RLOrderItem item = new RLOrderItem();
				String skuno = list.get(10).trim();
				if(skuno.indexOf("--")>0){
					skuno= skuno.substring(0, skuno.indexOf(LogisticsService.BZ));
				}
				item.setSku(new Sku(skuno));
				item.setDescription(list.get(11).trim());
				item.setQuantity(list.get(12).trim());
				items.add(item);
			}
			order.setRlorderitems(items);
			order.setShipaddress1(value.get(0).get(17));
			String address2 = "";
			if(null != value.get(0).get(18) && !value.get(0).get(18).trim().equals("")){
				address2 = value.get(0).get(18).trim();
				if(null != value.get(0).get(19) && !value.get(0).get(19).trim().equals("")){
					address2 = address2 + " " + value.get(0).get(19).trim();
				}
			}
			order.setShipaddress2(address2);
			order.setShipcity(value.get(0).get(20).trim());
			order.setShipstate(value.get(0).get(21).trim());
			order.setPostalcode(value.get(0).get(22).trim());
			order.setShipcountry(value.get(0).get(23).toUpperCase().trim());
			order.setBuyername(value.get(0).get(16).trim());
			String country = order.getShipcountry();
			String guojia = PageConfig.getGuojia(country);
			if(guojia == null ||guojia.equals("")){
				guojia = "";
			}
			order.setGuojia(guojia);
			String currency = PageConfig.getCurrency(guojia);
			if(currency == null ||currency.equals("")){
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
			orders.add(order);
		}
		return orders;
	}
	public static void main(String[] args) {
		String a = "lsdflskx.lakjdf";
		System.out.println(a.substring(0,a.lastIndexOf(".")));
		System.out.println(PageConfig.getCurrency("英国"));
	}
}
