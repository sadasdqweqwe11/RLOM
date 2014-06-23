package com.raylinetech.ssh2.logistics.common.ajax;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.raylinetech.ssh2.logistics.common.action.LoginAction;
import com.raylinetech.ssh2.logistics.common.config.PageConfig;
import com.raylinetech.ssh2.logistics.common.entity.OrderFile;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.User;
import com.raylinetech.ssh2.logistics.common.exception.ExcelException;
import com.raylinetech.ssh2.logistics.common.service.ExcelService;
import com.raylinetech.ssh2.logistics.common.service.RLOrderService;
import com.raylinetech.ssh2.logistics.common.service.impl.ExcelServiceImpl;
import com.raylinetech.ssh2.logistics.common.util.DateUtil;

public class GetChartsAjax extends ActionSupport {

	private RLOrderService rlOrderService;
	
	
	public RLOrderService getRlOrderService() {
		return rlOrderService;
	}


	public void setRlOrderService(RLOrderService rlOrderService) {
		this.rlOrderService = rlOrderService;
	}


	public static Logger getLogger() {
		return logger;
	}


	private static final Logger logger = LoggerFactory
			.getLogger(GetChartsAjax.class);


	public String getCharts() throws Exception {
		System.out.println("comes here");
		// judge if user exists in session
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse  response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		String vendor = request.getParameter("vendor");
		List<Object[]> logis = this.rlOrderService.getLogisticsAndCountFromDateVendor(DateUtil.yyyyMMdd(), DateUtil.yyyyMMdd(), vendor);
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Charset", "utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		JSONObject[] jsObjs = null;
		if(null == logis|| logis.size() == 0 ){
			System.out.println("没有取到任何数据");
			response.getWriter().write("[]");
			response.flushBuffer();
			return null;
		}
		jsObjs = new JSONObject[logis.size()];
		for (int i = 0; i < jsObjs.length; i++) {
			jsObjs[i]= new JSONObject();
			jsObjs[i].put("logistics", logis.get(i)[0]);
			jsObjs[i].put("count", logis.get(i)[1]);
		}
		JSONArray jsonArray = JSONArray.fromObject(jsObjs);
		response.getWriter().write(jsonArray.toString());
		System.out.println(jsonArray.toString());
		response.flushBuffer();
		return null;
	}

}
