package com.raylinetech.ssh2.logistics.common.file;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;
import com.raylinetech.ssh2.logistics.common.service.ExcelService;
import com.raylinetech.ssh2.logistics.common.util.ExcelUtil;
import com.raylinetech.ssh2.logistics.common.util.StringUtil;

public class GHZGYZXBExcel extends ExcelModel {

	public static int COLUMN_NUMBER = 23;
	public static final int EQUICK = 1;
	public static final String DEFAULT_CUSTOMERNO = "PEK130011";
	public static final String DEFAULT_STATENAME = "";
	public static final String DEFAULT_EMAIL = "";
	public static final String DEFAULT_SHIPPINGMETHORD = "小包";

	public GHZGYZXBExcel(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			this.generateHeader();
			this.generateExcel(rlOrders);
		}
	}

	private void generateExcel(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			int rowCount = rlOrders.size();
			this.data = new Object[GHZGYZXBExcel.COLUMN_NUMBER][rowCount];
			System.out.println(rowCount);
			int rowNumber = 0;
			for (RLOrder p : rlOrders) {
				this.data[0][rowNumber] = p.getTrackingno();
				int logid = p.getLogistics().getId();
				String bizhong = "1";
				if(logid==ExcelService.LOGISTICS_SHXB || logid==ExcelService.LOGISTICS_YWSHXB){
					this.data[1][rowNumber] = "中邮上海挂号小包";		
				}else if(logid==ExcelService.LOGISTICS_BJXB || logid==ExcelService.LOGISTICS_SZBJXB){
					this.data[1][rowNumber] = "中邮北京挂号小包";			
				}else if(logid==ExcelService.LOGISTICS_BJXBBGH || logid==ExcelService.LOGISTICS_SZBJXBBGH||logid==ExcelService.LOGISTICS_SHXBBGH || logid==ExcelService.LOGISTICS_YWSHXBBGH){
					this.data[1][rowNumber] = "中邮平邮小包(通用)";			
				}else if(logid==ExcelService.LOGISTICS_SZHLXB || logid==ExcelService.LOGISTICS_BJHLXB||logid==ExcelService.LOGISTICS_SHHLXB || logid==ExcelService.LOGISTICS_YWHLXB){
					this.data[1][rowNumber] = "荷兰邮政挂号小包";			
				}else if(logid==ExcelService.LOGISTICS_BJYYB || logid==ExcelService.LOGISTICS_SZYYB||logid==ExcelService.LOGISTICS_SHYYB || logid==ExcelService.LOGISTICS_YWYYB){
					this.data[1][rowNumber] = "燕邮宝挂号";			
				}else if(logid==ExcelService.LOGISTICS_BJYYBBGH || logid==ExcelService.LOGISTICS_SZYYBBGH||logid==ExcelService.LOGISTICS_SHYYBBGH || logid==ExcelService.LOGISTICS_YWYYBBGH){
					this.data[1][rowNumber] = "燕邮宝平邮";			
				}else if(logid==ExcelService.LOGISTICS_BJYODEL || logid==ExcelService.LOGISTICS_SZYODEL||logid==ExcelService.LOGISTICS_SHYODEL || logid==ExcelService.LOGISTICS_YWYODEL){
					this.data[1][rowNumber] = "英国YODEL专线(标准)";	
					bizhong="3";
				}else if(logid==ExcelService.LOGISTICS_BJYODEL_SMALL || logid==ExcelService.LOGISTICS_SZYODEL_SMALL||logid==ExcelService.LOGISTICS_SHYODEL_SMALL || logid==ExcelService.LOGISTICS_YWYODEL_SMALL){
					this.data[1][rowNumber] = "英国YODEL专线(不含电小包)";			
					bizhong="3";
				}else if(logid==ExcelService.LOGISTICS_BJYODEL_ELE || logid==ExcelService.LOGISTICS_SZYODEL_ELE||logid==ExcelService.LOGISTICS_SHYODEL_ELE || logid==ExcelService.LOGISTICS_YWYODEL_ELE){
					this.data[1][rowNumber] = "英国YODEL专线(含电小包)";			
					bizhong="3";
				}else if(logid==ExcelService.LOGISTICS_YWSHEUB){
						this.data[1][rowNumber] = "中邮上海E邮宝(线下)";			
				}
				this.data[2][rowNumber] = p.getRlordernumber();
				this.data[3][rowNumber] = p.getBuyername();
				this.data[4][rowNumber] = p.getGuojia();
				this.data[5][rowNumber] = this.getShipAdress(p.getShipaddress1(),p.getShipaddress2(),p.getShipcity());
				
				//如果state为空的话，则将city写入state中
				String state = "";
				if(null==p.getShipstate()||"".equals(p.getShipstate().trim())){
					state = p.getShipcity();
				}else{
					state = p.getShipstate();
				}
				this.data[6][rowNumber] = state;
				this.data[7][rowNumber] = state;
				this.data[8][rowNumber] = p.getPostalcode();
				this.data[9][rowNumber] = p.getBuyerphonenumber();
				this.data[10][rowNumber] = p.getBuyerphonenumber();
				this.data[11][rowNumber] = "";
				this.data[12][rowNumber] = "";
				this.data[13][rowNumber] = p.getRlorderitems().get(0).getSku().getPinming();
				this.data[14][rowNumber] = p.getRlorderitems().get(0).getSku().getName();
				
				int quantity = 0;
				double price = 0.0;
				int totalWeight = 0;
				price= StringUtil.random(3.99, 14.99);
				List<RLOrderItem> products = p.getRlorderitems();
				for (RLOrderItem l : products) {
					// 申报价值
					quantity = quantity+l.getQuantity();
					// 实际重量
					totalWeight = l.getQuantity() * 100 + totalWeight;
				}
				DecimalFormat df = new DecimalFormat("#.##");
				
				
				this.data[15][rowNumber] = quantity;
				this.data[16][rowNumber] = totalWeight;
				this.data[17][rowNumber] = bizhong;
				this.data[18][rowNumber] = df.format(price);
				this.data[19][rowNumber] = "";
				this.data[20][rowNumber] = "";
				this.data[21][rowNumber] = p.getRlorderitems().get(0).getSku().getName();
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
