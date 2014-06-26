package com.raylinetech.ssh2.logistics.common.service;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.RLOrder;

public interface RLOrderService {

	public void saveOrUpdateRLOrderList(List<RLOrder> rlOrders);
	
	public List<RLOrder> getRLOrderListFromDateLogistics(String beginDate,String endDate,int logisticsid);

	public List<RLOrder> getRLOrderListFromFile(long id);

	public List<RLOrder> getRLOrderListFromRLOrderIds(List<Long> orderIds);

	/**
	 * 按vendor获取当日所有的订单
	 * 如果vendor.equals("all")，则获取当日全部订单
	 * @param orderIds
	 * @return
	 */
	public List<RLOrder> getRLOrderListFromDateVendor(String beginDate, String endDate, String vendor);

	public void deleteRLOrdersAndReturnTrackingno(List orders);

	public List<RLOrder> getRLOrderListFromRLOrdernumbers(List rlOrdernumbers);

	
	public List<Object[]> getLogisticsAndCountFromDateVendor(String yyyyMMdd,
			String yyyyMMdd2, String vendor);

	public List<String> getVendorFromDate(String startDate, String endDate);

	public List<RLOrder> getRLOrderListFromDate(String beginDate, String endDate);
	
	public List<RLOrder> splitOrders(List<String> rlOrderIds);
	
	public List<RLOrder> mergeOrders(List<String> rlOrderIds);
	
	public List<RLOrder> copyOrders(List<String> rlOrderIds);
	
	
}
