package com.raylinetech.ssh2.logistics.common.action;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.raylinetech.ssh2.logistics.common.config.PageConfig;
import com.raylinetech.ssh2.logistics.common.entity.Logistics;
import com.raylinetech.ssh2.logistics.common.entity.OrderFile;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.TrackingNo;
import com.raylinetech.ssh2.logistics.common.entity.User;
import com.raylinetech.ssh2.logistics.common.exception.ExcelException;
import com.raylinetech.ssh2.logistics.common.file.EUBExcel;
import com.raylinetech.ssh2.logistics.common.file.ExcelModel;
import com.raylinetech.ssh2.logistics.common.file.ExcelOperator;
import com.raylinetech.ssh2.logistics.common.file.ExcelOperatorFactory;
import com.raylinetech.ssh2.logistics.common.service.ExcelService;
import com.raylinetech.ssh2.logistics.common.service.LogisticsService;
import com.raylinetech.ssh2.logistics.common.service.OrderFileService;
import com.raylinetech.ssh2.logistics.common.service.PdfService;
import com.raylinetech.ssh2.logistics.common.service.RLOrderService;
import com.raylinetech.ssh2.logistics.common.service.TxtService;
import com.raylinetech.ssh2.logistics.common.service.impl.TxtServiceImpl;
import com.raylinetech.ssh2.logistics.common.service.TrackingNoService;
import com.raylinetech.ssh2.logistics.common.service.impl.PdfServiceImpl;
import com.raylinetech.ssh2.logistics.common.util.DateUtil;

public class CheckExcelAction extends ActionSupport {
	
	private static final Logger logger = LoggerFactory
			.getLogger(CheckExcelAction.class);
	private String urlPath;
	
	private String fid;
	
	private String logisticsid;
	
	private String method;
	
	private String[] rlOrderIds;
	
	private RLOrderService rlOrderService;
	
	private TrackingNoService trackingNoService;
	
	private LogisticsService logisticsService;
	
	private OrderFileService orderFileService;
	
	private ExcelService excelService; 
	
	private TxtService txtService; 
	
	public TxtService getTxtService() {
		return txtService;
	}

	public void setTxtService(TxtService txtService) {
		this.txtService = txtService;
	}

	public ExcelService getExcelService() {
		return excelService;
	}

	public void setExcelService(ExcelService excelService) {
		this.excelService = excelService;
	}
	
	public String[] getRlOrderIds() {
		return rlOrderIds;
	}
	
	public void setRlOrderIds(String[] rlOrderIds) {
		this.rlOrderIds = rlOrderIds;
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}


	public OrderFileService getOrderFileService() {
		return orderFileService;
	}

	public void setOrderFileService(OrderFileService orderFileService) {
		this.orderFileService = orderFileService;
	}

	public String getLogisticsid() {
		return logisticsid;
	}
	
	public void setLogisticsid(String logisticsid) {
		this.logisticsid = logisticsid;
	}
	
	public String getUrlPath() {
		return urlPath;
	}
	
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	
	public LogisticsService getLogisticsService() {
		return logisticsService;
	}

	public void setLogisticsService(LogisticsService logisticsService) {
		this.logisticsService = logisticsService;
	}

	public String getFid() {
		return fid;
	}
	
	public void setFid(String fid) {
		this.fid = fid;
	}

	public RLOrderService getRlOrderService() {
		return rlOrderService;
	}

	public void setRlOrderService(RLOrderService rlOrderService) {
		this.rlOrderService = rlOrderService;
	}


	public TrackingNoService getTrackingNoService() {
		return trackingNoService;
	}

	public void setTrackingNoService(TrackingNoService trackingNoService) {
		this.trackingNoService = trackingNoService;
	}

	public String checkExcel() throws Exception {
		//judge if user exists in session
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session  = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		ActionContext ac = ActionContext.getContext();   
		ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);   
		String path = sc.getRealPath("/");
		String classPath = sc.getContextPath();
		System.out.println(path);
		System.out.println(classPath);
		OrderFile orderFile = (OrderFile)request.getAttribute("orderFile");
		//test
		List<List> stringList = null;
		List<List> checkList = null;
		String fileType = "xls";
		boolean flag = true;
		if(orderFile.getFilename().endsWith("xls")){
			stringList= this.excelService.excelToList(path+PageConfig.ORDERFILE_PATH, orderFile);
			checkList = this.excelService.validateExcel(stringList);
			fileType = "xls";
		}else{
			stringList= this.txtService.txtToList(path+PageConfig.ORDERFILE_PATH, orderFile);
			checkList = this.txtService.validateTxt(stringList);
			fileType = "txt";
		}
		if(checkList!= null){
			for (List list : checkList) {
				for (Object object : list) {
					if(object.equals(false)){
						flag = false;
						break;
					}
				}
			}
		}
		request.setAttribute("fileType",fileType);
		request.setAttribute("stringList",stringList);
		request.setAttribute("checkList",checkList);
		request.setAttribute("orderFile",orderFile);
		request.setAttribute("flag",flag);
		return SUCCESS;
	}
	
	public String checkTrackingnoExcel() throws Exception {
		//judge if user exists in session
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session  = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		ActionContext ac = ActionContext.getContext();   
		ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);   
		String path = sc.getRealPath("/");
		String classPath = sc.getContextPath();
		System.out.println(path);
		System.out.println(classPath);
		OrderFile orderFile = (OrderFile)request.getAttribute("orderFile");
		//test
		List<List> stringList= this.excelService.excelToList(path+PageConfig.TRACKINGNOFILE_PATH, orderFile);
		List<List> checkList = this.excelService.validateTrackingnoExcel(stringList);
		boolean flag = true;
		for (List list : checkList) {
			for (Object object : list) {
				if(object.equals(false)){
					flag = false;
					break;
				}
			}
		}
		request.setAttribute("stringList",stringList);
		request.setAttribute("checkList",checkList);
		request.setAttribute("orderFile",orderFile);
		request.setAttribute("flag",flag);
		
		return SUCCESS;
	}
	
	
	public String deleteExcel() throws Exception {
		//judge if user exists in session
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session  = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		this.orderFileService.cancelOrderFile(Long.parseLong(fid));
		return SUCCESS;
	}

	public String storeExcel() throws Exception {
	//judge if user exists in session
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session  = request.getSession();
	User user = (User) session.getAttribute("user");
	if(user == null){
		return "login";
	}
	ActionContext ac = ActionContext.getContext();   
	ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);   
	String path = sc.getRealPath("/");
	try {
		String classPath = sc.getContextPath();
		System.out.println(path);
		System.out.println(classPath);
//		OrderFile orderFile = (OrderFile)request.getAttribute("orderFile");
		OrderFile orderFile = this.orderFileService.find(Long.parseLong(this.fid));
		List<RLOrder> rlOrders = null;
		if(orderFile.getFilename().endsWith("xls")){
			 rlOrders = this.excelService.excelToRLORder(path+PageConfig.ORDERFILE_PATH, orderFile);
		}else{
			rlOrders = this.txtService.txtToRLORder(path+PageConfig.ORDERFILE_PATH, orderFile);
			System.out.println(rlOrders.size() + "check");
		}
		//save orders no tradiingno
//		Map<Integer, List<RLOrder>> orderMap= this.logisticsService.getMapFromRLOrders(rlOrders);
		List<RLOrder> result = this.logisticsService.allocationRLOrders(rlOrders);
		this.rlOrderService.saveOrUpdateRLOrderList(result);
		for (RLOrder rlOrder : result) {
			 String STR_FORMAT = "0000"; 
			 DecimalFormat df = new DecimalFormat(STR_FORMAT);
			 int id = (int)rlOrder.getId()%10000;
			 String rlOrdernumber = DateUtil.yyMMdd()+df.format(id);
			 rlOrder.setRlordernumber(rlOrdernumber);
		}
		this.rlOrderService.saveOrUpdateRLOrderList(result);
		this.urlPath = "orderFile/"+orderFile.getId();
		return SUCCESS;
	} catch (ExcelException e) {
 		StringWriter errors = new StringWriter();
 		e.printStackTrace(new PrintWriter(errors));
 		logger.error(errors.toString());
 		request.setAttribute("error", e.getMessage());
		return "fail";
	}
}
	
	public String storeTrackingnoExcel() throws Exception {
	//judge if user exists in session
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session  = request.getSession();
	User user = (User) session.getAttribute("user");
	if(user == null){
		return "login";
	}
	ActionContext ac = ActionContext.getContext();   
	ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);   
	String path = sc.getRealPath("/");
	try {
		String classPath = sc.getContextPath();
		System.out.println(path);
		System.out.println(classPath);
		OrderFile orderFile = this.orderFileService.find(Long.parseLong(this.fid));
		List<List> lists = this.excelService.excelToList(path+PageConfig.TRACKINGNOFILE_PATH, orderFile);
		List first = new ArrayList();
		Map orderMap = new HashMap();
//		for (List list : lists) {
//			orderMap.put(list.get(0), list.get(1));
//			first.add(list.get(0));
//		}
		for (List list : lists) {
			orderMap.put(list.get(1), list.get(0));
			first.add(list.get(1));
		}
		List<RLOrder> orders = this.rlOrderService.getRLOrderListFromRLOrdernumbers(first);
		for (RLOrder rlOrder : orders) {
			rlOrder.setTrackingno((String)orderMap.get(rlOrder.getRlordernumber()));
		}
		this.rlOrderService.saveOrUpdateRLOrderList(orders);
		this.urlPath = "orderFile/"+orderFile.getId();
		return SUCCESS;
	} catch (ExcelException e) {
 		StringWriter errors = new StringWriter();
 		e.printStackTrace(new PrintWriter(errors));
 		logger.error(errors.toString());
 		request.setAttribute("error", e.getMessage());
		return "fail";
	}
}
	
	public String generateExcel() throws Exception {
		//judge if user exists in session
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		ActionContext ac = ActionContext.getContext();   
		ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);   
//		String path = sc.getRealPath("/");
//		String classPath = sc.getContextPath();
		
		List<RLOrder> rlOrders = this.rlOrderService.getRLOrderListFromFile(Long.parseLong(this.fid));
        OrderFile orderFile = this.orderFileService.getOrderFile(Long.parseLong(this.fid));
//		String date = DateUtil.yyyyMMdd();
		//创建大文件夹
//		String filePath = path+"/downloadfile/"+date + "/";
//		File floder1 = new File(filePath);
//		if(!floder1.exists()){
//			floder1.mkdirs();
//		}
//		File zipFloder = new File(path+"/downloadfile/"+ "package/");	
//		if(!zipFloder.exists()){
//			zipFloder.mkdirs();
//		}
		//分配订单号
		int size = rlOrders.size();
		List<Long> rlOrderids = new LinkedList<Long>();
		for (RLOrder rlOrder : rlOrders) {
			rlOrder.setLogistics(new Logistics(Integer.parseInt(this.logisticsid),"",""));
			rlOrderids.add(rlOrder.getId());
		}
		
		List<TrackingNo> trackingnos  = this.trackingNoService.getTrackingNos(rlOrderids,Integer.parseInt(this.logisticsid));
		for (int i = 0; i < trackingnos.size(); i++) {
			rlOrders.get(i).setTrackingno(trackingnos.get(i).getTrackingno());
			rlOrders.get(i).setFileid(orderFile.getId());
		}
		//如果为equick
		this.rlOrderService.saveOrUpdateRLOrderList(rlOrders);
		return SUCCESS;
	}

	public String downloadExcel() throws Exception{

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session  = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		
		try {
			OutputStream out = response.getOutputStream(); 
	        //设置响应头和下载保存的文件名                
	        response.setHeader("content-disposition","attachment;filename="+"abc.xls");
//			response.setHeader("content-disposition","attachment;filename="+"abc.pdf");
			
	        //定义输出类型  
	        response.setContentType("APPLICATION/msexcel");      
			List<RLOrder> orders = this.rlOrderService.getRLOrderListFromFile(10);
			ExcelModel model = new EUBExcel(orders);
			ExcelOperator operator = ExcelOperatorFactory.getExcelOperatorInstance();
			operator.WriteExcel(model, out);
			PdfService pdf = new PdfServiceImpl();
//			pdf.rlOrdersToPdf(orders, PdfService.LOGISTIC_BJEUB, out);
//			pdf.rlOrdersToPdf(orders, PdfService.LOGISTIC_ZGYZPY, out);
			out.close();  
	        response.flushBuffer();//强行将响应缓存中的内容发送到目的
	        return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private Map<String, List<RLOrder>> getrlOrderByVendor(List<RLOrder> l){
		Map<String, List<RLOrder>> map = new HashMap<String, List<RLOrder>>();
		for (RLOrder p : l) {
			String vendor = p.getVendor();
			if(map.keySet().contains(vendor)){
				map.get(vendor).add(p);
			}else{
				List<RLOrder> print = new LinkedList<RLOrder>();
				print.add(p);
				map.put(vendor, print);
			}
		}
		return map;
	}
	public static void main(String[] args) {
//		String STR_FORMAT = "0000"; 
//		 DecimalFormat df = new DecimalFormat(STR_FORMAT);
//		 System.out.println(df.format(2));
		DecimalFormat df= new DecimalFormat("#.##");
		String amount = df.format(3.0 + Math.random()*11);
		System.out.println(amount);
	}
		
}
