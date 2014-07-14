package com.raylinetech.ssh2.logistics.common.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageConfig {

	private static Map<String, String> guojiaMap;
	private static Map<String, String> currencyMap;
	private static Map<String, String> skuTempMap;

	private static List<String> amazonList;
	private static List<String> povosList;
	private static List<String> benList;
	private static List<String> zhijiaList;
	private static List<String> pijingList;
	
	
	static {
		guojiaMap = new HashMap<String, String>();
		guojiaMap.put("UK", "英国");
		guojiaMap.put("UNITED KINGDOM", "英国");
		guojiaMap.put("GB", "英国");
		guojiaMap.put("US", "美国");
		guojiaMap.put("UNITED STATE", "美国");
		guojiaMap.put("USA", "美国");
		guojiaMap.put("RUSSIA", "俄罗斯");
		guojiaMap.put("FR", "法国");
		currencyMap = new HashMap<String, String>();
		currencyMap.put("英国", "GBP");
		currencyMap.put("法国", "EUR");
		currencyMap.put("美国", "USD");

		skuTempMap = new HashMap<String, String>();
		skuTempMap.put("BAG", "CBJX11201-Y");
		skuTempMap.put("BLACK BLACKBOARD", "CBJZ11-HBT910hei");
		skuTempMap.put("091BLACK", "CBJZ11-HBT910hei");
		skuTempMap.put("091HEI", "CBJZ11-HBT910hei");
		skuTempMap.put("CBJZ11-910HEI", "CBJZ11-HBT910hei");
		skuTempMap.put("01A", "CTJZ21R01A");
		skuTempMap.put("001A", "CTJZ21R01A");
		skuTempMap.put("02B", "CTJZ21R01B");
		skuTempMap.put("002B", "CTJZ21R01B");
		skuTempMap.put("PR321 POVOS", "PR321");
		skuTempMap.put("PW326", "PR326");
		skuTempMap.put("PS1086W", "PS1086");
		skuTempMap.put("CBJZ11-001LAN", "CBJZ11-AM001lan");
		skuTempMap.put("BLACKBOARD001", "CBJZ11-HBT910hei");
		skuTempMap.put("LY-TZTE-YE96", "PS5301");
		skuTempMap.put("091GREEN", "CBJZ11-HBT910lv");
		skuTempMap.put("QP-UY5I-3T5I", "PR322");
		skuTempMap.put("0I-YWCG-7QGQ", "PR3017");

		amazonList = new ArrayList<String>();
		amazonList.add("CTJZ21R01A");
		amazonList.add("CTJZ21R01B");
		amazonList.add("CTJZ21R01C");
		amazonList.add("CTJZ21R01D");
		amazonList.add("CTJZ21R01E");
		amazonList.add("CBJZ11-HBT910HEI");
		amazonList.add("CBJZ11-AM001FEN");
		amazonList.add("CBJZ11-AM001LAN");
		amazonList.add("CBJZ11-AM001LV");
		amazonList.add("CBJZ11-AM001ZI");

		amazonList.add("CBJX11201-Y");
		amazonList.add("CBJZ11-AM003");
		amazonList.add("CBJX11211-B");
		amazonList.add("CSZW11-1208101063");
		amazonList.add("CBJZ11-AM824");
		amazonList.add("CBJX11197-G");
		amazonList.add("CBJX11197-B");
		amazonList.add("CSZW11-1208100801");
		amazonList.add("CSZW11-1208101027");
		amazonList.add("CBJX11204-G");
		amazonList.add("CBJX11207-P");
		amazonList.add("CBJX11202-P");
		amazonList.add("CBJX11211-D");

		
		povosList = new ArrayList<String>();

		benList = new ArrayList<String>();
		benList.add("CBJZ11-AM999T");

		zhijiaList = new ArrayList<String>();
		zhijiaList.add("CTJZ21-XF863");
		zhijiaList.add("CTJZ21-XF826");
		zhijiaList.add("CTJZ21-XF809");
		zhijiaList.add("CTJZ21-XF815");
		zhijiaList.add("CTJZ21-XF816");
		zhijiaList.add("CTJZ21-XF833");
		zhijiaList.add("CTJZ21-XF859");
		zhijiaList.add("CTJZ21-XF872");
		zhijiaList.add("CTJZ21-XF874");
		zhijiaList.add("CTJZ21-XF576");
		zhijiaList.add("CTJZ21-XF567");
		zhijiaList.add("CTJZ21-XF184");
		zhijiaList.add("CTJZ21-XF202");
		zhijiaList.add("CTJZ21-XF281");
		zhijiaList.add("CTJZ21-XF553");
		zhijiaList.add("CTJZ21-XF199");
		zhijiaList.add("CTJZ21-XF615");
		zhijiaList.add("CTJZ21-XF456");
		zhijiaList.add("CTJZ21-XF454");
		zhijiaList.add("CTJZ21-XF442");
		zhijiaList.add("CTJZ21-XF450");
		zhijiaList.add("CTJZ21-XF746");
		zhijiaList.add("CTJZ21-XF602");
		zhijiaList.add("CTJZ21-XF707");
		zhijiaList.add("CTJZ21-XF722");
		zhijiaList.add("CTJZ21-XF727");
		zhijiaList.add("CTJZ21-M005");
		zhijiaList.add("CTJZ21-M021");
		zhijiaList.add("CTJZ21-M030");
		zhijiaList.add("CTJZ21-XF1288");
		zhijiaList.add("CTJZ21-XF1213");
		zhijiaList.add("CTJZ21-XF1319");
		zhijiaList.add("CTJZ21-XF1271");
		zhijiaList.add("CTJZ21-XF1212");
		zhijiaList.add("CTJZ21-XF1348");
		zhijiaList.add("CTJZ21-XF1266");
		zhijiaList.add("CTJZ21-XF1268");
		zhijiaList.add("CTJZ21-XF1262");
		zhijiaList.add("CTJZ21-XF1280");
		zhijiaList.add("CTJZ21-XF1294");
		zhijiaList.add("CTJZ21-XF6015");
		zhijiaList.add("CTJZ21-XF6016");
		zhijiaList.add("CTJZ21-XF6024");
		zhijiaList.add("CTJZ21-XF6048");
		zhijiaList.add("CTJZ21-XF6059");
		zhijiaList.add("CTJZ21-XF6064");
		zhijiaList.add("CTJZ21-XF6065");
		zhijiaList.add("CTJZ21-XF6066");
		zhijiaList.add("CTJZ21-XF458S");
		zhijiaList.add("CTJZ21-XF6072");
		zhijiaList.add("CTJZ21-XF6069");
		zhijiaList.add("CTJZ21-XF6061");
		zhijiaList.add("CTJZ21-HL209");
		zhijiaList.add("CTJZ21-HL211");
		zhijiaList.add("CTJZ21-HL218");
		zhijiaList.add("CTJZ21-HL227");
		zhijiaList.add("CTJZ21-HL230W");
		zhijiaList.add("CTJZ21-XF460");
		zhijiaList.add("CTJZ21-XF468");
		zhijiaList.add("CTJZ21-XF467");
		zhijiaList.add("CTJZ21-XF457B");
		

		pijingList = new ArrayList<String>();
		pijingList.add("CTJZ21SS");
		pijingList.add("CTJZ21ROSE");
		pijingList.add("CTJZ21ORANGE");
		pijingList.add("CTJZ21BLACK");
		pijingList.add("CTJZ21YELLOW");
		pijingList.add("CTJZ21PINK");
		pijingList.add("CTJZ21WHITE");
		pijingList.add("CTJZ21PURPLE");
		pijingList.add("CTJZ21GREEN");
		pijingList.add("CTJZ21LAKEBLUE");
		pijingList.add("CTJZ21YGSS");
		pijingList.add("CTJZ21UVSS");
		pijingList.add("CTJZ21600BOX");
	}

	public static String getGuojia(String country) {
		return guojiaMap.get(country);
	}

	public static String getCurrency(String guojia) {
		return currencyMap.get(guojia);
	}

	public static String getTempSku(String skuno) {
		return skuTempMap.get(skuno);
	}

	public static boolean isInBenList(String skuno) {
		return benList.contains(skuno);
	}

	public static boolean isInPovosList(String skuno) {
		return povosList.contains(skuno);
	}

	public static boolean isInAmazonList(String skuno) {
		return amazonList.contains(skuno);
	}
	
	public static boolean isInZhijiaList(String skuno) {
		return zhijiaList.contains(skuno);
	}
	
	public static boolean isInpijingList(String skuno) {
		return pijingList.contains(skuno);
	}
	

	public static final int PAGESIZE = 30;
	public static final int OTHER_TOPIC_DISPLAY_NUMBER = 5;
	public static final String NO_TOPIC_FOUND = "没有找到相关文章，指定的文章不存在或链接错误";
	public static final String NOT_LOGIN = "您尚未登录，请先登录再操作";
	public static final String NO_RIGHT = "抱歉，您没有权限执行此操作";
	public static final String SIGNUP_ERROR = "抱歉，邮箱验证码错误，请重新注册";
	public static final String BOARD_REGISTER_ERROR = "抱歉，申请版主失败，请重新申请";
	public static final String NAME_PASSWORD_ERROR = "用户名或密码错误，请重新登录";
	public static final String NAME_UPLOAD_ERROR = "上传文件错误，请检查,错误原因可能为\n1 文件格式错误，请另存为excel\n2 文件内部错误，请检查";
	public static final String NAME_READAY_SIGNUP = "已注册成功";
	public static final String NOSIGNUP = "您还没有进行邮箱验证，验证邮件可能躺在垃圾箱中，请再在邮箱里找找吧";
	public static final String COOKIE_USERID = "xiaoquUserId";
	public static final String COOKIE_SESSIONID = "sessionId";
	public static final String MESSAGE_TOOLONG = "您一口气写的太多啦~！分两次发试试吧~";
	public static final String ORDERFILE_PATH = "/uploadfile/excel/";
	public static final String TRACKINGNOFILE_PATH = "/uploadfile/trackingno/";
	public static final String AMOUNTFILE_PATH = "/uploadfile/amount/";
	
	/* 记录是否拆单，不需要拆单0，系统拆单拆单1，系统合单2，手动拆单3，手动合单4，copy订单5 default 0 */
	public static final int SPLIT_SYS_SPLIT = 1;

	public static final int SPLIT_SYS_MERGE = 2;

	public static final int SPLIT_MT_SPLIT = 3;

	public static final int SPLIT_MT_MERGE = 4;

	public static final int SPLIT_COPY = 5;

	public static final int SPLIT_SYS_FIRST = 6;

}