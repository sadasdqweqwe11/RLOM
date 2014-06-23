package com.raylinetech.ssh2.logistics.common.service;

import java.io.OutputStream;
import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.OrderFile;
import com.raylinetech.ssh2.logistics.common.entity.PrintLable;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.exception.ExcelException;

public interface ExcelService {
	
	public static final String DEFAULT_CUSTOMERNO = "PEK130011";
	public static final String DEFAULT_STATENAME = "";
	public static final String DEFAULT_EMAIL = "";
	public static final String DEFAULT_SHIPPINGMETHORD = "小包";
	public static final String BZ = "--";
	public static int LOGISTIC_BJXB = 1;
	public static int LOGISTIC_BJXBBGH = 2;
	public static int LOGISTIC_SHXB = 3;
	public static int LOGISTIC_SHXBBGH = 4;
	public static int LOGISTIC_YWSHXB = 5;
	public static int LOGISTIC_YWSHXBBGH = 6;
	public static int LOGISTIC_SZBJXB = 7;
	public static int LOGISTIC_SZBJXBBGH = 8;
	public static int LOGISTIC_SZSBT = 9;
	public static int LOGISTIC_BJEQUICK = 10;
	public static int LOGISTIC_SZEQUICK = 11;
	public static int LOGISTIC_SHEQUICK = 12;
	public static int LOGISTIC_SHEUB = 13;
	public static int LOGISTIC_BJEUB = 14;
	public static int LOGISTIC_SZHLXB =15;
	public static int LOGISTIC_SHHLXB =16;
	public static int LOGISTIC_BJHLXB =17;
	public static int LOGISTIC_YWHLXB =18;
	public static int LOGISTIC_SZYYB =19;
	public static int LOGISTIC_SHYYB =20;
	public static int LOGISTIC_BJYYB =21;
	public static int LOGISTIC_YWYYB =22;
	
	
	
//	public static int LOGISTIC_GHZGYZXB = 1;
//	public static int LOGISTIC_EQUICKYGKSXB = 2;
//	public static int LOGISTIC_BJEUB = 3;
//	public static int LOGISTIC_SHEUB = 4;
//	public static int LOGISTIC_BGHZGYZXB =5;
//	public static int LOGISTIC_RSXB =6;
//	public static int LOGISTIC_YWROYALMAIL =7;
//	public static int LOGISTIC_YYBELS =8;
//	public static int LOGISTIC_GHSZXB = 9;
//	public static int LOGISTIC_BGHSZXB = 10;
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
	 * 校验trackingnoExcel
	 * @param stringList
	 * @return
	 */
	public List<List> validateTrackingnoExcel(List<List> stringList);
}
