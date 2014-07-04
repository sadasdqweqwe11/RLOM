package com.raylinetech.ssh2.logistics.common.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.service.PdfService;
import com.raylinetech.ssh2.logistics.common.service.RLOrderService;
import com.raylinetech.ssh2.logistics.common.service.impl.PdfServiceImpl;
import com.raylinetech.ssh2.logistics.common.util.DateUtil;
import com.raylinetech.ssh2.logistics.common.util.FontUtil;

public class PdfServiceTest {

	private PdfService pdfService;
	@Before
	public void setUp() throws Exception {
		this.pdfService = new PdfServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		this.pdfService = null;
	}
	
	@Test
	public void testRota() {
		RLOrder order = new RLOrder();
		
		Document document = new Document(PageSize.A4, 40, 40, 20,
				20);
		try {
			FileOutputStream out = new FileOutputStream("test.pdf");
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();
			
			document.newPage();

			float[] widthsOut = { 0.54f ,0.46f };
			PdfPTable table = new PdfPTable(widthsOut);
			table.setWidthPercentage(100);
			
			
			float[] widthsLeft = { 0.33f, 0.34f, 0.33f};
			PdfPTable left = new PdfPTable(widthsLeft);
			PdfPCell cell = null;
			cell= new PdfPCell(new Phrase(""));
			Image jpeg = Image.getInstance("image-1"); 
			//设置图片的位置，参数Image.UNDERLYING是作为文字的背景显示。
			jpeg.setAlignment(Image.UNDERLYING);
			//设置图片的尽对位置
			jpeg.setAbsolutePosition(0, 40); 
			jpeg.scalePercent(25f);
			jpeg.setRotationDegrees(0);
			document.add(jpeg);
			PdfPCell ce = null;
			PdfPCell cellOuter22 = null;
			ce = new PdfPCell(new Phrase("sldkjflskdjlfksd"));
			cellOuter22 = new PdfPCell(new Phrase("afafffffafafa"));
			ce.setPadding(10f);
			cellOuter22.setRotation(270);
			cellOuter22.setBorder(Rectangle.NO_BORDER);
			ce.setBorder(Rectangle.NO_BORDER);
			table.addCell(ce);
			table.addCell(cellOuter22);
			// 第一个模块
			document.add(table);
			
			
			document.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
