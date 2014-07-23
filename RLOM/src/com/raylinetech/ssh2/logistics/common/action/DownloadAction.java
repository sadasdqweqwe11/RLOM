package com.raylinetech.ssh2.logistics.common.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.file.AmazonExcel;
import com.raylinetech.ssh2.logistics.common.file.BGHZGYZXBExcel;
import com.raylinetech.ssh2.logistics.common.file.EUBExcel;
import com.raylinetech.ssh2.logistics.common.file.EquickExcel;
import com.raylinetech.ssh2.logistics.common.file.ExcelModel;
import com.raylinetech.ssh2.logistics.common.file.ExcelOperator;
import com.raylinetech.ssh2.logistics.common.file.ExcelOperatorFactory;
import com.raylinetech.ssh2.logistics.common.file.FJDExcelOperator;
import com.raylinetech.ssh2.logistics.common.file.FenJianDanExcel;
import com.raylinetech.ssh2.logistics.common.file.GHZGYZXBExcel;
import com.raylinetech.ssh2.logistics.common.file.HLXBExcel;
import com.raylinetech.ssh2.logistics.common.file.RLFJDExcelOperator;
import com.raylinetech.ssh2.logistics.common.file.RLFenJianDanExcel;
import com.raylinetech.ssh2.logistics.common.file.SZSBTExcel;
import com.raylinetech.ssh2.logistics.common.file.TableExcel;
import com.raylinetech.ssh2.logistics.common.file.TableExcelOperator;
import com.raylinetech.ssh2.logistics.common.file.TongjiExcel;
import com.raylinetech.ssh2.logistics.common.file.TongjiExcelOperator;
import com.raylinetech.ssh2.logistics.common.file.YYBExcel;
import com.raylinetech.ssh2.logistics.common.file.ZongFenJianDanExcel;
import com.raylinetech.ssh2.logistics.common.service.ExcelService;
import com.raylinetech.ssh2.logistics.common.service.PdfService;
import com.raylinetech.ssh2.logistics.common.service.PdfService;
import com.raylinetech.ssh2.logistics.common.service.RLOrderService;
import com.raylinetech.ssh2.logistics.common.service.impl.ExcelServiceImpl;
import com.raylinetech.ssh2.logistics.common.service.impl.PdfServiceImpl;
import com.raylinetech.ssh2.logistics.common.util.DateUtil;
import com.raylinetech.ssh2.logistics.common.util.ZipUtil;

public class DownloadAction extends ActionSupport {
	private static final Logger logger = LoggerFactory
			.getLogger(DownloadAction.class);
	private List orders;

	private RLOrderService rlOrderService;

	private String date;

	private String vendor;

	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public List getOrders() {
		return orders;
	}

	public void setOrders(List orders) {
		this.orders = orders;
	}

	public static Logger getLogger() {
		return logger;
	}

	public RLOrderService getRlOrderService() {
		return rlOrderService;
	}

	public void setRlOrderService(RLOrderService rlOrderService) {
		this.rlOrderService = rlOrderService;
	}

	public String downloadSCBD() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if (null == this.orders) {
			return "fail";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String) object));
		}
		List<RLOrder> orders = this.rlOrderService
				.getRLOrderListFromRLOrderIds(ids);
		int logisticsid = orders.get(0).getLogistics().getId();
		ExcelService exc = new ExcelServiceImpl();
		PdfService pdf = new PdfServiceImpl();
		try {
			OutputStream out = response.getOutputStream();
			ExcelModel model = null;
			if (logisticsid == PdfService.LOGISTICS_BJEQUICK
					|| logisticsid == PdfService.LOGISTICS_SHEQUICK
					|| logisticsid == PdfService.LOGISTICS_SZEQUICK) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "scbd" + DateUtil.yyyyMMdd()
								+ orders.get(0).getLogistics().getEngname()
								+ ".xls");
				model = new EquickExcel(orders);
			} else if (logisticsid == PdfService.LOGISTICS_BJEUB
					|| logisticsid == PdfService.LOGISTICS_SHEUB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "scbd" + DateUtil.yyyyMMdd()
								+ orders.get(0).getLogistics().getEngname()
								+ ".xls");
				model = new EUBExcel(orders);
			} else if (logisticsid == PdfService.LOGISTICS_BJXB
					|| logisticsid == PdfService.LOGISTICS_SZBJXB
					|| logisticsid == PdfService.LOGISTICS_SHXB
					|| logisticsid == PdfService.LOGISTICS_YWSHXB
					|| logisticsid == PdfService.LOGISTICS_BJXBBGH
					|| logisticsid == PdfService.LOGISTICS_SZBJXBBGH
					|| logisticsid == PdfService.LOGISTICS_SHXBBGH
					|| logisticsid == PdfService.LOGISTICS_YWSHXBBGH
					|| logisticsid == PdfService.LOGISTICS_BJYYB
					|| logisticsid == PdfService.LOGISTICS_SHYYB
					|| logisticsid == PdfService.LOGISTICS_SZYYB
					|| logisticsid == PdfService.LOGISTICS_YWYYB
					|| logisticsid == PdfService.LOGISTICS_BJYYBBGH
					|| logisticsid == PdfService.LOGISTICS_SHYYBBGH
					|| logisticsid == PdfService.LOGISTICS_SZYYBBGH
					|| logisticsid == PdfService.LOGISTICS_YWYYBBGH
					|| logisticsid == PdfService.LOGISTICS_BJHLXB
					|| logisticsid == PdfService.LOGISTICS_SHHLXB
					|| logisticsid == PdfService.LOGISTICS_SZHLXB
					|| logisticsid == PdfService.LOGISTICS_YWHLXB
					|| logisticsid == PdfService.LOGISTICS_BJYODEL
					|| logisticsid == PdfService.LOGISTICS_BJYODEL_ELE
					|| logisticsid == PdfService.LOGISTICS_BJYODEL_SMALL
					|| logisticsid == PdfService.LOGISTICS_SZYODEL
					|| logisticsid == PdfService.LOGISTICS_SZYODEL_ELE
					|| logisticsid == PdfService.LOGISTICS_SZYODEL_SMALL
					|| logisticsid == PdfService.LOGISTICS_SHYODEL
					|| logisticsid == PdfService.LOGISTICS_SHYODEL_ELE
					|| logisticsid == PdfService.LOGISTICS_SHYODEL_SMALL
					|| logisticsid == PdfService.LOGISTICS_YWYODEL
					|| logisticsid == PdfService.LOGISTICS_YWYODEL_ELE
					|| logisticsid == PdfService.LOGISTICS_YWYODEL_SMALL
					|| logisticsid == PdfService.LOGISTICS_YWSHEUB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "scbd" + DateUtil.yyyyMMdd()
								+ orders.get(0).getLogistics().getEngname()
								+ ".xls");
				model = new GHZGYZXBExcel(orders);
			} else if (logisticsid == PdfService.LOGISTICS_SZSBT) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "scbd" + DateUtil.yyyyMMdd()
								+ "SZSBT.xls");
				model = new SZSBTExcel(orders);
			}
			response.setContentType("APPLICATION/msexcel");
			response.setCharacterEncoding("UTF-8");
			ExcelOperator operator = ExcelOperatorFactory
					.getExcelOperatorInstance();
			operator.WriteExcel(model, out);
			out.close();
			response.flushBuffer();// 强行将响应缓存中的内容发送到目的
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String downloadAmazon() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if (null == this.orders) {
			return "fail";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String) object));
		}
		List<RLOrder> orders = this.rlOrderService
				.getRLOrderListFromRLOrderIds(ids);
		int logisticsid = orders.get(0).getLogistics().getId();
		ExcelService exc = new ExcelServiceImpl();
		PdfService pdf = new PdfServiceImpl();
		try {
			OutputStream out = response.getOutputStream();
			ExcelModel model = null;
			response.setHeader("content-disposition", "attachment;filename="
					+ "Amazon" + DateUtil.yyyyMMdd() + ".xls");
			response.setContentType("APPLICATION/msexcel");
			response.setCharacterEncoding("UTF-8");
			model = new AmazonExcel(orders);
			ExcelOperator operator = ExcelOperatorFactory
					.getExcelOperatorInstance();
			operator.WriteExcel(model, out);
			out.close();
			response.flushBuffer();// 强行将响应缓存中的内容发送到目的
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String downloadTongji() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if (null == this.orders) {
			return "fail";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String) object));
		}
		List<RLOrder> orders = this.rlOrderService
				.getRLOrderListFromRLOrderIds(ids);
		int logisticsid = orders.get(0).getLogistics().getId();
		ExcelService exc = new ExcelServiceImpl();
		PdfService pdf = new PdfServiceImpl();
		try {
			OutputStream out = response.getOutputStream();
			ExcelModel model = null;
			response.setHeader("content-disposition", "attachment;filename="
					+ "Tongji" + DateUtil.yyyyMMdd() + ".xls");
			response.setContentType("APPLICATION/msexcel");
			response.setCharacterEncoding("UTF-8");
			model = new TongjiExcel(orders);
			TongjiExcelOperator operator = new TongjiExcelOperator();
			operator.WriteExcel(model, out);
			out.close();
			response.flushBuffer();// 强行将响应缓存中的内容发送到目的
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String downloadFJD() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if (null == this.orders) {
			return "fail";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String) object));
		}
		List<RLOrder> orders = this.rlOrderService
				.getRLOrdersZFJDFromRLOrderIds(ids);
		int logisticsid = orders.get(0).getLogistics().getId();
		try {
			OutputStream out = response.getOutputStream();

			if (logisticsid == PdfService.LOGISTICS_BJEQUICK) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "BJEQUICK.xls");
			} else if (logisticsid == PdfService.LOGISTICS_SHEQUICK) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "SHEQUICK.xls");
			} else if (logisticsid == PdfService.LOGISTICS_SZEQUICK) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "SZEQUICK.xls");
			} else if (logisticsid == PdfService.LOGISTICS_BJEUB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "BJEUB.xls");
			} else if (logisticsid == PdfService.LOGISTICS_SHEUB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "SHEUB.xls");
			} else if (logisticsid == PdfService.LOGISTICS_BJXB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "BJXB.xls");
			} else if (logisticsid == PdfService.LOGISTICS_SZBJXB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "SZBJXB.xls");
			} else if (logisticsid == PdfService.LOGISTICS_SHXB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "SHXB.xls");
			} else if (logisticsid == PdfService.LOGISTICS_YWSHXB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "YWSHXB.xls");
			} else if (logisticsid == PdfService.LOGISTICS_BJXBBGH) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "BJXBBGH.xls");
			} else if (logisticsid == PdfService.LOGISTICS_SZBJXBBGH) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "SZBJXBBGH.xls");
			} else if (logisticsid == PdfService.LOGISTICS_SHXBBGH) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "SHXBBGH.xls");
			} else if (logisticsid == PdfService.LOGISTICS_YWSHXBBGH) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "YWSHXBBGH.xls");
			}
			if (logisticsid == PdfService.LOGISTICS_BJYYB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "BJYYB.xls");
			} else if (logisticsid == PdfService.LOGISTICS_SHYYB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "SHYYB.xls");
			} else if (logisticsid == PdfService.LOGISTICS_SZYYB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "SZYYB.xls");
			} else if (logisticsid == PdfService.LOGISTICS_YWYYB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "YWYYB.xls");
			}
			if (logisticsid == PdfService.LOGISTICS_BJHLXB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "BJHLXB.xls");
			} else if (logisticsid == PdfService.LOGISTICS_SHHLXB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "SHHLXB.xls");
			} else if (logisticsid == PdfService.LOGISTICS_SZHLXB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "SZHLXB.xls");
			} else if (logisticsid == PdfService.LOGISTICS_YWHLXB) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "YWHLXB.xls");
			} else if (logisticsid == PdfService.LOGISTICS_SZSBT) {
				response.setHeader("content-disposition",
						"attachment;filename=" + "fjd" + DateUtil.yyyyMMdd()
								+ "SZSBT.xls");
			}
			response.setContentType("APPLICATION/msexcel");
			ExcelModel model = new ZongFenJianDanExcel(orders);
			FJDExcelOperator operator = new FJDExcelOperator();
			operator.WriteExcel(model, out);
			out.close();
			response.flushBuffer();// 强行将响应缓存中的内容发送到目的
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String downloadRLFJD() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if (null == this.orders) {
			return "fail";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String) object));
		}
		List<RLOrder> orders = this.rlOrderService
				.getRLOrdersZFJDFromRLOrderIds(ids);
		try {
			OutputStream out = response.getOutputStream();
			response.setHeader("content-disposition",
						"attachment;filename=" + "rlfjd" + DateUtil.yyyyMMdd()
								+ ".xls");
			response.setContentType("APPLICATION/msexcel");
			RLFJDExcelOperator operator = new RLFJDExcelOperator();
			operator.WriteExcel(orders, out);
			out.close();
			response.flushBuffer();// 强行将响应缓存中的内容发送到目的
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String downloadBQ() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if (null == this.orders) {
			return "fail";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String) object));
		}
		List<RLOrder> orders = this.rlOrderService
				.getRLOrdersBQFromIds(ids);
		// int logisticsid = orders.get(0).getLogistics().getId();
		PdfService pdf = new PdfServiceImpl();
		try {
			OutputStream out = response.getOutputStream();
			response.setHeader("content-disposition", "attachment;filename="
					+ "bq" + DateUtil.yyyyMMdd() + ".pdf");
			response.setContentType("APPLICATION/pdf");
			pdf.rlOrdersToPdf(orders, out);
			out.close();
			response.flushBuffer();// 强行将响应缓存中的内容发送到目的
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String deleteRLOrders() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if (null == this.orders) {
			return "fail";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String) object));
		}
		this.rlOrderService.deleteRLOrdersAndReturnTrackingno(ids);
		this.path = request.getHeader("referer");
		return SUCCESS;
	}

	public static void main(String[] args) {
		String rlOrderIds = ",123,34323,22";
		String[] ids = rlOrderIds.split(",");
		List<Long> orids = new LinkedList<Long>();
		for (int i = 0; i < ids.length; i++) {
			if (null != ids[i] && !ids[i].equals("")) {
				orids.add(Long.parseLong(ids[i]));
			}
		}
		System.out.println(orids);
	}

	public String downloadZFJD() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		String beginDate = "";
		String endDate = "";
		if (null == date || "".equals(date.trim())) {
			beginDate = DateUtil.yyyyMMdd();
			endDate = beginDate;
		} else {
			String[] dates = this.date.split("-");
			if (dates.length == 1) {
				beginDate = this.toStringDate(dates[0]);
				endDate = beginDate;
			}
			if (dates.length == 2) {
				beginDate = this.toStringDate(dates[0]);
				endDate = this.toStringDate(dates[1]);
			}
		}
		if (null == vendor || "".equals(vendor.trim())) {
			request.setAttribute("error", "不能获得供应商");
			return "fail";
		}

		List<RLOrder> orders = null;
		if ("ALL".equalsIgnoreCase(this.vendor.trim())) {
			orders = this.rlOrderService.getRLOrderListFromDate(beginDate,
					endDate);
		} else {
			orders = this.rlOrderService.getRLOrderListFromDateVendor(
					beginDate, endDate, this.vendor);
		}
		try {
			OutputStream out = response.getOutputStream();
			response.setHeader("content-disposition", "attachment;filename="
					+ "zfjd" + DateUtil.yyyyMMdd() + this.vendor + ".xls");
			response.setContentType("APPLICATION/msexcel");
			ExcelModel model = new ZongFenJianDanExcel(orders);
			FJDExcelOperator operator = new FJDExcelOperator();
			operator.WriteExcel(model, out);
			out.close();
			response.flushBuffer();// 强行将响应缓存中的内容发送到目的
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String downloadTable() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if (null == this.orders) {
			return "fail";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String) object));
		}
		List<RLOrder> orders = this.rlOrderService
				.getRLOrderListFromRLOrderIds(ids);
		int logisticsid = orders.get(0).getLogistics().getId();
		try {
			OutputStream out = response.getOutputStream();
			response.setHeader("content-disposition", "attachment;filename="
					+ "fjd" + DateUtil.yyyyMMdd() + "SZSBT.xls");
			response.setContentType("APPLICATION/msexcel");
			ExcelModel model = new TableExcel(orders);
			TableExcelOperator operator = new TableExcelOperator();
			operator.WriteExcel(model, out);
			out.close();
			response.flushBuffer();// 强行将响应缓存中的内容发送到目的
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String downloadVendor() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		String beginDate = "";
		String endDate = "";
		if (null == date || "".equals(date.trim())) {
			beginDate = DateUtil.yyyyMMdd();
			endDate = beginDate;
		} else {
			String[] dates = this.date.split("-");
			if (dates.length == 1) {
				beginDate = this.toStringDate(dates[0]);
				endDate = beginDate;
			}
			if (dates.length == 2) {
				beginDate = this.toStringDate(dates[0]);
				endDate = this.toStringDate(dates[1]);
			}
		}
		if (null == vendor || "".equals(vendor.trim())) {
			request.setAttribute("error", "不能获得供应商");
			return "fail";
		}

		List<RLOrder> orders = null;
		if ("ALL".equalsIgnoreCase(this.vendor.trim())) {
			orders = this.rlOrderService.getRLOrderListFromDate(beginDate,
					endDate);
		} else {
			orders = this.rlOrderService.getRLOrderListFromDateVendor(
					beginDate, endDate, this.vendor);
		}
		ActionContext ac = ActionContext.getContext();
		ServletContext sc = (ServletContext) ac
				.get(ServletActionContext.SERVLET_CONTEXT);
		String path = sc.getRealPath("/");
		String date = DateUtil.yyyyMMdd();
		String filePath = path + "downloadfile/" + date + "/" + this.vendor
				+ "/";
		try {
			File floder1 = new File(filePath);
			if (!floder1.exists()) {
				floder1.mkdirs();
			}
			File zipFloder = new File(path + "/downloadfile/" + "package/");
			if (!zipFloder.exists()) {
				zipFloder.mkdirs();
			}

			// 打印总分拣单
			File fileWrite = new File(filePath + "zfjd" + DateUtil.yyyyMMdd()
					+ vendor + ".xls");
			fileWrite.createNewFile();
			FileOutputStream out = new FileOutputStream(fileWrite);
			ExcelModel model = new ZongFenJianDanExcel(orders);
			FJDExcelOperator operator = new FJDExcelOperator();
			operator.WriteExcel(model, out);
			out.close();

			// 打印各个标签
			PdfService pdfService = new PdfServiceImpl();
			pdfService.rlOrdersToPdf(orders, filePath);

			String zipName = path + "downloadfile/" + "package/" + date
					+ this.vendor + ".zip";
			System.out.println("zipname is " + zipName);
			System.out.println("filePath is " + filePath);
			// 打包
			ZipUtil zca = new ZipUtil(zipName);
			zca.compress(filePath);

			File file = new File(zipName);
			FileInputStream inputStream = new FileInputStream(file);
			InputStream fis = new BufferedInputStream(inputStream);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();
			inputStream.close();

			response.setHeader("content-disposition", "attachment;filename="
					+ DateUtil.yyyyMMdd() + this.vendor + ".zip");
			response.setContentType("APPLICATION/zip");
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			// response.flushBuffer();//强行将响应缓存中的内容发送到目的
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 将05/08/2014转换成20140508
	 * 
	 * @param date
	 * @return
	 */
	private String toStringDate(String date) {
		StringBuilder target = new StringBuilder();
		if (null == date || date.equals("")) {
			return "";
		}
		String[] strs = date.split("/");
		target.append(strs[2].trim());
		target.append(strs[0].trim());
		target.append(strs[1].trim());
		return target.toString();
	}
}
