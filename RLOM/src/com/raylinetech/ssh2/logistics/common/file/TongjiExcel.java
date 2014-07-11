package com.raylinetech.ssh2.logistics.common.file;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;


import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;

public class TongjiExcel extends ExcelModel {

	private  int headerSize = 12; 

	public TongjiExcel(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			this.generateHeader();
			this.generateFJD(rlOrders);
		}
	}

	private void generateHeader() {
		this.header = new LinkedList<String>();
		this.header.add("序号");
		this.header.add("订单号");
		this.header.add("原始订单号");
		this.header.add("供应商");
		this.header.add("物料号");		
		this.header.add("数量");
		this.header.add("追踪号");
		this.header.add("日期");
		this.header.add("帐号");
		this.header.add("国家");
		this.header.add("币种");
		this.header.add("价格");
		
	}

	private void generateFJD(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			int rowCount = 0;
			for (RLOrder rlOrder : rlOrders) {
				int i = rlOrder.getRlorderitems().size();
				rowCount = rowCount+i;
			}
			this.data = new Object[this.headerSize][rowCount];
			int rowNumber = 0;
			int xuhao = 0;
			for (RLOrder o : rlOrders) {
				for (RLOrderItem item : o.getRlorderitems()) {
					this.data[0][rowNumber] = xuhao+1;
					this.data[1][rowNumber] = o.getRlordernumber();
					this.data[2][rowNumber] = o.getOrdernumber();
					this.data[3][rowNumber] = item.getSku().getVendor();
					this.data[4][rowNumber] = item.getSku().getSkuno();
					this.data[5][rowNumber] = item.getQuantity();
					this.data[6][rowNumber] = o.getTrackingno();
					this.data[7][rowNumber] = o.getDate();
					this.data[8][rowNumber] = o.getAccount();
					this.data[9][rowNumber] = o.getGuojia();
					this.data[10][rowNumber] = o.getCurrency();
					this.data[11][rowNumber] = o.getAmount();
					rowNumber++;
				}
				xuhao++;
			}
		}
	}
}
