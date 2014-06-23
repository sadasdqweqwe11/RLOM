package com.raylinetech.ssh2.logistics.common.file;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.raylinetech.ssh2.logistics.common.exception.ExcelException;

import jxl.Workbook;
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
public class ExcelOperator {

//    public  void WriteExcel(ExcelModel excel) throws ExcelException{           
//         String file = excel.getPath();  
//         FileOutputStream out = null;
//         //新建一输出文件流  
//         try {
//			out = new FileOutputStream(file);  
//			 WritableWorkbook workbook = this.getInitWorkbook(excel,out); 
//			  // 把相应的Excel 工作簿存盘  
//			  workbook.write();  
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ExcelException("写入错误");
//		} finally{
//			// 操作结束，关闭文件   
//            if (out != null ) {  
//                try {  
//                    out.close();
//                } catch (IOException io) {  
////log  
//                }  
//            }  
//		}
//    }

    /** *//** 
     * 将数据信息写入到Excel表文件 ，采取传入输出流的方式。 
     * @param excel Excel表的模型对象  
     * @param out  OutputStream 输出流 
     * @throws WriteException 
     * @throws Exception 
     */  
    public  void WriteExcel(ExcelModel excel,OutputStream out)throws ExcelException{  
        //新建一输出文件流  
    	WritableWorkbook workbook = null ;
        try {
			 workbook= this.getInitWorkbook(excel,out); 
			  // 把相应的Excel 工作簿存盘  
			 workbook.write();  
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("写入错误");
		} finally{
			if (workbook != null ) {  
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
    
	private WritableWorkbook getInitWorkbook(ExcelModel excel,OutputStream out) throws IOException, RowsExceededException, WriteException {
		WritableWorkbook wwb = Workbook.createWorkbook(out);
		WritableSheet ws = wwb.createSheet("sheet1", 0);
		//设置字体属性
		WritableFont wfc = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD);
		WritableCellFormat wcfFC = new WritableCellFormat(wfc);		
		List<String> header = excel.getHeader();
		if(header!=null){  
            for(int i=0;i<header.size();i++){  
            	Label label = new Label(i, 0, header.get(i),wcfFC);
            	ws.addCell(label);
            }  
        } 
		Object[][] data = excel.getData();
		System.out.println("data.length" + data.length);
		System.out.println("data[0].length" + data[0].length);
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				if(null == data[i][j] ){
					data[i][j] = "";
				}
            	Label label = new Label(i, j+1, data[i][j].toString());
            	ws.addCell(label);
			}
		}
		return wwb;
	}
    
    
}
