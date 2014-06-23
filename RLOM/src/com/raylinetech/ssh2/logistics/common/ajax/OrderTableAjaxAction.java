package com.raylinetech.ssh2.logistics.common.ajax;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.raylinetech.ssh2.logistics.common.action.LoginAction;
import com.raylinetech.ssh2.logistics.common.config.PageConfig;
import com.raylinetech.ssh2.logistics.common.entity.Logistics;
import com.raylinetech.ssh2.logistics.common.entity.OrderFile;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.User;
import com.raylinetech.ssh2.logistics.common.exception.ExcelException;
import com.raylinetech.ssh2.logistics.common.service.ExcelService;
import com.raylinetech.ssh2.logistics.common.service.RLOrderService;
import com.raylinetech.ssh2.logistics.common.service.SearchService;
import com.raylinetech.ssh2.logistics.common.service.impl.ExcelServiceImpl;

public class OrderTableAjaxAction  extends ActionSupport {

	private static final Logger logger = LoggerFactory
			.getLogger(OrderTableAjaxAction.class);
	
	private String date;
	
	private String func;
	
	private String code;
	
	private SearchService searchService;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public String loadTable() throws Exception {
		//judge if user exists in session
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session  = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			return "login";
		}
		if(null == date){
			request.setAttribute("error", "请选择日期");
			return "fail";
		}
		String[] dates = this.date.split("-");
		String beginDate = "";
		String endDate = "";
		if(dates.length==1){
			beginDate = this.toStringDate(dates[0]);
			endDate = beginDate;
		}
		if(dates.length==2){
			beginDate = this.toStringDate(dates[0]);			
			endDate = this.toStringDate(dates[1]);
		}
		System.out.println(beginDate + "is beginend");
		System.out.println(endDate + "is beginend");
		List<RLOrder> rlOrders = this.searchService.searchRlOrders(beginDate, endDate, this.func, this.code);
		List<Logistics> logisticss = this.searchService.getAllLogistics();
		
		if(rlOrders!=null && rlOrders.size() != 0 && rlOrders.get(0).getLogistics()!= null){
		request.setAttribute("logisticsid", rlOrders.get(0).getLogistics().getId());
		}
		request.setAttribute("rlOrders", rlOrders);
		request.setAttribute("logisticss", logisticss);
		request.setAttribute("func", this.func);
		return SUCCESS;
	}
	
	/**
	 * 将05/08/2014转换成20140508
	 * @param date
	 * @return
	 */
	private String toStringDate(String date){
		StringBuilder target = new StringBuilder();
		if(null==date||date.equals("")){
			return "";
		}
		String[] strs = date.split("/");
		target.append(strs[2].trim()); 
		target.append(strs[0].trim()); 
		target.append(strs[1].trim()); 
		return target.toString();
	}
	public static void main(String[] args) {
		
		System.out.println(new OrderTableAjaxAction().toStringDate("05/08/2014"));
	}
}
