package com.raylinetech.ssh2.logistics.common.service;

import java.io.OutputStream;
import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.PrintLable;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;

public interface PdfService_V2 {

	public static int LOGISTICS_GHZGYZXB = 1;
	public static int LOGISTICS_EQUICKYGKSXB = 2;
	public static int LOGISTICS_BJEUB = 3;
	public static int LOGISTICS_SHEUB = 4;
	public static int LOGISTICS_BGHZGYZXB =5;
	public static int LOGISTICS_GHSZXB = 6;
	public static int LOGISTICS_BGHSZXB = 6;
	
	
	public boolean printLablesToPdf(List<PrintLable> printLables,String path,String fileName);
	
	public boolean rlOrdersToPdf(List<RLOrder> rlOrders,String path,String fileName);
	/**
	 * 用rlorder生成输出流
	 * @param rlOrders
	 * @param logistics 运输方式
	 * @param out
	 * @param fileName
	 * @return
	 */
	public boolean rlOrdersToPdf(List<RLOrder> rlOrders,int logistics, OutputStream out);
}
