package com.raylinetech.ssh2.logistics.common.file;

public class ExcelOperatorFactory {

	private static ExcelOperator operator = new ExcelOperator();

	public ExcelOperator getOperator() {
		return operator;
	}

	public void setOperator(ExcelOperator operator) {
		this.operator = operator;
	}
	
	public static ExcelOperator getExcelOperatorInstance(){
		return operator;
	}
}
