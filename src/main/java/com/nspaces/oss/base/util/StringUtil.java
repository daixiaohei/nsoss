package com.nspaces.oss.base.util;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class StringUtil extends StringUtils {
	
	/**
	 * 
	 * @author zhangbingyue
	 * @email   155169553@qq.com
	 * @version 创建时间 2013-1-7 下午4:31:29
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else {
			if (str.equals("")) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNotEmpty(String str) {
		if (null != str && !"".equals(str)) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmptyORNull(String str) {
		if (null != str && !"".equals(str) && !"null".equals(str)) {
			return true;
		}
		return false;
	}
	
	public static String numberFormat(Double number, String format) {
		if (number == null || format == null || "".equals(format)) {
			return "";
		}
		
		try {
			DecimalFormat df = new DecimalFormat(format);
			
			return df.format(number);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String hideString(String input, int startPos, int endPos) {
		if (isEmpty(input)) {
			return input;
		}
		
		int len = input.length();
		
		if (startPos < 0 || endPos > (len - 1)) {
			return input;
		}
		
		char[] chars = input.toCharArray();
		
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < chars.length; i++) {
			if (i < startPos || i > endPos) {
				sb.append(chars[i]);
			}
			else {
				sb.append('*');
			}
		}
		
		return sb.toString();
	}
	
	
	public static String keepSecretString(String firstName, String lastName ) {
		
		String keepSecretString   = firstName +" "+ lastName;
		StringBuffer sb = new StringBuffer();
		if(keepSecretString.length() < keepSecretString.getBytes().length){
			keepSecretString = keepSecretString.replace(" ", "");
			//中文判断
			char[] chars = keepSecretString.toCharArray();
			System.out.println(chars.length);
			for (int i = 0; i < chars.length; i++) {
				if (i < 1 ) {
					sb.append(chars[i]);
				}
				else {
					sb.append('*');
				}
			}
		}else{
			
			if (StringUtil.isNotEmpty(firstName) && StringUtil.isNotEmpty(lastName) ) {
				char[] firstNameChars = firstName.toCharArray();
				char[] lastNameChars = lastName.toCharArray();
				for (int i = 0; i < firstNameChars.length; i++) {
					if (i < 1 || i > firstNameChars.length ) {
						sb.append(firstNameChars[i]);
					}
					else {
						sb.append('*');
					}
				}
				sb.append(" ");
				for (int i = 0; i < lastNameChars.length; i++) {
					if (i < 0 || i > lastNameChars.length-2 ) {
						sb.append(lastNameChars[i]);
					}
					else {
						sb.append('*');
					}
				}
			}
			else if(StringUtil.isEmpty(firstName)||StringUtil.isEmpty(lastName)) {
				keepSecretString = keepSecretString.replace(" ", "");
				char[] chars = keepSecretString.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					if (i < 1 || i > chars.length-2 ) {
						sb.append(chars[i]);
					}
					else {
						sb.append('*');
					}
				}
			}
		}
		return sb.toString();
		 
	}
	
	/**
	 * 格式化输出电话号码和传真号码
	 * 
	 * @author Simon.Ye <samlinye@163.com>
	 * 
	 * @param str 待处理的字符串
	 * @return
	 */
	public static String getFriendlyPhoneNumber(String str) {
		if (isEmpty(str)) {
			return "";
		}
		
		Pattern pattern;
		Matcher matcher;
		
		String countryCode = "";
		String areaCode = "";
		String number = "";
		
		pattern = Pattern.compile("^\\([+]*([0-9]*)\\)([0-9]+)[-]+([0-9]+)$");
		matcher = pattern.matcher(str);
		while (matcher.find()) {
			countryCode = matcher.group(1);
			areaCode = matcher.group(2);
			number = matcher.group(3);
			return "(+" + countryCode + ")" + areaCode + "-" + number;
		}
		
		matcher.reset();
		pattern = Pattern.compile("^([0-9]+)[-]+([0-9]+)$");
		matcher = pattern.matcher(str);
		while (matcher.find()) {
			areaCode = matcher.group(1);
			number = matcher.group(2);
			return areaCode + "-" + number;
		}
		
		matcher.reset();
		pattern = Pattern.compile("^([0-9]+)-([0-9]+)-([0-9]+)$");
		matcher = pattern.matcher(str);
		while (matcher.find()) {
			countryCode = matcher.group(1);
			areaCode = matcher.group(2);
			number = matcher.group(3);
			return "(+" + countryCode + ")" + areaCode + "-" + number;
		}
		
		matcher.reset();
		pattern = Pattern.compile("^([0-9]+)-([0-9]+)$");
		matcher = pattern.matcher(str);
		while (matcher.find()) {
			areaCode = matcher.group(1);
			number = matcher.group(2);
			return areaCode + "-" + number;
		}
		
		return str;
	}
	
	/**
	 * 格式化输出手机号码
	 * 
	 * @author Simon.Ye <samlinye@163.com>
	 * 
	 * @param str 待处理的字符串
	 * @return
	 */
	public static String getFriendlyMobileNumber(String str) {
		if (isEmpty(str)) {
			return "";
		}
		
		Pattern pattern;
		Matcher matcher;
		
		String countryCode = "";
		String number = "";
		
		pattern = Pattern.compile("^\\([+]*([0-9]*)\\)([0-9]+)$");
		matcher = pattern.matcher(str);
		while (matcher.find()) {
			countryCode = matcher.group(1);
			number = matcher.group(2);
			return "(+" + countryCode + ")" + number;
		}
		
		matcher.reset();
		pattern = Pattern.compile("^([0-9]+)-([0-9]+)$");
		matcher = pattern.matcher(str);
		while (matcher.find()) {
			countryCode = matcher.group(1);
			number = matcher.group(2);
			return "(+" + countryCode + ")" + number;
		}
		
		return str;
	}
	
	public static byte[] hex2byte(String str) {   
		  if (str == null){  
		   return null;  
		  }  
		    
		  str = str.trim();  
		  int len = str.length();  
		    
		  if (len == 0 || len % 2 == 1){  
		   return null;  
		  }  
		    
		  byte[] b = new byte[len / 2];  
		  try {  
		       for (int i = 0; i < str.length(); i += 2) {  
		            b[i / 2] = (byte) Integer.decode("0X" + str.substring(i, i + 2)).intValue();  
		       }  
		       return b;  
		  } catch (Exception e) {  
		   return null;  
		  }  
		}  
	
	public static void main(String[] args) {
		  String newUserId = "中古";
		  System.out.println(hex2byte(newUserId));
	}
	
}
