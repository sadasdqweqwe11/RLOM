package com.raylinetech.ssh2.logistics.common.service;

import java.io.OutputStream;
import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.PrintLable;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;

public interface PdfService {

	public static final int LOGISTICS_BJXB = 1;
	public static final int LOGISTICS_BJXBBGH = 2;
	public static final int LOGISTICS_SHXB = 3;
	public static final int LOGISTICS_SHXBBGH = 4;
	public static final int LOGISTICS_YWSHXB = 5;
	public static final int LOGISTICS_YWSHXBBGH = 6;
	public static final int LOGISTICS_SZBJXB = 7;
	public static final int LOGISTICS_SZBJXBBGH = 8;
	public static final int LOGISTICS_SZSBT = 9;
	public static final int LOGISTICS_BJEQUICK = 10;
	public static final int LOGISTICS_SZEQUICK = 11;
	public static final int LOGISTICS_SHEQUICK = 12;
	public static final int LOGISTICS_SHEUB = 13;
	public static final int LOGISTICS_BJEUB = 14;
	public static final int LOGISTICS_SZHLXB =15;
	public static final int LOGISTICS_SHHLXB =16;
	public static final int LOGISTICS_BJHLXB =17;
	public static final int LOGISTICS_YWHLXB =18;
	public static final int LOGISTICS_SZYYB =19;
	public static final int LOGISTICS_SHYYB =20;
	public static final int LOGISTICS_BJYYB =21;
	public static final int LOGISTICS_YWYYB =22;
	public static final int LOGISTICS_SZYODEL_SMALL =23;
	public static final int LOGISTICS_SHYODEL_SMALL =24;
	public static final int LOGISTICS_BJYODEL_SMALL =25;
	public static final int LOGISTICS_YWYODEL_SMALL =26;
	public static final int LOGISTICS_SZYODEL_ELE =27;
	public static final int LOGISTICS_SHYODEL_ELE =28;
	public static final int LOGISTICS_BJYODEL_ELE =29;
	public static final int LOGISTICS_YWYODEL_ELE =30;
	public static final int LOGISTICS_SZYODEL =31;
	public static final int LOGISTICS_SHYODEL =32;
	public static final int LOGISTICS_BJYODEL =33;
	public static final int LOGISTICS_YWYODEL =34;
	public static final int LOGISTICS_YWSHEUB = 35;
	public static final int LOGISTICS_SZYYBBGH = 36;
	public static final int LOGISTICS_SHYYBBGH = 37;
	public static final int LOGISTICS_BJYYBBGH = 38;
	public static final int LOGISTICS_YWYYBBGH = 39;
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
