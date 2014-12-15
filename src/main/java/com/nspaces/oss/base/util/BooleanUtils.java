package com.nspaces.oss.base.util;

public class BooleanUtils {

	public static final String YES = "Y";
	public static final String NO = "N";
	public static final String ZERO = "0";
	public static final String ONE = "1";
	
	public static boolean parseBoolean(String value) {
		if(YES.equalsIgnoreCase(value)) {
			return true;
		} else if(NO.equalsIgnoreCase(value)) {
			return false;
		} else if(ZERO.equals(value)) {
			return false;
		} else if(ONE.equals(value)) {
			return true;
		} else {
			return Boolean.valueOf(value);
		}
	}
	
	public static boolean parseBoolean(int value) {
		if(value == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public static int parseInt(boolean value) {
		if(value) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public static String parseString(boolean value) {
		if(value) {
			return "true";
		} else {
			return "false";
		}
	}
}
