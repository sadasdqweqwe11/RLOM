package com.raylinetech.ssh2.logistics.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtil {
	
	private Workbook work;
	
	/**
	 * 得到Workbook对象
	 * @param filePath 文件路径
	 * @throws BiffException 
	 * @throws IOException 
	 */
	public ExcelUtil(String filePath) throws BiffException, IOException {
		try {
			InputStream is = new FileInputStream(filePath);
			this.work = Workbook.getWorkbook(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (BiffException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	
	/**
	 * 得到Sheet，像数组一样，下标从0开始
	 * @param number Sheet数
	 * @return
	 * @throws IndexOutOfBoundsException
	 * @throws BiffException
	 * @throws IOException
	 */
	public Sheet getSheet(int number) {
		try {
			return work.getSheet(number);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 得到总行数
	 */
	public int getRows(Sheet sheet) {
		return sheet.getRows();
	}

	/**
	 * 得到总列数
	 */
	public int getCells(Sheet sheet) {
		return sheet.getColumns();
	}

	/**
	 * 得到sheet表格中每一个单元格的具体数据
	 * @param sheet 
	 * @param cell 列
	 * @param row 行
	 * @return 
	 */
	public String getData(Sheet sheet, int cell, int row) {
		return sheet.getCell(cell, cell).getContents();
	}
	
	/**   
	* 拷贝后,进行修改,其中file1为被copy对象，file2为修改后创建的对象   
	* 尽单元格原有的格式化修饰是不能去掉的，我们还是可以将新的单元格修饰加上去，   
	* 以使单元格的内容以不同的形式表现   
	* @param file1   
	* @param file2   
	*/   
	public static void modifyExcel(File file1,File file2)   
	{   
	try   
	{   
	Workbook rwb = Workbook.getWorkbook(file1);   
	WritableWorkbook wwb = Workbook.createWorkbook(file2,rwb);//copy   
	WritableSheet ws = wwb.getSheet(0);   
	WritableCell wc = ws.getWritableCell(0,0);   
	//判断单元格的类型,做出相应的转换   
	if(wc.getType() == CellType.LABEL)   
	{   
	Label label = (Label)wc;   
	label.setString("The value has been modified");   
	}   
	wwb.write();   
	wwb.close();   
	rwb.close();   
	}   
	catch(Exception e)   
	{   
	e.printStackTrace();   
	}   
	}   
	
	
	public static void main(String[] args) throws Exception {
		String filePath = "1405160002.xls";
		ExcelUtil xls = new ExcelUtil(filePath);
		Sheet sheet = xls.getSheet(0);
		int rows = xls.getRows(sheet);
		int cell = xls.getCells(sheet);
		System.out.println(xls.getRows(sheet));
		System.out.println(xls.getCells(sheet));
		for (int i = 0; i < rows; i++) {
			System.out.println("------------" + i);
			for (int j = 0; j < cell; j++) {
				String string = sheet.getCell(j, i).getContents();
				System.out.println(string);
			}
		}
	}
	
	
	
}
