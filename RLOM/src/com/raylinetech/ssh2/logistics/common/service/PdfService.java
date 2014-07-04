package com.raylinetech.ssh2.logistics.common.service;

import java.io.OutputStream;
import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.PrintLable;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;

public interface PdfService {

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
	public static int LOGISTIC_SZYODEL_SMALL =23;
	public static int LOGISTIC_SHYODEL_SMALL =24;
	public static int LOGISTIC_BJYODEL_SMALL =25;
	public static int LOGISTIC_YWYODEL_SMALL =26;
	public static int LOGISTIC_SZYODEL_ELE =27;
	public static int LOGISTIC_SHYODEL_ELE =28;
	public static int LOGISTIC_BJYODEL_ELE =29;
	public static int LOGISTIC_YWYODEL_ELE =30;
	public static int LOGISTIC_SZYODEL =31;
	public static int LOGISTIC_SHYODEL =32;
	public static int LOGISTIC_BJYODEL =33;
	public static int LOGISTIC_YWYODEL =34;
	
	/**
	 * 用rlorder生成输出流
	 * @param rlOrders
	 * @param out
	 * @param fileName
	 * @return
	 */
	boolean rlOrdersToPdf(List<RLOrder> rlOrders, OutputStream out);


	boolean rlOrdersToPdf(List<RLOrder> rlOrders, String filePath);
}
