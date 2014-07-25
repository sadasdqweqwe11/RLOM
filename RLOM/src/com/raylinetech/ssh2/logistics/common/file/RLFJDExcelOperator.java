package com.raylinetech.ssh2.logistics.common.file;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.exception.ExcelException;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 
 * @author 
 *
 */
public class RLFJDExcelOperator {

	
    /**  
     * 将数据信息写入到Excel表文件 ，采取传入输出流的方式。 
     * @param excel Excel表的模型对象  
     * @param out  OutputStream 输出流 
     * @throws WriteException 
     * @throws Exception 
     */  
    public  void WriteExcel(List<RLOrder> rlOrders,OutputStream out)throws ExcelException{  
    	WritableWorkbook wwb = null;
    	//新建一输出文件流  
        try {
        	wwb = Workbook.createWorkbook(out);
        	ExcelModel model= new RLFenJianDanExcel(rlOrders);
			this.getInitWorkbook(model,"总表",wwb,out); 
			Map<String,List<RLOrder>> logiMap = new LinkedHashMap<String, List<RLOrder>>();
			for (RLOrder rlOrder : rlOrders) {
				String logisticsName = rlOrder.getLogistics().getName();
				List<RLOrder> orders = logiMap.get(logisticsName);
				if(orders == null){
					orders = new ArrayList<RLOrder>();
					orders.add(rlOrder);
					logiMap.put(logisticsName, orders);
				}else{
					logiMap.get(logisticsName).add(rlOrder);
				}
			}
			for (Entry<String, List<RLOrder>> entry : logiMap.entrySet()) {
				String sheetName = entry.getKey();
				List<RLOrder> orders = entry.getValue();
				if(sheetName.equals("北京小包-不挂号")){
					Map<String,List<RLOrder>> bghmap = new LinkedHashMap<String,List<RLOrder>>();
					for (RLOrder order : orders) {
						List<RLOrder> os = bghmap.get(order.getSku().getName().trim());
						if(os == null){
							os = new ArrayList<RLOrder>();
							os.add(order);
							bghmap.put(order.getSku().getName(), os);
						}else{
							bghmap.get(order.getSku().getName()).add(order);
						}
					}
					for (Entry<String, List<RLOrder>> ent : bghmap.entrySet()) {
						List<RLOrder> skunameOrders = ent.getValue();
						ExcelModel excel= new RLFenJianDanExcel(skunameOrders);
						this.getInitWorkbook(excel,skunameOrders.get(0).getSku().getPinming(),wwb,out); 
					}
				}else{
					ExcelModel excel= new RLFenJianDanExcel(orders);
					this.getInitWorkbook(excel,sheetName,wwb,out); 

				}

				//				if(entry.getValue().size()<=120){
//				}else{
//					List<List<RLOrder>> lists = new LinkedList<List<RLOrder>>();
//					List<RLOrder> result = new LinkedList<RLOrder>();
//					for (int i = 0; i < orders.size(); i++) {
//						//在121单的时候，
//						result.add(orders.get(i));
//						if(result.size()==120){
//							lists.add(result);
//							result = new LinkedList<RLOrder>();
//						}
//						//在最后一单的时候无论如何将所有的内容置入lists里
//						if(i==orders.size()-1){
//							if(result.size()!=0){
//								lists.add(result);
//							}
//						}
//					}
//					for (int i = 0; i < lists.size(); i++) {
//						ExcelModel excel= new RLFenJianDanExcel(lists.get(i));
//						this.getInitWorkbook(excel,sheetName+"-"+(i+1),wwb,out); 
//					}
//				}
			}
			  // 把相应的Excel 工作簿存盘  
			wwb.write();  
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("写入错误");
		} finally{
			if (wwb != null ) {  
            	  try {
					wwb.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
          }  
		}
    }  
    
	private void getInitWorkbook(ExcelModel excel,String sheetName,WritableWorkbook wwb,OutputStream out) throws IOException, RowsExceededException, WriteException {

		WritableSheet ws = wwb.createSheet(sheetName, 0);
		ws.setColumnView(0, 8);
		ws.setColumnView(1, 20);
		ws.setColumnView(2, 12);
		ws.setColumnView(3, 20);
		ws.setColumnView(4, 8);
		ws.setColumnView(5, 20);
		Object[][] o = excel.getData();
		int summaryPosition = 0;
		for (int j = 0; j < o[0].length; j++) {
			if("summary".equalsIgnoreCase(o[0][j].toString())){
				summaryPosition = j;
				break;
			}
		}
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
		
		WritableCellFormat ouhang = new WritableCellFormat(wf); // 单元格定义
		ouhang.setBackground(jxl.format.Colour.GRAY_25); // 设置单元格的背景颜色
		ouhang.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
		ouhang.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		ouhang.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		WritableCellFormat jihang = new WritableCellFormat(wf); // 单元格定义
		jihang.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
		jihang.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		jihang.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		// 设置字体属性
		WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10,
				WritableFont.BOLD);
		WritableCellFormat wcfFC = new WritableCellFormat(wfc);
		wcfFC.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
		wcfFC.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcfFC.setBorder(Border.ALL, BorderLineStyle.THIN);
		List<String> header = excel.getHeader();
		if (header != null) {
			ws.setRowView(0, 300);
			for (int i = 0; i < header.size(); i++) {
				Label label = new Label(i, 0, header.get(i), wcfFC);
				ws.addCell(label);
			}
		}
		Object[][] data = excel.getData();
		for (int i = 0; i < data[0].length; i++) {
			ws.setRowView(i + 1, 300);
		}
		for (int i = 0; i < data.length; i++) {
			//标明行状态
			int lineRowCount = 0;
			//标明与之前
			int begin = 0;
			for (int j = 0; j < data[i].length; j++) {
				//如果为空，则写成""
				if (null == data[i][j]) {
					data[i][j] = "";
				}
				Label label = null;
				//如果不是第一行，且第0列的
				if(j<summaryPosition){
					if (j > 0 && data[0][j].equals(data[0][j - 1]) ) {
						if(i != 3 && i != 4){
							//当前元素是最后一个元素，则直接merge
							if(j==data[0].length-1){
								ws.mergeCells(i, begin, i, j + 1);
							}
							//如果当前元素与后边的一个元素不想同，merge，否则不做处理
							else if(!data[0][j].equals(data[0][j + 1])){
								System.out.println("合并单元格"+"i"+i+"begin"+begin+"i" + i+"end"+(j+1));
								ws.mergeCells(i, begin, i, j + 1);
							}
						}else{
							if ((lineRowCount-1) % 2 == 0) {
								label = new Label(i, j + 1, data[i][j].toString(), ouhang);
							} else {
								label = new Label(i, j + 1, data[i][j].toString(),jihang);
							}
							ws.addCell(label);
						}
					} else {
						if (lineRowCount % 2 == 0) {
							label = new Label(i, j + 1, data[i][j].toString(), ouhang);
						} else {
							label = new Label(i, j + 1, data[i][j].toString(),jihang);
						}
						ws.addCell(label);
						lineRowCount++;
						begin = j + 1;
					}
				}else{
					if(null == data[i][j] ){
						data[i][j] = "";
					}
	            	label = new Label(i, j+1, data[i][j].toString(),jihang);
	            	ws.addCell(label);

				}
			}
		}

	}
public static void main(String[] args) {
	List<List> lists = new LinkedList<List>();
	List result = new LinkedList();
	result.add(1);
	result.add(2);
	result.add(3);
	lists.add(result);
	result = new LinkedList();
	result.add(5);
	result.add(6);
	result.add(7);
	lists.add(result);
	result = new LinkedList();
	System.out.println(lists.get(0).get(2));
	System.out.println(lists.get(1).get(2));
	
}
}
