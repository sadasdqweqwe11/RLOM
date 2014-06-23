package com.raylinetech.ssh2.logistics.common.file;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;
import com.raylinetech.ssh2.logistics.common.util.StringUtil;

public class EUBExcel extends ExcelModel{
	
	
	private static final String WANGLUOQUDAO = "国际E邮宝E10";

	public EUBExcel(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			this.generateHeader();
			this.generateEUB(rlOrders);
		}
	}
	
	private void generateHeader() {
		this.header = new LinkedList<String>();
		this.header.add("运单号");
		this.header.add("目的地");
		this.header.add("网络渠道");
		this.header.add("收件人");
		this.header.add("收件地址");
		this.header.add("收件电话");
		this.header.add("收件邮编");
		this.header.add("声明价值");
		this.header.add("物品描述");
		this.header.add("物品数量");
		this.header.add("备注");
	}
	
	private void generateEUB(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			int rowCount = rlOrders.size();
			this.data = new Object[11][rowCount];
			int rowNumber = 0;
			for (RLOrder o : rlOrders) {
				this.data[0][rowNumber] = o.getTrackingno();
				this.data[1][rowNumber] = o.getGuojia();
				this.data[2][rowNumber] = EUBExcel.WANGLUOQUDAO;
				this.data[3][rowNumber] = o.getBuyername();
				this.data[4][rowNumber] = this.getShipAdress(o.getShipaddress1(),o.getShipaddress2(),o.getShipcity(),o.getShipstate(),o.getShipcountry());
				this.data[5][rowNumber] = o.getBuyerphonenumber();
				this.data[6][rowNumber] = o.getPostalcode();
				DecimalFormat df = new DecimalFormat("#.##");
				String price = df.format(Double.parseDouble(StringUtil
						.getDoubleFromAmount(o.getAmount())) * 0.3);
				this.data[7][rowNumber] = (int)Double.parseDouble(price);
				if(o.getRlorderitems().get(0)!= null){
					this.data[8][rowNumber] = o.getRlorderitems().get(0).getSku().getName();
					int totalQuantity = 0;
					for (int i = 0; i < o.getRlorderitems().size(); i++) {
						totalQuantity = totalQuantity + Integer.parseInt(o.getRlorderitems().get(i).getQuantity());
					}
					this.data[9][rowNumber] = totalQuantity+"";
				}else{
					this.data[8][rowNumber] = "";
					this.data[9][rowNumber] = "";
				}
				this.data[10][rowNumber] = "";
				rowNumber++;
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
		EUBExcel u = new EUBExcel(null);
		System.out.println(u.getShipAdress("123","456","789"));
		System.out.println((int)Double.parseDouble("3.99"));
	}
}
