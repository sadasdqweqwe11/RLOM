package com.raylinetech.ssh2.logistics.common.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.raylinetech.ssh2.logistics.common.entity.Logistics;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
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
}
