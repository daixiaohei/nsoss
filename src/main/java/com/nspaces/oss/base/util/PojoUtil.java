package com.nspaces.oss.base.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PojoUtil {
	
	private static final String GET = "get";
	private static final String IS = "is";
	
	/**
	 * main function
	 * @param obj
	 * @return 
	 */
	@SuppressWarnings("rawtypes")
	public static Object translate(Object obj) {
		if(obj == null) {
			return null;
		} else if(isArray(obj)) {
			return translateToListMap((Object[]) obj);
		} else if(isList(obj)) {
			return translateToListMap((List) obj);
		} else if(isPrimitive(obj)) {
			return obj;
		} else if(isMap(obj)) {
			return obj;
		}else {
			return translateToMap(obj);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> translateToMap(Object obj) {
		if(isArray(obj)) {
			throw new UnsupportedOperationException("Do not support the array translate to map!");
		} else if(isList(obj)) {
			throw new UnsupportedOperationException("Do not support the list translate to map!");
		} else if(isMap(obj)) {
			return (Map<String, Object>) obj;
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		Method[] methods = obj.getClass().getDeclaredMethods();
		for(Method m : methods) {
			String mName = m.getName();
			if(mName.startsWith(GET) || mName.startsWith(IS)) {
				Object r = null;
				try {
					r = m.invoke(obj);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				if(r == null) {
					continue;
				}
				String key = getFieldNameByMethodName(mName);
				if(isArray(r)) {
					if(((Object[])r).length > 0 && isPrimitive(((Object[])r)[0])) {
						result.put(key, r);
					} else {
						result.put(key, translateToListMap((Object[]) r));
					}
				} else if(isList(r)) {
					if(((List) r).isEmpty()) {
						result.put(key, r);
					} else {
						Object objInList = ((List) r).get(0);
						if(isPrimitive(objInList)) {
							result.put(key, r);
						} else {
							result.put(key, translateToListMap((List) r));
						}
					}
				} else if(isPrimitive(r)){
					result.put(key, r);
				} else {
					result.put(key, translateToMap(r));
				}
			}
		}
		
		return result;
	}
	
	private static String getFieldNameByMethodName(String mName) {
		String result = mName;
		if(mName.startsWith(GET)) {
			result = mName.replaceFirst(GET, "");
		} else if(mName.startsWith(IS)) {
			result =  mName.replaceFirst(IS, "");
			
		}
		return getFieldNameByMethodNameExcludeGetOrIs(result);
	}
	
	private static String getFieldNameByMethodNameExcludeGetOrIs(String name) {
		char first = name.charAt(0);
		String catStr = "";
		if(name.length() > 1) {
			catStr = name.substring(1);
		}
		return ("" + first).toLowerCase() + catStr; 
	}
	
	private static boolean isList(Object obj) {
		if(obj == null) {
			return false;
		}
		return obj instanceof List;
	}
	
	private static boolean isArray(Object obj) {
		if(obj == null) {
			return false;
		}
		return obj.getClass().isArray();
	}
	
	private static boolean isPrimitive(Object obj) {
		if(obj == null) {
			return false;
		} else if(obj.getClass().isPrimitive()) {
			return true;//Boolean.TYPE, Character.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Void.TYPE
		} else if(obj instanceof String) {
			return true;
		} else if(obj instanceof Boolean) {
			return true;
		} else if(obj instanceof Character) {
			return true;
		} else if(obj instanceof Byte) {
			return true;
		} else if(obj instanceof Short) {
			return true;
		} else if(obj instanceof Integer) {
			return true;
		} else if(obj instanceof Long) {
			return true;
		} else if(obj instanceof Float) {
			return true;
		} else if(obj instanceof Double) {
			return true;
		} else if(obj instanceof Void) {
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean isMap(Object obj) {
		if(obj == null) {
			return false;
		} else if(obj instanceof Map) {
			return true;
		} else {
			return false;
		}
	}
	
	public static List<Map<String, Object>> translateToListMap(Object[] objs) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		if(objs == null || objs.length == 0) {
			return result;
		}
		if(isArray(objs)) {
			if(isPrimitive(objs[0])) {
				throw new UnsupportedOperationException("Do not support primitive array translate to map!");
			}
		} else {
			for(Object o : objs) {
				result.add(translateToMap(o));
			}
		}
		return result;
	}
	
	public static List<Map<String, Object>> translateToListMap(List<?> objs) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		if(CollectionUtil.isEmpty(objs)) {
			return result;
		}
		if(isPrimitive(objs.get(0))) {
			throw new UnsupportedOperationException("Do not support the primitive or string array translate to map!");
		} else {
			for(Object o : objs) {
				result.add(translateToMap(o));
			}
		}
		return result;
	}
	
	
	
}
