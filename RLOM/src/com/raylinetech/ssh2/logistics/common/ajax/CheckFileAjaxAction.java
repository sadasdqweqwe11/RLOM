package com.raylinetech.ssh2.logistics.common.ajax;

import java.io.File;
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
import com.raylinetech.ssh2.logistics.common.service.impl.ExcelServiceImpl;

public class CheckFileAjaxAction  extends ActionSupport {

	private static final Logger logger = LoggerFactory
			.getLogger(CheckFileAjaxAction.class);
	
	private File upload;
	
	
	public File getUpload() {
		return upload;
	}


	public void setUpload(File upload) {
		this.upload = upload;
	}


	public String checkExcel() throws Exception {
		System.out.println("comes here");
		//judge if user exists in session
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session  = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		ExcelService excelService = new ExcelServiceImpl();
		ActionContext ac = ActionContext.getContext();   
		ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);   
		String path = sc.getRealPath("/");
		JSONObject jsObjs = new JSONObject();
		jsObjs.put("abc", "def");
		JSONArray jsonArray = JSONArray.fromObject(jsObjs);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Charset", "utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(jsonArray.toString());
		System.out.println("comes here");
		response.flushBuffer();
//		try {
//			String classPath = sc.getContextPath();
//			System.out.println(path);
//			System.out.println(classPath);
//			OrderFile orderFile = (OrderFile)request.getAttribute("orderFile");
//			List<RLOrder> rlOrders = excelService.excelToRLORder(path+PageConfig.ORDERFILE_PATH, orderFile);
//			
//			return SUCCESS;
//		} catch (ExcelException e) {
//	 		StringWriter errors = new StringWriter();
//	 		e.printStackTrace(new PrintWriter(errors));
//	 		logger.error(errors.toString());
//	 		request.setAttribute("error", e.getMessage());
//	 		return "fail";
//		}
		return null;
	}
	
}
