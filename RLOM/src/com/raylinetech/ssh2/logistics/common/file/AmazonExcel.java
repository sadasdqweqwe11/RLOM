package com.raylinetech.ssh2.logistics.common.file;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;
import com.raylinetech.ssh2.logistics.common.service.PdfService;
import com.raylinetech.ssh2.logistics.common.service.impl.PdfServiceImpl;
import com.raylinetech.ssh2.logistics.common.util.StringUtil;

public class AmazonExcel extends ExcelModel{
	
	

	public AmazonExcel(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			this.generateHeader();
			this.generateAmazon(rlOrders);
		}
	}
	
	private void generateHeader() {
		this.header = new LinkedList<String>();
		this.header.add("order-id");
		this.header.add("order-item-id");
		this.header.add("quantity");
		this.header.add("ship-date");
		this.header.add("carrier-code");
		this.header.add("carrier-name");
		this.header.add("tracking-number");
		this.header.add("ship-method");
	}
	
	private void generateAmazon(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			int rowCount = 0;
			for (RLOrder o : rlOrders) {
				for (RLOrderItem item : o.getRlorderitems()) {
					rowCount++;
				}
			}
			
			this.data = new Object[header.size()][rowCount];
			int rowNumber = 0;
			for (RLOrder o : rlOrders) {
				String carriercode = "china post";
				String carriername = "china post";
				String shipmethod = "挂号";
				int logid = o.getLogistics().getId();
				if(logid==PdfService.LOGISTIC_BJEQUICK ||logid==PdfService.LOGISTIC_SHEQUICK ||logid==PdfService.LOGISTIC_SZEQUICK){
					carriercode = "Equick";
					carriername = "Equick";
				}
				if(logid==PdfService.LOGISTIC_BJHLXB ||logid==PdfService.LOGISTIC_YWHLXB ||logid==PdfService.LOGISTIC_SZHLXB ||logid==PdfService.LOGISTIC_SHHLXB ){
					carriercode = "NLPG";
					carriername = "NLPG";
				}
				if(logid==PdfService.LOGISTIC_BJXBBGH||logid==PdfService.LOGISTIC_YWSHXBBGH||logid==PdfService.LOGISTIC_SHXBBGH||logid==PdfService.LOGISTIC_SZBJXBBGH){
					shipmethod = "不挂号";
				}
				for (RLOrderItem item : o.getRlorderitems()) {
					
				this.data[0][rowNumber] = item.getOrderno();
				this.data[1][rowNumber] = item.getItemno();
				this.data[2][rowNumber] = item.getQuantity();
				this.data[3][rowNumber] = o.getDate();
				
				
				
				this.data[4][rowNumber] = carriercode;
				this.data[5][rowNumber] = carriername;
				this.data[6][rowNumber] = o.getTrackingno();
				this.data[7][rowNumber] = shipmethod;
				rowNumber++;
				}
			}
		}
	}

	public String getShipAdress(String... args) {
		String address = "";
		for (int i = 0; i < args.length; i++) {
			if(i==0){
				if (null != args[i] && !"".equals(args[i])) {
					address = address + args[i];
					}
			}else{
				if (null != args[i] && !"".equals(args[i])) {
					address = address +", " + args[i];
					}
			}
		}
		//如果地址变态的超过了255个字符，则截取254位
		if(address.length()>255){
			address = address.substring(0, 254);
		}
		return address;
	}
	
	public static void main(String[] args) {
		AmazonExcel u = new AmazonExcel(null);
		System.out.println(u.getShipAdress("123","456","789"));
		System.out.println((int)Double.parseDouble("3.99"));
	}
}
