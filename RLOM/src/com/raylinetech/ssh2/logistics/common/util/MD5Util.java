package com.raylinetech.ssh2.logistics.common.util;

/*
 * @(#)MD5Util.java	1.00 2012-3-7 12:31:07
 * 
 * Team: 
 * Copyright (C) 2012 
 * Use is subject to license terms. 
 */
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author <a href="mailto:sunzhanhu@163.com">sunzhanhu</a>
 * @version 1.00
 */

public class MD5Util {
	/**
	 * @param str
	 *            the string will be encoded by MD5
	 * @return
	 */
	// public MD5(String str) {
	// this.str = str;
	// }
	/**
	 * @param str
	 *            the string will be encoded by MD5
	 * @return
	 */
	public static String enCode(String str) {
		try {
			// ���������к��壬������Ե�ʱ�����ײ鿴
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes());
			byte[] digest = messageDigest.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String hashtext = bigInt.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "Wrong!";
	}

	/**
	 * get the verifyCode
	 * @return 
	 */
	public static String getVerifyCode(String name, String password) {
		return MD5Util.enCode(name + ":xx:" + password);
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Util.getVerifyCode("malei", "123123"));
	}
}
