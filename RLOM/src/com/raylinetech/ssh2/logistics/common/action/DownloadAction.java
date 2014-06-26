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
import com.raylinetech.ssh2.logistics.common.file.SZSBTExcel;
import com.raylinetech.ssh2.logistics.common.file.TableExcel;
import com.raylinetech.ssh2.logistics.common.file.TableExcelOperator;
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

public class DownloadAction extends ActionSupport{
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
		HttpSession session  = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if(null == this.orders){
			return "fail";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String)object));
		}
		List<RLOrder> orders = this.rlOrderService.getRLOrderListFromRLOrderIds(ids);
		int logisticsid = orders.get(0).getLogistics().getId();
		ExcelService exc = new ExcelServiceImpl();
		PdfService pdf = new PdfServiceImpl();
		try {
			OutputStream out = response.getOutputStream(); 
		        ExcelModel model = null;
//				if(logisticsid == PdfService.LOGISTIC_BJEQUICK){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "BJEQUICK.xls");
//					model= new EquickExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_SHEQUICK){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SHEQUICK.xls");
//					model= new EquickExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_SZEQUICK){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SZEQUICK.xls");
//					model= new EquickExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_BJEUB){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "BJEUB.xls");
//					model = new EUBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_SHEUB){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SHEUB.xls");
//					model = new EUBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_BJXB){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "BJXB.xls");
//					model = new GHZGYZXBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_SZBJXB){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SZBJXB.xls");
//					model = new GHZGYZXBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_SHXB){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SHXB.xls");
//					model = new GHZGYZXBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_YWSHXB){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "YWSHXB.xls");
//					model = new GHZGYZXBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_BJXBBGH){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "BJXBBGH.xls");
//					model = new BGHZGYZXBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_SZBJXBBGH){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SZBJXBBGH.xls");
//					model = new BGHZGYZXBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_SHXBBGH){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SHXBBGH.xls");
//					model = new BGHZGYZXBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_YWSHXBBGH){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "YWSHXBBGH.xls");
//					model = new BGHZGYZXBExcel(orders);
//				}if(logisticsid == PdfService.LOGISTIC_BJYYB){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "BJYYB.xls");
//					model = new YYBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_SHYYB){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SHYYB.xls");
//					model = new YYBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_SZYYB){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SZYYB.xls");
//					model = new YYBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_YWYYB){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "YWYYB.xls");
//					model = new YYBExcel(orders);
//				}if(logisticsid == PdfService.LOGISTIC_BJHLXB){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "BJHLXB.xls");
//					model = new HLXBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_SHHLXB){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SHHLXB.xls");
//					model = new HLXBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_SZHLXB){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SZHLXB.xls");
//					model = new HLXBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_YWHLXB){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "YWHLXB.xls");
//					model = new HLXBExcel(orders);
//				}else if(logisticsid == PdfService.LOGISTIC_SZSBT){
//					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SZSBT.xls");
//					model = new SZSBTExcel(orders);
//				}
				if(logisticsid == PdfService.LOGISTIC_BJEQUICK){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "BJEQUICK.xls");
					model= new EquickExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_SHEQUICK){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SHEQUICK.xls");
					model= new EquickExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_SZEQUICK){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SZEQUICK.xls");
					model= new EquickExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_BJEUB){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "BJEUB.xls");
					model = new EUBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_SHEUB){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SHEUB.xls");
					model = new EUBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_BJXB){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "BJXB.xls");
					model = new GHZGYZXBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_SZBJXB){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SZBJXB.xls");
					model = new GHZGYZXBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_SHXB){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SHXB.xls");
					model = new GHZGYZXBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_YWSHXB){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "YWSHXB.xls");
					model = new GHZGYZXBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_BJXBBGH){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "BJXBBGH.xls");
					model = new GHZGYZXBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_SZBJXBBGH){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SZBJXBBGH.xls");
					model = new GHZGYZXBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_SHXBBGH){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SHXBBGH.xls");
					model = new GHZGYZXBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_YWSHXBBGH){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "YWSHXBBGH.xls");
					model = new GHZGYZXBExcel(orders);
				}if(logisticsid == PdfService.LOGISTIC_BJYYB){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "BJYYB.xls");
					model = new GHZGYZXBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_SHYYB){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SHYYB.xls");
					model = new GHZGYZXBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_SZYYB){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SZYYB.xls");
					model = new GHZGYZXBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_YWYYB){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "YWYYB.xls");
					model = new GHZGYZXBExcel(orders);
				}if(logisticsid == PdfService.LOGISTIC_BJHLXB){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "BJHLXB.xls");
					model = new GHZGYZXBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_SHHLXB){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SHHLXB.xls");
					model = new GHZGYZXBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_SZHLXB){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SZHLXB.xls");
					model = new GHZGYZXBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_YWHLXB){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "YWHLXB.xls");
					model = new GHZGYZXBExcel(orders);
				}else if(logisticsid == PdfService.LOGISTIC_SZSBT){
					response.setHeader("content-disposition","attachment;filename="+"scbd"+DateUtil.yyyyMMdd()+ "SZSBT.xls");
					model = new SZSBTExcel(orders);
				}
				response.setContentType("APPLICATION/msexcel");
				ExcelOperator operator = ExcelOperatorFactory.getExcelOperatorInstance();
				operator.WriteExcel(model, out);
			out.close();  
	        response.flushBuffer();//强行将响应缓存中的内容发送到目的
	        return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String downloadFJD() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session  = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if(null == this.orders){
			return "fail";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String)object));
		}
		List<RLOrder> orders = this.rlOrderService.getRLOrderListFromRLOrderIds(ids);
		int logisticsid = orders.get(0).getLogistics().getId();
		try {
			OutputStream out = response.getOutputStream(); 
			
			if(logisticsid == PdfService.LOGISTIC_BJEQUICK){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "BJEQUICK.xls");
			}else if(logisticsid == PdfService.LOGISTIC_SHEQUICK){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "SHEQUICK.xls");
			}else if(logisticsid == PdfService.LOGISTIC_SZEQUICK){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "SZEQUICK.xls");
			}else if(logisticsid == PdfService.LOGISTIC_BJEUB){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "BJEUB.xls");
			}else if(logisticsid == PdfService.LOGISTIC_SHEUB){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "SHEUB.xls");
			}else if(logisticsid == PdfService.LOGISTIC_BJXB){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "BJXB.xls");
			}else if(logisticsid == PdfService.LOGISTIC_SZBJXB){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "SZBJXB.xls");
			}else if(logisticsid == PdfService.LOGISTIC_SHXB){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "SHXB.xls");
			}else if(logisticsid == PdfService.LOGISTIC_YWSHXB){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "YWSHXB.xls");
			}else if(logisticsid == PdfService.LOGISTIC_BJXBBGH){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "BJXBBGH.xls");
			}else if(logisticsid == PdfService.LOGISTIC_SZBJXBBGH){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "SZBJXBBGH.xls");
			}else if(logisticsid == PdfService.LOGISTIC_SHXBBGH){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "SHXBBGH.xls");
			}else if(logisticsid == PdfService.LOGISTIC_YWSHXBBGH){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "YWSHXBBGH.xls");
			}if(logisticsid == PdfService.LOGISTIC_BJYYB){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "BJYYB.xls");
			}else if(logisticsid == PdfService.LOGISTIC_SHYYB){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "SHYYB.xls");
			}else if(logisticsid == PdfService.LOGISTIC_SZYYB){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "SZYYB.xls");
			}else if(logisticsid == PdfService.LOGISTIC_YWYYB){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "YWYYB.xls");
			}if(logisticsid == PdfService.LOGISTIC_BJHLXB){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "BJHLXB.xls");
			}else if(logisticsid == PdfService.LOGISTIC_SHHLXB){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "SHHLXB.xls");
			}else if(logisticsid == PdfService.LOGISTIC_SZHLXB){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "SZHLXB.xls");
			}else if(logisticsid == PdfService.LOGISTIC_YWHLXB){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "YWHLXB.xls");
			}else if(logisticsid == PdfService.LOGISTIC_SZSBT){
				response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "SZSBT.xls");
			}
		        response.setContentType("APPLICATION/msexcel");     
				ExcelModel model = new ZongFenJianDanExcel(orders);
				FJDExcelOperator operator = new FJDExcelOperator();
				operator.WriteExcel(model, out);
			out.close();  
	        response.flushBuffer();//强行将响应缓存中的内容发送到目的
	        return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String downloadBQ() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session  = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if(null == this.orders ){
			return "fail";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String)object));
		}
		List<RLOrder> orders = this.rlOrderService.getRLOrderListFromRLOrderIds(ids);
//		int logisticsid = orders.get(0).getLogistics().getId();
		PdfService pdf = new PdfServiceImpl();
		try {
			OutputStream out = response.getOutputStream(); 
//				if(logisticsid == PdfService.LOGISTIC_BJEQUICK){
//					response.setHeader("content-disposition","attachment;filename="+"bq"+DateUtil.yyyyMMdd()+ "BJEQUICK.pdf");
//				}else if(logisticsid == PdfService.LOGISTIC_SHEQUICK){
//					response.setHeader("content-disposition","attachment;filename="+"bq"+DateUtil.yyyyMMdd()+ "SHEQUICK.pdf");
//				}else if(logisticsid == PdfService.LOGISTIC_SZEQUICK){
//					response.setHeader("content-disposition","attachment;filename="+"bq"+DateUtil.yyyyMMdd()+ "SZEQUICK.pdf");
//				}else if(logisticsid == PdfService.LOGISTIC_BJEUB){
//					response.setHeader("content-disposition","attachment;filename="+"bq"+DateUtil.yyyyMMdd()+ "BJEUB.pdf");
//				}else if(logisticsid == PdfService.LOGISTIC_SHEUB){
//					response.setHeader("content-disposition","attachment;filename="+"bq"+DateUtil.yyyyMMdd()+ "SHEUB.pdf");
//				}else if(logisticsid == PdfService.LOGISTIC_BJXB){
//					response.setHeader("content-disposition","attachment;filename="+"bq"+DateUtil.yyyyMMdd()+ "BJXB.pdf");
//				}else if(logisticsid == PdfService.LOGISTIC_SZBJXB){
//					response.setHeader("content-disposition","attachment;filename="+"bq"+DateUtil.yyyyMMdd()+ "SZBJXB.pdf");
//				}else if(logisticsid == PdfService.LOGISTIC_SHXB){
//					response.setHeader("content-disposition","attachment;filename="+"bq"+DateUtil.yyyyMMdd()+ "SHXB.pdf");
//				}else if(logisticsid == PdfService.LOGISTIC_YWSHXB){
//					response.setHeader("content-disposition","attachment;filename="+"bq"+DateUtil.yyyyMMdd()+ "YWSHXB.pdf");
//				}else if(logisticsid == PdfService.LOGISTIC_BJXBBGH){
//					response.setHeader("content-disposition","attachment;filename="+"bq"+DateUtil.yyyyMMdd()+ "BJXBBGH.pdf");
//				}else if(logisticsid == PdfService.LOGISTIC_SZBJXBBGH){
//					response.setHeader("content-disposition","attachment;filename="+"bq"+DateUtil.yyyyMMdd()+ "SZBJXBBGH.pdf");
//				}else if(logisticsid == PdfService.LOGISTIC_SHXBBGH){
//					response.setHeader("content-disposition","attachment;filename="+"bq"+DateUtil.yyyyMMdd()+ "SHXBBGH.pdf");
//				}else if(logisticsid == PdfService.LOGISTIC_YWSHXBBGH){
//					response.setHeader("content-disposition","attachment;filename="+"bq"+DateUtil.yyyyMMdd()+ "YWSHXBBGH.pdf");
//				}
			response.setHeader("content-disposition","attachment;filename="+"bq"+DateUtil.yyyyMMdd()+ ".pdf");
		    response.setContentType("APPLICATION/pdf");     
			pdf.rlOrdersToPdf(orders, out);
			out.close();  
	        response.flushBuffer();//强行将响应缓存中的内容发送到目的
	        return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String deleteRLOrders() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session  = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if(null == this.orders ){
			return "fail";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String)object));
		}
		this.rlOrderService.deleteRLOrdersAndReturnTrackingno(ids);
		this.path = request.getHeader("referer");
		return SUCCESS;
	}
	public static void main(String[] args) {
		String rlOrderIds = ",123,34323,22";
		String[] ids = rlOrderIds.split(",");
		List<Long>  orids = new LinkedList<Long>();  
		for (int i = 0; i < ids.length; i++) {
			if(null != ids[i] &&!ids[i].equals("")){
				orids.add(Long.parseLong(ids[i]));
			}
		}
		System.out.println(orids);
	}
	
	
	public String downloadZFJD() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session  = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		String beginDate = "";
		String endDate = "";
		if(null == date|| "".equals(date.trim())){
			beginDate = DateUtil.yyyyMMdd();
			endDate = beginDate;
		}else{
			String[] dates = this.date.split("-");
			if(dates.length==1){
				beginDate = this.toStringDate(dates[0]);
				endDate = beginDate;
			}
			if(dates.length==2){
				beginDate = this.toStringDate(dates[0]);			
				endDate = this.toStringDate(dates[1]);
			}
		}
		if(null == vendor || "".equals(vendor.trim())){
			request.setAttribute("error", "不能获得供应商");
			return "fail";
		}
		
		List<RLOrder> orders = null;
		if("ALL".equalsIgnoreCase(this.vendor.trim())){
			orders = this.rlOrderService.getRLOrderListFromDate(beginDate, endDate);
		}else{
			orders= this.rlOrderService.getRLOrderListFromDateVendor(beginDate, endDate, this.vendor);
		}
		try {
			OutputStream out = response.getOutputStream(); 
				response.setHeader("content-disposition","attachment;filename="+"zfjd"+DateUtil.yyyyMMdd()+ this.vendor + ".xls");
		        response.setContentType("APPLICATION/msexcel");     
				ExcelModel model = new ZongFenJianDanExcel(orders);
				FJDExcelOperator operator = new FJDExcelOperator();
				operator.WriteExcel(model, out);
			out.close();  
	        response.flushBuffer();//强行将响应缓存中的内容发送到目的
	        return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String downloadTable() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session  = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		if(null == this.orders){
			return "fail";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String)object));
		}
		List<RLOrder> orders = this.rlOrderService.getRLOrderListFromRLOrderIds(ids);
		int logisticsid = orders.get(0).getLogistics().getId();
		try {
			OutputStream out = response.getOutputStream(); 
			response.setHeader("content-disposition","attachment;filename="+"fjd"+DateUtil.yyyyMMdd()+ "SZSBT.xls");
			response.setContentType("APPLICATION/msexcel");     
			ExcelModel model = new TableExcel(orders);
			TableExcelOperator operator = new TableExcelOperator();
			operator.WriteExcel(model, out);
			out.close();  
	        response.flushBuffer();//强行将响应缓存中的内容发送到目的
	        return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String downloadVendor() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session  = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		String beginDate = "";
		String endDate = "";
		if(null == date|| "".equals(date.trim())){
			beginDate = DateUtil.yyyyMMdd();
			endDate = beginDate;
		}else{
			String[] dates = this.date.split("-");
			if(dates.length==1){
				beginDate = this.toStringDate(dates[0]);
				endDate = beginDate;
			}
			if(dates.length==2){
				beginDate = this.toStringDate(dates[0]);			
				endDate = this.toStringDate(dates[1]);
			}
		}
		if(null == vendor || "".equals(vendor.trim())){
			request.setAttribute("error", "不能获得供应商");
			return "fail";
		}
		
		List<RLOrder> orders = null;
		if("ALL".equalsIgnoreCase(this.vendor.trim())){
			orders = this.rlOrderService.getRLOrderListFromDate(beginDate, endDate);
		}else{
			orders= this.rlOrderService.getRLOrderListFromDateVendor(beginDate, endDate, this.vendor);
		}
		ActionContext ac = ActionContext.getContext();   
		ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);   
		String path = sc.getRealPath("/");
		String date = DateUtil.yyyyMMdd();
		String filePath = path+"downloadfile/"+date + "/"+this.vendor+"/";
		try {
			File floder1 = new File(filePath);
				if(!floder1.exists()){
				floder1.mkdirs();
			}
			File zipFloder = new File(path+"/downloadfile/"+ "package/");	
			if(!zipFloder.exists()){
				zipFloder.mkdirs();
			}
			
			//打印总分拣单
			File fileWrite = new File(filePath + "zfjd" + DateUtil.yyyyMMdd()+vendor+".xls");
			fileWrite.createNewFile();
			FileOutputStream out = new FileOutputStream(fileWrite); 
			ExcelModel model = new ZongFenJianDanExcel(orders);
			FJDExcelOperator operator = new FJDExcelOperator();
			operator.WriteExcel(model, out);
			out.close();  
			
			//打印各个标签
			PdfService pdfService = new PdfServiceImpl();
			pdfService.rlOrdersToPdf(orders, filePath);
			
			
			String zipName = path+"downloadfile/"+ "package/"+date+this.vendor+".zip";
			System.out.println("zipname is " + zipName);
			System.out.println("filePath is " + filePath);
			//打包
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
			
			response.setHeader("content-disposition","attachment;filename="+DateUtil.yyyyMMdd()+ this.vendor + ".zip");
			response.setContentType("APPLICATION/zip");  
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
//			response.flushBuffer();//强行将响应缓存中的内容发送到目的
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 将05/08/2014转换成20140508
	 * @param date
	 * @return
	 */
	private String toStringDate(String date){
		StringBuilder target = new StringBuilder();
		if(null==date||date.equals("")){
			return "";
		}
		String[] strs = date.split("/");
		target.append(strs[2].trim()); 
		target.append(strs[0].trim()); 
		target.append(strs[1].trim()); 
		return target.toString();
	}
}
