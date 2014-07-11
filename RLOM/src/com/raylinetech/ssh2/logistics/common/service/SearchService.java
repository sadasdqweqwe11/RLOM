package com.raylinetech.ssh2.logistics.common.service;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.Logistics;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;

public interface SearchService {

	public static final String SEARCH_DATE = "-1";
	public static final String SEARCH_LOGISTICSID = "1";
	public static final String SEARCH_LOGISTICS_ACCOUNT = "2";
	public static final String SEARCH_LOGISTICS_ADDRESS = "3";
	public static final String SEARCH_VENDOR = "4";
	public static final String SEARCH_ACCOUNT = "5";
	
	
	
	public List<RLOrder> searchRlOrders(String beginDate, String endDate, String func,
			String code);

	public List getSearchFunc(String func,String beginDate, String endDate);
	
	public List<Logistics> getAllLogistics();

}
