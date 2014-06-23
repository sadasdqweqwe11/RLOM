package com.raylinetech.ssh2.logistics.common.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.raylinetech.ssh2.logistics.common.entity.Logistics;
import com.raylinetech.ssh2.logistics.common.entity.OrderFile;
import com.raylinetech.ssh2.logistics.common.entity.User;
import com.raylinetech.ssh2.logistics.common.service.LogisticsService;
import com.raylinetech.ssh2.logistics.common.service.OrderFileService;
import com.raylinetech.ssh2.logistics.common.service.RLOrderService;

public class ListUploadFilesAction extends ActionSupport {
	
	private OrderFileService orderFileService; 
	private RLOrderService rlOrderService;
	private LogisticsService logisticsService;
	
	public LogisticsService getLogisticsService() {
		return logisticsService;
	}


	public void setLogisticsService(LogisticsService logisticsService) {
		this.logisticsService = logisticsService;
	}


	public RLOrderService getRlOrderService() {
		return rlOrderService;
	}


	public void setRlOrderService(RLOrderService rlOrderService) {
		this.rlOrderService = rlOrderService;
	}


	public OrderFileService getOrderFileService() {
		return orderFileService;
	}


	public void setOrderFileService(OrderFileService orderFileService) {
		this.orderFileService = orderFileService;
	}


	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext
		.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		List<Logistics> logisticss = this.logisticsService.getAllLogistics();
		System.out.println(logisticss.size());
		request.setAttribute("logisticss", logisticss);
		return SUCCESS;
	}
}
