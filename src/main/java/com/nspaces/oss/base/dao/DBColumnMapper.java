package com.nspaces.oss.base.dao;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.nspaces.oss.base.util.CollectionUtil;



@Component
public class DBColumnMapper {

	private static Map<String, HashMap<String, ColumnMapper>> mapper = new HashMap<String, HashMap<String, ColumnMapper>>();
	
	
	
	
	private static final String UNDERLINE = "_";
	
	public HashMap<String, ColumnMapper> getColumnMapper(Class<?> clazz) {
		HashMap<String, ColumnMapper> columnMapperList = mapper.get(clazz.getName());
		if(CollectionUtil.isEmpty(columnMapperList)) {
			columnMapperList = generateColumnMapper(clazz);
			mapper.put(clazz.getName(), columnMapperList);
		}
		return columnMapperList;
	}

	

	private HashMap<String, ColumnMapper> generateColumnMapper(Class<?> clazz) {
		HashMap<String, ColumnMapper> columnMapperList = new HashMap<String, ColumnMapper>();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			String fieldName = field.getName();
			if(!"serialVersionUID".equalsIgnoreCase(fieldName)) {
				ColumnMapper columnMapper = new ColumnMapper();
	
				columnMapper.setFiled(fieldName);
				columnMapper.setColumn(obtainColumn(fieldName));

				columnMapperList.put(fieldName, columnMapper);
			}
		}
		return columnMapperList;
	}

	private String obtainColumn(String fieldName) {
		StringBuffer builder = new StringBuffer();
		char[] charArray = fieldName.toCharArray();
		for(int index = 0; index < charArray.length; index++) {
			char ch = charArray[index];
			if(Character.isUpperCase(ch)) {
				if(index == 0) {
					builder.append(Character.toLowerCase(ch));
				} else {
					builder.append(UNDERLINE);
					builder.append(Character.toLowerCase(ch));
				}
			} else {
				builder.append(ch);
			}
		}
		return builder.toString();
	}
	
	
	public String obtainFiledName(String column) {
		StringBuffer builder = new StringBuffer();
		char[] charArray = column.toCharArray();
		for(int index = 0; index < charArray.length; index++) {
			char ch = charArray[index];
			if(ch == '_') {
				if(index == 0) {
					char chLast = charArray[index+1];
					builder.append(chLast);
				} else {
					char chLast = charArray[index+1];
					builder.append(Character.toUpperCase(chLast));
				}
				index = index + 1;
			} else {
				builder.append(ch);
			}
		}
		return builder.toString();
	}
	
	public boolean isExcludeColumn(String column)
	{
		if(column.length() > 3)
		{
			String frontStr = column.substring(0,3);
			
			if("exc".equals(frontStr))
			{
				return true;
			}else
			{
				return false;
			}
		}
		return false;
	}
	
}
