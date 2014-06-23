package com.raylinetech.ssh2.logistics.common.service;

import java.io.OutputStream;
import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.OrderFile;
import com.raylinetech.ssh2.logistics.common.entity.PrintLable;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.exception.ExcelException;

public interface ExcelService_v1 {
	
	public static final String DEFAULT_CUSTOMERNO = "PEK130011";
	public static final String DEFAULT_STATENAME = "";
	public static final String DEFAULT_EMAIL = "";
	public static final String DEFAULT_SHIPPINGMETHORD = "小包";
	
	public static int LOGISTIC_GHZGYZXB = 1;
	public static int LOGISTIC_EQUICKYGKSXB = 2;
	public static int LOGISTIC_BJEUB = 3;
	public static int LOGISTIC_SHEUB = 4;
	public static int LOGISTIC_BGHZGYZXB =5;
	public static int LOGISTIC_RSXB =6;
	public static int LOGISTIC_YWROYALMAIL =7;
	public static int LOGISTIC_YYBELS =8;
	public static int LOGISTIC_GHSZXB = 9;
	public static int LOGISTIC_BGHSZXB = 10;
	/**
	 * 将表格转化为实体
	 * @param path
	 * @param fileName
	 * @return
	 */
	public List<RLOrder> excelToRLORder(String path, OrderFile orderFile) throws ExcelException;

	/**
	 * 校验表格
	 * @param path
	 * @param fileName
	 * @return
	 */
	public List<List> validateExcel(List<List>  lists) ;

	/**
	 * 表格转化为list对象
	 * @param path
	 * @param fileName
	 * @return
	 */
	public List<List> excelToList(String path, OrderFile orderFile) throws ExcelException;

	
	/**
	 * 将实体转化为规定物流商的物流方式
	 * @param printLable
	 * @param logistics
	 * @return
	 */
	public boolean rlOrderToExcel(List<RLOrder> rlOrders, int logistics, String path, String outFileName);
	
	/**
	 * 
	 * @param rlOrders
	 * @param path
	 * @param outFileName
	 * @return
	 */
	public boolean orderToYanwenExcel(List<RLOrder> rlOrders, String path, String outFileName);
	
	/**
	 * 
	 * @param rlOrders
	 * @param path
	 * @param outFileName
	 * @return
	 */
	public boolean orderToFenjiandan(List<RLOrder> rlOrders,
			String path, String outFileName);

	public boolean rlOrderToExcel(List<RLOrder> rlOrders, int logistics,
			OutputStream out);

	/**
	 * 校验trackingnoExcel
	 * @param stringList
	 * @return
	 */
	public List<List> validateTrackingnoExcel(List<List> stringList);
}
