package com.raylinetech.ssh2.logistics.common.file;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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
public class TongjiExcelOperator {

	/**
	 * 将数据信息写入到Excel表文件 ，采取传入输出流的方式。
	 * 
	 * @param excel
	 *            Excel表的模型对象
	 * @param out
	 *            OutputStream 输出流
	 * @throws WriteException
	 * @throws Exception
	 */
	public void WriteExcel(ExcelModel excel, OutputStream out)
			throws ExcelException {
		// 新建一输出文件流
		WritableWorkbook workbook = null;
		try {
			workbook = this.getInitWorkbook(excel, out);
			// 把相应的Excel 工作簿存盘
			workbook.write();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("写入错误");
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private WritableWorkbook getInitWorkbook(ExcelModel excel, OutputStream out)
			throws IOException, RowsExceededException, WriteException {
		WritableWorkbook wwb = Workbook.createWorkbook(out);
		WritableSheet ws = wwb.createSheet("sheet1", 0);
		ws.getSettings().setVerticalFreeze(1);
		ws.setColumnView(0, 8);
		ws.setColumnView(1, 16);
		ws.setColumnView(2, 20);
		ws.setColumnView(3, 10);
		ws.setColumnView(4, 24);
		ws.setColumnView(5, 8);
		ws.setColumnView(6, 16);
		ws.setColumnView(7, 16);
		ws.setColumnView(8, 16);
		ws.setColumnView(9, 10);
		ws.setColumnView(10, 8);
		ws.setColumnView(11, 10);

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
			ws.setRowView(0, 600);
			for (int i = 0; i < header.size(); i++) {
				Label label = new Label(i, 0, header.get(i), wcfFC);
				ws.addCell(label);
			}
		}
		Object[][] data = excel.getData();
		for (int i = 0; i < data[0].length; i++) {
			ws.setRowView(i + 1, 600);
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
				if (j > 0 && data[0][j].equals(data[0][j - 1]) ) {
					if(i != 4 && i != 5){
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
			}
		}
		return wwb;
	}

}
