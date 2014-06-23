package com.raylinetech.ssh2.logistics.common.service.impl;

import java.util.LinkedList;
import java.util.List;

import com.raylinetech.ssh2.logistics.common.dao.OrderFileDao;
import com.raylinetech.ssh2.logistics.common.dao.RLOrderDao;
import com.raylinetech.ssh2.logistics.common.dao.TrackingNoDao;
import com.raylinetech.ssh2.logistics.common.entity.OrderFile;
import com.raylinetech.ssh2.logistics.common.entity.OutputFile;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.TrackingNo;
import com.raylinetech.ssh2.logistics.common.service.OrderFileService;
import com.raylinetech.ssh2.logistics.common.service.OutputFileService;

public class OrderFileServiceImpl implements OrderFileService{

	private OrderFileDao orderFileDao;
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

	public OrderFileDao getOrderFileDao() {
		return orderFileDao;
	}

	public void setOrderFileDao(OrderFileDao orderFileDao) {
		this.orderFileDao = orderFileDao;
	}

	@Override
	public OrderFile find(long id) {
		return this.orderFileDao.find(id);
	}

	@Override
	public void save(OrderFile orderFile) {
		this.orderFileDao.save(orderFile);
		
	}

	@Override
	public void modify(OrderFile orderFile) {
		this.orderFileDao.update(orderFile);
	}

	@Override
	public List<OrderFile> getOrderFilesByUserId(long userId) {
		return this.orderFileDao.getOrderFilesByUserId(userId);
	}

	@Override
	public OrderFile getOrderFile(long id) {
		return this.orderFileDao.find(id);
	}

	@Override
	public void cancelOrderFile(long id) {
		OrderFile file = this.orderFileDao.find(id);
//		List<RLOrder> orders = this.rlOrderDao.findByFile(id);
//		List<Long> oids = new LinkedList<Long>();
//		for (RLOrder order : orders) {
//			oids.add(order.getId());
//		}
//		List<TrackingNo> nos= this.trackingNoDao.getTrackingNoByRLOrderIds(oids);
//		if(null!=nos){
//			if(nos.size() != 0);
//			for (TrackingNo trackingNo : nos) {
//				trackingNo.setRlorderid(0);
//				trackingNo.setIsused(0);
//			}
//			this.trackingNoDao.updateAll(nos);
//		}
//		this.rlOrderDao.deleteAll(orders);
		if(null != file){
			this.orderFileDao.delete(file);
		}
	}



}
