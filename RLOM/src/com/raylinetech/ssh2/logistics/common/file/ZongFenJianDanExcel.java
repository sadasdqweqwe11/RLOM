package com.raylinetech.ssh2.logistics.common.file;

import java.util.LinkedList;
import java.util.List;

import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;

public class ZongFenJianDanExcel extends ExcelModel {

	private  int headerSize = 13; 
	
	public ZongFenJianDanExcel(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			this.generateHeader();
			this.generateFJD(rlOrders);
		}
	}

	private void generateHeader() {
		this.header = new LinkedList<String>();
		this.header.add("序号");
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
		if (null != rlOrders && rlOrders.size() != 0) {
			int rowCount = 0;
			for (RLOrder rlOrder : rlOrders) {
				int i = rlOrder.getRlorderitems().size();
				rowCount = rowCount + i;
			}
			this.data = new Object[this.headerSize][rowCount];
			int rowNumber = 0;
			int xuhao = 0;
			for (RLOrder o : rlOrders) {
				for (RLOrderItem item : o.getRlorderitems()) {
					this.data[0][rowNumber] = xuhao+1;
					this.data[1][rowNumber] = o.getRlordernumber();
					this.data[2][rowNumber] = o.getVendor();
					if (null != o.getLogistics()) {
						this.data[3][rowNumber] = item.getSku().getSkucode();
						this.data[4][rowNumber] = item.getQuantity();
						this.data[5][rowNumber] = o.getLogistics().getName();
						this.data[6][rowNumber] = o.getLogistics().getAddress();
						this.data[7][rowNumber] = o.getLogistics().getMailto();
						this.data[8][rowNumber] = o.getLogistics()
								.getPostalcode();
						this.data[9][rowNumber] = o.getLogistics()
								.getLogifunc();
						this.data[10][rowNumber] = o.getLogistics()
								.getPhonenumber1();
						this.data[11][rowNumber] = o.getLogistics()
								.getPhonenumber2();
						this.data[12][rowNumber] = o.getLogistics()
								.getRemarks();
					}
					rowNumber++;
				}
				xuhao++;
			}
		}
	}
}
