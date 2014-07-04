package com.raylinetech.ssh2.logistics.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author malei
 * 
 */
public class DateUtil {
	
    public static final String TIMEFORMAT = "HHmmss";
    public static final String DATEFORMAT= "yyyyMMdd";
    public static final String DATEFORMAT_YYMMDD= "yyMMdd";
    
    public static final String DATETIMEFORMAT = DATEFORMAT + TIMEFORMAT;
    public static String getCurrentTimeString(String format, long mills) {
        SimpleDateFormat simpleFormat = new SimpleDateFormat(format);
		return simpleFormat.format(new Date(mills));
    }
    
    public static String getYesterDayEvening() {
    	String yyyyMMdd = getCurrentTimeString(DATEFORMAT, System.currentTimeMillis()-(24*60*60*1000));
        return yyyyMMdd + "210000";
    }
    
    public static String getCurrentFormatTime(String format) {
        return getCurrentTimeString(format, System.currentTimeMillis());
    }
    
    /**
     * get current time format yyyyMMddHHmmss
     */
    public static String yyyyMMddHHmmss() {
        return getCurrentTimeString(DATETIMEFORMAT, System.currentTimeMillis());
    }
    
    /** 
     * get current time format yyyyMMdd
     */
    public static String yyyyMMdd() {
        return getCurrentTimeString(DATEFORMAT, System.currentTimeMillis());
    }
    
    /** 
     * get current time format yyyyMMdd
     */
    public static String yyMMdd() {
        return getCurrentTimeString(DATEFORMAT_YYMMDD, System.currentTimeMillis());
    }
    /**
     * get target miles formatted as yyyyMMdd
     * @param mills 
     * @return String date
     */
    public static String getFormatedDate(long mills) {
        return getCurrentTimeString(DATEFORMAT, mills);
    }
    
    /**
     * get target miles formatted as yyyyMMdd
     * @param mills 
     * @return String time
     */
    public static String getFormatedTime(long mills) {
        return getCurrentTimeString(DATETIMEFORMAT, mills);
    }
    
    public static String reFormatDate(String dateString, String format1,String format2){
		SimpleDateFormat format = new SimpleDateFormat(format1);
		Date date=null;
		String sdate= "";
		try {
			date = format.parse(dateString);
			sdate = new SimpleDateFormat(format2).format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return sdate;
    }
    
    public static void main(String[] args) {
//		String ab = "123/545;123124124:123123";
//		String c = ab.substring(0,ab.indexOf("/"));
//		String d = ab.substring(ab.indexOf("/")+1,ab.length());
//		System.out.println(c);
//		System.out.println(d);
    	System.out.println(yyMMdd());
    	System.out.println(reFormatDate("20020124", "yyyyMMdd", "yy-MM-dd"));
	}
}
