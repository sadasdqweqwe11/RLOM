package com.raylinetech.ssh2.logistics.common.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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

import com.itextpdf.text.xml.XMLUtil;
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
import com.raylinetech.ssh2.logistics.common.util.XmlUtil;

public class YanwenXML  extends ActionSupport {

	private static final Logger logger = LoggerFactory
			.getLogger(YanwenXML.class);
	
	public void login() throws Exception {
		System.out.println("xml here");
		//judge if user exists in session
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session  = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return;
		}
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String url="http://online.yw56.com.cn/service/Common/LoginUser/"+name+"/"+password;  
        String curLine = "";  
        StringBuilder content = new StringBuilder();  
        URL server=new URL(url);  
        HttpURLConnection connection = (HttpURLConnection) server.openConnection();  
        connection.connect();  
        InputStream is = connection.getInputStream();  
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));  
        while ((curLine = reader.readLine()) != null) {  
            content.append(curLine);  
        }  
        is.close();  
        System.out.println(content.toString());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/xml;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Charset", "utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(content.toString());
	}
	
	public void getTrackingno() throws Exception {
		System.out.println("track here");
		//judge if user exists in session
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session  = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return;
		}
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String url="http://online.yw56.com.cn/service_sandbox/Users/100000/Expresses";  
        URL server=new URL(url);  
        HttpURLConnection connection = (HttpURLConnection) server.openConnection();  
        connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        connection.setRequestProperty("Authorization", "basic MTAwMDAwOjEwMDAwMQ==");
        //connection.connect();  
        connection.setDoOutput(true);
        connection.setDoInput(true);
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(),"utf-8");
        PrintWriter writer = new PrintWriter(out);
        writer.print(XmlUtil.test());
        writer.flush();
        writer.close();
        
        
        StringBuilder content = new StringBuilder();  
        InputStream is = connection.getInputStream();  
        String curLine = "";  
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));  
        while ((curLine = reader.readLine()) != null) {  
            content.append(curLine);  
        }  
        is.close();  
        System.out.println(content.toString());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/xml;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Charset", "utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(content.toString());
	}
}
