package com.raylinetech.ssh2.logistics.common.action;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.raylinetech.ssh2.logistics.common.config.PageConfig;
import com.raylinetech.ssh2.logistics.common.entity.Logistics;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;
import com.raylinetech.ssh2.logistics.common.entity.User;
import com.raylinetech.ssh2.logistics.common.file.EUBExcel;
import com.raylinetech.ssh2.logistics.common.file.EquickExcel;
import com.raylinetech.ssh2.logistics.common.file.ExcelModel;
import com.raylinetech.ssh2.logistics.common.file.ExcelOperator;
import com.raylinetech.ssh2.logistics.common.file.ExcelOperatorFactory;
import com.raylinetech.ssh2.logistics.common.file.FenJianDanExcel;
import com.raylinetech.ssh2.logistics.common.file.SZSBTExcel;
import com.raylinetech.ssh2.logistics.common.service.ExcelService;
import com.raylinetech.ssh2.logistics.common.service.PdfService;
import com.raylinetech.ssh2.logistics.common.service.RLOrderService;
import com.raylinetech.ssh2.logistics.common.service.impl.ExcelServiceImpl;
import com.raylinetech.ssh2.logistics.common.service.impl.PdfServiceImpl;
import com.raylinetech.ssh2.logistics.common.util.DateUtil;

public class LogisticsAction extends ActionSupport{
	private static final Logger logger = LoggerFactory
			.getLogger(LogisticsAction.class);
	private List orders;
	
	private String logisticsid;
	
	private RLOrderService rlOrderService;

	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List getOrders() {
		return orders;
	}

	public String getLogisticsid() {
		return logisticsid;
	}

	public void setLogisticsid(String logisticsid) {
		this.logisticsid = logisticsid;
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

	public String changeLogistics() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String)object));
		}
		List<RLOrder> rlOrders = this.rlOrderService.getRLOrderListFromRLOrderIds(ids);
		for (RLOrder rlOrder : rlOrders) {
			rlOrder.setLogistics(new Logistics(Integer.parseInt(this.logisticsid),"",""));
		}
		this.rlOrderService.saveOrUpdateRLOrderList(rlOrders);
		this.path = request.getHeader("referer");
		return SUCCESS;
	}
	
	public String copyOrder() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String)object));
		}
		List<RLOrder> rlOrders = this.rlOrderService.getRLOrderListFromRLOrderIds(ids);
		List<RLOrder> copyOrders = new ArrayList<RLOrder>();
		for (RLOrder rlOrder : rlOrders) {
			RLOrder order = rlOrder.clone();
			order.setId(0);
			order.setRlordernumber("");
			order.setSplitstatus(PageConfig.SPLIT_COPY);
			order.setTrackingno("");
			copyOrders.add(order);
		}
		this.rlOrderService.saveOrUpdateRLOrderList(copyOrders);
		String STR_FORMAT = "0000"; 
		DecimalFormat df = new DecimalFormat(STR_FORMAT);
		for (RLOrder rlOrder : copyOrders) {
			 int id = (int)rlOrder.getId()%10000;
			 String date = rlOrder.getDate();
			 String rlOrdernumber = "";
			 if(date.length()>2){
				 rlOrdernumber = date+df.format(id);
			 }else{
				 date = DateUtil.yyMMdd();
				 rlOrdernumber = date+df.format(id);
			 }
			 //TOBE UPDATE copy出来的即是促销帐号，在这里为了区分将DATE少写两位，可能会造成打印标签的时候出现故障，测试一下。
			 rlOrder.setRlordernumber(rlOrdernumber);
		}
		this.rlOrderService.saveOrUpdateRLOrderList(copyOrders);
		this.path = request.getHeader("referer");
		return SUCCESS;
	}
	
	public String splitOrder() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String)object));
		}
		List<RLOrder> rlOrders = this.rlOrderService.getRLOrderListFromRLOrderIds(ids);
		List<RLOrder> splitOrders = new ArrayList<RLOrder>();
		for (RLOrder rlOrder : rlOrders) {
			if(rlOrder.getRlorderitems().size()<=1 && rlOrder.getRlorderitems().get(0).getQuantity().equals("1")){
				continue;
			}else{
				for (RLOrderItem item : rlOrder.getRlorderitems()) {
					int qua = Integer.parseInt(item.getQuantity());
					for (int i = 0; i < qua; i++) {
						RLOrder order = rlOrder.clone();
						RLOrderItem it = (RLOrderItem)item.clone();
						it.setQuantity("1");
						List<RLOrderItem> items = new ArrayList<RLOrderItem>();
						items.add(it);
						order.setRlorderitems(items);
						order.setId(0);
						order.setRlordernumber("");
						order.setSplitstatus(PageConfig.SPLIT_MT_SPLIT);
						splitOrders.add(order);
					}
				}
				
			}
		}
		if(splitOrders != null && splitOrders.size()!=0){
			this.rlOrderService.saveOrUpdateRLOrderList(splitOrders);
			String STR_FORMAT = "0000"; 
			DecimalFormat df = new DecimalFormat(STR_FORMAT);
			//设置ordernumber
			for (RLOrder rlOrder : splitOrders) {
				int id = (int)rlOrder.getId()%10000;
				String date = rlOrder.getDate();
				String rlOrdernumber = "";
				if(date.length()>2){
					rlOrdernumber = date.substring(2)+df.format(id);
				}else{
					rlOrdernumber = DateUtil.yyMMdd()+df.format(id);
				}
				rlOrder.setRlordernumber(rlOrdernumber);
			}
			this.rlOrderService.saveOrUpdateRLOrderList(splitOrders);
			this.rlOrderService.deleteRLOrdersAndReturnTrackingno(ids);
		}
		this.path = request.getHeader("referer");
		return SUCCESS;
	}
	
	public String mergeOrder() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		List ids = new ArrayList();
		for (Object object : this.orders) {
			ids.add(Long.parseLong((String)object));
		}
		List<RLOrder> rlOrders = this.rlOrderService.getRLOrderListFromRLOrderIds(ids);
		if(rlOrders== null || rlOrders.size()<2){
			return SUCCESS;
		}
		//首先克隆一份
		RLOrder mergeOrder = rlOrders.get(0).clone();
		mergeOrder.setId(0);
		mergeOrder.getRlorderitems().clear();
		mergeOrder.setSplitstatus(PageConfig.SPLIT_MT_MERGE);

		//将rlrders写入map中，合并sku相同的项
		Map<String,RLOrderItem> oneOrder = new LinkedHashMap<String, RLOrderItem>();
		for (RLOrder rlOrder : rlOrders) {
			for (RLOrderItem item : rlOrder.getRlorderitems()) {
				RLOrderItem it = oneOrder.get(item.getSku().getSkuno());
				if(it== null){
					//在这里将这个skuno以及对应的itemput进去
					oneOrder.put(item.getSku().getSkuno(), item);
				}else{
					//在这里得到这个it并且将item里的quantity加进来
					int quantity = Integer.parseInt(oneOrder.get(item.getSku().getSkuno()).getQuantity());
					quantity = quantity + Integer.parseInt(item.getQuantity());
					oneOrder.get(item.getSku().getSkuno()).setQuantity(quantity+"");
				}
			}
		}

		for (RLOrderItem rlOrderItem : oneOrder.values()) {
			mergeOrder.getRlorderitems().add(rlOrderItem);
		}
		
		List<RLOrder> mergeOrders = new ArrayList<RLOrder>();
		mergeOrders.add(mergeOrder);
		this.rlOrderService.saveOrUpdateRLOrderList(mergeOrders);
		String strFormat = "0000"; 
		DecimalFormat df = new DecimalFormat(strFormat);
		for (RLOrder rlOrder : mergeOrders) {
			 int id = (int)rlOrder.getId()%10000;
			 String date = rlOrder.getDate();
			 String rlOrdernumber = "";
			 if(date.length()>2){
				 rlOrdernumber = date.substring(2)+df.format(id);
			 }else{
				 rlOrdernumber = DateUtil.yyMMdd()+df.format(id);
			 }
			 rlOrder.setRlordernumber(rlOrdernumber);
		}
		//保存RLOrdernumber 
		this.rlOrderService.saveOrUpdateRLOrderList(mergeOrders);
		//删除已完成合单的子订单
		this.rlOrderService.deleteRLOrdersAndReturnTrackingno(ids);
		this.path = request.getHeader("referer");
		return SUCCESS;
	}
	
}
