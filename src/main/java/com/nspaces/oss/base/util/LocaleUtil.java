package com.nspaces.oss.base.util;

import java.util.Locale;

/**
 * 语言工具类
 *
 */
public class LocaleUtil {
	
	private static String[] chinese = {"zh", "cn", "tw", "zhcn", "zh_cn", "zhtw", "zh_tw"};
	
	public static boolean isCn(Locale locale){
		
		if(locale == null) {
			return false;
		}
		
		if(locale.equals(Locale.CHINA) || locale.equals(Locale.CHINESE)
				|| locale.equals(Locale.TAIWAN)){
			return true;
		}
		
		
		for(String c : chinese){
			if(c.equalsIgnoreCase(locale.toString())){
				return true;
			}
		}
		
		return false;
	}

}
