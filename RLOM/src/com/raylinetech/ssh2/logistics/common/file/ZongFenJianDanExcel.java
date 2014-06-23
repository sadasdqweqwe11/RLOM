package com.raylinetech.ssh2.logistics.common.file;

import java.util.LinkedList;
import java.util.List;


import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;

public class ZongFenJianDanExcel extends ExcelModel {


	public ZongFenJianDanExcel(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			this.generateHeader();
			this.generateFJD(rlOrders);
		}
	}

	private void generateHeader() {
		this.header = new LinkedList<String>();
		this.header.add("订单号");
		this.header.add("客户号");
		this.header.add("库存号");
		this.header.add("数量");
		this.header.add("物流");
		this.header.add("地址");
		this.header.add("收件人");
		this.header.add("邮编");
		this.header.add("企业名称");
		this.header.add("联系电话1");
		this.header.add("联系电话2");
		this.header.add("备注");
		
	}

	private void generateFJD(List<RLOrder> rlOrders) {
		System.out.println(rlOrders.size() + "fssfsfsiisisisisisi");
		if (null != rlOrders && rlOrders.size() != 0) {
			int rowCount = 0;
			for (RLOrder rlOrder : rlOrders) {
				int i = rlOrder.getRlorderitems().size();
				if(i>1){
					rowCount = rowCount+i+1;
				}else{
					rowCount = rowCount +i;
				}
				System.out.println(rowCount);
			}
			System.out.println(rowCount);
			this.data = new Object[12][rowCount];
			int rowNumber = 0;
			for (RLOrder o : rlOrders) {
				this.data[0][rowNumber] = o.getRlordernumber();
				this.data[1][rowNumber] = o.getVendor();
				if(null!=o.getLogistics()){
					this.data[4][rowNumber] = o.getLogistics().getName();
					this.data[5][rowNumber] = o.getLogistics().getAddress();
					this.data[6][rowNumber] = o.getLogistics().getMailto();
					this.data[7][rowNumber] = o.getLogistics().getPostalcode();
					this.data[8][rowNumber] = o.getLogistics().getLogifunc();
					this.data[9][rowNumber] = o.getLogistics().getPhonenumber1();
					this.data[10][rowNumber] = o.getLogistics().getPhonenumber2();
					this.data[11][rowNumber] = o.getLogistics().getRemarks();
				}
				if (o.getRlorderitems().size() == 1) {
					// SKU
					this.data[2][rowNumber] = o.getRlorderitems().get(0)
							.getSku().getSkuno();
					// 数量
					this.data[3][rowNumber] = o.getRlorderitems().get(0)
							.getQuantity();
				} else {
					int orderLine = 1;
					for (RLOrderItem item : o.getRlorderitems()) {
						// SKU
						this.data[2][rowNumber + orderLine] = item.getSku().getSkuno();
						// 数量
						this.data[3][rowNumber + orderLine] = item
								.getQuantity();
						// 实际重量
						orderLine = orderLine + 1;
					}
					rowNumber = rowNumber + orderLine-1;
				}
				rowNumber++;
			}
		}
	}
}
