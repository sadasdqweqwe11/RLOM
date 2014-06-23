package com.raylinetech.ssh2.logistics.common.action;

import java.util.List;


import com.raylinetech.ssh2.logistics.common.entity.PrintLable;
import com.raylinetech.ssh2.logistics.common.service.ExcelService;
import com.raylinetech.ssh2.logistics.common.service.PdfService;
import com.raylinetech.ssh2.logistics.common.service.impl.ExcelServiceImpl;
import com.raylinetech.ssh2.logistics.common.service.impl.PdfServiceImpl;

public class PrintExcelToPdf {

	private PdfService pdfService;
	
	private ExcelService excelService;
	
	
	public PdfService getPdfService() {
		return pdfService;
	}

	public void setPdfService(PdfService pdfService) {
		this.pdfService = pdfService;
	}

	public ExcelService getExcelService() {
		return excelService;
	}

	public void setExcelService(ExcelService excelService) {
		this.excelService = excelService;
	}

	public PrintExcelToPdf(PdfService pdfService, ExcelService excelService) {
		super();
		this.pdfService = pdfService;
		this.excelService = excelService;
	}

	
	public PrintExcelToPdf() {
		super();
	}

	public static void main(String[] args) {
		PrintExcelToPdf print = new PrintExcelToPdf();
		print.setExcelService(new ExcelServiceImpl());
		print.setPdfService(new PdfServiceImpl());
//		List<PrintLable> printLables = print.getExcelService().excelToPrintLable("receive/", "Rayline Orders Amaon 20140508.xls");
//		for (PrintLable printLable : printLables) {
//			System.out.println("123123123+++++" + printLable.getProducts());
//		}
//		boolean b = print.getPdfService().printLablesToPdf(printLables,"pdf/","123.pdf");
//		boolean c = print.getExcelService().printLablesToExcel(printLables, ExcelService.EQUICK, "equick/", "Amazon 5.0.8CBJZ11xls");
	}
}
