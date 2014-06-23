package com.raylinetech.ssh2.logistics.common.service;

import java.util.List;
import java.util.Map;

import com.raylinetech.ssh2.logistics.common.entity.Logistics;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;

public interface LogisticsService {

	public static final String BZ = "--";

	/**
	 * 获取所有的物流方式备用
	 * @return
	 */
	public List<Logistics> getAllLogistics();

	List<RLOrder> allocationRLOrders(List<RLOrder> rlOrders);
	
	
}
