package com.raylinetech.ssh2.logistics.common.file;

import java.util.LinkedList;
import java.util.List;

public class ExcelModel {
    /** 
     * 文件路径，这里是包含文件名的路径 
     */  
    protected String path;  
    /** 
     * 工作表名 
     */  
    protected String sheetName;  
    /** *//** 
     * 表内数据,保存在二维的ArrayList对象中 
     */  
    protected Object[][] data;  
    /** 
     * 数据表的标题内容 
     */  
    protected List header;  
    /** 
     * 用于设置列宽的整型数组 
     * 这个方法在程序中暂未用到 
     * 适用于固定列数的表格 
     */  
    protected int[] width;
    
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public List getHeader() {
		return header;
	}
	public void setHeader(List header) {
		this.header = header;
	}
	public int[] getWidth() {
		return width;
	}
	public void setWidth(int[] width) {
		this.width = width;
	}
	public Object[][] getData() {
		return data;
	}
	public void setData(Object[][] data) {
		this.data = data;
	}
	public ExcelModel() {
		super();
	}
	public ExcelModel(String path, String sheetName, Object[][] data,
			List header, int[] width) {
		super();
		this.path = path;
		this.sheetName = sheetName;
		this.data = data;
		this.header = header;
		this.width = width;
	}  

}
