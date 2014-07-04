package com.raylinetech.ssh2.logistics.common.util;

import java.util.HashMap;
import java.util.Map;

public class StringUtil {


	
	public static String getDoubleFromAmount(String amount){
		StringBuilder sb = new StringBuilder();
        char[] chars = amount.toCharArray();  
        for(int i = 0; i < chars.length; i ++) {  
            if((chars[i] >= 48 && chars[i] <= 57)|| chars[i] == 46) {  
            	sb.append(chars[i]);
            }  
        }
        return sb.toString();
	}
	
	public static String getNumberFromPhone(String phoneNumber){
		StringBuilder sb = new StringBuilder();
        char[] chars = phoneNumber.toCharArray();  
        for(int i = 0; i < chars.length; i ++) {  
            if(chars[i] >= 48 && chars[i] <= 57) {  
            	sb.append(chars[i]);
            }  
        }
        String target=sb.toString();
        int len = sb.length();
        if(len>10){
        	target = sb.subSequence(len-10, len).toString();
        }
        return target;
        
	}
	
	/**
	 * symbol "-" "," or other 
	 * @param symbol
	 * @param args
	 * @return
	 */
	public static String linkString(String symbol,String... args) {
		String address = "";
		for (int i = 0; i < args.length; i++) {
			if(i==0){
				if (null != args[i] && !"".equals(args[i])) {
					address = address + args[i];
					}
			}else{
				if (null != args[i] && !"".equals(args[i])) {
					address = address +symbol + args[i];
					}
			}
		}
		//如果地址变态的超过了255个字符，则截取254位
		if(address.length()>255){
			address = address.substring(0, 254);
		}
		return address;
	}

	public static boolean validateNull(String data){
		boolean flag = true;
		if(null == data|| "".equals(data.toString().trim())){
			flag = false;
		}
		return flag;
	}
	
	public static void main(String[] args) {
//		System.out.println(getNumberFromPhone("(123)-4567-8901"));
//		System.out.println("12345678901".length());
//		System.out.println((int)(3.22));
//		
		System.out.println(linkString(", ","aba","lksdlfk",null,"laflaf"));
	}
}
