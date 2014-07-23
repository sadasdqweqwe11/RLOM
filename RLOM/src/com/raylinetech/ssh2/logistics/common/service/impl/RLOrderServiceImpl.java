package com.raylinetech.ssh2.logistics.common.service.impl;

import java.util.LinkedList;
import java.util.List;

import com.raylinetech.ssh2.logistics.common.dao.RLOrderDao;
import com.raylinetech.ssh2.logistics.common.dao.TrackingNoDao;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.TrackingNo;
import com.raylinetech.ssh2.logistics.common.service.RLOrderService;

public class RLOrderServiceImpl implements RLOrderService{

	private RLOrderDao rlOrderDao;
	private TrackingNoDao trackingNoDao;
	
	
	public RLOrderDao getRlOrderDao() {
		return rlOrderDao;
	}

	public void setRlOrderDao(RLOrderDao rlOrderDao) {
		this.rlOrderDao = rlOrderDao;
	}
	
	public TrackingNoDao getTrackingNoDao() {
		return trackingNoDao;
	}

	public void setTrackingNoDao(TrackingNoDao trackingNoDao) {
		this.trackingNoDao = trackingNoDao;
	}

	@Override
	public void saveOrUpdateRLOrderList(List<RLOrder> rlOrders) {
		this.rlOrderDao.saveorUpdateAll(rlOrders);
	}

	@Override
	public List<RLOrder> getRLOrderListFromDateLogistics(String beginDate,String endDate,int logisticsid) {
		return this.rlOrderDao.findByDateLogistics(beginDate,endDate,logisticsid);
	}

	@Override
	public List<RLOrder> getRLOrderListFromFile(long id) {
		return this.rlOrderDao.findByFile(id);
	}

	@Override
	public List<RLOrder> getRLOrderListFromRLOrderIds(List<Long> orderIds) {
		return this.rlOrderDao.findByIds(orderIds);
	}

	/**
	 * 17时28分14秒
	 * 由于出现过多个订单运单号重复，修改代码，删除的运单号直接标记为2，即已删除状态
	 */
	@Override
	public void deleteRLOrdersAndReturnTrackingno(List orders) {
		List<RLOrder> rlOrders = this.rlOrderDao.findByIds(orders);
		List<Long> oids = new LinkedList<Long>();
		for (RLOrder order : rlOrders) {
			oids.add(order.getId());
		}
		List<TrackingNo> nos= this.trackingNoDao.getTrackingNoByRLOrderIds(oids);
		if(null!=nos){
			if(nos.size() != 0);
			for (TrackingNo trackingNo : nos) {
				trackingNo.setRlorderid(0);
				trackingNo.setIsused(2);
			}
			this.trackingNoDao.updateAll(nos);
		}
		this.rlOrderDao.deleteAll(rlOrders);
		
	}

	@Override
	public List<RLOrder> getRLOrderListFromDateVendor(String beginDate,
			String endDate, String vendor) {
		return this.rlOrderDao.findByDateVendor(beginDate,endDate,vendor);
	}

	@Override
	public List<RLOrder> getRLOrderListFromRLOrdernumbers(List rlOrdernumbers) {
		return this.rlOrderDao.findByRLOrdernumbers(rlOrdernumbers);
	}

	@Override
	public List<Object[]> getLogisticsAndCountFromDateVendor(String yyyyMMdd,
			String yyyyMMdd2, String vendor) {
		if(null != vendor &&"ALL".equalsIgnoreCase(vendor)){
			return this.rlOrderDao.findLogisticsAndCountByDate(yyyyMMdd,yyyyMMdd2);
		}
		return this.rlOrderDao.getLogisticsAndCountFromDateVendor(yyyyMMdd,yyyyMMdd2,vendor);
	}

	@Override
	public List<String> getVendorFromDate(String startDate, String endDate) {
		return this.rlOrderDao.findVendorFromDate(startDate,endDate);
	}

	@Override
	public List<RLOrder> getRLOrderListFromDate(String beginDate, String endDate) {
		return this.rlOrderDao.findByDate(beginDate,endDate);
	}

	@Override
	public List<RLOrder> splitOrders(List<String> rlOrderIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RLOrder> mergeOrders(List<String> rlOrderIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RLOrder> copyOrders(List<String> rlOrderIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RLOrder> getRLOrdersZFJDFromRLOrderIds(List ids) {
		return this.rlOrderDao.findByIdsOrderByLidSkunameQuaRLOid(ids);
	}

	@Override
	public List<RLOrder> getRLOrdersBQFromIds(List ids) {
		return this.rlOrderDao.findByIdsOrderByLidSkunameQuaRLOid(ids);
	}
	
	

}
