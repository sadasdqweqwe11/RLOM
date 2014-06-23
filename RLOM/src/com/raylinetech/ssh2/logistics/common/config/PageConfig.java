package com.raylinetech.ssh2.logistics.common.config;

import java.util.HashMap;
import java.util.Map;

public class PageConfig{
	
	private static Map<String, String> guojiaMap;
	private static Map<String, String> currencyMap;
	
	static {
		guojiaMap = new HashMap<String, String>();
		guojiaMap.put("UK", "英国");
		guojiaMap.put("UNITED KINGDOM", "英国");
		guojiaMap.put("GB", "英国");
		guojiaMap.put("US", "美国");
		guojiaMap.put("UNITED STATE", "美国");
		guojiaMap.put("USA", "美国");
		guojiaMap.put("RUSSIA", "俄罗斯");
		guojiaMap.put("FR", "法国");
		currencyMap =  new HashMap<String, String>();
		currencyMap.put("英国", "GBP");
		currencyMap.put("法国", "EUR");
		currencyMap.put("美国", "USD");
	}
	public static String getGuojia(String country){
		return guojiaMap.get(country);
	}
	public static String getCurrency(String guojia){
		return currencyMap.get(guojia);
	}
	
	
	public static final int PAGESIZE=30;
	public static final int OTHER_TOPIC_DISPLAY_NUMBER=5;
	public static final String NO_TOPIC_FOUND = "没有找到相关文章，指定的文章不存在或链接错误";
	public static final String NOT_LOGIN = "您尚未登录，请先登录再操作";
	public static final String NO_RIGHT = "抱歉，您没有权限执行此操作";
	public static final String SIGNUP_ERROR = "抱歉，邮箱验证码错误，请重新注册";
	public static final String BOARD_REGISTER_ERROR= "抱歉，申请版主失败，请重新申请";
	public static final String NAME_PASSWORD_ERROR= "用户名或密码错误，请重新登录";
	public static final String NAME_UPLOAD_ERROR= "上传文件错误，请检查,错误原因可能为\n1 文件格式错误，请另存为excel\n2 文件内部错误，请检查";
	public static final String NAME_READAY_SIGNUP= "已注册成功";
	public static final String NOSIGNUP= "您还没有进行邮箱验证，验证邮件可能躺在垃圾箱中，请再在邮箱里找找吧";
	public static final String COOKIE_USERID="xiaoquUserId";
	public static final String COOKIE_SESSIONID="sessionId";
	public static final String MESSAGE_TOOLONG="您一口气写的太多啦~！分两次发试试吧~";
	public static final String ORDERFILE_PATH="/uploadfile/excel/";
	public static final String TRACKINGNOFILE_PATH="/uploadfile/trackingno/";
	
	
	
}