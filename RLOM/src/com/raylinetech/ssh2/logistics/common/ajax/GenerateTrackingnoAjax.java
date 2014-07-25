package com.raylinetech.ssh2.logistics.common.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.TrackingNo;
import com.raylinetech.ssh2.logistics.common.service.RLOrderService;
import com.raylinetech.ssh2.logistics.common.service.TrackingNoService;

public class GenerateTrackingnoAjax extends ActionSupport {
	private static final long serialVersionUID = -6957468240449062657L;

	private static final Logger logger = LoggerFactory
			.getLogger(GenerateTrackingnoAjax.class);
	
	private String orders1;
	
	public String getOrders1() {
		return orders1;
	}

	public void setOrders1(String orders1) {
		this.orders1 = orders1;
	}

	private RLOrderService rlOrderService;

	private TrackingNoService trackingNoService;
	
	public TrackingNoService getTrackingNoService() {
		return trackingNoService;
	}

	public void setTrackingNoService(TrackingNoService trackingNoService) {
		this.trackingNoService = trackingNoService;
	}

	public RLOrderService getRlOrderService() {
		return rlOrderService;
	}

	public void setRlOrderService(RLOrderService rlOrderService) {
		this.rlOrderService = rlOrderService;
	}

	public String generateTrackingno() throws Exception{
		HttpServletResponse  response = ServletActionContext.getResponse();
		String[] orders = this.orders1.split(",");
		List ids = new ArrayList();
		for (Object object : orders) {
			ids.add(Long.parseLong((String)object));
		}
		List<RLOrder> rlOrders = this.rlOrderService.getRLOrderListFromRLOrderIds(ids);
		if(rlOrders == null){
			System.out.println("没有查找到订单");
			return null;
		}
		this.trackingNoService.generateTrackingNoTORLOrder(rlOrders);
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.write("generate success");
		out.close();
		return null;
	}
}
