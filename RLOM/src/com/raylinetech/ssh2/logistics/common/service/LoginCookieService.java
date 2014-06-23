package com.raylinetech.ssh2.logistics.common.service;

public interface LoginCookieService {
	public void insertLoginCookie(long userId, String sessionId);
	public boolean searchLoginCookie(long userId, String sessionId);
	
}
