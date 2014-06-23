package com.raylinetech.ssh2.logistics.common.dao;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.Logistics;

public interface LogisticsDao {
	
	/**
	 * 获取全部
	 * @return
	 */
	public List<Logistics> getAll();

	public Logistics find(int logisticsid);

	/**
	 * 获取id和name组成的list
	 * @return
	 */
	public List getIdName();

	public List getIdAccount();

	public List getIdAddress();
}
