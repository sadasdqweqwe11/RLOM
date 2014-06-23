package com.raylinetech.ssh2.logistics.common.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.raylinetech.ssh2.logistics.common.entity.User;

public class IndexAction extends ActionSupport {

	
	@Override
	public String execute() throws Exception {
		//judge if user exists in session
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		return "login";
//		StringWriter errors = new StringWriter();
//		e.printStackTrace(new PrintWriter(errors));
//		logger.error("downalod exception:"+e.getMessage()+"\t"+errors.toString());

	}

	
}
