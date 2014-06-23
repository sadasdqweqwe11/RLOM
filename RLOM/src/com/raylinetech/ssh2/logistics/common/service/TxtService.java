package com.raylinetech.ssh2.logistics.common.service;

import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.OrderFile;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.exception.ExcelException;

public interface TxtService {
	/**
	 * 校验文件
	 * @param path
	 * @param fileName
	 * @return
	 */
	public List<List> validateTxt(List<List>  lists) ;

	/**
	 * txt转化为list对象
	 * @param path
	 * @param fileName
	 * @return
	 */
	public List<List> txtToList(String path, OrderFile orderFile) throws ExcelException;
	
	/**
	 * 将txt转化为实体
	 * @param path
	 * @param fileName
	 * @return
	 */
	public List<RLOrder> txtToRLORder(String path, OrderFile orderFile) throws ExcelException;

	
}
