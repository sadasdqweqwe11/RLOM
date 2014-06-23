package com.raylinetech.ssh2.logistics.common.exception;

public class ExceptionMessage {
//	public static final int SHEET_NOT_FOUND = -10001;
//
//	public static final int FILE_NOT_FOUND = -20001;
//
//	public static final int WORKBOOK_IO = -30001;
//	
//	public static final int FILE_DATA_WRONG = -40001;
//	
//	public static final int FILE_DATA_CONFLICT = -50001;
	public static final String NO_TOPIC_FOUND = "没有找到相关文章，指定的文章不存在或链接错误";
	public static final String NOT_LOGIN = "您尚未登录，请先登录再操作";
	public static final String NO_RIGHT = "抱歉，您没有权限执行此操作";
	public static final String SIGNUP_ERROR = "抱歉，邮箱验证码错误，请重新注册";
	public static final String BOARD_REGISTER_ERROR= "抱歉，申请版主失败，请重新申请";
	public static final String NAME_PASSWORD_ERROR= "用户名或密码错误，请重新登录";

	public static final String DEFAULT_CODE = "0";
	public static final String DEFAULT_TYPE = "0";

	
	//错误码 eg：1121代表某个详细的错误
	private String code;
	//错误类型 eg：1 代表服务器错误 
	private String type;
	//错误描述，这个是给用户看的
	private String description;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ExceptionMessage(String code, String type, String description) {
		super();
		this.code = code;
		this.type = type;
		this.description = description;
	}
	public ExceptionMessage() {
		super();
	}
	public ExceptionMessage(String description) {
		super();
		this.code = DEFAULT_CODE;
		this.type = DEFAULT_TYPE;
		this.description = description;
	}
	
}
