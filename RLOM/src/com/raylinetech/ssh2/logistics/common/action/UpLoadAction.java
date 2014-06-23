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

public class UpLoadAction extends ActionSupport{
	
	private static final Logger logger = LoggerFactory
			.getLogger(UpLoadAction.class);
	private File uploadFile;
	
	private String uploadFileFileName;
	
	private String uploadFileContenType;
	
	private File uploadTrackingno;
	
	private String uploadTrackingnoFileName;
	
	private String uploadTrackingnoContenType;
	
	private UserService userService;
	
	private OrderFileService orderFileService;
	
	public File getUploadTrackingno() {
		return uploadTrackingno;
	}

	public void setUploadTrackingno(File uploadTrackingno) {
		this.uploadTrackingno = uploadTrackingno;
	}

	public String getUploadTrackingnoFileName() {
		return uploadTrackingnoFileName;
	}

	public void setUploadTrackingnoFileName(String uploadTrackingnoFileName) {
		this.uploadTrackingnoFileName = uploadTrackingnoFileName;
	}

	public String getUploadTrackingnoContenType() {
		return uploadTrackingnoContenType;
	}

	public void setUploadTrackingnoContenType(String uploadTrackingnoContenType) {
		this.uploadTrackingnoContenType = uploadTrackingnoContenType;
	}

	public OrderFileService getOrderFileService() {
		return orderFileService;
	}

	public void setOrderFileService(OrderFileService orderFileService) {
		this.orderFileService = orderFileService;
	}


	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUploadFileContenType() {
		return uploadFileContenType;
	}

	public void setUploadFileContenType(String uploadFileContenType) {
		this.uploadFileContenType = uploadFileContenType;
	}

	public static Logger getLogger() {
		return logger;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String uploadOrder() throws Exception{
		PrintWriter out = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		File f = this.getUploadFile();
		long size = 0;
			String saveName;
			try {
				InputStream is = new FileInputStream(f);
				String savePath = ServletActionContext.getServletContext().getRealPath("");
				size = is.available();
				savePath = savePath + PageConfig.ORDERFILE_PATH;
				//set saveName
				String extName = uploadFileFileName.substring(uploadFileFileName.lastIndexOf("."));
				saveName = user.getUid()+""+DateUtil.yyyyMMddHHmmss()+extName;
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
				orderFile.setOriginalfilename(uploadFileFileName);
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
	
	public String uploadTrackingno() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		File f = this.getUploadTrackingno();
		long size = 0;
			String saveName;
			try {
				InputStream is = new FileInputStream(f);
				String savePath = ServletActionContext.getServletContext().getRealPath("");
				size = is.available();
				savePath = savePath + PageConfig.TRACKINGNOFILE_PATH;
				//set saveName
				String extName = this.uploadTrackingnoFileName.substring(this.uploadTrackingnoFileName.lastIndexOf("."));
				saveName = user.getUid()+""+DateUtil.yyyyMMddHHmmss()+extName;
				//save upload file

				System.out.println("gos here1 ");
				File destfile = new File(savePath,saveName);
				System.out.println("gos here1 ");
				OutputStream os = new FileOutputStream(destfile);
				System.out.println("gos here1 ");
				byte[] buffer = new byte[400];
				int length = 0 ;
				while((length = is.read(buffer)) > 0){
				     os.write(buffer, 0, length);
				}		 
				System.out.println("gos here2 ");
				is.close();
				os.close();
				OrderFile orderFile = new OrderFile();

				System.out.println("gos here3 ");
				orderFile.setUid(user.getUid());
				orderFile.setOriginalfilename(this.uploadTrackingnoFileName);
				orderFile.setFilesize(size);
				orderFile.setFiletype(extName);
				orderFile.setPostdatetime(new Date());
				orderFile.setFilename(saveName);
				System.out.println("gos here4 ");
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
