package com.raylinetech.ssh2.logistics.common.file;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import jxl.write.Label;

import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;
import com.raylinetech.ssh2.logistics.common.util.StringUtil;

public class FenJianDanExcel extends ExcelModel {

	private static final String WANGLUOQUDAO = "国际E邮宝E10";

	public FenJianDanExcel(List<RLOrder> rlOrders) {
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
	}

	private void generateFJD(List<RLOrder> rlOrders) {
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
			this.data = new Object[4][rowCount];
			int rowNumber = 0;
			for (RLOrder o : rlOrders) {
				this.data[0][rowNumber] = o.getRlordernumber();
				this.data[1][rowNumber] = o.getVendor();
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
