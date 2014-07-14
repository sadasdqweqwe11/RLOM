package com.raylinetech.ssh2.logistics.common.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlUtil {

	public static String test(){
		 Document document = DocumentHelper.createDocument();
		  Element root = document.addElement("ExpressType");
		  Element Epcode = root.addElement("Epcode").addText("");
		  Element Userid = root.addElement("Userid").addText("100000");
		  Element Channel = root.addElement("Channel").addText("中邮北京挂号小包");
		  Element Package = root.addElement("Package").addText("无");
		  Element SendDate = root.addElement("SendDate").addText("2014-07-12T00:00:00");
		  Element Receiver = root.addElement("Receiver");
		  Element recUserid = Receiver.addElement("Userid").addText("100000");
		  Element Name = Receiver.addElement("Name").addText("tang");
		  Element Phone = Receiver.addElement("Phone").addText("13113131313");
		  Element Mobile = Receiver.addElement("Mobile").addText("13922222222");
		  Element Email = Receiver.addElement("Email").addText("jpcn@mpc.com.br");
		  Element Company = Receiver.addElement("Company").addText("");
		  Element Country = Receiver.addElement("Country").addText("澳大利亚");
		  Element Postcode = Receiver.addElement("Postcode").addText("ukh 101");
		  Element State = Receiver.addElement("State").addText("tang");
		  Element City = Receiver.addElement("City").addText("澳大利亚");
		  Element Address1 = Receiver.addElement("Address1").addText("ukh 101");
		  Element Address2 = Receiver.addElement("Address2").addText("tang");
		  Element Memo = root.addElement("Memo").addText("");
		  Element Quantity = root.addElement("Quantity").addText("3");
		  Element GoodsName = root.addElement("GoodsName");
		  Element Id = GoodsName.addElement("Id").addText("0");
		  Element goodsUserid = GoodsName.addElement("Userid").addText("100000");
		  Element NameCh = GoodsName.addElement("NameCh").addText("多媒体播放器");
		  Element NameEn = GoodsName.addElement("NameEn").addText("MP3");
		  Element Weight = GoodsName.addElement("Weight").addText("222");
		  Element DeclaredValue = GoodsName.addElement("DeclaredValue").addText("125");
		  Element DeclaredCurrency = GoodsName.addElement("DeclaredCurrency").addText("USD");
		  String xmlStr = "";
		  xmlStr = document.asXML();
		  return xmlStr;
	}
	
	public static String uploadNew(){
		 Document document = DocumentHelper.createDocument();
		  Element root = document.addElement("ExpressType");
		  Element Epcode = root.addElement("Epcode").addText("");
		  Element Userid = root.addElement("Userid").addText("100000");
		  Element Channel = root.addElement("Channel").addText("中邮北京挂号小包");
		  Element Package = root.addElement("Package").addText("无");
		  Element SendDate = root.addElement("SendDate").addText("2014-07-12T00:00:00");
		  Element Receiver = root.addElement("Receiver");
		  Element recUserid = Receiver.addElement("Userid").addText("100000");
		  Element Name = Receiver.addElement("Name").addText("tang");
		  Element Phone = Receiver.addElement("Phone").addText("13113131313");
		  Element Mobile = Receiver.addElement("Mobile").addText("13922222222");
		  Element Email = Receiver.addElement("Email").addText("jpcn@mpc.com.br");
		  Element Company = Receiver.addElement("Company").addText("");
		  Element Country = Receiver.addElement("Country").addText("澳大利亚");
		  Element Postcode = Receiver.addElement("Postcode").addText("ukh 101");
		  Element State = Receiver.addElement("State").addText("tang");
		  Element City = Receiver.addElement("City").addText("澳大利亚");
		  Element Address1 = Receiver.addElement("Address1").addText("ukh 101");
		  Element Address2 = Receiver.addElement("Address2").addText("tang");
		  Element Memo = root.addElement("Memo").addText("");
		  Element Quantity = root.addElement("Quantity").addText("3");
		  Element GoodsName = root.addElement("GoodsName");
		  Element Id = GoodsName.addElement("Id").addText("0");
		  Element goodsUserid = GoodsName.addElement("Userid").addText("100000");
		  Element NameCh = GoodsName.addElement("NameCh").addText("多媒体播放器");
		  Element NameEn = GoodsName.addElement("NameEn").addText("MP3");
		  Element Weight = GoodsName.addElement("Weight").addText("222");
		  Element DeclaredValue = GoodsName.addElement("DeclaredValue").addText("125");
		  Element DeclaredCurrency = GoodsName.addElement("DeclaredCurrency").addText("USD");
		  String xmlStr = "";
		  xmlStr = document.asXML();
		  return xmlStr;
	}
	
	public static void main(String[] args) {
		System.out.println(test());
	}
}
