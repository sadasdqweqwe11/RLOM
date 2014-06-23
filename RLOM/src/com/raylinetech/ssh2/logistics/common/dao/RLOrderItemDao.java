package com.raylinetech.ssh2.logistics.common.dao;

import com.raylinetech.ssh2.logistics.common.entity.OrderFile;


public interface RLOrderItemDao {

	public void save(OrderFile orderFile);

	public void update(OrderFile orderFile);
	
	public void delete(OrderFile orderFile);
}
