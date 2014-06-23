package com.raylinetech.ssh2.logistics.common.action;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.raylinetech.ssh2.logistics.common.config.PageConfig;
import com.raylinetech.ssh2.logistics.common.entity.User;
import com.raylinetech.ssh2.logistics.common.service.LoginCookieService;
import com.raylinetech.ssh2.logistics.common.service.UserService;
import com.raylinetech.ssh2.logistics.common.util.MD5Util;

public class LoginAction extends ActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(LoginAction.class);
	private String path;
	
	private String name;

	private String password;
	
	private String email;

	private List autoLogin;
	
	private UserService userService;

	private LoginCookieService loginCookieService;
	
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext
		.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		System.out.println(email);
		System.out.println(password);
		if (email != null && email.length() > 0) {
			logger.info("UserEmail:" + email);
			if (password != null && password.length() > 0) {
				logger.info("Password:" + password);
//				password = MD5Util.enCode(password);
				User user = this.userService.findByEmail(email.trim());
				if (user != null) {
					if("false".equals(user.getSalt())){
						request.setAttribute("error", PageConfig.NOSIGNUP);
						return "fail";
					}
					if (user.getPassword().equals(password.trim())) {
						session.setAttribute("user", user);
						String sessionId;
						Cookie cookieUserid, cookieSessionid;
						//为了解决checkbox单选导致的无法取消选择的问题，增加了一个隐藏的被选中的控件
						//所以这里判断是不是2，如果是1则表示该单选框没有选中
						if(null!=autoLogin){
							if(autoLogin.size()==2){
								cookieUserid = new Cookie(PageConfig.COOKIE_USERID, user.getUid()+""); // user是代表用户的bean    
								cookieUserid.setMaxAge(60 * 60 * 24 * 30);   //设置Cookie有效期为14天    
								sessionId = session.getId(); // 取得当前的session id    
								cookieSessionid = new Cookie(PageConfig.COOKIE_SESSIONID, sessionId);    
								cookieSessionid.setMaxAge(60 * 60 * 24 * 30);    
								long userId = user.getUid();
//								loginCookieService.insertLoginCookie(userId, sessionId);
								response.addCookie(cookieUserid);    
								response.addCookie(cookieSessionid);	
							}
						}
						/*
						 * 实现登录后跳转到当前界面
						 * 获取referer头，referer头为上一个url路径
						 * 如果是从引导页登入的，则进入主界面。否则，进去之前的界面。
						 */
						this.path = request.getHeader("referer");
						if(this.path!= null){
							String currentUrl = path.substring(path.lastIndexOf("/")+1);
							if(currentUrl.equals("index")||currentUrl.equals("index.action")||currentUrl.equals("")){
								return "main";
							}else{
								return "currentUrl";
							}
						}
					} else {
						logger.info("Login:password is wrong");
					}
				} else {
					System.out
					.println("Login:user name not exits or register is not completed");
				}
			} else {
				logger.info("Login:password is illegal");
			}
		} else {
			logger.info("Login:user name is illegal");
		}
		request.setAttribute("error", PageConfig.NAME_PASSWORD_ERROR);
		return "fail";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List getAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(List autoLogin) {
		this.autoLogin = autoLogin;
	}

	public LoginCookieService getLoginCookieService() {
		return loginCookieService;
	}

	public void setLoginCookieService(LoginCookieService loginCookieService) {
		this.loginCookieService = loginCookieService;
	}

public static void main(String[] args) {
	String currentUrl = "http://localhost:8080/bbs/index";
	String path = currentUrl.substring(currentUrl.lastIndexOf("/")+1);
	System.out.println(path);
}
}
