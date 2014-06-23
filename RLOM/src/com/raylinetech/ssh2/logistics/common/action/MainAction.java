package com.raylinetech.ssh2.logistics.common.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.raylinetech.ssh2.logistics.common.entity.User;
import com.raylinetech.ssh2.logistics.common.service.RLOrderService;
import com.raylinetech.ssh2.logistics.common.util.DateUtil;

public class MainAction extends ActionSupport {

	private RLOrderService rlOrderService;
	
	public RLOrderService getRlOrderService() {
		return rlOrderService;
	}

	public void setRlOrderService(RLOrderService rlOrderService) {
		this.rlOrderService = rlOrderService;
	}

	@Override
	public String execute() throws Exception {
		//judge if user exists in session
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		String startDate = DateUtil.yyyyMMdd();
		String endDate = DateUtil.yyyyMMdd();
		
		List<String> vendors = this.rlOrderService.getVendorFromDate(startDate, endDate);
		request.setAttribute("vendors", vendors);
		return "main";
	}

	
}
