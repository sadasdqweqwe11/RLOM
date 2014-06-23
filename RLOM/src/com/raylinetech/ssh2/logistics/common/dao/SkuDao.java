package com.raylinetech.ssh2.logistics.common.dao;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.Logistics;
import com.raylinetech.ssh2.logistics.common.entity.Sku;

public interface SkuDao {
	
	/**
	 * 获取sku
	 * @return
	 */
	public List<Sku> findBySkuno(List<String> skuno);

	public Sku find(String skuno);
}
