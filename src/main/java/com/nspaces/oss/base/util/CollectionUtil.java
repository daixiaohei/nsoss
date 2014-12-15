package com.nspaces.oss.base.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nspaces.oss.base.dto.Pagination;



public class CollectionUtil {
	
	public static final String COMMA = ",";

	public static boolean isEmpty(Collection<?> collection) {
		if(collection == null || collection.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isEmpty(Pagination<Map<String, Object>> collectList) {
		if(collectList == null || collectList.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
 
	
	public static boolean isNotEmpty(Map<?, ?> map) {
		if(map != null && !map.isEmpty() && map.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isEmpty(Map<?, ?> map) {
		return !isNotEmpty(map);
	}
	
	public static String convertToStringWithComma(List<String> list) {
		return convertToStringWithSplitor(list, COMMA);
	}
	
	public static String convertToStringWithComma(Map<String, ?> map) {
		return convertToStringWithSplitor(map, COMMA);
	}
	
	public static String convertToStringWithSplitor(Map<String, ?> map, String splitor) {
		if(isEmpty(map)) {
			return "";
		}
		if(StringUtil.isEmpty(splitor)) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for(String key : map.keySet()) {
			builder.append(key).append(splitor);
		}
		String result = builder.toString();
		if(StringUtil.isEmpty(result)) {
			return "";
		} else {
			return result.substring(0, result.length() - 1);
		}
	}
	
	public static String convertToStringWithSplitor(List<String> list, String splitor) {
		if(CollectionUtil.isEmpty(list)) {
			return "";
		}
		if(StringUtil.isEmpty(splitor)) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		Map<String, String> map = new HashMap<String, String>();
		for(String str : list) {
			if(map.containsKey(str)) {
				continue;
			}
			map.put(str, str);
		}
		for(String key : map.keySet()) {
			builder.append(key).append(splitor);
		}
		String result = builder.toString();
		if(StringUtil.isEmpty(result)) {
			return "";
		} else {
			return result.substring(0, result.length() - 1);
		}
	}
	
	/**
	 * 对比两个集合中的对象，得到第一个集合中不存在于第二个集合的元素对象
	 * @param first			第一个数组
	 * @param second		第二个数组
	 * @return
	 */
	public static <T> List<T> findObjectsNotInSecondList(List<T> first, List<T> second){
		List<T> returnObject = null;
		if(first != null && first.size() > 0){
			//如果第二个集合是空的，则返回第一个集合所有
			if(second == null || second.size() == 0){
				return first;
			}else{
				returnObject = new ArrayList<T>();
				for(T firstObject : first){
					boolean notContain = true;//不存在第二个数组的标记，true为不存在，false为存在
					for(T secondObject : second){
						if(firstObject.equals(secondObject)){
							notContain = false;
							break;
						}
					}
					if(notContain){
						returnObject.add(firstObject);
					}
				}
			}
		}
		return returnObject;
	}
}
