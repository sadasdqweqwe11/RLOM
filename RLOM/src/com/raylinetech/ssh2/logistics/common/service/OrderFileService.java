package com.raylinetech.ssh2.logistics.common.service;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.OrderFile;


public interface OrderFileService {
	public OrderFile find(long id);

	public void save(OrderFile orderFile);

	public void modify(OrderFile orderFile);
	
	public List<OrderFile> getOrderFilesByUserId(long userId);

	public OrderFile getOrderFile(long id);

	public void cancelOrderFile(long id);
}
