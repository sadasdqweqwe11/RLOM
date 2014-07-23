package com.raylinetech.ssh2.logistics.common.dao;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.RLOrder;


public interface RLOrderDao {

	public void save(RLOrder rlOrder);

	public void saveorUpdateAll(List<RLOrder> rlOrders);

	public List<RLOrder> findByDate(String date);
	
	public List<RLOrder> findByDate(String startDate, String endDate);
	
	public void update(RLOrder rlOrder);
	
	public void delete(RLOrder rlOrder);

	public List<RLOrder> findByFile(long id);

	public void deleteAll(List<RLOrder> orders);

	public List<RLOrder> findByDateLogistics(String beginDate, String endDate,
			int logisticsid);

	public List<RLOrder> findByIds(List<Long> orderIds);

	public List<RLOrder> findByDateVendor(String beginDate, String endDate,
			String vendor);

	public List<RLOrder> findByRLOrdernumbers(List rlOrdernumbers);

	public List<Object[]> getLogisticsAndCountFromDateVendor(String yyyyMMdd,
			String yyyyMMdd2, String vendor);

	public List<String> findVendorFromDate(String startDate, String endDate);
	

	public List<RLOrder> findByDateLogisticsAccount(String beginDate,
			String endDate, String code);

	public List<RLOrder> findByDateLogisticsAddress(String beginDate,
			String endDate, String code);

	public List<Object[]> findLogisticsAndCountByDate(String yyyyMMdd,
			String yyyyMMdd2);

	public List<RLOrder> findByDateAccount(String beginDate, String endDate,
			String code);

	public List getAccountsByDate(String beginDate, String endDate);
	public List getVendorVendorByDate(String startDate, String endDate);


	public List<RLOrder> findByIdsOrderByLidSkunameQuaRLOid(List ids);
}
