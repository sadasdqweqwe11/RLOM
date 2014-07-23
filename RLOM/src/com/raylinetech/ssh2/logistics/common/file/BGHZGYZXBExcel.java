package com.raylinetech.ssh2.logistics.common.file;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;
import com.raylinetech.ssh2.logistics.common.util.StringUtil;

public class BGHZGYZXBExcel extends ExcelModel {

	public static int COLUMN_NUMBER = 23;
	public static final int EQUICK = 1;
	public static final String DEFAULT_CUSTOMERNO = "PEK130011";
	public static final String DEFAULT_STATENAME = "";
	public static final String DEFAULT_EMAIL = "";
	public static final String DEFAULT_SHIPPINGMETHORD = "小包";

	public BGHZGYZXBExcel(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			this.generateHeader();
			this.generateExcel(rlOrders);
		}
	}

	private void generateExcel(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			int rowCount = rlOrders.size();
			this.data = new Object[BGHZGYZXBExcel.COLUMN_NUMBER][rowCount];
			int rowNumber = 0;
			for (RLOrder p : rlOrders) {
				this.data[0][rowNumber] = "";
				this.data[1][rowNumber] = "不挂号中国邮政小包（通用）";			
				this.data[2][rowNumber] = p.getRlordernumber();
				this.data[3][rowNumber] = p.getBuyername();
				this.data[4][rowNumber] = p.getGuojia();
				this.data[5][rowNumber] = this.getShipAdress(p.getShipaddress1(),p.getShipaddress2(),p.getShipcity());
				this.data[6][rowNumber] = p.getShipstate();
				this.data[7][rowNumber] = "";
				this.data[8][rowNumber] = p.getPostalcode();
				this.data[9][rowNumber] = "";
				this.data[10][rowNumber] = p.getBuyerphonenumber();
				this.data[11][rowNumber] = "";
				this.data[12][rowNumber] = "";
				this.data[13][rowNumber] = p.getRlorderitems().get(0).getSku().getPinming();
				this.data[14][rowNumber] = p.getRlorderitems().get(0).getSku().getName();
				
				int quantity = 0;
				double price = 0.0;
				double totalWeight = 0.0;
				price= p.getAmount() * 0.3;
				List<RLOrderItem> products = p.getRlorderitems();
				for (RLOrderItem l : products) {
					// 申报价值
					quantity = quantity+l.getQuantity();
					// 实际重量
					totalWeight = (double)l.getQuantity() * 0.25 + totalWeight;
				}
				DecimalFormat df = new DecimalFormat("#.##");
				
				
				this.data[15][rowNumber] = quantity;
				this.data[16][rowNumber] = "";
				this.data[17][rowNumber] = "1";
				this.data[18][rowNumber] = df.format(price);
				this.data[19][rowNumber] = "";
				this.data[20][rowNumber] = "";
				this.data[21][rowNumber] = "";
				this.data[22][rowNumber] = "";
				rowNumber = rowNumber + 1;
			}
		}
	}

	private void generateHeader() {
		this.header = new LinkedList<String>();
		this.header.add("快递单号");
		this.header.add("发货方式");
		this.header.add("订单号");
		this.header.add("收件人姓名");
		this.header.add("收件人国名（中文）");
		this.header.add("收件人地址");
		this.header.add("收件人城市");
		this.header.add("州编号");
		this.header.add("收件人邮编");
		this.header.add("收件人电话");
		this.header.add("收件人手机");
		this.header.add("收件人EMAIL");
		this.header.add("收件人公司");
		this.header.add("中文品名");
		this.header.add("英文品名");
		this.header.add("件数");
		this.header.add("重量(g)");
		this.header.add("币种");
		this.header.add("申报价值");
		this.header.add("收件人地址2");
		this.header.add("备注");
		this.header.add("多品名");
		this.header.add("商品海关编码");
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
	

}
