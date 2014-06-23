package com.raylinetech.ssh2.logistics.common.dao;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.TrackingNo;


public interface TrackingNoDao {

	public void save(TrackingNo trackingNo);

	public void update(TrackingNo trackingNo);
	
	public void delete(TrackingNo trackingNo);
	
	public List<TrackingNo> getTrackingNo(int count,int logisticid);
	
	public void updateAll(List<TrackingNo> trackingNos);

	/**
	 * 根据导入的oids提取出TrackingNo
	 * @param oids
	 * @return
	 */
	public List<TrackingNo> getTrackingNoByRLOrderIds(List<Long> oids);
}
