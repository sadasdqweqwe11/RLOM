package com.raylinetech.ssh2.logistics.common.service;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.TrackingNo;

public interface TrackingNoService {

	public List<TrackingNo> getTrackingNos(List<Long> rlOrderid,int logisticid);
	
	public void generateTrackingNoTORLOrder(List<RLOrder> rlOrders);
	
	public void returnTrackingNos(List<TrackingNo> trackingNos);
}
