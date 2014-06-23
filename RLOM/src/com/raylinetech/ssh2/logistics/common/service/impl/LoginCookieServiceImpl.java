package com.raylinetech.ssh2.logistics.common.service.impl;

import com.raylinetech.ssh2.logistics.common.service.LoginCookieService;


public class LoginCookieServiceImpl implements LoginCookieService{

	@Override
	public void insertLoginCookie(long userId, String sessionId) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean searchLoginCookie(long userId, String sessionId) {
		// TODO Auto-generated method stub
		return false;
	}

//	private LoginCookieDao loginCookieDao;
//	@Override
//	public void insertLoginCookie(long userId, String sessionId) {
//		//为保证一个id对应一个sessionid，如果没有则置入，如果有则更新
//		LoginCookie loginCookie = this.loginCookieDao.findByUid(userId);
//		if(loginCookie == null){
//			loginCookie = new LoginCookie();
//			loginCookie.setUid(userId);
//			loginCookie.setSessionId(sessionId);
//			this.loginCookieDao.add(loginCookie);
//		}else{
//			loginCookie.setSessionId(sessionId);
//			this.loginCookieDao.update(loginCookie);
//		}
//		
//
//	}
//
//	@Override
//	public boolean searchLoginCookie(long userId, String sessionId) {
//		LoginCookie loginCookie = this.loginCookieDao.findByUid(userId);
//		if(loginCookie == null){
//			return false;
//		}else{
//			String sid = loginCookie.getSessionId();
//			if(sessionId.equals(sid)){
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public LoginCookieDao getLoginCookieDao() {
//		return loginCookieDao;
//	}
//
//	public void setLoginCookieDao(LoginCookieDao loginCookieDao) {
//		this.loginCookieDao = loginCookieDao;
//	}

}
