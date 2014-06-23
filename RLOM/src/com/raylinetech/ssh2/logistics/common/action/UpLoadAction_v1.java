package com.raylinetech.ssh2.logistics.common.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.raylinetech.ssh2.logistics.common.config.PageConfig;
import com.raylinetech.ssh2.logistics.common.entity.OrderFile;
import com.raylinetech.ssh2.logistics.common.entity.User;
import com.raylinetech.ssh2.logistics.common.service.OrderFileService;
import com.raylinetech.ssh2.logistics.common.service.UserService;
import com.raylinetech.ssh2.logistics.common.util.DateUtil;

public class UpLoadAction_v1 extends ActionSupport{
	
	private static final Logger logger = LoggerFactory
			.getLogger(LoginAction.class);
	private File upload;
	
	private String fileid;
	
	private String uploadFileName;
	
	private String uploadContenType;
	
	private UserService userService;
	
	private OrderFileService orderFileService;
	
	public OrderFileService getOrderFileService() {
		return orderFileService;
	}

	public void setOrderFileService(OrderFileService orderFileService) {
		this.orderFileService = orderFileService;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContenType() {
		return uploadContenType;
	}

	public void setUploadContenType(String uploadContenType) {
		this.uploadContenType = uploadContenType;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute() throws Exception{
		PrintWriter out = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		File f = this.getUpload();
		long size = 0;
//			out = response.getWriter();
			String saveName;
			try {
				InputStream is = new FileInputStream(f);
				String savePath = ServletActionContext.getServletContext().getRealPath("");
				size = is.available();
				savePath = savePath + "/uploadfile/excel/";
				//set saveName
				fileid = user.getUid()+"";
				String extName = uploadFileName.substring(uploadFileName.lastIndexOf("."));
				saveName = fileid+DateUtil.yyyyMMddHHmmss()+extName;
				
				//save upload file
				File destfile = new File(savePath,saveName);
				OutputStream os = new FileOutputStream(destfile);
				byte[] buffer = new byte[400];
				int length = 0 ;
				while((length = is.read(buffer)) > 0){
				     os.write(buffer, 0, length);
				}		 
				is.close();
				os.close();
				OrderFile orderFile = new OrderFile();
				orderFile.setUid(user.getUid());
				orderFile.setOriginalfilename(uploadFileName);
				orderFile.setFilesize(size);
				orderFile.setFiletype(extName);
				orderFile.setPostdatetime(new Date());
				orderFile.setFilename(saveName);
				this.orderFileService.save(orderFile);
				request.setAttribute("orderFile", orderFile);
			return SUCCESS;
			} catch (FileNotFoundException e) {
		 		StringWriter errors = new StringWriter();
		 		e.printStackTrace(new PrintWriter(errors));
		 		logger.error(errors.toString());
				request.setAttribute("error", PageConfig.NAME_UPLOAD_ERROR);
				return "fail";
			}
	}
}
