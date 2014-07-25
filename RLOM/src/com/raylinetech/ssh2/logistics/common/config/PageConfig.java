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
	private static List<String> hudieList;
	private static List<String> hudieAccountList;
	private static List<String> kouziList;
	private static List<String> kouziAccountList;
	private static List<String> zhuziList;

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
		guojiaMap.put("AUS", "澳大利亚");
		guojiaMap.put("AUSTRLIA", "澳大利亚");
		
		currencyMap = new HashMap<String, String>();
		currencyMap.put("英国", "GBP");
		currencyMap.put("法国", "EUR");
		currencyMap.put("美国", "USD");
		currencyMap.put("澳大利亚", "AUD");

		skuTempMap = new HashMap<String, String>();
		skuTempMap.put("BAG", "CBJX11201Y");
		skuTempMap.put("BLACK BLACKBOARD", "CBJZ11HBT910hei");
		skuTempMap.put("091BLACK", "CBJZ11HBT910hei");
		skuTempMap.put("091HEI", "CBJZ11HBT910hei");
		skuTempMap.put("CBJZ11910HEI", "CBJZ11HBT910hei");
		skuTempMap.put("01A", "CTJZ21R01A");
		skuTempMap.put("001A", "CTJZ21R01A");
		skuTempMap.put("02B", "CTJZ21R01B");
		skuTempMap.put("002B", "CTJZ21R01B");
		skuTempMap.put("PR321 POVOS", "PR321");
		skuTempMap.put("PW326", "PR326");
		skuTempMap.put("PS1086W", "PS1086");
		skuTempMap.put("CBJZ11001LAN", "CBJZ11AM001lan");
		skuTempMap.put("BLACKBOARD001", "CBJZ11HBT910hei");
		skuTempMap.put("LYTZTEYE96", "PS5301");
		skuTempMap.put("091GREEN", "CBJZ11HBT910lv");
		skuTempMap.put("QPUY5I3T5I", "PR322");
		skuTempMap.put("0IYWCG7QGQ", "PR3017");

		amazonList = new ArrayList<String>();
		amazonList.add("CBJZ11HBT910HEI");
		amazonList.add("CBJZ11AM001FEN");
		amazonList.add("CBJZ11AM001LAN");
		amazonList.add("CBJZ11AM001LV");
		amazonList.add("CBJZ11AM001ZI");
		amazonList.add("CBJX11201Y");
		amazonList.add("CBJZ11AM003");
		amazonList.add("CBJX11211B");
		amazonList.add("CSZW111208101063");
		amazonList.add("CBJZ11AM824");
		amazonList.add("CBJX11197G");
		amazonList.add("CBJX11197B");
		amazonList.add("CSZW111208100801");
		amazonList.add("CSZW111208101027");
		amazonList.add("CBJX11204G");
		amazonList.add("CBJX11207P");
		amazonList.add("CBJX11202P");
		amazonList.add("CBJX11211D");

		kouziList = new ArrayList<String>();
		kouziList.add("CTJZ21R01A");
		kouziList.add("CTJZ21R01B");
		kouziList.add("CTJZ21R01C");
		kouziList.add("CTJZ21R01D");
		kouziList.add("CTJZ21R01E");

		povosList = new ArrayList<String>();

		zhuziList = new ArrayList<String>();
		zhuziList.add("CTJT11A173");
		zhuziList.add("CTJT11A174");
		zhuziList.add("CTJT11A175");
		zhuziList.add("CTJT11A176");
		zhuziList.add("CTJT11PJ001");
		zhuziList.add("CTJT11PJ002");
		zhuziList.add("CTJT11PJ003");
		zhuziList.add("CTJT11PJ004");
		zhuziList.add("CTJT11PJ005");
		zhuziList.add("CTJT11PJ006");
		zhuziList.add("CTJT11PJ007");
		zhuziList.add("CTJT11PJ008");
		zhuziList.add("CTJT11PJ009");
		zhuziList.add("CTJT11PJ010");
		zhuziList.add("CTJT11PJ011");
		zhuziList.add("CTJT11PJ012");
		zhuziList.add("CTJT11PJ013");
		zhuziList.add("CTJT11PJ014");
		zhuziList.add("CTJT11PJ015");
		zhuziList.add("CTJT11PJ016");
		zhuziList.add("CTJT11PJ017");
		zhuziList.add("CTJT11PJ018");
		zhuziList.add("CTJT11PJ019");
		zhuziList.add("CTJT11PJ020");
		zhuziList.add("CTJT11PJ021");
		zhuziList.add("CTJT11PJ022");
		zhuziList.add("CTJT11PJ023");
		zhuziList.add("CTJT11PJ024");
		zhuziList.add("CTJT11PJ025");
		zhuziList.add("CTJT11PJ026");
		zhuziList.add("CTJT11EU35002");
		zhuziList.add("CTJT11EU35004");
		zhuziList.add("CTJT11EU35005");
		zhuziList.add("CTJT11EU35009");
		zhuziList.add("CTJT11EU35017");
		zhuziList.add("CTJT11EU35018");
		zhuziList.add("CTJT11EU35028");
		zhuziList.add("CTJT11EU35031");
		zhuziList.add("CTJT11EU35033");
		zhuziList.add("CTJT11EU35049");
		zhuziList.add("CTJT11EU35098");
		zhuziList.add("CTJT11EU35006");
		zhuziList.add("CTJT11EU30012");
		zhuziList.add("CTJT11EU35029");
		zhuziList.add("CTJT11EU35044");
		zhuziList.add("CTJT11EU35105");
		zhuziList.add("CTJT11EU35082");
		zhuziList.add("CTJT11EU35104");
		zhuziList.add("CTJT11EU35058");
		zhuziList.add("CTJT11EU35065");
		zhuziList.add("CTJT11EU35068");
		zhuziList.add("CTJT11EU35071");
		zhuziList.add("CTJT11EU35079");
		zhuziList.add("CTJT11EU30021");
		zhuziList.add("CTJT11EU5060");
		zhuziList.add("CTJT11EU35056");
		zhuziList.add("CTJT11EU35057");
		zhuziList.add("CTJT11EU35106");
		zhuziList.add("CTJT11EU35087");
		zhuziList.add("CTJT11EU35100");
		zhuziList.add("CTJT11EU35099");
		zhuziList.add("CTJT11EU35000");
		zhuziList.add("CTJT11EU35081");
		zhuziList.add("CTJT11EU35089");
		zhuziList.add("CTJT11EU35090");
		zhuziList.add("CTJT11EU35059");
		zhuziList.add("CTJT11EU35077");
		zhuziList.add("CTJT11EU35062");
		zhuziList.add("CTJT11EU35069");
		zhuziList.add("CTJT11EU35070");
		zhuziList.add("CTJT11EU35097");
		zhuziList.add("CTJT11EU35096");
		zhuziList.add("CTJT11EU35107");
		zhuziList.add("CTJT11EU35072");
		zhuziList.add("CTJT11EU35073");
		zhuziList.add("CTJT11EU35108");
		zhuziList.add("CTJT11EU35063");
		zhuziList.add("CTJT11EU35093");
		zhuziList.add("CTJT11EU30024");
		zhuziList.add("CTJT11EU35094");
		zhuziList.add("CTJT11EU35095");
		zhuziList.add("CTJT11EU35076");
		zhuziList.add("CTJT11EU35034");
		zhuziList.add("CTJT11EU35074");
		zhuziList.add("CTJT11EU35091");
		zhuziList.add("CTJT11EU35085");
		zhuziList.add("CTJT11EU35086");
		zhuziList.add("CTJT11EU35092");
		zhuziList.add("CTJT11EU35066");
		zhuziList.add("CTJT11EU35055");
		zhuziList.add("CTJT11EU35061");
		zhuziList.add("CTJT11EU35067");
		zhuziList.add("CTJT11EU35101");
		zhuziList.add("CTJT11EU35102");
		zhuziList.add("CTJT11EU35103");
		zhuziList.add("CTJT11EU35075");
		zhuziList.add("CTJT11EU35078");
		zhuziList.add("CTJT11EU35080");
		zhuziList.add("CTJT11EU35064");
		zhuziList.add("CTJT11EU35083");
		zhuziList.add("CTJT11EU35084");
		zhuziList.add("CTJT11A355");
		zhuziList.add("CTJT11A913");
		zhuziList.add("CTJT11A917");
		zhuziList.add("CTJT11A920");
		zhuziList.add("CTJT11A1407");
		
		benList = new ArrayList<String>();
		benList.add("CBJZ11AM999T");

		zhijiaList = new ArrayList<String>();
		zhijiaList.add("CTJZ21XF863");
		zhijiaList.add("CTJZ21XF826");
		zhijiaList.add("CTJZ21XF809");
		zhijiaList.add("CTJZ21XF815");
		zhijiaList.add("CTJZ21XF816");
		zhijiaList.add("CTJZ21XF833");
		zhijiaList.add("CTJZ21XF859");
		zhijiaList.add("CTJZ21XF872");
		zhijiaList.add("CTJZ21XF874");
		zhijiaList.add("CTJZ21XF576");
		zhijiaList.add("CTJZ21XF567");
		zhijiaList.add("CTJZ21XF184");
		zhijiaList.add("CTJZ21XF202");
		zhijiaList.add("CTJZ21XF281");
		zhijiaList.add("CTJZ21XF553");
		zhijiaList.add("CTJZ21XF199");
		zhijiaList.add("CTJZ21XF615");
		zhijiaList.add("CTJZ21XF456");
		zhijiaList.add("CTJZ21XF454");
		zhijiaList.add("CTJZ21XF442");
		zhijiaList.add("CTJZ21XF450");
		zhijiaList.add("CTJZ21XF746");
		zhijiaList.add("CTJZ21XF602");
		zhijiaList.add("CTJZ21XF707");
		zhijiaList.add("CTJZ21XF722");
		zhijiaList.add("CTJZ21XF727");
		zhijiaList.add("CTJZ21M005");
		zhijiaList.add("CTJZ21M021");
		zhijiaList.add("CTJZ21M030");
		zhijiaList.add("CTJZ21XF1288");
		zhijiaList.add("CTJZ21XF1213");
		zhijiaList.add("CTJZ21XF1319");
		zhijiaList.add("CTJZ21XF1271");
		zhijiaList.add("CTJZ21XF1212");
		zhijiaList.add("CTJZ21XF1348");
		zhijiaList.add("CTJZ21XF1266");
		zhijiaList.add("CTJZ21XF1268");
		zhijiaList.add("CTJZ21XF1262");
		zhijiaList.add("CTJZ21XF1280");
		zhijiaList.add("CTJZ21XF1294");
		zhijiaList.add("CTJZ21XF6015");
		zhijiaList.add("CTJZ21XF6016");
		zhijiaList.add("CTJZ21XF6024");
		zhijiaList.add("CTJZ21XF6048");
		zhijiaList.add("CTJZ21XF6059");
		zhijiaList.add("CTJZ21XF6064");
		zhijiaList.add("CTJZ21XF6065");
		zhijiaList.add("CTJZ21XF6066");
		zhijiaList.add("CTJZ21XF458S");
		zhijiaList.add("CTJZ21XF6072");
		zhijiaList.add("CTJZ21XF6069");
		zhijiaList.add("CTJZ21XF6061");
		zhijiaList.add("CTJZ21HL209");
		zhijiaList.add("CTJZ21HL211");
		zhijiaList.add("CTJZ21HL218");
		zhijiaList.add("CTJZ21HL227");
		zhijiaList.add("CTJZ21HL230W");
		zhijiaList.add("CTJZ21XF460");
		zhijiaList.add("CTJZ21XF468");
		zhijiaList.add("CTJZ21XF467");
		zhijiaList.add("CTJZ21XF457B");

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

		hudieList = new ArrayList<String>();
		hudieList.add("CTJZ21WS01P");
		hudieList.add("CTJZ21WS01B");
		hudieList.add("CTJZ21WS02R");
		hudieList.add("CTJZ21WS02W");
		hudieList.add("CTJZ21WS02B");
		hudieList.add("CBJZ11WS03B");
		hudieList.add("CBJZ11WS03W");
		hudieList.add("CBJZ11WS03R");
		hudieList.add("CBJZ11ABSWS03WH");
		hudieList.add("CBJZ11ABSWS03BLA");
		hudieList.add("CBJZ11ABSWS03PI");
		hudieList.add("CBJZ11ABSWS03RO");
		hudieList.add("CBJZ11ABSWS03RE");
		hudieList.add("CBJZ11ABSWS03OR");
		hudieList.add("CBJZ11ABSWS03BLU");
		hudieList.add("CBJZ11ABSWS03CO");
		hudieList.add("CBJZ11ABSWS03YE");
		hudieList.add("CBJZ11ABSWS03GR");
		hudieList.add("CBJZ11ABSWS03PU");
		hudieList.add("CBJZ11WS03RED");
		hudieList.add("CBJZ11WS03BLUE");
		hudieList.add("CBJZ11WS03YELL");

		hudieAccountList = new ArrayList<String>();
		hudieAccountList.add("DAVID UK");
		hudieAccountList.add("DAVID US");
		hudieAccountList.add("POVOS UK");
		hudieAccountList.add("POVOS US");
		hudieAccountList.add("BEN UK");
		hudieAccountList.add("WZFS");
		hudieAccountList.add("MIKE88283");
		hudieAccountList.add("PERSONALCARE12");

		kouziAccountList = new ArrayList<String>();
		kouziAccountList.add("DAVID UK");
		kouziAccountList.add("DAVID US");
		kouziAccountList.add("POVOS UK");
		kouziAccountList.add("POVOS US");
		kouziAccountList.add("BEN UK");
		kouziAccountList.add("WZFS");
		kouziAccountList.add("PERSONALCARE12");
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

	public static boolean isInhudieList(String skuno) {
		return hudieList.contains(skuno);
	}

	public static boolean isInhudieAccountList(String skuno) {
		return hudieAccountList.contains(skuno);
	}

	public static boolean isInkouziAccountList(String skuno) {
		return kouziAccountList.contains(skuno);
	}

	public static boolean isInkouziList(String skuno) {
		return kouziList.contains(skuno);
	}
	public static boolean isInzhuziList(String skuno) {
		return zhuziList.contains(skuno);
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