package com.raylinetech.ssh2.logistics.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.raylinetech.ssh2.logistics.common.dao.LogisticsDao;
import com.raylinetech.ssh2.logistics.common.dao.RLOrderDao;
import com.raylinetech.ssh2.logistics.common.dao.TrackingNoDao;
import com.raylinetech.ssh2.logistics.common.dao.impl.RLOrderDaoImpl;
import com.raylinetech.ssh2.logistics.common.entity.Logistics;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.TrackingNo;
import com.raylinetech.ssh2.logistics.common.service.TrackingNoService;

public class TrackingNoServiceImpl implements TrackingNoService{

	private TrackingNoDao trackingNoDao;
	
	private LogisticsDao logisticsDao;
	
	private RLOrderDao rlOrderDao; 
	
	public LogisticsDao getLogisticsDao() {
		return logisticsDao;
	}

	public void setLogisticsDao(LogisticsDao logisticsDao) {
		this.logisticsDao = logisticsDao;
	}

	public TrackingNoDao getTrackingNoDao() {
		return trackingNoDao;
	}

	public void setTrackingNoDao(TrackingNoDao trackingNoDao) {
		this.trackingNoDao = trackingNoDao;
	}

	
	public RLOrderDao getRlOrderDao() {
		return rlOrderDao;
	}

	public void setRlOrderDao(RLOrderDao rlOrderDao) {
		this.rlOrderDao = rlOrderDao;
	}

	@Override
	public List<TrackingNo> getTrackingNos(List<Long> rlOrderids,int logisticsid) {
		Logistics logi = this.logisticsDao.find(logisticsid);
		List<TrackingNo> nos = this.trackingNoDao.getTrackingNo(rlOrderids.size(),logi.getTrackingstore());
		for (int i = 0; i < nos.size(); i++) {
			nos.get(i).setIsused(1);
			nos.get(i).setRlorderid(rlOrderids.get(i));
		}
		this.trackingNoDao.updateAll(nos);
		return nos;
	}

	@Override
	public void returnTrackingNos(List<TrackingNo> trackingNos) {
		this.trackingNoDao.updateAll(trackingNos);
	}

	@Override
	public void generateTrackingNoTORLOrder(List<RLOrder> rlOrders) {
		//判断orders里边是否有trackingno,如果有，则不做任何处理。
		//如果没有的，装进map里。
		//将map中的每一个list获取trackingno
		Map<Integer,List<RLOrder>> map  = new TreeMap<Integer, List<RLOrder>>();
		for (RLOrder rlOrder : rlOrders) {
			if(null == rlOrder.getTrackingno() ||rlOrder.getTrackingno().trim().equals("")){
				if(null == rlOrder.getLogistics() ||rlOrder.getLogistics().getId()==0){
					continue;
				}
				if(map.get(rlOrder.getLogistics().getId())==null){
					List<RLOrder> orders = new ArrayList<RLOrder>();
					orders.add(rlOrder);
					map.put(rlOrder.getLogistics().getId(), orders);
				}else{
					map.get(rlOrder.getLogistics().getId()).add(rlOrder);
				}
			}
		}
		
		for (Entry<Integer, List<RLOrder>> entry: map.entrySet()) {
			int key = entry.getKey();
			List<RLOrder> orders = entry.getValue();
			Map<Long, RLOrder> orderMap  = new TreeMap<Long, RLOrder>();
			for (RLOrder order : orders) {
				orderMap.put(order.getId(), order);
			}
			List<TrackingNo> nos = this.getTrackingNos(new ArrayList(orderMap.keySet()), key);
			if(null == nos || nos.size()==0){
				continue;
			}
			for (TrackingNo tr : nos) {
				orderMap.get(tr.getRlorderid()).setTrackingno(tr.getTrackingno());
			}
		}
		this.rlOrderDao.saveorUpdateAll(rlOrders);
	}

}
