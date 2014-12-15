package com.nspaces.oss.base.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
	private static Properties props = new Properties();

	

	public static String get(String key) {
		return props.getProperty(key);
	}

    public static void setProps(Properties p){
         props = p;
    }
}
