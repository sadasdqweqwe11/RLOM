package com.raylinetech.ssh2.logistics.common.util;

public class ExpressPrint {
	public static String[] GetGroupGPY(String country) {
		String[] Result = new String[2];

		if (country.equals("美国")) {
			Result[0] = "挂11";
			Result[1] = "A";

		} else if (country.equals("英国")) {
			Result[0] = "挂12";
			Result[1] = "A";

		} else if (country.equals("俄罗斯")) {
			Result[0] = "挂13";
			Result[1] = "A";

		} else if (country.equals("巴西")) {
			Result[0] = "挂14";
			Result[1] = "A";

		} else if (country.equals("澳大利亚")) {
			Result[0] = "挂15";
			Result[1] = "A";

		} else if (country.equals("法国")) {
			Result[0] = "挂16";
			Result[1] = "A";

		} else if (country.equals("加拿大")) {
			Result[0] = "挂17";
			Result[1] = "A";

		} else if (country.equals("西班牙")) {
			Result[0] = "挂18";
			Result[1] = "A";

		} else if (country.equals("德国")) {
			Result[0] = "挂19";
			Result[1] = "A";

		} else if (country.equals("以色列")) {
			Result[0] = "挂20";
			Result[1] = "A";

		} else if (country.equals("瑞典")) {
			Result[0] = "挂21";
			Result[1] = "A";

		} else if (country.equals("挪威")) {
			Result[0] = "挂22";
			Result[1] = "A";

		} else if (country.equals("阿根廷")) {
			Result[0] = "挂23";
			Result[1] = "A";

		} else if (country.equals("乌克兰")) {
			Result[0] = "挂24";
			Result[1] = "A";

		} else {
			Result[0] = "挂1";
			Result[1] = "B";
		}
		return Result;
	}

	public static String GetGroupCN(String country) {
		String Result = "";
		if (country.equals("俄罗斯")) {
			Result = "序1";

		} else if (country.equals("美国")) {
			Result = "序2";

		} else if (country.equals("巴西")) {
			Result = "序3";

		} else if (country.equals("英国")) {
			Result = "序4";

		} else if (country.equals("瑞典")) {
			Result = "序5";

		} else if (country.equals("澳大利亚")) {
			Result = "序6";

		} else if (country.equals("阿根廷")) {
			Result = "序7";

		} else if (country.equals("乌克兰")) {
			Result = "序8";

		} else if (country.equals("日本")) {
			Result = "序9";

		} else if (country.equals("以色列")) {
			Result = "序10";

		} else if (country.equals("加拿大")) {
			Result = "序11";

		} else if (country.equals("挪威")) {
			Result = "序12";

		} else if (country.equals("西班牙")) {
			Result = "序13";

		} else if (country.equals("法国")) {
			Result = "序14";

		} else if (country.equals("德国")) {
			Result = "序15";

		} else if (country.equals("土耳其")) {
			Result = "序16";

		} else if (country.equals("意大利")) {
			Result = "序17";

		} else if (country.equals("芬兰")) {
			Result = "序18";

		} else if (country.equals("比利时")) {
			Result = "序19";

		} else if (country.equals("智利")) {
			Result = "序20";

		} else if (country.equals("克罗地亚")) {
			Result = "序21";

		} else if (country.equals("捷克")) {
			Result = "序22";

		} else if (country.equals("希腊")) {
			Result = "序23";

		} else if (country.equals("台湾")) {
			Result = "序24";

		} else if (country.equals("匈牙利")) {
			Result = "序25";

		} else if (country.equals("葡萄牙")) {
			Result = "序26";

		} else if (country.equals("爱尔兰")) {
			Result = "序27";

		} else if (country.equals("丹麦")) {
			Result = "序28";

		} else if (country.equals("荷兰")) {
			Result = "序29";

		} else if (country.equals("白俄罗斯")) {
			Result = "序30";

		} else if (country.equals("墨西哥")) {
			Result = "序31";

		} else if (country.equals("拉脱维亚")) {
			Result = "序32";

		} else if (country.equals("波兰")) {
			Result = "序33";

		} else if (country.equals("斯洛伐克")) {
			Result = "序34";

		} else if (country.equals("立陶宛")) {
			Result = "序35";

		} else if (country.equals("新加坡")) {
			Result = "序36";

		} else if (country.equals("奥地利")) {
			Result = "序37";

		} else if (country.equals("马耳他")) {
			Result = "序38";

		} else if (country.equals("爱沙尼亚")) {
			Result = "序39";

		} else if (country.equals("保加利亚")) {
			Result = "序40";

		} else {
			Result = "序41";
		}
		return Result;
	}

	public static String GetYPOSTP(String country) {
		String Result = "";
		if (country.equals("美国")) {
			Result = "平1";

		} else if (country.equals("英国")) {
			Result = "平2";

		} else if (country.equals("澳大利亚")) {
			Result = "平3";

		} else if (country.equals("加拿大")) {
			Result = "平4";

		} else if (country.equals("巴西")) {
			Result = "平5";

		} else if (country.equals("俄罗斯")) {
			Result = "平6";

		} else if (country.equals("以色列")) {
			Result = "平7";

		} else if (country.equals("德国")) {
			Result = "平8";

		} else if (country.equals("法国")) {
			Result = "平9";

		} else if (country.equals("意大利")) {
			Result = "平10";

		} else if (country.equals("荷兰")) {
			Result = "平11";

		} else if (country.equals("西班牙")) {
			Result = "平12";

		} else if (country.equals("瑞典")) {
			Result = "平13";

		} else if (country.equals("阿根廷")) {
			Result = "平14";

		} else if (country.equals("挪威")) {
			Result = "平15";

		} else if (country.equals("捷克")) {
			Result = "平16";

		} else if (country.equals("丹麦")) {
			Result = "平17";

		} else if (country.equals("比利时")) {
			Result = "平18";

		} else if (country.equals("葡萄牙")) {
			Result = "平19";

		} else if (country.equals("爱尔兰")) {
			Result = "平20";
		}
		return Result;
	}

	public static void main(String[] args) {
		System.out.println(GetGroupGPY("英国")[0]);
	}
}