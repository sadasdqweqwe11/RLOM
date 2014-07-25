package com.raylinetech.ssh2.logistics.common.util;

import java.util.Random;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlUtil {

	public static String test(){
		 Document document = DocumentHelper.createDocument();
		  Element root = document.addElement("ExpressType");
		  Element Epcode = root.addElement("Epcode").addText("");
		  Element Userid = root.addElement("Userid").addText("100000");
		  Element Channel = root.addElement("Channel").addText("中邮北京挂号小包");
		  Element UserOrderNumber = root.addElement("UserOrderNumber").addText("140202"+new Random().nextInt());
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
	
	public static String getTrackingNoFromXml(String xml){
		Document doc = null;
		String result = "";
			try {
				doc = DocumentHelper.parseText(xml); // 将字符串转为XML
				Element root = doc.getRootElement(); // 获取根节点
				Element success = root.element("CallSuccess");  
				String returnFlag = success.getTextTrim();
				if(null!=returnFlag){
					if("true".equals(returnFlag)){
						Element eName = root.element("CreatedExpress").element("Epcode");  
						result=eName.getTextTrim();
					}else{
						Element eName = root.element("Response").element("ReasonMessage");  
						result=eName.getTextTrim();
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
		return result;
	}		
	public static void main(String[] args) {
//		System.out.println(test());
		String xml = "<?xml version='1.0' encoding='utf-8'?><CreateExpressResponseType xmlns:xsd='http://www.w3.org/2001/XMLSchema' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'><CallSuccess>true</CallSuccess><CreatedExpress><Epcode>TE190876082ST</Epcode><Userid>100000</Userid><ChannelType><Id>154</Id><Name>中邮北京挂号小包</Name><Status>true</Status></ChannelType><Channel>中邮北京挂号小包</Channel><Package>无</Package><UserOrderNumber/><SendDate>2014-07-12T00:00:00</SendDate><Receiver><Userid>100000</Userid><Name>tang</Name><Phone>13113131313 13922222222</Phone><Mobile>13113131313 13922222222</Mobile><Email>jpcn@mpc.com.br</Email><Company/><Country>AUSTRALIA</Country><Postcode>ukh 101</Postcode><State>tang</State><City>澳大利亚</City><Address1>ukh 101</Address1><Address2>tang</Address2></Receiver><Quantity>3</Quantity><GoodsName><Id>0</Id><Userid>100000</Userid><NameCh>多媒体播放器</NameCh><NameEn>MP3</NameEn><Weight>222</Weight><DeclaredValue>125</DeclaredValue><DeclaredCurrency>USD</DeclaredCurrency><MoreGoodsName/><HsCode/></GoodsName><ReferenceNo/><PackageNo/><Insure>false</Insure><Memo/><TrackingStatus>暂无信息</TrackingStatus><IsPrint>false</IsPrint><CreateDate>2014-07-25T00:00:00+08:00</CreateDate></CreatedExpress><Response><Userid>100000</Userid><Operation>Create</Operation><Success>true</Success><Reason>None</Reason><ReasonMessage>没有错误</ReasonMessage></Response></CreateExpressResponseType>";
		String xml2 = "<?xml version='1.0' encoding='utf-8'?><CreateExpressResponseType xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:xsd='http://www.w3.org/2001/XMLSchema'><CallSuccess>false</CallSuccess><Response><Userid>100000</Userid><Operation>Create</Operation><Success>false</Success><Reason>V101</Reason><ReasonMessage>渠道不正确</ReasonMessage></Response></CreateExpressResponseType>";
		String result = getTrackingNoFromXml(xml2);
		System.out.println(result);
	}
}
