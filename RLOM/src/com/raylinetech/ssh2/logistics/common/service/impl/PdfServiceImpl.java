package com.raylinetech.ssh2.logistics.common.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;
import com.raylinetech.ssh2.logistics.common.entity.Sku;
import com.raylinetech.ssh2.logistics.common.service.PdfService;
import com.raylinetech.ssh2.logistics.common.util.DateUtil;
import com.raylinetech.ssh2.logistics.common.util.ExpressPrint;
import com.raylinetech.ssh2.logistics.common.util.FontUtil;
import com.raylinetech.ssh2.logistics.common.util.StringUtil;

public class PdfServiceImpl implements PdfService {

//	@Override
//	public boolean rlOrdersToPdf(List<RLOrder> rlOrders, int logistics,
//			OutputStream out) {
//		
//		
//		
//		
//		if (logistics == PdfService.LOGISTIC_BJXB||logistics == PdfService.LOGISTIC_SZBJXB) {
//			this.rlOrdersToGHZGYZXB(rlOrders, out);
//		} else if (logistics == PdfService.LOGISTIC_BJXBBGH||logistics == PdfService.LOGISTIC_SZBJXBBGH) {
//			this.rlOrdersToBGHZGYZXB(rlOrders, out);
//		} else if (logistics == PdfService.LOGISTIC_SHEUB) {
//			this.rlOrdersToEUB(rlOrders, out);
//		} else if (logistics == PdfService.LOGISTIC_BJEUB) {
//			this.rlOrdersToEUB(rlOrders, out);
//		} else if (logistics == PdfService.LOGISTIC_SZSBT||logistics == PdfService.LOGISTIC_SHXB||logistics == PdfService.LOGISTIC_YWSHXB) {
//			this.rlOrdersToGHZGYZXBOLD(rlOrders, out);
//		}else if (logistics == PdfService.LOGISTIC_SHXBBGH||logistics == PdfService.LOGISTIC_YWSHXBBGH) {
//			this.rlOrdersToGHZGYZXBOLD(rlOrders, out);
//		}else if (logistics == PdfService.LOGISTIC_SHEQUICK||logistics == PdfService.LOGISTIC_BJEQUICK) {
//			this.rlOrdersToEQUICK(rlOrders, out);
//		}else if (logistics == PdfService.LOGISTIC_SZYYB||logistics == PdfService.LOGISTIC_BJYYB||logistics == PdfService.LOGISTIC_YWYYB||logistics == PdfService.LOGISTIC_SHYYB) {
//			this.rlOrdersToYYB(rlOrders, out);
//		}else if (logistics == PdfService.LOGISTIC_SZHLXB||logistics == PdfService.LOGISTIC_BJHLXB||logistics == PdfService.LOGISTIC_SHHLXB||logistics == PdfService.LOGISTIC_YWHLXB) {
//			this.rlOrdersToHLXB(rlOrders, out);
//		}
//		return true;
//	}
	
	@Override
	public boolean rlOrdersToPdf(List<RLOrder> rlOrders, String filePath) {
		if (rlOrders == null) {
			System.out.println("没有取到数据");
		}
		try {
			Map<Integer, List<RLOrder>> orderMap = new TreeMap<Integer, List<RLOrder>>();
			for (RLOrder o : rlOrders) {
				if(o.getLogistics()==null){
					continue;
				}
				int logid = o.getLogistics().getId();
				List<RLOrder> list = orderMap.get(logid); 
				//如果为空，则表示还没有元素在该键值对中，创建新list并且put进入map中
				if(null==list){
					list = new ArrayList<RLOrder>();
					list.add(o);
					orderMap.put(logid, list);
				}else{
					orderMap.get(logid).add(o);
				}
			}
			for (Entry<Integer, List<RLOrder>> entry: orderMap.entrySet()) {
				int logisticsid = entry.getKey();
				List<RLOrder> orders = entry.getValue();
				Document document = new Document(PageSize.A4, 40, 40, 20,
						20);
				// Step 2—Get a PdfWriter instance.
				FileOutputStream out = new FileOutputStream(filePath+DateUtil.yyyyMMdd()+orders.get(0).getLogistics().getName()+".pdf");
				PdfWriter writer = PdfWriter.getInstance(document, out);
//				writer.setViewerPreferences(PdfWriter.PageLayoutTwoColumnLeft);
				// Step 3—Open the Document.
				document.open();
				if (logisticsid == PdfService.LOGISTIC_BJXB||logisticsid == PdfService.LOGISTIC_SZBJXB) {
					this.rlOrdersToGHZGYZXB(orders, out,document,writer);
				} else if (logisticsid == PdfService.LOGISTIC_BJXBBGH||logisticsid == PdfService.LOGISTIC_SZBJXBBGH) {
					this.rlOrdersToBGHZGYZXB(orders, out,document,writer);
				} else if (logisticsid == PdfService.LOGISTIC_SHEUB||logisticsid == PdfService.LOGISTIC_BJEUB) {
					this.rlOrdersToEUB(orders, out,document,writer);
				} else if (logisticsid == PdfService.LOGISTIC_SZSBT||logisticsid == PdfService.LOGISTIC_SHXB||logisticsid == PdfService.LOGISTIC_YWSHXB||logisticsid == PdfService.LOGISTIC_SHXBBGH||logisticsid == PdfService.LOGISTIC_YWSHXBBGH) {
					this.rlOrdersToGHZGYZXBOLD(orders, out,document,writer);
				}else if (logisticsid == PdfService.LOGISTIC_SHEQUICK||logisticsid == PdfService.LOGISTIC_BJEQUICK||logisticsid == PdfService.LOGISTIC_SZEQUICK) {
					this.rlOrdersToEQUICK(orders, out,document,writer);
				}else if (logisticsid == PdfService.LOGISTIC_SZYYB||logisticsid == PdfService.LOGISTIC_BJYYB||logisticsid == PdfService.LOGISTIC_YWYYB||logisticsid == PdfService.LOGISTIC_SHYYB) {
					this.rlOrdersToYYB(orders, out,document,writer);
				}else if (logisticsid == PdfService.LOGISTIC_SZHLXB||logisticsid == PdfService.LOGISTIC_BJHLXB||logisticsid == PdfService.LOGISTIC_SHHLXB||logisticsid == PdfService.LOGISTIC_YWHLXB) {
					this.rlOrdersToHLXB(orders, out,document,writer);
				}
				document.close();
				out.close();
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	
	@Override
	public boolean rlOrdersToPdf(List<RLOrder> rlOrders,
			OutputStream out) {
		if (rlOrders == null) {
			System.out.println("没有取到数据");
		}
		try {
			// create a document
			Document document = new Document(PageSize.A4, 40, 40, 20,
					20);
			// Step 2—Get a PdfWriter instance.
			PdfWriter writer = PdfWriter.getInstance(document, out);
			writer.setViewerPreferences(PdfWriter.PageLayoutTwoColumnLeft);
			// Step 3—Open the Document.
			document.open();
			Map<Integer, List<RLOrder>> orderMap = new TreeMap<Integer, List<RLOrder>>();
			for (RLOrder o : rlOrders) {
				if(o.getLogistics()==null){
					continue;
				}
				int logid = o.getLogistics().getId();
				List<RLOrder> list = orderMap.get(logid); 
				//如果为空，则表示还没有元素在该键值对中，创建新list并且put进入map中
				if(null==list){
					list = new ArrayList<RLOrder>();
					list.add(o);
					orderMap.put(logid, list);
				}else{
					orderMap.get(logid).add(o);
				}
			}
			for (Entry<Integer, List<RLOrder>> entry: orderMap.entrySet()) {
			    int logisticsid = entry.getKey();
			    List<RLOrder> orders = entry.getValue();
				if (logisticsid == PdfService.LOGISTIC_BJXB||logisticsid == PdfService.LOGISTIC_SZBJXB||logisticsid == PdfService.LOGISTIC_SHXB||logisticsid == PdfService.LOGISTIC_YWSHXB) {
					this.rlOrdersToGHZGYZXB(orders, out,document,writer);
				} else if (logisticsid == PdfService.LOGISTIC_BJXBBGH||logisticsid == PdfService.LOGISTIC_SZBJXBBGH||logisticsid == PdfService.LOGISTIC_SHXBBGH||logisticsid == PdfService.LOGISTIC_YWSHXBBGH) {
					this.rlOrdersToBGHZGYZXB(orders, out,document,writer);
				} else if (logisticsid == PdfService.LOGISTIC_SHEUB||logisticsid == PdfService.LOGISTIC_BJEUB) {
					this.rlOrdersToEUB(orders, out,document,writer);
				} else if (logisticsid == PdfService.LOGISTIC_SZSBT) {
					this.rlOrdersToGHZGYZXBOLD(orders, out,document,writer);
				}else if (logisticsid == PdfService.LOGISTIC_SHEQUICK||logisticsid == PdfService.LOGISTIC_BJEQUICK||logisticsid == PdfService.LOGISTIC_SZEQUICK) {
					this.rlOrdersToEQUICK(orders, out,document,writer);
				}else if (logisticsid == PdfService.LOGISTIC_SZYYB||logisticsid == PdfService.LOGISTIC_BJYYB||logisticsid == PdfService.LOGISTIC_YWYYB||logisticsid == PdfService.LOGISTIC_SHYYB) {
					this.rlOrdersToYYB(orders, out,document,writer);
				}else if (logisticsid == PdfService.LOGISTIC_SZHLXB||logisticsid == PdfService.LOGISTIC_BJHLXB||logisticsid == PdfService.LOGISTIC_SHHLXB||logisticsid == PdfService.LOGISTIC_YWHLXB) {
					this.rlOrdersToHLXB(orders, out,document,writer);
				}
			}
			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	
	
	
	/**
	 * 组成一个四分之一方格的全部内容，包括一张运输单，一张拣货单，一张报关签条
	 * @param writer
	 * @param printLable
	 * @return
	 */
	private PdfPTable formGHZGYZXB(PdfWriter writer, RLOrder rlOrder) {
		float[] widths2 = { 0.45f, 0.1f, 0.45f };
		PdfPTable table = new PdfPTable(widths2);
		table.setWidthPercentage(100);
		PdfPCell cellOuter21 = null;
		PdfPCell cellOuter22 = null;
		if(null == rlOrder){
			cellOuter21 = new PdfPCell();
			cellOuter21.setBorder(Rectangle.NO_BORDER);
			cellOuter22 = new PdfPCell();
			cellOuter22.setBorder(Rectangle.NO_BORDER);
		}else{
			cellOuter21 = new PdfPCell(this.GHZGYZXBFenJianDan(writer,
				rlOrder));
			cellOuter22 = new PdfPCell(this.getTableBaoGuan(writer,
				rlOrder));
		}
		PdfPCell cellSpace = new PdfPCell();
		cellSpace.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellOuter21);
		table.addCell(cellSpace);
		table.addCell(cellOuter22);
		return table;
	}

	private PdfPTable GHZGYZXBFenJianDan(PdfWriter writer, RLOrder ll) {
		PdfContentByte cb = writer.getDirectContent();
		Barcode128 code128 = new Barcode128();
		code128.setCodeType(Barcode128.CODE_A);
		/* 设置条码宽度 begin */
		String fullCode = code128.getRawText(ll.getTrackingno(), false);
		// code128.setCodeType(Barcode128.CODE128_UCC);
		int len = fullCode.length();
		code128.setCode(ll.getTrackingno());
		code128.setX(170 / ((len + 2) * 11 + 2f));
		code128.setBarHeight(25f);
		Image tradingImage = code128.createImageWithBarcode(cb, null, null);
		/* 设置条码宽度 end */
		// image.setAlignment(Element.ALIGN_CENTER);
//		tradingImage.scaleToFit(120, 40);
		
		float[] widths = { 0.20f, 0.20f, 0.25f, 0.15f, 0.20f };
		PdfPTable table = new PdfPTable(widths);
		PdfPCell cell;

		Image img = null;
		try {
			String rootPath = System.getProperty("tansungWeb.root");
			img = Image.getInstance(rootPath + "/images/chinapostlogo.png");
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		img.scaleAbsolute(60f, 20f);
		// fst 2*4
		cell = new PdfPCell(img);
		cell.setBorder(Rectangle.BOTTOM);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(30f);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("航空\nSmall Packet\nBY AIR",
				FontUtil.getChi8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(ll.getGuojia(), FontUtil.getChi10()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);

		String comp = "协议客户：北京燕文物流有限公司(11010502740000)";
		if(ll.getLogistics().getId()==PdfService.LOGISTIC_SHXB||ll.getLogistics().getId()==PdfService.LOGISTIC_YWSHXB){
			comp = "协议客户：北京燕文物流有限公司上海分公司(31000041066000)";
		}
		cell = new PdfPCell(new Phrase(comp,
				FontUtil.getChi8()));
		cell.setUseAscender(true);
		cell.setFixedHeight(20f);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(5);
		table.addCell(cell);

		String from = "FROM    北京市朝阳区万红路5号兰涛中A101";
		if(ll.getLogistics().getId()==PdfService.LOGISTIC_SHXB||ll.getLogistics().getId()==PdfService.LOGISTIC_YWSHXB){
			from = "FROM    上海市闸北区灵石路697-3号五号楼1楼";
		}
		
		cell = new PdfPCell(new Phrase(from,
				FontUtil.getChi8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(5);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(ll.getLogistics().getAccount(), FontUtil.getChi10()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		int logistics = ll.getLogistics().getId(); 
		cell = new PdfPCell();
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setRowspan(2);
		cell.setColspan(3);
		cell.setBorder(Rectangle.NO_BORDER);
		if(logistics == PdfService.LOGISTIC_BJXB||logistics == PdfService.LOGISTIC_SZBJXB){
			float[] widths2 = { 0.33f, 0.34f, 0.33f };
			PdfPTable gxp = new PdfPTable(widths2);
			PdfPCell gua = null; 
			PdfPCell code = null;
//		(logistics == PdfService.LOGISTIC_BJXBBGH||logistics == PdfService.LOGISTIC_SZBJXBBGH){
//			String guaa = ExpressPrint.GetYPOSTP(ll.getGuojia());
//			gua = new PdfPCell(new Phrase(guaa, FontUtil.getChi10BOLD()));
//			gua.setHorizontalAlignment(Element.ALIGN_CENTER);
//			code = new PdfPCell(new Phrase("", FontUtil.getChi10BOLD()));
//		}else{
			String[] guaa = null;
			guaa = ExpressPrint.GetGroupGPY(ll.getGuojia().trim());
			gua = new PdfPCell(new Phrase(guaa[0], FontUtil.getChi10BOLD()));
			gua.setHorizontalAlignment(Element.ALIGN_CENTER);
			code = new PdfPCell(new Phrase(guaa[1], FontUtil.getChi10BOLD()));
			code.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell xu = new PdfPCell(new Phrase(ExpressPrint.GetGroupCN(ll
					.getGuojia()), FontUtil.getChi10BOLD()));
			xu.setHorizontalAlignment(Element.ALIGN_CENTER);
			gxp.addCell(gua);
			gxp.addCell(code);
			gxp.addCell(xu);
			cell.addElement(gxp);
		}
		table.addCell(cell);

		
		
		cell = new PdfPCell(new Phrase("Order:  " + ll.getRlordernumber(),
				FontUtil.getChi8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("TO", FontUtil.getEng10()));
		cell.setRowspan(3);
		cell.setBorder(Rectangle.TOP);
		table.addCell(cell);
		// sec 3*1
		cell = new PdfPCell(new Phrase(ll.getBuyername(), FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.TOP);
		cell.setColspan(4);
		table.addCell(cell);
		// trd 3*1

		String ShipAdress = this.getShipAdress(ll.getShipaddress1(),
				ll.getShipaddress2(), ll.getShipcity(), ll.getShipstate());
		cell = new PdfPCell(new Phrase(ShipAdress, FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setFixedHeight(40f);
		cell.setColspan(4);
		table.addCell(cell);
		// for 3*1
		cell = new PdfPCell(new Phrase(ll.getShipcountry(), FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(4);
		table.addCell(cell);
		// fif 3*1
		cell = new PdfPCell(new Phrase("Zip  "+ll.getPostalcode(), FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		cell.setFixedHeight(20f);
		table.addCell(cell);
		// six 2*1
		cell = new PdfPCell(new Phrase("Tel  "+ll.getBuyerphonenumber(), FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);
		
		String ret = "退件单位： 北京国际邮电局集中收寄网点";
		if(ll.getLogistics().getId()==PdfService.LOGISTIC_SHXB||ll.getLogistics().getId()==PdfService.LOGISTIC_YWSHXB){
			ret = "退件单位： 上海广告商函局";
		}
		cell = new PdfPCell(new Phrase(ret, FontUtil.getChi8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(5);
		table.addCell(cell);
		
		
		// sev 3*1
		if(logistics == PdfService.LOGISTIC_BJXBBGH||logistics == PdfService.LOGISTIC_SZBJXBBGH){
			cell = new PdfPCell(new Phrase("",FontUtil.getEng30()));
			cell.setFixedHeight(40f);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
			// 14 3*1
			cell = new PdfPCell();
			cell.setColspan(4);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
		}else if(logistics == PdfService.LOGISTIC_YWSHXB||logistics == PdfService.LOGISTIC_SHXB){
			cell = new PdfPCell(tradingImage);
			cell.setColspan(5);
			cell.setFixedHeight(40f);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
		}else{
			cell = new PdfPCell(new Phrase("R",FontUtil.getEng30()));
			cell.setFixedHeight(40f);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
			// 14 3*1
			cell = new PdfPCell(tradingImage);
			cell.setColspan(4);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.TOP);
			table.addCell(cell);
		}

		
		return table;
	}

	/**
	 * 旧版本
	 * 
	 * @param writer
	 * @param ll
	 * @return
	 */
	private PdfPTable GHSZXBFenJianDan(PdfWriter writer, RLOrder ll) {
		PdfContentByte cb = writer.getDirectContent();
		Barcode128 code128 = new Barcode128();
		/* 设置条码宽度 begin */
		String fullCode = code128.getRawText(ll.getRlordernumber(), false);
		// code128.setCodeType(Barcode128.CODE128_UCC);
		int len = fullCode.length();
		code128.setCode(ll.getRlordernumber());
		code128.setX(120 / ((len + 2) * 11 + 2f));
		Image rlImage = code128.createImageWithBarcode(cb, null, null);
		/* 设置条码宽度 end */
		// image.setAlignment(Element.ALIGN_CENTER);
		rlImage.scaleToFit(120, 40);
		Barcode128 code1282 = new Barcode128();
		code1282.setCodeType(Barcode128.CODE_A);
		code1282.setCode(ll.getTrackingno());
		Image tradingImage = code1282.createImageWithBarcode(cb, null, null);
		// image.setAlignment(Element.ALIGN_CENTER);
		tradingImage.scaleToFit(120, 40);

		float[] widths = { 0.15f, 0.20f, 0.25f, 0.15f, 0.25f };
		PdfPTable table = new PdfPTable(widths);
		PdfPCell cell;
		int logistics = ll.getLogistics().getId();
		if(logistics == PdfService.LOGISTIC_SHXBBGH||logistics == PdfService.LOGISTIC_YWSHXBBGH){
		cell = new PdfPCell();
			cell.setFixedHeight(40f);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);
			// 14 3*1
			cell = new PdfPCell();
			cell.setColspan(4);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.BOTTOM);
			table.addCell(cell);
		}else{
			
		cell = new PdfPCell(new Phrase("R", FontUtil.getEng30()));
		cell.setFixedHeight(40f);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		// 14 3*1
		cell = new PdfPCell(tradingImage);
		cell.setColspan(4);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.BOTTOM);
		table.addCell(cell);
		}

		cell = new PdfPCell(new Phrase("Ship\nTo:", FontUtil.getEng8()));
		cell.setRowspan(4);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		// sec 3*1
		cell = new PdfPCell(new Phrase(ll.getBuyername(), FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(4);
		table.addCell(cell);
		// trd 3*1

		String ShipAdress = this.getShipAdress(ll.getShipaddress1(),
				ll.getShipaddress2(), ll.getShipcity(), ll.getShipstate());
		cell = new PdfPCell(new Phrase(ShipAdress, FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setFixedHeight(30f);
		cell.setColspan(4);
		table.addCell(cell);
		// for 3*1
		cell = new PdfPCell(new Phrase(ll.getPostalcode(), FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(4);
		table.addCell(cell);
		// fif 3*1
		cell = new PdfPCell(new Phrase(ll.getShipcountry(), FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(4);
		table.addCell(cell);
		// six 2*1
		cell = new PdfPCell(new Phrase("Phone number：", FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(2);
		table.addCell(cell);
		// sev 3*1
		cell = new PdfPCell(new Phrase(ll.getBuyerphonenumber(),
				FontUtil.getEng8()));
		cell.setColspan(3);
		table.addCell(cell);
		// eig 2*1
		cell = new PdfPCell(new Phrase("Order Number：", FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(2);
		table.addCell(cell);
		// nin 3*1
		cell = new PdfPCell(new Phrase(ll.getOrdernumber(), FontUtil.getEng8()));
		cell.setColspan(3);
		table.addCell(cell);
		// ten 1*1
		cell = new PdfPCell(new Phrase("Vendor", FontUtil.getEng6()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);
		// ele 3*1

		cell = new PdfPCell(new Phrase("RL Order Number", FontUtil.getEng8()));
		cell.setColspan(3);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		// twe 1*1
		cell = new PdfPCell(new Phrase("Dates", FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		// 13 1*1
		cell = new PdfPCell(new Phrase(ll.getVendor(), FontUtil.getEng6()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		// 14 3*1
		cell = new PdfPCell(rlImage);
		cell.setColspan(3);
		cell.setFixedHeight(25f);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		// 15 1*1
		cell = new PdfPCell(new Phrase(ll.getDate(), FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		// 20 ~44
		if (ll.getRlorderitems().size() > 5) {
			System.out.println("产品个数大于5");
		} else {
			for (int i = 0; i < 5; i++) {
				if (i < ll.getRlorderitems().size()) {
					RLOrderItem productDetail = ll.getRlorderitems().get(i);
					cell = new PdfPCell(new Phrase(i + 1 + "",
							FontUtil.getEng8()));
					cell.setUseAscender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					String sku = productDetail.getSku().getSkuno();
					if('C'==sku.charAt(0)||'c'==sku.charAt(0)){
						sku = sku.substring(6);
					}
					cell = new PdfPCell(new Phrase(sku.trim(),
							FontUtil.getEng8()));
					cell.setFixedHeight(20f);
					cell.setColspan(2);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(productDetail.getQuantity(),
							FontUtil.getEng8()));
					cell.setUseAscender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase("", FontUtil.getEng8()));
					cell.setUseAscender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
				} else {
					cell = new PdfPCell(new Phrase(i + 1 + "",
							FontUtil.getEng8()));
					cell.setUseAscender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase());
					cell.setFixedHeight(20f);
					cell.setColspan(2);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase());
					cell.setUseAscender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase());
					cell.setUseAscender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
				}
			}
		}

		return table;
	}

	/**
	 * 组成一个四分之一方格的全部内容，包括一张运输单，一张拣货单，一张报关签条
	 * 
	 * @param writer
	 * @param printLable
	 * @return
	 */
	private PdfPTable formZGYZPY(PdfWriter writer, RLOrder rlOrder) {
		
		float[] widths2 = { 0.49f, 0.02f, 0.49f };
		PdfPTable table = new PdfPTable(widths2);
		table.setWidthPercentage(100);
		PdfPCell cellOuter21 = null;
		PdfPCell cellOuter22 = null;
		if(null == rlOrder){
			cellOuter21 = new PdfPCell();
			cellOuter21.setBorder(Rectangle.NO_BORDER);
		}else{
			cellOuter21 = new PdfPCell(this.BGHZGYZXBFenJianDan(writer,
					rlOrder));
		}
		if(null == rlOrder){
			cellOuter22 = new PdfPCell();
			cellOuter22.setBorder(Rectangle.NO_BORDER);
		}else{
			cellOuter22 = new PdfPCell(this.BGHZGYZXBBaoGuan(writer,
					rlOrder));
		}
		PdfPCell cellSpace = new PdfPCell();
		cellSpace.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellOuter21);
		table.addCell(cellSpace);
		table.addCell(cellOuter22);
		return table;
	}

	
	private PdfPTable BGHZGYZXBBaoGuan(PdfWriter writer, RLOrder rlOrder) {
			List<RLOrderItem> products = rlOrder.getRlorderitems();
			Font font = new Font();
			font.setSize(10);
			Font fontSmall = new Font();
			fontSmall.setSize(8);
			fontSmall.setSize(Font.BOLD);
			BaseFont bf = null;
			Font chi10 = null;
			Font chi8 = null;
			Font chi5 = null;
			try {
				bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
						BaseFont.NOT_EMBEDDED);
				chi10 = new Font(bf, 10, Font.BOLD);
				chi8 = new Font(bf, 8, Font.BOLD);
				chi5 = new Font(bf, 5, Font.BOLD);

			} catch (DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			float[] widths = { 0.4f, 0.05f, 0.15f, 0.05f, 0.15f, 0.2f };
			PdfPTable table = new PdfPTable(widths);
			PdfPCell cell;
			// fst 2*4
			cell = new PdfPCell(new Phrase("报关签条\nCUSTOMS DECLARATION", chi8));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(2);
			table.addCell(cell);
			// sec 3*1
			cell = new PdfPCell(new Phrase("邮2113\nCN22", chi8));
			cell.setBorder(Rectangle.BOTTOM);
			cell.setColspan(4);
			cell.setRowspan(2);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);
			// trd 3*1
			cell = new PdfPCell(new Phrase("可径行开拆\nMay be opened officially", chi8));
			cell.setBorder(Rectangle.BOTTOM);
			cell.setColspan(4);
			table.addCell(cell);
			// for 3*1
			cell = new PdfPCell(new Phrase("中国邮政\nCHINA POST", chi8));
			cell.setBorder(Rectangle.BOTTOM);
			cell.setFixedHeight(20f);
			cell.setColspan(1);

			table.addCell(cell);
			// for 3*1
			cell = new PdfPCell(new Phrase(
					"请先阅读背面的注意事项\nSee instructions on the back", chi5));
			cell.setBorder(Rectangle.BOTTOM);
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setColspan(5);
			table.addCell(cell);
			// six 2*1
			cell = new PdfPCell(new Phrase("邮件种类 \n Category of item", chi8));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(1);
			table.addCell(cell);
			// sev 3*1
			cell = new PdfPCell(new Phrase("​√​", chi5));
			cell.setColspan(1);
			table.addCell(cell);
			// sev 3*1
			cell = new PdfPCell(new Phrase("​礼品\n gift​", chi8));
			cell.setColspan(1);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase());
			cell.setColspan(1);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("​商品货样\nCommercial Sample​", chi8));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("请在适当的内容前划​√", chi8));
			cell.setBorder(Rectangle.BOTTOM);
			cell.setColspan(1);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase());
			cell.setColspan(1);
			table.addCell(cell);
			// sev 3*1
			cell = new PdfPCell(new Phrase("​文件\n Documents​", chi5));
			cell.setColspan(1);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase());
			cell.setColspan(1);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("​其他\n Other", chi8));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(
					"​内件详细名称和数量\n Quantity and detailed description of contents",
					chi8));
			cell.setColspan(3);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("​重量(千克)\n Weight(Kg)", chi5));
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("​价值\nValue", chi5));
			cell.setColspan(1);
			table.addCell(cell);
			/*
			 * 这里是内件详细名称和价值，预留4个书写位置
			 */
			int quantity = 0;
			double totalPrice = 0.0;
			double totalWeight = 0.0;
			for (RLOrderItem l : rlOrder.getRlorderitems()) {
				// 申报价值
				quantity = quantity+Integer.parseInt(l.getQuantity());
				if(null != rlOrder.getAmount() &&!"".equals(rlOrder.getAmount().trim())){
					totalPrice= Double.parseDouble(StringUtil
							.getDoubleFromAmount(rlOrder.getAmount())) * 0.3 + totalPrice;
				}
				// 实际重量
				totalWeight = Double.parseDouble(l.getQuantity()) * 0.25 + totalWeight;
			}
			DecimalFormat df = new DecimalFormat("#.##");
			for (int i = 0; i < 4; i++) {
				RLOrderItem productDetail = null;
				if (i < products.size()) {
					productDetail = products.get(i);
					String sku = productDetail.getSku().getSkuno();
					if('C'==sku.charAt(0)||'c'==sku.charAt(0)){
						sku = sku.substring(6);
					}
					String pinming = sku.trim() + " " + productDetail.getSku().getPinming()+"/"+
							productDetail.getSku().getName() + " * " + productDetail.getQuantity();
//						if (pinming.length() > 36) {
//							pinming = pinming.substring(0, 36) + " ...";
//						}
					cell = new PdfPCell(new Phrase(pinming, chi8));
					cell.setBorder(Rectangle.RIGHT);
					cell.setColspan(3);
					table.addCell(cell);
					if (i == 0) {
						cell = new PdfPCell(new Phrase(df.format(totalWeight), chi8));
						cell.setBorder(Rectangle.RIGHT);
						cell.setColspan(2);
						table.addCell(cell);
						String price = df
								.format(Double.parseDouble(StringUtil
										.getDoubleFromAmount(rlOrder.getAmount())) * 0.3);
						cell = new PdfPCell(new Phrase(price, chi8));
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						table.addCell(cell);
					}else{
						cell = new PdfPCell(new Phrase("", chi8));
						cell.setBorder(Rectangle.RIGHT);
						cell.setColspan(2);
						table.addCell(cell);
						cell = new PdfPCell(new Phrase("", chi8));
						cell.setBorder(Rectangle.NO_BORDER);
						cell.setColspan(1);
						table.addCell(cell);
					}
				} else {
					cell = new PdfPCell(new Phrase("​", chi10));
					cell.setBorder(Rectangle.RIGHT);
					cell.setColspan(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("​", chi8));
					cell.setBorder(Rectangle.RIGHT);
					cell.setColspan(2);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("", chi8));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setColspan(1);
					table.addCell(cell);
				}
			}

			/*
			 * 协调系统行
			 */
			cell = new PdfPCell(
					new Phrase(
							"​协调系统税则号和货物原产国(只对商品邮件填写)\nHS tariff number and country of goods (For commercial items only)",
							chi5));
			cell.setColspan(3);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("​总重量\nTotal Weight", chi5));
			cell.setColspan(2);
			cell.setRowspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("​总价值\nTotal Value", chi5));
			cell.setRowspan(2);
			table.addCell(cell);

			/*
			 * 总重量行
			 */
			cell = new PdfPCell(new Phrase(" ", font));
			cell.setUseAscender(true);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setColspan(3);
			table.addCell(cell);

			/*
			 * 寄件人签字
			 */
			cell = new PdfPCell(
					new Phrase(
							"​我保证上述申报准确无误，本函件内未装寄法律或邮政和海关规章禁止寄递的任何危险物品\n​I, the undersigned, ceritfy that the particulars given in this declaration are correct and this item does not contain any dangerous articials prohi bited by legislation or by postal or customs regulations",
							chi5));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(6);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("​寄件人签字 Sender's signature", chi8));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(3);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("​"));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(2);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(""));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setColspan(1);
			table.addCell(cell);

			return table;
	}








	private PdfPTable BGHZGYZXBFenJianDan(PdfWriter writer, RLOrder order) {
		PdfContentByte cb = writer.getDirectContent();
		Barcode128 code128 = new Barcode128();
		code128.setCodeType(Barcode128.CODE_A);
		/* 设置条码宽度 begin */
		String fullCode = code128.getRawText(order.getTrackingno(), false);
		// code128.setCodeType(Barcode128.CODE128_UCC);
		int len = fullCode.length();
		code128.setCode(order.getTrackingno());
		code128.setX(160 / ((len + 2) * 11 + 2f));
		code128.setBarHeight(30f);
		Image tradingImage = code128.createImageWithBarcode(cb, null, null);
		/* 设置条码宽度 end */
		// image.setAlignment(Element.ALIGN_CENTER);
//		tradingImage.scaleToFit(120, 40);
		
		float[] widths = { 0.30f,0.50f, 0.20f};
		PdfPTable table = new PdfPTable(widths);
		PdfPCell cell;
		// fst 2*4

		
		cell = new PdfPCell(tradingImage);
		cell.setBorder(Rectangle.BOTTOM);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(45f);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);

		
		cell = new PdfPCell(new Phrase(" To:",
				FontUtil.getEng30()));
		cell.setBorder(Rectangle.TOP);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell.setFixedHeight(30f);
		cell.setColspan(1);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("  " + order.getGuojia(),
				FontUtil.getChi15()));
		cell.setBorder(Rectangle.TOP);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("  "+order.getBuyername(),FontUtil.getEng15()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("  Tel: "+ order.getBuyerphonenumber(),FontUtil.getEng15()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(	"  " + this.getShipAdress(order.getShipaddress1(), order.getShipaddress2(), "", ""),FontUtil.getEng10()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("  "+	this.getShipAdress(order.getShipcity(), order.getShipstate(), "", "") + "  " + order.getPostalcode(),FontUtil.getEng10()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("  " +order.getShipcountry(),FontUtil.getEng10()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("",FontUtil.getEng4()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("  OrderNo: "+order.getRlordernumber(),FontUtil.getEng12()));
		cell.setBorder(Rectangle.BOTTOM);
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(order.getLogistics().getAccount(),FontUtil.getEng8()));
		cell.setBorder(Rectangle.BOTTOM);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase("航空",FontUtil.getChi10()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setColspan(3);
		table.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase("YW",FontUtil.getEng6()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setColspan(3);
		table.addCell(cell);
		
		return table;
	}








	private PdfPTable ZGYZPYFenJianDan(PdfWriter writer, RLOrder ll) {
		PdfContentByte cb = writer.getDirectContent();
		Barcode128 code128 = new Barcode128();
		/* 设置条码宽度 begin */
		String fullCode = code128.getRawText(ll.getRlordernumber(), false);
		// code128.setCodeType(Barcode128.CODE128_UCC);
		int len = fullCode.length();
		code128.setCode(ll.getRlordernumber());
		code128.setX(120 / ((len + 2) * 11 + 2f));
		Image rlImage = code128.createImageWithBarcode(cb, null, null);
		/* 设置条码宽度 end */
		// image.setAlignment(Element.ALIGN_CENTER);
		rlImage.scaleToFit(120, 40);
		// Barcode128 code1282 = new Barcode128();
		// code1282.setCodeType(Barcode128.CODE_A);
		// code1282.setCode(ll.getTradingno());
		// Image tradingImage = code1282.createImageWithBarcode(cb, null, null);
		// tradingImage.scaleToFit(120, 40);

		float[] widths = { 0.15f, 0.15f, 0.3f, 0.2f, 0.2f };
		PdfPTable table = new PdfPTable(widths);
		PdfPCell cell;

		cell = new PdfPCell(new Phrase("", FontUtil.getEng15()));
		cell.setFixedHeight(40f);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		// 14 3*1
		cell = new PdfPCell();
		cell.setColspan(4);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.BOTTOM);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Ship\nTo:", FontUtil.getEng8()));
		cell.setRowspan(4);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		// sec 3*1
		cell = new PdfPCell(new Phrase(ll.getBuyername(), FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(4);
		table.addCell(cell);
		// trd 3*1

		String ShipAdress = this.getShipAdress(ll.getShipaddress1(),
				ll.getShipaddress2(), ll.getShipcity(), ll.getShipstate());
		cell = new PdfPCell(new Phrase(ShipAdress, FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setFixedHeight(30f);
		cell.setColspan(4);
		table.addCell(cell);
		// for 3*1
		cell = new PdfPCell(new Phrase(ll.getPostalcode(), FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(4);
		table.addCell(cell);
		// fif 3*1
		cell = new PdfPCell(new Phrase(ll.getShipcountry(), FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(4);
		table.addCell(cell);
		// six 2*1
		cell = new PdfPCell(new Phrase("Phone number：", FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(2);
		table.addCell(cell);
		// sev 3*1
		cell = new PdfPCell(new Phrase(ll.getBuyerphonenumber(),
				FontUtil.getEng8()));
		cell.setColspan(3);
		table.addCell(cell);
		// eig 2*1
		cell = new PdfPCell(new Phrase("Order Number：", FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(2);
		table.addCell(cell);
		// nin 3*1
		cell = new PdfPCell(new Phrase(ll.getOrdernumber(), FontUtil.getEng8()));
		cell.setColspan(3);
		table.addCell(cell);
		// ten 1*1
		cell = new PdfPCell(new Phrase("Vendor", FontUtil.getEng6()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);
		// ele 3*1

		cell = new PdfPCell(new Phrase("RL Order Number", FontUtil.getEng8()));
		cell.setColspan(3);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		// twe 1*1
		cell = new PdfPCell(new Phrase("Dates", FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);
		// 13 1*1
		cell = new PdfPCell(new Phrase(ll.getVendor(), FontUtil.getEng6()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		// 14 3*1
		cell = new PdfPCell(rlImage);
		cell.setColspan(3);
		cell.setFixedHeight(25f);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		// 15 1*1
		cell = new PdfPCell(new Phrase(ll.getDate(), FontUtil.getEng8()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		// 20 ~44
		if (ll.getRlorderitems().size() > 5) {
			System.out.println("产品个数大于5");
		} else {
			for (int i = 0; i < 5; i++) {
				if (i < ll.getRlorderitems().size()) {
					RLOrderItem productDetail = ll.getRlorderitems().get(i);
					cell = new PdfPCell(new Phrase(i + 1 + "",
							FontUtil.getEng8()));
					cell.setUseAscender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					String sku = productDetail.getSku().getSkuno();
					if('C'==sku.charAt(0)||'c'==sku.charAt(0)){
						sku = sku.substring(6);
					}
					cell = new PdfPCell(new Phrase(sku.trim(),
							FontUtil.getEng8()));
					cell.setFixedHeight(20f);
					cell.setColspan(2);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(productDetail.getQuantity(),
							FontUtil.getEng8()));
					cell.setUseAscender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase("", FontUtil.getEng8()));
					cell.setUseAscender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
				} else {
					cell = new PdfPCell(new Phrase(i + 1 + "",
							FontUtil.getEng8()));
					cell.setUseAscender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase());
					cell.setFixedHeight(20f);
					cell.setColspan(2);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase());
					cell.setUseAscender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase());
					cell.setUseAscender(true);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
				}
			}
		}

		return table;
	}

	/**
	 * 通过地址拼接出详细地址
	 * 
	 * @param shipAdress1
	 * @param shipAdress2
	 * @param shipCity
	 * @param shipState
	 * @return
	 */
	private String getShipAdress(String shipAdress1, String shipAdress2,
			String shipCity, String shipState) {
		String adress = "";
		if (null != shipAdress1 && !"".equals(shipAdress1)) {
			adress = adress + shipAdress1;
		}
		if (null != shipAdress2 && !"".equals(shipAdress2)) {
			adress = adress + ", " + shipAdress2;
		}
		if (null != shipCity && !"".equals(shipCity)) {
			adress = adress + ", " + shipCity;
		}
		if (null != shipState && !"".equals(shipState)) {
			adress = adress + ", " + shipState;
		}
		return adress;
	}

	/**
	 * 旧版本中邮小包标签
	 * 
	 * @param writer
	 * @param printLable
	 * @return
	 */
	private PdfPTable formGHSZXB(PdfWriter writer, RLOrder rlOrder) {
		float[] widths2 = { 0.49f, 0.02f, 0.49f };
		PdfPTable table = new PdfPTable(widths2);
		table.setWidthPercentage(100);
		PdfPCell cellOuter21 = new PdfPCell(this.GHSZXBFenJianDan(writer,
				rlOrder));
		PdfPCell cellOuter22 = new PdfPCell(this.getTableSZBaoGuan(writer,
				rlOrder));
		PdfPCell cellSpace = new PdfPCell();
		cellSpace.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellOuter21);
		table.addCell(cellSpace);
		table.addCell(cellOuter22);
		return table;
	}
	
	private PdfPTable getTableSZBaoGuan(PdfWriter writer, RLOrder printLable) {
		List<RLOrderItem> products = printLable.getRlorderitems();
		Font font = new Font();
		font.setSize(10);
		Font fontSmall = new Font();
		fontSmall.setSize(8);
		fontSmall.setSize(Font.BOLD);
		BaseFont bf = null;
		Font chi10 = null;
		Font chi8 = null;
		Font chi5 = null;
		try {
			bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
			chi10 = new Font(bf, 10, Font.BOLD);
			chi8 = new Font(bf, 8, Font.BOLD);
			chi5 = new Font(bf, 5, Font.BOLD);

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		float[] widths = { 0.4f, 0.05f, 0.15f, 0.05f, 0.15f, 0.2f };
		PdfPTable table = new PdfPTable(widths);
		PdfPCell cell;
		// fst 2*4
		cell = new PdfPCell(new Phrase("报关签条\nCUSTOMS DECLARATION", chi8));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);
		// sec 3*1
		cell = new PdfPCell(new Phrase("邮2113\nCN22", chi8));
		cell.setBorder(Rectangle.BOTTOM);
		cell.setColspan(4);
		cell.setRowspan(2);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		// trd 3*1
		cell = new PdfPCell(new Phrase("可径行开拆\nMay be opened officially", chi8));
		cell.setBorder(Rectangle.BOTTOM);
		cell.setColspan(4);
		table.addCell(cell);
		// for 3*1
		cell = new PdfPCell(new Phrase("中国邮政\nCHINA POST", chi8));
		cell.setBorder(Rectangle.BOTTOM);
		cell.setFixedHeight(20f);
		cell.setColspan(1);

		table.addCell(cell);
		// for 3*1
		cell = new PdfPCell(new Phrase(
				"请先阅读背面的注意事项\nSee instructions on the back", chi5));
		cell.setBorder(Rectangle.BOTTOM);
		cell.setColspan(5);
		table.addCell(cell);
		// six 2*1
		cell = new PdfPCell(new Phrase("邮件种类 \n Category of item", chi8));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(1);
		table.addCell(cell);
		// sev 3*1
		cell = new PdfPCell(new Phrase("​√​", chi5));
		cell.setColspan(1);
		table.addCell(cell);
		// sev 3*1
		cell = new PdfPCell(new Phrase("​礼品\n gift​", chi8));
		cell.setColspan(1);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase());
		cell.setColspan(1);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("​商品货样\nCommercial Sample​", chi8));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("请在适当的内容前划​√", chi8));
		cell.setBorder(Rectangle.BOTTOM);
		cell.setColspan(1);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase());
		cell.setColspan(1);
		table.addCell(cell);
		// sev 3*1
		cell = new PdfPCell(new Phrase("​文件\n Documents​", chi5));
		cell.setColspan(1);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase());
		cell.setColspan(1);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("​其他\n Other", chi8));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(
				"​内件详细名称和数量\n Quantity and detailed description of contents",
				chi8));
		cell.setColspan(3);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("​重量(千克)\n Weight(Kg)", chi5));
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("​价值\nValue", chi5));
		cell.setColspan(1);
		table.addCell(cell);
		/*
		 * 这里是内件详细名称和价值，预留4个书写位置
		 */

		for (int i = 0; i < 3; i++) {
			RLOrderItem productDetail = null;
			if (i < products.size()) {
				if (i == 0) {
					productDetail = products.get(i);
					String pinming = productDetail.getSku().getPinming()
							+ productDetail.getSku().getName();
					if (pinming.length() > 40) {
						pinming = pinming.substring(0, 36) + "...";
					}
					cell = new PdfPCell(new Phrase(pinming, chi8));
					cell.setBorder(Rectangle.RIGHT);
					cell.setFixedHeight(10f);
					cell.setColspan(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("​", chi8));
					cell.setBorder(Rectangle.RIGHT);
					cell.setColspan(2);
					table.addCell(cell);
					DecimalFormat df = new DecimalFormat("#.##");
					String price = df
							.format(Double.parseDouble(StringUtil
									.getDoubleFromAmount(printLable.getAmount())) * 0.3);
					cell = new PdfPCell(new Phrase("$" + price, chi8));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setColspan(1);
					table.addCell(cell);
				}

			} else {
				cell = new PdfPCell(new Phrase("​", chi10));
				cell.setBorder(Rectangle.RIGHT);
				cell.setColspan(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("​", chi8));
				cell.setBorder(Rectangle.RIGHT);
				cell.setColspan(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("", chi8));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setColspan(1);
				table.addCell(cell);
			}
		}

		/*
		 * 协调系统行
		 */
		cell = new PdfPCell(
				new Phrase(
						"​协调系统税则号和货物原产国(只对商品邮件填写)\nHS tariff number and country of goods (For commercial items only)",
						chi5));
		cell.setColspan(3);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("​总重量\nTotal Weight", chi5));
		cell.setColspan(2);
		cell.setRowspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("​总价值\nTotal Value", chi5));
		cell.setRowspan(2);
		table.addCell(cell);

		/*
		 * 总重量行
		 */
		cell = new PdfPCell(new Phrase("CN", font));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(3);
		table.addCell(cell);

		/*
		 * 寄件人签字
		 */
		cell = new PdfPCell(
				new Phrase(
						"​我保证上述申报准确无误，本函件内未装寄法律或邮政和海关规章禁止寄递的任何危险物品\n​I, the undersigned, ceritfy that the particulars given in this declaration are correct and this item does not contain any dangerous articials prohi bited by legislation or by postal or customs regulations",
						chi5));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(6);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("​寄件人签字 Sender's signature", chi8));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("​"));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(1);
		table.addCell(cell);

		return table;
	}

	private PdfPTable getTableBaoGuan(PdfWriter writer, RLOrder printLable) {
		List<RLOrderItem> products = printLable.getRlorderitems();
		Font font = new Font();
		font.setSize(10);
		Font fontSmall = new Font();
		fontSmall.setSize(8);
		BaseFont bf = null;
		Font chi10 = null;
		Font chi8 = null;
		Font chi6 = null;
		Font chi7 = null;
		Font chi5 = null;

		try {
			bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
			chi10 = new Font(bf, 10, Font.BOLD);
			chi8 = new Font(bf, 8, Font.BOLD);
			chi6 = new Font(bf, 6, Font.BOLD);
			chi7 = new Font(bf, 7, Font.BOLD);
			chi5 = new Font(bf, 5, Font.BOLD);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		float[] widths = { 0.4f, 0.05f, 0.15f, 0.05f, 0.15f, 0.2f };
		Image img = null;
		try {
			String rootPath = System.getProperty("tansungWeb.root");
			img = Image.getInstance(rootPath + "/images/chinapostlogo.png");
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		img.scaleAbsolute(60f, 20f);
		PdfPTable table = new PdfPTable(widths);
		PdfPCell cell;
		// fst 2*4
		cell = new PdfPCell(img);
		cell.setBorder(Rectangle.BOTTOM);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(21f);
		cell.setColspan(2);
		cell.setRowspan(2);
		table.addCell(cell);
		// sec 3*1
		cell = new PdfPCell(new Phrase("报关签条           邮2113", chi7));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(4);
		table.addCell(cell);
		// trd 3*1
		cell = new PdfPCell(new Phrase("CUSTOMS DECLARATION CN22", chi7));
		cell.setBorder(Rectangle.BOTTOM);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(4);
		table.addCell(cell);
		// trd 3*1
		cell = new PdfPCell(new Phrase(
				"可径行开拆          May be opened officially", chi7));
		cell.setBorder(Rectangle.BOTTOM);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setFixedHeight(15f);
		cell.setColspan(6);
		table.addCell(cell);
		// for 3*1
		// six 2*1
		cell = new PdfPCell(new Phrase("邮件种类 \n Category of item", chi6));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(1);
		table.addCell(cell);
		// sev 3*1
		cell = new PdfPCell(new Phrase("​x", chi6));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(1);
		table.addCell(cell);
		// sev 3*1
		cell = new PdfPCell(new Phrase("​礼品\n gift​", chi6));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(1);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase());
		cell.setColspan(1);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("​商品货样\nCommercial Sample​", chi6));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("请在适当的内容前划​x", chi6));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.BOTTOM);
		cell.setColspan(1);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase());
		cell.setColspan(1);
		table.addCell(cell);
		// sev 3*1
		cell = new PdfPCell(new Phrase("​文件\n Documents​", chi5));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(1);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase());
		cell.setColspan(1);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("​其他\n Other", chi6));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(
				"​内件详细名称和数量\n Quantity and detailed description of contents",
				chi6));
		cell.setColspan(3);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("​重量\n(千克)\nWeight\n(Kg)", chi5));
		cell.setColspan(2);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("​价值\nValue", chi6));
		cell.setColspan(1);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		/*
		 * 这里是内件详细名称和价值，预留4个书写位置
		 */
		int quantity = 0;
		double totalPrice = 0.0;
		double totalWeight = 0.0;
		StringBuilder sb = new StringBuilder();
		for (RLOrderItem l : printLable.getRlorderitems()) {
			Sku sku = l.getSku();
			String skuno = sku.getSkuno();
			if('C'==skuno.charAt(0)||'c'==skuno.charAt(0)){
				skuno = skuno.substring(6);
			}
			sb.append(skuno.trim() + " * " + l.getQuantity()+"\n");
			// 申报价值
			quantity = quantity+Integer.parseInt(l.getQuantity());
			totalPrice= Double.parseDouble(StringUtil
					.getDoubleFromAmount(printLable.getAmount())) * 0.3 + totalPrice;
			// 实际重量
			totalWeight = Double.parseDouble(l.getQuantity()) * 0.25 + totalWeight;
		}
		DecimalFormat df = new DecimalFormat("#.##");
		
		for (int i = 0; i < 5; i++) {
			RLOrderItem productDetail = null;
			if (i < products.size()) {
					productDetail = products.get(i);
					String sku = productDetail.getSku().getSkuno();
					if('C'==sku.charAt(0)||'c'==sku.charAt(0)){
						sku = sku.substring(6);
					}
					String pinming = sku.trim() + " " + productDetail.getSku().getPinming()
							+ productDetail.getSku().getName() + "  " + productDetail.getQuantity();
					if (pinming.length() > 40) {
						pinming = pinming.substring(0, 36) + "...";
					}
					cell = new PdfPCell(new Phrase(pinming, chi6));
					cell.setBorder(Rectangle.RIGHT);
					cell.setFixedHeight(10f);
					cell.setColspan(3);
					table.addCell(cell);
					
				if (i == 0) {
					cell = new PdfPCell(new Phrase(df.format(totalWeight), chi6));
					cell.setBorder(Rectangle.RIGHT);
					cell.setColspan(2);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase(df.format(totalPrice), chi6));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setColspan(1);
					table.addCell(cell);
				}else{
					cell = new PdfPCell(new Phrase("", chi6));
					cell.setBorder(Rectangle.RIGHT);
					cell.setColspan(2);
					table.addCell(cell);
					cell = new PdfPCell(new Phrase("", chi6));
					cell.setBorder(Rectangle.NO_BORDER);
					cell.setColspan(1);
					table.addCell(cell);
				}
			} else {
				cell = new PdfPCell(new Phrase("​", chi6));
				cell.setBorder(Rectangle.RIGHT);
				cell.setColspan(3);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("​", chi6));
				cell.setBorder(Rectangle.RIGHT);
				cell.setColspan(2);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase("", chi6));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setColspan(1);
				table.addCell(cell);
			}
		}

		/*
		 * 协调系统行
		 */
		cell = new PdfPCell(
				new Phrase(
						"​协调系统税则号和货物原产国(只对商品邮件填写) HS tariff number and country of goods (For commercial items only)",
						chi6));
		cell.setColspan(3);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("​总重量\nTotal Weight", chi6));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("​总价值\nTotal Value", chi6));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		/*
		 * 总重量行
		 */
		cell = new PdfPCell(new Phrase("CN", font));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(3);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(df.format(totalWeight), chi6));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(2);
		table.addCell(cell);
		cell = new PdfPCell(new Phrase(df.format(totalPrice), chi6));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		/*
		 * 寄件人签字
		 */
		cell = new PdfPCell(
				new Phrase(
						"​我保证上述申报准确无误，本函件内未装寄法律或邮政和海关规章禁止寄递的任何危险物品\n​I, the undersigned, ceritfy that the particulars given in this declaration are correct and this item does not contain any dangerous articials prohi bited by legislation or by postal or customs regulations",
						chi5));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(6);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("          ​寄件人签字 Sender's signature",
				chi6));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("​"));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(""));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(1);
		table.addCell(cell);

		return table;
	}




	/**
	 * 竖版A4 6个标签
	 * @param rlOrders
	 * @param out
	 * @return
	 */
	private boolean rlOrdersToGHZGYZXB(List<RLOrder> rlOrders, OutputStream out, Document document,PdfWriter writer) {
		try {
			int size = rlOrders.size();
			for (int i = 0; i < (size+2)/3; i++) {
				document.newPage();
				List<RLOrder> orders = new ArrayList<RLOrder>();
				for (int j = 0; j < 3; j++) {
					//如果size>i*3+j
					if(size>i*3 + j){
						orders.add(rlOrders.get(i*3+j));
					// 第一个模块
					}else{
						orders.add(null);
					}
				}
				PdfPTable table1 = this.formGHZGYZXB(writer,
						orders.get(0));
				// 第一个模块
				document.add(table1);
				document.add(new Paragraph("\n"));  
				document.add(new Paragraph("\n"));  
				PdfPTable table2 = this.formGHZGYZXB(writer,
						orders.get(1));
				// 第一个模块
				document.add(table2);
				document.add(new Paragraph("\n"));  
				document.add(new Paragraph("\n"));  
				PdfPTable table3 = this.formGHZGYZXB(writer,
						orders.get(2));
				// 第一个模块
				document.add(table3);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}



	private PdfPTable EUBFenJianDan(PdfWriter writer, RLOrder ll) {
		PdfContentByte cb = writer.getDirectContent();
		Barcode128 code128 = new Barcode128();
		/* 设置条码宽度 begin */
		String fullCode = code128.getRawText(ll.getTrackingno(), false);
		// code128.setCodeType(Barcode128.CODE128_UCC);
		int len = fullCode.length();
		code128.setCode(ll.getTrackingno());
		code128.setX(120 / ((len + 2) * 11 + 2f));
		code128.setBarHeight(30f);
		Image image = code128.createImageWithBarcode(cb, null, null);

		float[] widths = { 0.3f, 0.7f };
		PdfPTable table = new PdfPTable(widths);
		PdfPCell cell;

		cell = new PdfPCell(new Phrase("", FontUtil.getEng8()));
		cell.setBorder(Rectangle.BOTTOM);
		cell.setColspan(2);
		table.addCell(cell);

		
		
		cell = new PdfPCell(new Phrase("RL Order NO", FontUtil.getEng8()));
		cell.setFixedHeight(40f);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.TOP);
		cell.setBorder(Rectangle.LEFT);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(ll.getRlordernumber(),
				FontUtil.getEng10()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.TOP);
		cell.setBorder(Rectangle.RIGHT);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Tracking NO", FontUtil.getEng8()));
		cell.setFixedHeight(90f);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorder(Rectangle.LEFT);
		table.addCell(cell);

		cell = new PdfPCell(image);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setBorder(Rectangle.RIGHT);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setColspan(4);
		table.addCell(cell);

		float[] widths2 = { 0.1f, 0.8f ,0.1f};
		PdfPTable tableDown = new PdfPTable(widths2);
		PdfPCell cell1;
		List<RLOrderItem> items = ll.getRlorderitems();
		for (int i = 0; i < 4; i++) {
			if(items.size()>i){
				cell1 = new PdfPCell(new Phrase(i+1+"", FontUtil.getEng8()));
				cell1.setUseAscender(true);
				cell1.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDown.addCell(cell1);
				String sku = items.get(i).getSku().getSkuno();
				if('C'==sku.charAt(0)||'c'==sku.charAt(0)){
					sku = sku.substring(6);
				}
				cell1 = new PdfPCell(new Phrase(sku.trim(), FontUtil.getEng8()));
				cell1.setUseAscender(true);
				cell1.setVerticalAlignment(Element.ALIGN_BOTTOM);
				tableDown.addCell(cell1);
				
				cell1 = new PdfPCell(new Phrase(items.get(i).getQuantity(), FontUtil.getEng8()));
				cell1.setUseAscender(true);
				cell1.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDown.addCell(cell1);
			}else{
				cell1 = new PdfPCell(new Phrase(i+1+"", FontUtil.getEng8()));
				cell1.setUseAscender(true);
				cell1.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDown.addCell(cell1);
				
				cell1 = new PdfPCell(new Phrase("", FontUtil.getEng8()));
				cell1.setUseAscender(true);
				cell1.setVerticalAlignment(Element.ALIGN_BOTTOM);
				tableDown.addCell(cell1);
				
				cell1 = new PdfPCell(new Phrase("", FontUtil.getEng8()));
				cell1.setUseAscender(true);
				cell1.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableDown.addCell(cell1);
				
			}
		}
		cell1 = new PdfPCell(new Phrase(" ", FontUtil.getEng8()));
		cell1.setColspan(3);
		cell1.setBorder(Rectangle.NO_BORDER);
		tableDown.addCell(cell1);

		cell = new PdfPCell(tableDown);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);
		
		return table;
	}

	/**
	 * 横版A4 16个标签
	 * @param rlOrders
	 * @param out
	 * @return
	 */
	private boolean rlOrdersToEUB(List<RLOrder> rlOrders, OutputStream out, Document document,PdfWriter writer) {
		try {
			// Step 3—Open the Document.
			document.setPageSize(PageSize.A4.rotate());
			int size = rlOrders.size();
			for (int i = 0; i < (size + 11) / 12; i++) {
				document.newPage();
				float[] widthOuter = { 0.25f, 0.25f, 0.25f, 0.25f };
				PdfPTable table = new PdfPTable(widthOuter);
				table.setWidthPercentage(100);
				for (int j = 0; j < 12; j++) {
					System.out.println("j=" + j);
					if (size > i * 12 + j + 1) {
						PdfPCell tableCell1 = new PdfPCell(this.EUBFenJianDan(
								writer, rlOrders.get(i * 12 + j)));
						tableCell1.setBorder(Rectangle.NO_BORDER);
						table.addCell(tableCell1);
					} else if (size == i * 12 + j + 1) {
						PdfPCell cellSpace = new PdfPCell();
						cellSpace.setBorder(Rectangle.NO_BORDER);
						PdfPCell tableCell1 = new PdfPCell(this.EUBFenJianDan(
								writer, rlOrders.get(i * 12 + j)));
						tableCell1.setBorder(Rectangle.NO_BORDER);
						table.addCell(tableCell1);
						int loop = 4 - (j + 1) % 4;
						for (int k = 0; k < loop; k++) {
							table.addCell(cellSpace);
						}
					} else {
						break;
					}
				}
				document.add(table);
			}
			document.setPageSize(PageSize.A4);
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		} finally {
		}
		return true;
	}
	
	private boolean rlOrdersToBGHZGYZXB(List<RLOrder> rlOrders, OutputStream out, Document document,PdfWriter writer) {
//		if (rlOrders == null) {
//			System.out.println("没有取到数据");
//			return false;
//		}
//		try {
//			// create a document
//			Document document = new Document(PageSize.A4.rotate(), 15, 15, 28,
//					28);
//			// Step 2—Get a PdfWriter instance.
//			PdfWriter writer = PdfWriter.getInstance(document, out);
//			writer.setViewerPreferences(PdfWriter.PageLayoutTwoColumnLeft);
//			// Step 3—Open the Document.
//			int size = rlOrders.size();
//			System.out.println("size ====" + size);
//			document.open();
//			for (int i = 0; i < (size + 3) / 4; i++) {
//
//				document.newPage();
//				float[] widthOuter = { 0.48f, 0.04f, 0.48f };
//				PdfPTable table = new PdfPTable(widthOuter);
//				table.setWidthPercentage(100);
//				// 第一个模块
//				PdfPCell cellSpace = new PdfPCell();
//				cellSpace.setBorder(Rectangle.NO_BORDER);
//				PdfPCell tableCell1 = new PdfPCell(this.formZGYZPY(writer,
//						rlOrders.get(i * 4)));
//				table.addCell(tableCell1);
//				table.addCell(cellSpace);
//				// 如果最后一页只有一个订单，则会报The document has no
//				// pages错误或者不显示，所以当只有一位的时候，在后边增加一个空列
//				// 当某一页只有一个订单 && 这一页是最后一页
//				if (size % 4 == 1 && i == size / 4) {
//					System.out.println("of course");
//					PdfPCell tableCell2 = new PdfPCell();
//					tableCell2.setBorder(Rectangle.NO_BORDER);
//					table.addCell(tableCell2);
//				}
//				// 第二个模块
//				if (i * 4 + 1 < size) {
//					PdfPCell tableCell2 = new PdfPCell(this.formZGYZPY(writer,
//							rlOrders.get(i * 4 + 1)));
//					table.addCell(tableCell2);
//				}
//				// 第三个模块
//				if (i * 4 + 2 < size) {
//					PdfPCell cellHorizontalSpace = new PdfPCell();
//					cellHorizontalSpace.setColspan(3);
//					cellHorizontalSpace.setFixedHeight(20f);
//					cellHorizontalSpace.setBorder(Rectangle.NO_BORDER);
//					table.addCell(cellHorizontalSpace);
//					PdfPCell tableCell3 = new PdfPCell(this.formZGYZPY(writer,
//							rlOrders.get(i * 4 + 2)));
//					table.addCell(tableCell3);
//					table.addCell(cellSpace);
//				}
//				// 如果最后一页有三个订单，则会不显示最后，所以当只有一位的时候，在后边增加一个空列
//				// 当某一页只有三个订单 && 这一页是最后一页
//				if (size % 4 == 3 && i == size / 4) {
//					System.out.println("of course123123123");
//					PdfPCell tableCell2 = new PdfPCell();
//					tableCell2.setBorder(Rectangle.NO_BORDER);
//					table.addCell(tableCell2);
//				}
//				// 第四个模块
//				if (i * 4 + 3 < size) {
//					PdfPCell tableCell3 = new PdfPCell(this.formZGYZPY(writer,
//							rlOrders.get(i * 4 + 3)));
//					table.addCell(tableCell3);
//				}
//				document.add(table);
//			}
//			document.close();
//		} catch (DocumentException e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
		try {
			int size = rlOrders.size();
			for (int i = 0; i < (size+2)/3; i++) {
				document.newPage();
				
				List<RLOrder> orders = new ArrayList<RLOrder>();
				for (int j = 0; j < 3; j++) {
					//如果size>i*6+j
					if(size>i*3 + j){
						orders.add(rlOrders.get(i*3+j));
					// 第一个模块
					}else{
						orders.add(null);
					}
				}
				System.out.println("size ====" + orders.size());
				PdfPTable table = this.formZGYZPY(writer,orders.get(0));
				document.add(table);
				document.add(new Paragraph("\n"));  
				PdfPTable table2 = this.formZGYZPY(writer,orders.get(1));
				document.add(table2);
				document.add(new Paragraph("\n"));  
				PdfPTable table3 = this.formZGYZPY(writer,orders.get(2));
				document.add(table3);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean rlOrdersToGHZGYZXBOLD(List<RLOrder> rlOrders, OutputStream out, Document document,PdfWriter writer) {
		try {
			document.setPageSize(PageSize.A4.rotate());
			int size = rlOrders.size();
			for (int i = 0; i < (size + 3) / 4; i++) {
				document.newPage();
				float[] widthOuter = { 0.48f, 0.04f, 0.48f };
				PdfPTable table = new PdfPTable(widthOuter);
				table.setWidthPercentage(100);
				// 第一个模块
				PdfPCell cellSpace = new PdfPCell();
				cellSpace.setBorder(Rectangle.NO_BORDER);
				PdfPCell tableCell1 = new PdfPCell(this.formGHSZXB(writer,
						rlOrders.get(i * 4)));
				table.addCell(tableCell1);
				table.addCell(cellSpace);
				// 如果最后一页只有一个订单，则会报The document has no
				// pages错误或者不显示，所以当只有一位的时候，在后边增加一个空列
				// 当某一页只有一个订单 && 这一页是最后一页
				if (size % 4 == 1 && i == size / 4) {
					System.out.println("of course");
					PdfPCell tableCell2 = new PdfPCell();
					tableCell2.setBorder(Rectangle.NO_BORDER);
					table.addCell(tableCell2);
				}
				// 第二个模块
				if (i * 4 + 1 < size) {
					PdfPCell tableCell2 = new PdfPCell(this.formGHSZXB(
							writer, rlOrders.get(i * 4 + 1)));
					table.addCell(tableCell2);
				}
				// 第三个模块
				if (i * 4 + 2 < size) {
					PdfPCell cellHorizontalSpace = new PdfPCell();
					cellHorizontalSpace.setColspan(3);
					cellHorizontalSpace.setFixedHeight(20f);
					cellHorizontalSpace.setBorder(Rectangle.NO_BORDER);
					table.addCell(cellHorizontalSpace);
					PdfPCell tableCell3 = new PdfPCell(this.formGHSZXB(
							writer, rlOrders.get(i * 4 + 2)));
					table.addCell(tableCell3);
					table.addCell(cellSpace);
				}
				// 如果最后一页有三个订单，则会不显示最后，所以当只有一位的时候，在后边增加一个空列
				// 当某一页只有三个订单 && 这一页是最后一页
				if (size % 4 == 3 && i == size / 4) {
					System.out.println("of course123123123");
					PdfPCell tableCell2 = new PdfPCell();
					tableCell2.setBorder(Rectangle.NO_BORDER);
					table.addCell(tableCell2);
				}
				// 第四个模块
				if (i * 4 + 3 < size) {
					PdfPCell tableCell3 = new PdfPCell(this.formGHSZXB(
							writer, rlOrders.get(i * 4 + 3)));
					table.addCell(tableCell3);
				}
				document.add(table);
			}
			document.setPageSize(PageSize.A4);
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	private void rlOrdersToHLXB(List<RLOrder> rlOrders, OutputStream out, Document document,PdfWriter writer) {
		try {
			int size = rlOrders.size();
			for (int i = 0; i < (size+5)/6; i++) {
				document.newPage();
				
				List<RLOrder> orders = new ArrayList<RLOrder>();
				for (int j = 0; j < 6; j++) {
					//如果size>i*6+j
					if(size>i*6 + j){
						orders.add(rlOrders.get(i*6+j));
					// 第一个模块
					}else{
						orders.add(null);
					}
				}
				System.out.println("size ====" + orders.size());
				RLOrder[] rls = {orders.get(0),orders.get(1)};
				PdfPTable table = this.formHLXB(writer,rls);
				document.add(table);
				document.add(new Paragraph("\n"));  
				RLOrder[] rls2 = {orders.get(2),orders.get(3)};
				PdfPTable table2 = this.formHLXB(writer,rls2);
				document.add(table2);
				document.add(new Paragraph("\n"));  
				RLOrder[] rls3 = {orders.get(4),orders.get(5)};
				PdfPTable table3 = this.formHLXB(writer,rls3);
				document.add(table3);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private void rlOrdersToYYB(List<RLOrder> rlOrders, OutputStream out, Document document,PdfWriter writer) {
		try {
			int size = rlOrders.size();
			for (int i = 0; i < (size+5)/6; i++) {
				document.newPage();
				
				List<RLOrder> orders = new ArrayList<RLOrder>();
				for (int j = 0; j < 6; j++) {
					//如果size>i*6+j
					if(size>i*6 + j){
						orders.add(rlOrders.get(i*6+j));
					// 第一个模块
					}else{
						orders.add(null);
					}
				}
				System.out.println("size ====" + orders.size());
				RLOrder[] rls = {orders.get(0),orders.get(1)};
				PdfPTable table = this.formYYB(writer,rls);
				document.add(table);
				document.add(new Paragraph("\n"));  
				document.add(new Paragraph("\n"));  
				RLOrder[] rls2 = {orders.get(2),orders.get(3)};
				PdfPTable table2 = this.formYYB(writer,rls2);
				document.add(table2);
				document.add(new Paragraph("\n"));  
				document.add(new Paragraph("\n"));  
				RLOrder[] rls3 = {orders.get(4),orders.get(5)};
				PdfPTable table3 = this.formYYB(writer,rls3);
				document.add(table3);
				document.add(new Paragraph("\n"));  
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	private void rlOrdersToEQUICK(List<RLOrder> rlOrders, OutputStream out, Document document,PdfWriter writer) {
		try {
			// Step 3—Open the Document.
			int size = rlOrders.size();
			for (int i = 0; i < (size+5)/6; i++) {
				document.newPage();
				List<RLOrder> orders = new ArrayList<RLOrder>();
				for (int j = 0; j < 6; j++) {
					//如果size>i*6+j
					if(size>i*6 + j){
						orders.add(rlOrders.get(i*6+j));
					// 第一个模块
					}else{
						orders.add(null);
					}
				}
				System.out.println("size ====" + orders.size());
				RLOrder[] rls = {orders.get(0),orders.get(1)};
				PdfPTable table = this.formEquick(writer,rls);
				document.add(table);
				document.add(new Paragraph("\n"));  
				RLOrder[] rls2 = {orders.get(2),orders.get(3)};
				PdfPTable table2 = this.formEquick(writer,rls2);
				document.add(table2);
				document.add(new Paragraph("\n"));  
				RLOrder[] rls3 = {orders.get(4),orders.get(5)};
				PdfPTable table3 = this.formEquick(writer,rls3);
				document.add(table3);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
		
	
	private PdfPTable getTableEquick(PdfWriter writer, RLOrder order) {
		PdfContentByte cb = writer.getDirectContent();
		Barcode128 code128 = new Barcode128();
		code128.setCodeType(Barcode128.CODE_A);
		/* 设置条码宽度 begin */
		String fullCode = code128.getRawText(order.getTrackingno(), false);
		// code128.setCodeType(Barcode128.CODE128_UCC);
		int len = fullCode.length();
		code128.setCode(order.getTrackingno());
		code128.setX(107 / ((len + 2) * 11 + 2f));
		code128.setBarHeight(35f);
		Image tradingImage = code128.createImageWithBarcode(cb, null, null);
		/* 设置条码宽度 end */
		// image.setAlignment(Element.ALIGN_CENTER);
//		tradingImage.scaleToFit(120, 40);
		
		float[] widths = { 0.70f, 0.30f};
		PdfPTable table = new PdfPTable(widths);
		PdfPCell cell;
		// fst 2*4
		
		float[] widths1 = { 0.66f, 0.34f};
		PdfPTable tableInner = new PdfPTable(widths1);
		PdfPCell cell1 = null;
		cell1 = new PdfPCell(tradingImage);
		cell1.setBorder(Rectangle.BOTTOM);
		cell1.setUseAscender(true);
		cell1.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell1.setFixedHeight(48f);
		cell1.setBorder(Rectangle.NO_BORDER);
		tableInner.addCell(cell1);
		
		cell1 = new PdfPCell();
		cell1.setBorder(Rectangle.NO_BORDER);
		tableInner.addCell(cell1);
		
		
		
		
		cell = new PdfPCell(tableInner);
		cell.setBorder(Rectangle.BOTTOM);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setFixedHeight(48f);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setRowspan(3);
		table.addCell(cell);

		
		
		
		
		
		
		
		
		
		
		
		cell = new PdfPCell(new Phrase("Sender",
				FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(order.getLogistics().getAccount(), FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("EQUK-2", FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Consignee", FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(order.getBuyername(), FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Company Name", FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(order.getBuyername(), FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);
		
		String country = "";
		if(null != order.getGuojia()){
			if(order.getGuojia().equals("英国")){
				country = "UNITED KINGDOM (GB)";
			}
			if(order.getGuojia().equals("美国")){
				country = "UNITED STATE (US)";
			}
			
		}
		cell = new PdfPCell(new Phrase("Country  " + country, FontUtil.getChi8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);

		String state = order.getShipstate();
		if(null == state || "".equals(state.trim())){
			state = order.getShipcity();
		}
		cell = new PdfPCell(new Phrase("State  "+ state, FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("City  " + order.getShipcity(), FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);
		
		//postalcode
		cell = new PdfPCell(new Phrase("Postcode  "+order.getPostalcode()+"      Phone  "+ order.getBuyerphonenumber(), FontUtil.getEng8()));
		cell.setColspan(2);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		
		cell = new PdfPCell(new Phrase("Detail Address", FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(this.getShipAdress(order.getShipaddress1(), order.getShipaddress2(), order.getShipcity(), ""), FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setFixedHeight(35);
		cell.setColspan(2);
		table.addCell(cell);
		
		int quantity = 0;
		double totalPrice = 0.0;
		double totalWeight = 0.0;
		for (RLOrderItem l : order.getRlorderitems()) {
			// 申报价值
			quantity = quantity+Integer.parseInt(l.getQuantity());
			totalPrice= Double.parseDouble(StringUtil
					.getDoubleFromAmount(order.getAmount())) * 0.3 + totalPrice;
			// 实际重量
			totalWeight = Double.parseDouble(l.getQuantity()) * 0.25 + totalWeight;
		}
		
		DecimalFormat df = new DecimalFormat("#.##");
		String price = df
				.format(Double.parseDouble(StringUtil
						.getDoubleFromAmount(order.getAmount())) * 0.3);
		//weight
		cell = new PdfPCell(new Phrase("Weight  "+ df.format(totalWeight)+"      Declare Value  " +  (int)totalPrice+" USD", FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);
		
		if(null != order.getRlorderitems()&& 0 != order.getRlorderitems().size()){
		//good name
		cell = new PdfPCell(new Phrase("Goods Name  "+ order.getRlorderitems().get(0).getSku().getName(), FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);
		}
		//Ref No 
		cell = new PdfPCell(new Phrase("Ref No  "+ order.getRlordernumber(), FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(this.getEquickItem(order.getRlorderitems()), FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		cell.setFixedHeight(25f);
		table.addCell(cell);
		
		return table;
	}
	
	/**
	 * 组成一个四分之一方格的全部内容，包括一张运输单，一张拣货单，一张报关签条
	 * 
	 * @param writer
	 * @param printLable
	 * @return
	 */
	private PdfPTable formEquick(PdfWriter writer, RLOrder[] rlOrders) {
		float[] widths2 = { 0.47f, 0.06f, 0.47f };
		PdfPTable table = new PdfPTable(widths2);
		table.setWidthPercentage(100);
		PdfPCell cellOuter21 = null;
		PdfPCell cellOuter22 = null;
		if(null == rlOrders[0]){
			cellOuter21 = new PdfPCell();
			cellOuter21.setBorder(Rectangle.NO_BORDER);
		}else{
			cellOuter21 = new PdfPCell(this.getTableEquick(writer,
					rlOrders[0]));
		}
		if(null == rlOrders[1]){
			cellOuter22 = new PdfPCell();
			cellOuter22.setBorder(Rectangle.NO_BORDER);
		}else{
			cellOuter22 = new PdfPCell(this.getTableEquick(writer,
					rlOrders[1]));
		}
		PdfPCell cellSpace = new PdfPCell();
		cellSpace.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellOuter21);
		table.addCell(cellSpace);
		table.addCell(cellOuter22);
		return table;
	}
	
	/**
	 * 组成一个四分之一方格的全部内容，包括一张运输单，一张拣货单，一张报关签条
	 * 
	 * @param writer
	 * @param printLable
	 * @return
	 */
	private PdfPTable formHLXB(PdfWriter writer, RLOrder[] rlOrders) {
		float[] widths2 = { 0.47f, 0.06f, 0.47f  };
		PdfPTable table = new PdfPTable(widths2);
		table.setWidthPercentage(100);
		PdfPCell cellOuter21 = null;
		PdfPCell cellOuter22 = null;
		if(null == rlOrders[0]){
			cellOuter21 = new PdfPCell();
			cellOuter21.setBorder(Rectangle.NO_BORDER);
		}else{
			cellOuter21 = new PdfPCell(this.getTableHLXB(writer,
					rlOrders[0]));
		}
		if(null == rlOrders[1]){
			cellOuter22 = new PdfPCell();
			cellOuter22.setBorder(Rectangle.NO_BORDER);
		}else{
			cellOuter22 = new PdfPCell(this.getTableHLXB(writer,
					rlOrders[1]));
		}
		PdfPCell cellSpace = new PdfPCell();
		cellSpace.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellOuter21);
		table.addCell(cellSpace);
		table.addCell(cellOuter22);
		return table;
	}
	/**
	 * 组成一个四分之一方格的全部内容，包括一张运输单，一张拣货单，一张报关签条
	 * 
	 * @param writer
	 * @param printLable
	 * @return
	 */
	private PdfPTable formYYB(PdfWriter writer, RLOrder[] rlOrders) {
		float[] widths2 = { 0.47f, 0.06f, 0.47f };
		PdfPTable table = new PdfPTable(widths2);
		table.setWidthPercentage(100);
		PdfPCell cellOuter21 = null;
		PdfPCell cellOuter22 = null;
		if(null == rlOrders[0]){
			cellOuter21 = new PdfPCell();
			cellOuter21.setBorder(Rectangle.NO_BORDER);
		}else{
			cellOuter21 = new PdfPCell(this.getTableYYB(writer,
					rlOrders[0]));
		}
		if(null == rlOrders[1]){
			cellOuter22 = new PdfPCell();
			cellOuter22.setBorder(Rectangle.NO_BORDER);
		}else{
			cellOuter22 = new PdfPCell(this.getTableYYB(writer,
					rlOrders[1]));
		}
		PdfPCell cellSpace = new PdfPCell();
		cellSpace.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellOuter21);
		table.addCell(cellSpace);
		table.addCell(cellOuter22);
		return table;
	}
	
	private PdfPTable getTableYYB(PdfWriter writer, RLOrder order) {
		PdfContentByte cb = writer.getDirectContent();
		Barcode128 code128 = new Barcode128();
		code128.setCodeType(Barcode128.CODE_A);
		/* 设置条码宽度 begin */
		String fullCode = code128.getRawText(order.getTrackingno(), false);
		// code128.setCodeType(Barcode128.CODE128_UCC);
		int len = fullCode.length();
		code128.setCode(order.getTrackingno());
		code128.setX(150 / ((len + 2) * 11 + 2f));
		code128.setBarHeight(30f);
		Image tradingImage = code128.createImageWithBarcode(cb, null, null);
		/* 设置条码宽度 end */
		// image.setAlignment(Element.ALIGN_CENTER);
//		tradingImage.scaleToFit(120, 40);
		
		float[] widths = { 0.30f,0.50f, 0.20f};
		PdfPTable table = new PdfPTable(widths);
		PdfPCell cell;
		// fst 2*4

		cell = new PdfPCell(new Phrase("YANWEN",
				FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("1",
				FontUtil.getEng30()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setRowspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(tradingImage);
		cell.setBorder(Rectangle.BOTTOM);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(45f);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setRowspan(3);
		cell.setColspan(2);
		table.addCell(cell);

		
		
		cell = new PdfPCell(new Phrase("",
				FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setRowspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Y-POST - 俄罗斯",
				FontUtil.getChi10()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(Rectangle.TOP);
		cell.setColspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("TO:",
				FontUtil.getChi10()));
		cell.setBorder(Rectangle.TOP);
		cell.setColspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(order.getBuyername()+"  Tel: "+ order.getBuyerphonenumber(),FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(	this.getShipAdress(order.getShipaddress1(), order.getShipaddress2(), "", ""),FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(	this.getShipAdress(order.getShipcity(), order.getShipstate(), "", "") + "    " + order.getPostalcode(),FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Russia",FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("俄罗斯",FontUtil.getChi8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" ",FontUtil.getChi8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("OrderNo: "+order.getRlordernumber(),FontUtil.getChi8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" ",FontUtil.getChi8()));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		StringBuilder sb = new StringBuilder();
		for (RLOrderItem l : order.getRlorderitems()) {
			String sku = l.getSku().getSkuno();
			if('C'==sku.charAt(0)||'c'==sku.charAt(0)){
				sku = sku.substring(6);
			}
			sb.append("  " + sku.trim() + " * " + l.getQuantity()+"\n");
		}
		cell = new PdfPCell(new Phrase(sb.toString(),FontUtil.getChi8()));
		cell.setColspan(2);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setRowspan(5);
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" ",FontUtil.getChi8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setRowspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(order.getLogistics().getAccount(), FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date=null;
		String sdate= "";
		try {
			date = format.parse(order.getDate());
			sdate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		cell = new PdfPCell(new Phrase(sdate, FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);

		
		return table;
	}
	private PdfPTable getTableHLXB(PdfWriter writer, RLOrder order) {
		PdfContentByte cb = writer.getDirectContent();
		Barcode128 code128 = new Barcode128();
		code128.setCodeType(Barcode128.CODE_A);
		/* 设置条码宽度 begin */
		String fullCode = code128.getRawText(order.getTrackingno(), false);
		// code128.setCodeType(Barcode128.CODE128_UCC);
		int len = fullCode.length();
		code128.setCode(order.getTrackingno());
		code128.setX(216 / ((len + 2) * 11 + 2f));
		code128.setBarHeight(40f);
		Image tradingImage = code128.createImageWithBarcode(cb, null, null);
		/* 设置条码宽度 end */
		// image.setAlignment(Element.ALIGN_CENTER);
//		tradingImage.scaleToFit(120, 40);
		Image img = null;
		try {
			String rootPath = System.getProperty("tansungWeb.root");
			img = Image.getInstance(rootPath + "/images/hlxb1.png");
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		img.scaleAbsolute(80f, 40f);
		
		Image img2 = null;
		try {
			String rootPath = System.getProperty("tansungWeb.root");
			img2 = Image.getInstance(rootPath + "/images/hlxb2.png");
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		img2.scaleAbsolute(80f, 40f);
		
		
		float[] widths = { 0.25f, 0.30f, 0.45f};
		PdfPTable table = new PdfPTable(widths);
		PdfPCell cell;

		cell = new PdfPCell(new Phrase("S-01135",
				FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(img);
		cell.setBorder(Rectangle.BOTTOM);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setFixedHeight(41f);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setRowspan(2);
		table.addCell(cell);	
		
		cell = new PdfPCell(img2);
		cell.setBorder(Rectangle.BOTTOM);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(41f);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setRowspan(2);
		table.addCell(cell);	
		
		cell = new PdfPCell(new Phrase("Postbus 71203\n3000 PE\nRotterdom\nNetthands",
				FontUtil.getEng6()));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("R",
				FontUtil.getEng15()));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Registered/recommandé                  "+order.getLogistics().getAccount(),
				FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setColspan(2);
		table.addCell(cell);
		
		
		// fst 2*4
		cell = new PdfPCell(tradingImage);
		cell.setBorder(Rectangle.BOTTOM);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_TOP);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setFixedHeight(40f);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(3);
		table.addCell(cell);

		cell = new PdfPCell(this.getHLXBBG(writer, order));
		cell.setColspan(2);
		cell.setRowspan(2);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(order.getBuyername()+"\n"+ this.getShipAdress(order.getShipaddress1(), order.getShipaddress2(), order.getShipcity(),"")+"\n"+order.getPostalcode()+"\n"+order.getShipcountry()+"\n\n"+order.getBuyerphonenumber(),
				FontUtil.getEng10()));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		StringBuilder sb = new StringBuilder();
		sb.append(order.getRlordernumber()+"\n");
		for (RLOrderItem l : order.getRlorderitems()) {
			String sku = l.getSku().getSkuno();
			if('C'==sku.charAt(0)||'c'==sku.charAt(0)){
				sku = sku.substring(6);
			}
			sb.append(sku.trim() + " * " + l.getQuantity()+"\n");
			
		}
		cell = new PdfPCell(new Phrase(sb.toString() ,FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		
		return table;
	}








	private PdfPTable getHLXBBG(PdfWriter writer, RLOrder order) {
		float[] widths2 = { 0.5f, 0.25f, 0.25f };
		PdfPTable table = new PdfPTable(widths2);
		PdfPCell cell = null;
		
		cell = new PdfPCell(new Phrase("CUSTOMS\nDECLARATION",FontUtil.getEng8()));
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("May be\nopened\nofficially",FontUtil.getEng6()));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("CN22",FontUtil.getEng8()));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Designated operator Post NL",FontUtil.getEng6()));
		cell.setColspan(3);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Important! See instructions",FontUtil.getEng6()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setColspan(3);
		table.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase("□ Gift",FontUtil.getChi6()));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("□ Sample",FontUtil.getChi6()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("□ Documents",FontUtil.getChi6()));
		cell.setBorder(Rectangle.NO_BORDER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("√ Others(Tick as\nappropriate)",FontUtil.getChi6()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setColspan(2);
		table.addCell(cell);
		

		cell = new PdfPCell(new Phrase("Quantity and\ndetailed",FontUtil.getEng6()));
		cell.setRowspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Weigth",FontUtil.getEng6()));
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Value",FontUtil.getEng6()));
		table.addCell(cell);
		
		
		int quantity = 0;
		double totalPrice = 0.0;
		double totalWeight = 0.0;
		StringBuilder sb = new StringBuilder();
		for (RLOrderItem l : order.getRlorderitems()) {
			sb.append(l.getSku().getName() + " * " + l.getQuantity()+"\n");
			// 申报价值
			quantity = quantity+Integer.parseInt(l.getQuantity());
			totalPrice= Double.parseDouble(StringUtil
					.getDoubleFromAmount(order.getAmount())) * 0.3 + totalPrice;
			// 实际重量
			totalWeight = Double.parseDouble(l.getQuantity()) * 0.25 + totalWeight;
		}
		DecimalFormat df = new DecimalFormat("#.##");
		cell = new PdfPCell(new Phrase(df.format(totalWeight),FontUtil.getEng6()));
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("USD:" + df.format(totalPrice),FontUtil.getEng6()));
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(sb.toString(),FontUtil.getEng6()));
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setFixedHeight(28);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Total\nWeight(KG)",FontUtil.getEng6()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Total\nValue",FontUtil.getEng6()));
		cell.setUseAscender(true);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Origin:China",FontUtil.getEng6()));
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(df.format(totalWeight),FontUtil.getEng6()));
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("USD:" + df.format(totalPrice),FontUtil.getEng6()));
		table.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase("I,the undersigned whose name and address are given on the item certify\nthat the particulars given in the declaration are correct and that\nthis item does not contain any dangerous article or articles prohibited\nby legislation or by postal or customs regulations.",FontUtil.getEng4()));
		cell.setColspan(3);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Date and Sender's Signature",FontUtil.getEng6()));
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(" ",FontUtil.getEng6()));
		table.addCell(cell);
		
		
		return table;
	}
	
	public static void main(String[] args) {
		System.out.println(Element.ALIGN_BOTTOM);
		System.out.println(Element.ALIGN_JUSTIFIED);
		System.out.println(Element.ALIGN_JUSTIFIED_ALL);
		if('C'=="CJB".charAt(0)||'c'=="CLL".charAt(0)){
		System.out.println("yes".substring(2));	
		}
	}
	
	private String getEquickItem(List<RLOrderItem> items){
		StringBuilder sb = new StringBuilder();
		if(items!=null){
			for (int i = 0; i < 4; i++) {
				if(i<items.size()){
					sb.append("    ");
					String sku = items.get(i).getSku().getSkuno();
					if('C'==sku.charAt(0)||'c'==sku.charAt(0)){
						sku = sku.substring(6);
					}
					sb.append(sku.trim() + "  " + items.get(i).getQuantity());
					if(i%2==1){
						sb.append("\n");
					}
				}
			}
		}
		return sb.toString();
	}
	
}