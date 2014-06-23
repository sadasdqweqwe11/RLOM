package com.raylinetech.ssh2.logistics.common.config;

public class ServiceConfigBean {
	public static final int GENDER_MALE=1;
	public static final int GENDER_FEMALE=0;
	
	
	private String userName;
	private String password;
	private String serverHost;
	private String serverPort;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getServerHost() {
		return serverHost;
	}
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}
	public String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	public ServiceConfigBean(String userName, String password,
			String serverHost, String serverPort) {
		super();
		this.userName = userName;
		this.password = password;
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}
	private ServiceConfigBean() {
		super();
	}
}
