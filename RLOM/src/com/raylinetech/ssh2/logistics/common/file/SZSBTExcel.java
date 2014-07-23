package com.raylinetech.ssh2.logistics.common.file;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;
import com.raylinetech.ssh2.logistics.common.util.StringUtil;

public class SZSBTExcel extends ExcelModel {
	public SZSBTExcel(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			this.generateHeader();
			this.generateSZXB(rlOrders);
		}
	}
	
	private void generateHeader() {
		this.header = new LinkedList<String>();
		this.header.add("邮件编号");
		this.header.add("投保易");
		this.header.add("收件人姓名");
		this.header.add("收件人地址");
		this.header.add("收件人国家");
		this.header.add("邮编");
		this.header.add("物品类别");
		this.header.add("物品类别内容");
		this.header.add("内载物品详情");
		this.header.add("数量");
		this.header.add("净重");
		this.header.add("货币");
		this.header.add("价值");
		this.header.add("总重量（千克）");
		this.header.add("货币（总值）");
		this.header.add("总值");
		this.header.add("tracking NO");
	}
	
	private void generateSZXB(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			int rowCount = rlOrders.size();
			this.data = new Object[this.header.size()][rowCount];
			int rowNumber = 0;
			for (RLOrder o : rlOrders) {
				this.data[0][rowNumber] = "HKRL" + o.getRlordernumber();
				this.data[1][rowNumber] = "N";
				this.data[2][rowNumber] = o.getBuyername();
				this.data[3][rowNumber] = this.getShipAdress(o.getShipaddress1(),o.getShipaddress2(),o.getShipcity(),o.getShipstate());
				if(o.getGuojia().equals("英国")){
					this.data[4][rowNumber] = "GB";
				}else if(o.getGuojia().equals("俄罗斯")){
					this.data[4][rowNumber] = "RU";			
				}else{
					this.data[4][rowNumber] = o.getGuojia();
				}
				this.data[5][rowNumber] = o.getPostalcode();
				this.data[6][rowNumber] = "W";
				if(null==o.getRlorderitems()|| o.getRlorderitems().size()==0){
					this.data[7][rowNumber] = "null";
					this.data[8][rowNumber] = "null";
					this.data[9][rowNumber] = "null";
				}else{
					this.data[7][rowNumber] = o.getRlorderitems().get(0).getSku().getSkuno();
					this.data[8][rowNumber] = o.getRlorderitems().get(0).getSku().getName();
					int quantity=0;
					for (RLOrderItem item : o.getRlorderitems()) {
						quantity = quantity + item.getQuantity();
					}
					this.data[9][rowNumber] = quantity;
				}
				this.data[10][rowNumber] = "";
				this.data[11][rowNumber] = o.getCurrency();
				DecimalFormat df = new DecimalFormat("#.##");
				String price = df.format(o.getAmount() * 0.3);
				this.data[12][rowNumber] = Double.parseDouble(price);
				this.data[13][rowNumber] = "0.1";
				this.data[14][rowNumber] = Double.parseDouble(price);
				this.data[15][rowNumber] = Double.parseDouble(price);
				this.data[16][rowNumber] = o.getTrackingno();
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
		SZSBTExcel u = new SZSBTExcel(null);
		System.out.println(u.getShipAdress("123","456","789"));
		System.out.println((int)Double.parseDouble("3.99"));
	}
}
