package com.raylinetech.ssh2.logistics.common.dao;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.OrderFile;


public interface OrderFileDao {

	public void save(OrderFile orderFile);

	public void update(OrderFile orderFile);
	
	public void delete(OrderFile orderFile);

	public List<OrderFile> getOrderFilesByUserId(long userId);

	public OrderFile find(long id);

}
