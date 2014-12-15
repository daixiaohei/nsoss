/**
 * 
 */
package com.nspaces.oss.base.converter;

import org.springframework.stereotype.Component;

import com.nspaces.oss.base.util.BeanUtils;


@Component("commonConverterTools")
public class CommonConverterTools {
	/**
	 * 对象转换
	 * @param clazz  转换后的类  
	 * @param object 被转后的对象
	 * @return
	 */
	public <T> T convert(Class<T> clazz , Object object){
		if(object!=null){
			T obj=null;
			try {
				obj = clazz.newInstance();
				BeanUtils.copyProperties(object, obj);
				return obj ;
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} 
		}
		return null;
	}
}
