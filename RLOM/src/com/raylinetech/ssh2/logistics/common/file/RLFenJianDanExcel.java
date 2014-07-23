package com.raylinetech.ssh2.logistics.common.file;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;

public class RLFenJianDanExcel extends ExcelModel {

	private  int headerSize = 6; 
	
	public RLFenJianDanExcel(List<RLOrder> rlOrders) {
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

	}

	private void generateFJD(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			Map<String, Integer> summary = this.getSummary(rlOrders);
			int rowCount = 0;
			for (RLOrder rlOrder : rlOrders) {
				int i = rlOrder.getRlorderitems().size();
				rowCount = rowCount + i;
			}
			rowCount+=(summary.size()+1)/2+1;
			
			
			this.data = new Object[this.headerSize][rowCount];
			int rowNumber = 0;
			int xuhao = 0;
			for (RLOrder o : rlOrders) {
				for (RLOrderItem item : o.getRlorderitems()) {
					this.data[0][rowNumber] = xuhao+1;
					this.data[1][rowNumber] = o.getRlordernumber();
					this.data[2][rowNumber] = o.getVendor();
					this.data[3][rowNumber] = item.getSku().getSkucode();
					this.data[4][rowNumber] = item.getQuantity();
					if (null != o.getLogistics()) {
						this.data[5][rowNumber] = o.getLogistics().getName();
					}
					rowNumber++;
				}
				xuhao++;
			}
			this.data[0][rowNumber] = "SUMMARY";
			rowNumber++;

			boolean flag = true;
			for (Entry<String, Integer> entry : summary.entrySet()) {
				if(flag){
					this.data[1][rowNumber] = entry.getKey();
					this.data[2][rowNumber] = entry.getValue();
					flag= false;
				}else{
					this.data[3][rowNumber] = entry.getKey();
					this.data[4][rowNumber] = entry.getValue();
					flag=true;
					rowNumber++;
				}
			}
		}
	}
	
	private Map<String, Integer> getSummary(List<RLOrder> orders){
		Map<String, Integer> summary = new LinkedHashMap<String, Integer>();
		for (RLOrder rlOrder : orders) {
			for (RLOrderItem item : rlOrder.getRlorderitems()) {
				String skuno =item.getSku().getSkucode();
				Integer count = summary.get(skuno);
				if (null == count) {
					summary.put(skuno, item.getQuantity());
				} else {
					count += item.getQuantity();
					summary.put(skuno, count);
				}
			}
		}
		return summary;
	}
	
	public static void main(String[] args) {
		Map<String, Integer> summary = new LinkedHashMap<String, Integer>();
		summary.put("abc", 1);
		System.out.println(null==summary.get("123"));
	}
}
