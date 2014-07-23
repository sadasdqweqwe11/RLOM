package com.raylinetech.ssh2.logistics.common.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.raylinetech.ssh2.logistics.common.entity.Logistics;
import com.raylinetech.ssh2.logistics.common.entity.OrderFile;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.User;
import com.raylinetech.ssh2.logistics.common.service.LogisticsService;
import com.raylinetech.ssh2.logistics.common.service.OrderFileService;
import com.raylinetech.ssh2.logistics.common.service.RLOrderService;

public class OrderFileAction extends ActionSupport {
	
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


	public String showFile() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		String url = request.getRequestURI();
		System.out.println(url);
		String id = url.substring(url.indexOf("orderFile/") + 10);
		OrderFile orderFile = this.orderFileService.find(Long.parseLong(id));
		System.out.println(orderFile);
		List<RLOrder> rlOrders = this.rlOrderService.getRLOrderListFromFile(orderFile.getId());
		//TODO
		for (RLOrder rlOrder : rlOrders) {
			System.out.println("vendor is "+ rlOrder.getVendor());
			
		}
		if(null == orderFile.getDescription() || "".equals(orderFile.getDescription())){
			List<Logistics> logisticss = this.logisticsService.getAllLogistics();
			request.setAttribute("logisticss", logisticss);
		}
		Map<Integer, List<RLOrder>> orderMap = this.getMapFromRLOrders(rlOrders);
		request.setAttribute("orderFile", orderFile);
		request.setAttribute("orderMap", orderMap);
		return SUCCESS;
	}
	
	private Map<Integer, List<RLOrder>> getMapFromRLOrders(List<RLOrder> rlOrders) {
		if(rlOrders == null){
			return null;
		}
		Map<Integer, List<RLOrder>> map = new TreeMap<Integer, List<RLOrder>>();
		for (RLOrder rlOrder : rlOrders) {
			if(null == rlOrder.getLogistics()){
				if(null == map.get(0)){
					List<RLOrder> orders  =  new ArrayList<RLOrder>();
					orders.add(rlOrder);
					map.put(0, orders);
				}else{
					map.get(0).add(rlOrder);
				}	
			}else{
				int logid = rlOrder.getLogistics().getId();
				if(null == map.get(logid)){
					List<RLOrder> orders  =  new ArrayList<RLOrder>();
					orders.add(rlOrder);
					map.put(rlOrder.getLogistics().getId(), orders);
				}else{
					map.get(rlOrder.getLogistics().getId()).add(rlOrder);
				}
			}
		}
		map.put(0, map.get(999));
		map.remove(999);
		return map;
	}
}
