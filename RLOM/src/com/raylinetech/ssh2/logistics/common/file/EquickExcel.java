package com.raylinetech.ssh2.logistics.common.file;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;
import com.raylinetech.ssh2.logistics.common.util.StringUtil;

public class EquickExcel extends ExcelModel {

	public static int COLUMN_NUMBER = 25;
	public static final int EQUICK = 1;
	public static final String DEFAULT_CUSTOMERNO = "PEK130011";
	public static final String DEFAULT_STATENAME = "";
	public static final String DEFAULT_EMAIL = "";
	public static final String DEFAULT_SHIPPINGMETHORD = "小包";

	public EquickExcel(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			this.generateHeader();
			this.generateEquick(rlOrders);
		}
	}

	private void generateEquick(List<RLOrder> rlOrders) {
		if (null != rlOrders && rlOrders.size() != 0) {
			int rowCount = rlOrders.size();
			this.data = new Object[EquickExcel.COLUMN_NUMBER][rowCount];
			int rowNumber = 0;
			for (RLOrder p : rlOrders) {
				this.data[0][rowNumber] = "GB";
				this.data[1][rowNumber] = EquickExcel.DEFAULT_STATENAME;
				if(p.getGuojia().equals("美国")){
					this.data[0][rowNumber] = "US";
					String st = this.getShortState(p.getShipstate());
					this.data[1][rowNumber] = st;
					System.out.println("meiguomeiguomeiguo   "+st);
				}
				//如果shipstate为空，则将shipcity写入city栏里
				if(null==p.getShipstate()||"".equals(p.getShipstate().trim())){
					this.data[2][rowNumber] = p.getShipcity();
				}else{
					this.data[2][rowNumber] = p.getShipstate();
				}
//				this.data[2][rowNumber] = p.getShipstate();
				this.data[3][rowNumber] = this.getEquickAddress(p.getShipaddress1(),
						p.getShipaddress2(), p.getShipcity());
				this.data[4][rowNumber] = p.getBuyername();
				this.data[5][rowNumber] = p.getBuyername();
				this.data[6][rowNumber] = p.getPostalcode();
				this.data[7][rowNumber] = StringUtil.getNumberFromPhone(p.getBuyerphonenumber());
				this.data[8][rowNumber] = EquickExcel.DEFAULT_EMAIL;
				this.data[16][rowNumber] = "";
				this.data[17][rowNumber] = "";
				this.data[18][rowNumber] = "";
				this.data[19][rowNumber] = "";
				this.data[20][rowNumber] = p.getOrdernumber();
				this.data[21][rowNumber] = p.getTrackingno();
				this.data[22][rowNumber] = p.getLogistics().getAccount();
				this.data[23][rowNumber] = p.getRlordernumber();
				this.data[24][rowNumber] = EquickExcel.DEFAULT_SHIPPINGMETHORD;

				/* 货物详细 */
				// 货物申报币种
				this.data[13][rowNumber] = "USD";
				int quantity = 0;
				double totalPrice = 0.0;
				double totalWeight = 0.0;
				if(null != p.getRlorderitems() && 0!=p.getRlorderitems().size()){
					// 英文名称
					this.data[9][rowNumber] = p.getRlorderitems().get(0).getSku().getName();
					// 中文名称
					this.data[10][rowNumber] = p.getRlorderitems().get(0).getSku().getPinming();
					// SKU
					this.data[11][rowNumber] = p.getRlorderitems().get(0).getSku().getSkuno();
					List<RLOrderItem> products = p.getRlorderitems();
					for (RLOrderItem l : products) {
						// 申报价值
						quantity = quantity+Integer.parseInt(l.getQuantity());
						totalPrice= Double.parseDouble(StringUtil
								.getDoubleFromAmount(p.getAmount())) * 0.3 + totalPrice;
						// 实际重量
						totalWeight = Double.parseDouble(l.getQuantity()) * 0.25 + totalWeight;
					}
				}
				DecimalFormat df = new DecimalFormat("#.##");
				this.data[12][rowNumber] = df.format(totalPrice);
				// 货物件数
				this.data[14][rowNumber] = quantity;
				// 实际重量
				this.data[15][rowNumber] = df.format(totalWeight);
				rowNumber = rowNumber + 1;
			}
		}
	}

	private String getShortState(String shipstate) {
		String st = "";
		if(shipstate.trim().equalsIgnoreCase("ALABAMA")){st="AL";}
		if(shipstate.trim().equalsIgnoreCase("ALASKA")){st="AK";}
		if(shipstate.trim().equalsIgnoreCase("AMERICANSAMOA")){st="AS";}
		if(shipstate.trim().equalsIgnoreCase("ARIZONA")){st="AZ";}
		if(shipstate.trim().equalsIgnoreCase("ARKANSAS")){st="AR";}
		if(shipstate.trim().equalsIgnoreCase("CALIFORNIA")){st="CA";}
		if(shipstate.trim().equalsIgnoreCase("COLORADO")){st="CO";}
		if(shipstate.trim().equalsIgnoreCase("CONNECTICUT")){st="CT";}
		if(shipstate.trim().equalsIgnoreCase("DELAWARE")){st="DE";}
		if(shipstate.trim().equalsIgnoreCase("DISTRICTOFCOLUMBIA")){st="DC";}
		if(shipstate.trim().equalsIgnoreCase("FEDERATEDSTATESOFMICRONESIA")){st="FM";}
		if(shipstate.trim().equalsIgnoreCase("FLORIDA")){st="FL";}
		if(shipstate.trim().equalsIgnoreCase("GEORGIA")){st="GA";}
		if(shipstate.trim().equalsIgnoreCase("GUAM")){st="GU";}
		if(shipstate.trim().equalsIgnoreCase("HAWAII")){st="HI";}
		if(shipstate.trim().equalsIgnoreCase("IDAHO")){st="ID";}
		if(shipstate.trim().equalsIgnoreCase("ILLINOIS")){st="IL";}
		if(shipstate.trim().equalsIgnoreCase("INDIANA")){st="IN";}
		if(shipstate.trim().equalsIgnoreCase("IOWA")){st="IA";}
		if(shipstate.trim().equalsIgnoreCase("KANSAS")){st="KS";}
		if(shipstate.trim().equalsIgnoreCase("KENTUCKY")){st="KY";}
		if(shipstate.trim().equalsIgnoreCase("LOUISIANA")){st="LA";}
		if(shipstate.trim().equalsIgnoreCase("MAINE")){st="ME";}
		if(shipstate.trim().equalsIgnoreCase("MARSHALLISLANDS")){st="MH";}
		if(shipstate.trim().equalsIgnoreCase("MARYLAND")){st="MD";}
		if(shipstate.trim().equalsIgnoreCase("MASSACHUSETTS")){st="MA";}
		if(shipstate.trim().equalsIgnoreCase("MICHIGAN")){st="MI";}
		if(shipstate.trim().equalsIgnoreCase("MINNESOTA")){st="MN";}
		if(shipstate.trim().equalsIgnoreCase("MISSISSIPPI")){st="MS";}
		if(shipstate.trim().equalsIgnoreCase("MISSOURI")){st="MO";}
		if(shipstate.trim().equalsIgnoreCase("MONTANA")){st="MT";}
		if(shipstate.trim().equalsIgnoreCase("NEBRASKA")){st="NE";}
		if(shipstate.trim().equalsIgnoreCase("NEVADA")){st="NV";}
		if(shipstate.trim().equalsIgnoreCase("NEWHAMPSHIRE")){st="NH";}
		if(shipstate.trim().equalsIgnoreCase("NEWJERSEY")){st="NJ";}
		if(shipstate.trim().equalsIgnoreCase("NEWMEXICO")){st="NM";}
		if(shipstate.trim().equalsIgnoreCase("NEWYORK")){st="NY";}
		if(shipstate.trim().equalsIgnoreCase("NORTHCAROLINA")){st="NC";}
		if(shipstate.trim().equalsIgnoreCase("NORTHDAKOTA")){st="ND";}
		if(shipstate.trim().equalsIgnoreCase("NORTHERNMARIANAISLANDS")){st="MP";}
		if(shipstate.trim().equalsIgnoreCase("OHIO")){st="OH";}
		if(shipstate.trim().equalsIgnoreCase("OKLAHOMA")){st="OK";}
		if(shipstate.trim().equalsIgnoreCase("OREGON")){st="OR";}
		if(shipstate.trim().equalsIgnoreCase("PALAU")){st="PW";}
		if(shipstate.trim().equalsIgnoreCase("PENNSYLVANIA")){st="PA";}
		if(shipstate.trim().equalsIgnoreCase("PUERTORICO")){st="PR";}
		if(shipstate.trim().equalsIgnoreCase("RHODEISLAND")){st="RI";}
		if(shipstate.trim().equalsIgnoreCase("SOUTHCAROLINA")){st="SC";}
		if(shipstate.trim().equalsIgnoreCase("SOUTHDAKOTA")){st="SD";}
		if(shipstate.trim().equalsIgnoreCase("TENNESSEE")){st="TN";}
		if(shipstate.trim().equalsIgnoreCase("TEXAS")){st="TX";}
		if(shipstate.trim().equalsIgnoreCase("UTAH")){st="UT";}
		if(shipstate.trim().equalsIgnoreCase("VERMONT")){st="VT";}
		if(shipstate.trim().equalsIgnoreCase("VIRGINISLANDS")){st="VI";}
		if(shipstate.trim().equalsIgnoreCase("VIRGINIA")){st="VA";}
		if(shipstate.trim().equalsIgnoreCase("WASHINGTON")){st="WA";}
		if(shipstate.trim().equalsIgnoreCase("WESTVIRGINIA")){st="WV";}
		if(shipstate.trim().equalsIgnoreCase("WISCONSIN")){st="WI";}
		if(shipstate.trim().equalsIgnoreCase("WYOMING")){st="WY";}
		if(shipstate.trim().length()==2){st=shipstate.trim();}
		return st;
	}

	private void generateHeader() {
		this.header = new LinkedList<String>();
		this.header.add("国家代码");
		this.header.add("州名");
		this.header.add("城市");
		this.header.add("收货地址");
		this.header.add("收货公司名称");
		this.header.add("收货人");
		this.header.add("邮编");
		this.header.add("电话");
		this.header.add("电子邮件");
		this.header.add("货物英文名称");
		this.header.add("货物中文名称");
		this.header.add("SKU");
		this.header.add("货物申报价值");
		this.header.add("货物申报币种");
		this.header.add("货物件数");
		this.header.add("实际重量");
		this.header.add("长(CM)");
		this.header.add("宽(CM)");
		this.header.add("高(CM)");
		this.header.add("其它说明");
		this.header.add("代理单号");
		this.header.add("EQUICK单号");
		this.header.add("客户编号");
		this.header.add("客户参考号码");
		this.header.add("运输方式");
	}


	private String getEquickAddress1(String shipAdress1, String shipAdress2,
			String shipCity) {
		String address = "";
		if (null != shipAdress1 && !"".equals(shipAdress1)) {
			address = address + shipAdress1;
		}
		if (null != shipAdress2 && !"".equals(shipAdress2)) {
			address = address + ", " + shipAdress2;
		}
		if (null != shipCity && !"".equals(shipCity)) {
			address = address + ", " + shipCity;
		}
		// 获取截取出来的adress
		int[] flag = { 0, 0, 0, 0, 0 };
		String[] line = { "", "", "", "" };
		String tempString = address;
		for (int i = 0; i < line.length; i++) {
			// 根据条件获取flag[i+1]
			if (i < 2) {
				flag[i + 1] = this.getPostLine(18, tempString);
			} else {
				flag[i + 1] = this.getPostLine(13, tempString);
			}
			// 将flag[i+1] 与flag[i]相加，得到字符串的endIndex
			flag[i + 1] = flag[i + 1] + flag[i];
			// 如果endindex > startindex,则直接截取字符串
			if (flag[i + 1] > flag[i]) {
				line[i] = address.substring(flag[i], flag[i + 1]);

			}
			// 如果相等，则从flag[i+1]开始，截取后边所有的字符
			else if (flag[i + 1] == flag[i]) {
				line[i] = address.substring(flag[i + 1]);
				break;
			}
			// 如果小于，则证明返回了-1，此列没有字符
			else {
				line[i] = "";
			}
			tempString = address.substring(flag[i + 1]);
		}
		return line[0] + "\r\n" + line[1] + "\r\n" + line[2] + "\r\n" + line[3];
	}

	/**
	 * case 1 如果ts > length
	 * 
	 * @param length
	 * @param targetString
	 * @return
	 */
	private int getPostLine(int length, String targetString) {
		if (null == targetString || 0 == targetString.length()) {
			return -1;
		}

		else if (targetString.length() > 0 && targetString.length() <= length) {
			return 0;
		}

		else {
			char char20 = targetString.charAt(length - 1);
			if (" ".equals(char20) || ",".equals(char20)) {
				return length;
			} else {
				String sub20 = targetString.substring(0, length);
				int lastSpace = sub20.lastIndexOf(" ");
				int lastComma = sub20.lastIndexOf(",");
				int lastUnchar = Math.max(lastSpace, lastComma);
				if (-1 != lastUnchar) {
					return lastUnchar + 1;
				} else {
					System.out.println("error , long world , please input");
					return length;
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 共4行前两行不超过40个字符，后两行不超过30个字符
	 * 先截取20个字符，如果超出则返回之前的一个，确定第一行
	 * 再截取前40个字符，如果超出则返回之前的一个，确定第二行
	 * 再截取前55个字符
	 * 再截取剩下的
	 * @param shipAdress1
	 * @param shipAdress2
	 * @param shipCity
	 * @return
	 */
	public String getEquickAddress(String shipAdress1, String shipAdress2,
			String shipCity) {
		String address = "";
		if (null != shipAdress1 && !"".equals(shipAdress1)) {
			address = address + shipAdress1;
		}
		if (null != shipAdress2 && !"".equals(shipAdress2)) {
			address = address + ", " + shipAdress2;
		}
		if (null != shipCity && !"".equals(shipCity)) {
			address = address + ", " + shipCity;
		}
		// 获取截取出来的adress
		int[] flag = {0, 0, 0, 0, 0 };
		String[] line = { "", "", "", "" };
		flag[1] = this.getPostLine(20, address);
		flag[2] = this.getPostLine(40, address);
		flag[3] = this.getPostLine(55, address);
		flag[4] = this.getPostLine(70, address);
		StringBuilder sb= new StringBuilder();
		for (int i = 0; i < line.length; i++) {
			if(flag[i+1]==0){
				if(flag[i]!=0){
					if(i>0){
					line[i] = address.substring(flag[i]);
					sb.append(line[i].trim());
					}
				}else{
					//如果长度不超过20
					if(i==0){
						sb.append(address);
					}else{
						break;
					}
				}
			}else if(flag[i+1]==-1){
				break;
			}else{
				line[i] = address.substring(flag[i],flag[i+1]);
				sb.append(line[i].trim());
				sb.append("\r\n");
			}
			System.out.println(flag[i+1]);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String i = "Amazon Development ";
		String ii = "Amazon Development Centre London, 26 Glasshouse Yard,fasdfasdf asdfasdfasdf asdfaf";
		System.out.println(new EquickExcel(null).getEquickAddress(ii, "", ""));
	}
	
}
