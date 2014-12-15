package com.nspaces.oss.base.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.base.dto.PaginationCondition;
import com.nspaces.oss.base.util.StringUtil;

public abstract class BaseDAO {
	protected static final Logger logger = LoggerFactory.getLogger(BaseDAO.class);
	
	@Autowired
	@Qualifier("jdbcTemplate")
	protected JdbcTemplate jdbcTemplateCore;
	
	@Autowired
	@Qualifier("namedParameterJdbcTemplate")
	protected NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("namedParameterJdbcTemplateReadOnly")
	protected NamedParameterJdbcTemplate JdbcTemplateReadOnly;
		
	@Autowired
	private DBColumnMapper mapper;
	
	private boolean isIgnoreField(String field, String[] excludingNameArray) {
		for(String excludingName : excludingNameArray) {
			if(field.equalsIgnoreCase(excludingName)) {
				return true;
			}
		}
		return false;
	}
	
	protected <T> boolean insert(T obj, String tableName, String[] excludingNameArray) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = obj.getClass();
		HashMap<String, ColumnMapper> columnMapperList = mapper.getColumnMapper(clazz);
		
		
		
		StringBuffer insertColumn = new StringBuffer();
		
		StringBuffer insertField = new StringBuffer();
		
		Iterator<String> iterator = columnMapperList.keySet().iterator();
		while(iterator.hasNext()) {
		
			ColumnMapper curColumnMapper = columnMapperList.get(iterator.next());
			
			if(isIgnoreField(curColumnMapper.getFiled(), excludingNameArray) && mapper.isExcludeColumn(curColumnMapper.getFiled())) {
				continue;
			}
			
			insertColumn.append(curColumnMapper.getColumn());
			insertColumn.append(",");

			insertField.append(":");
			insertField.append(curColumnMapper.getFiled());
			insertField.append(",");

		}
		
		String strInsertColumn = insertColumn.toString();
		strInsertColumn = strInsertColumn.substring(0, strInsertColumn.length() -1);
		
		String strInsertField = insertField.toString();
		strInsertField = strInsertField.substring(0, strInsertField.length() -1);
		
		StringBuffer insertSQL = new StringBuffer();
		insertSQL.append("INSERT INTO ");
		insertSQL.append(tableName);
		insertSQL.append("(");
		insertSQL.append(strInsertColumn);
		insertSQL.append(")");
		insertSQL.append(" ");
		insertSQL.append("VALUES");
		insertSQL.append("(");
		insertSQL.append(strInsertField);
		insertSQL.append(")");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			String fieldName = field.getName();
			if(!"serialVersionUID".equalsIgnoreCase(fieldName) && !isIgnoreField(fieldName, excludingNameArray) && !mapper.isExcludeColumn(fieldName)) {
				field.setAccessible(true);
				Object val = field.get(obj);
				Date now = new Date();
				if (field.getName().equals("createdAt") && val == null) {//澶勭悊榛樿鍊�
					val = now;
				} else if (field.getName().equals("updatedAt") && val == null) {//澶勭悊榛樿鍊�
					val = now;
				}
				paramMap.put(field.getName(), val);
				field.setAccessible(false);
			}
		}
		
		int rowNum = jdbcTemplate.update(insertSQL.toString(), paramMap);
		return rowNum > 0;
	}
	
	protected <T> boolean insert(T obj, String tableName, String excludingName) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = obj.getClass();
		HashMap<String, ColumnMapper> columnMapperList = mapper.getColumnMapper(clazz);
		
		StringBuffer insertColumn = new StringBuffer();
		
		StringBuffer insertField = new StringBuffer();
		
		Iterator<String> iterator = columnMapperList.keySet().iterator();
		while(iterator.hasNext()) {
		
			ColumnMapper curColumnMapper = columnMapperList.get(iterator.next());
			
			if(curColumnMapper.getFiled().equalsIgnoreCase(excludingName) && mapper.isExcludeColumn(curColumnMapper.getFiled())) {
				continue;
			}
			
			insertColumn.append(curColumnMapper.getColumn());
			insertColumn.append(",");

			insertField.append(":");
			insertField.append(curColumnMapper.getFiled());
			insertField.append(",");

		}
		
		String strInsertColumn = insertColumn.toString();
		strInsertColumn = strInsertColumn.substring(0, strInsertColumn.length() -1);
		
		String strInsertField = insertField.toString();
		strInsertField = strInsertField.substring(0, strInsertField.length() -1);
		
		StringBuffer insertSQL = new StringBuffer();
		insertSQL.append("INSERT INTO ");
		insertSQL.append(tableName);
		insertSQL.append("(");
		insertSQL.append(strInsertColumn);
		insertSQL.append(")");
		insertSQL.append(" ");
		insertSQL.append("VALUES");
		insertSQL.append("(");
		insertSQL.append(strInsertField);
		insertSQL.append(")");
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			String fieldName = field.getName();
			if(!"serialVersionUID".equalsIgnoreCase(fieldName) && !fieldName.equalsIgnoreCase(excludingName) && !mapper.isExcludeColumn(fieldName)) {
				field.setAccessible(true);
				Object val = field.get(obj);
				Date now = new Date();
				if (field.getName().equals("createTime") && val == null) {//澶勭悊榛樿鍊�
					val = now;
				} else if (field.getName().equals("modifyTime") && val == null) {//澶勭悊榛樿鍊�
					val = now;
				}
				paramMap.put(field.getName(), val);
				field.setAccessible(false);
			}
		}
		
		int rowNum = jdbcTemplate.update(insertSQL.toString(), paramMap);
		return rowNum > 0;
	}
	
	protected <T> Integer insertAndReturnId(T obj, String tableName, String excludingName) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = obj.getClass();
		HashMap<String, ColumnMapper> columnMapperList = mapper.getColumnMapper(clazz);
		
		StringBuffer insertColumn = new StringBuffer();
		
		StringBuffer insertField = new StringBuffer();
		
		Iterator<String> iterator = columnMapperList.keySet().iterator();
		while(iterator.hasNext()) {
		
			ColumnMapper curColumnMapper = columnMapperList.get(iterator.next());
			
			if(curColumnMapper.getFiled().equalsIgnoreCase(excludingName) && mapper.isExcludeColumn(curColumnMapper.getFiled())) {
				continue;
			}
			
			insertColumn.append(curColumnMapper.getColumn());
			insertColumn.append(",");

			insertField.append(":");
			insertField.append(curColumnMapper.getFiled());
			insertField.append(",");

		}
		
		String strInsertColumn = insertColumn.toString();
		strInsertColumn = strInsertColumn.substring(0, strInsertColumn.length() -1);
		
		String strInsertField = insertField.toString();
		strInsertField = strInsertField.substring(0, strInsertField.length() -1);
		
		StringBuffer insertSQL = new StringBuffer();
		insertSQL.append("INSERT INTO ");
		insertSQL.append(tableName);
		insertSQL.append("(");
		insertSQL.append(strInsertColumn);
		insertSQL.append(")");
		insertSQL.append(" ");
		insertSQL.append("VALUES");
		insertSQL.append("(");
		insertSQL.append(strInsertField);
		insertSQL.append(")");
		
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			String fieldName = field.getName();
			if(!"serialVersionUID".equalsIgnoreCase(fieldName) && !fieldName.equalsIgnoreCase(excludingName) && !mapper.isExcludeColumn(fieldName)) {
				field.setAccessible(true);
				Object val = field.get(obj);
				Date now = new Date();
				if (field.getName().equals("createdAt") && val == null) {//澶勭悊榛樿鍊�
					field.set(obj, now);
				} else if (field.getName().equals("updateAt") && val == null) {//澶勭悊榛樿鍊�
					field.set(obj, now);
				}
				field.setAccessible(false);
			}
		}
		
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
		KeyHolder holder = new GeneratedKeyHolder();
		int rowNum = jdbcTemplate.update(insertSQL.toString(), paramSource, holder);
		if(rowNum > 0) {
			return holder.getKey().intValue();
		} else {
			return null;
		}
	}
	
	protected <T> boolean update(List<T> objList, String tableName, String idColumnName) throws IllegalArgumentException, IllegalAccessException {
		for(T obj : objList) {
			boolean result = update(obj, tableName, idColumnName);
			if(!result) {
				return false;
			}
		}
		return true;
	}
	
	protected <T> boolean update(T obj, String tableName, String idColumnName) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazz = obj.getClass();
		HashMap<String, ColumnMapper> columnMapperList = mapper.getColumnMapper(clazz);
		
		StringBuilder updateSQL = new StringBuilder();
		updateSQL.append("UPDATE");
		updateSQL.append(" ");
		updateSQL.append(tableName);
  		updateSQL.append(" ");
		updateSQL.append("SET");
		updateSQL.append(" ");
		
		StringBuilder updateSQLField = new StringBuilder();
		
 		String idFieldName = mapper.obtainFiledName(idColumnName);

		updateSQL.append(" ");
		
		StringBuilder updateSQLWhere = new StringBuilder();
		if(idFieldName != null && StringUtil.isNotEmpty(idFieldName)){
			updateSQLWhere.append(" WHERE");
			updateSQLWhere.append(" ");
			updateSQLWhere.append(idColumnName);
			updateSQLWhere.append("=");
			updateSQLWhere.append(":").append(idFieldName);
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			String fieldName = field.getName();
			
			if(!"serialVersionUID".equalsIgnoreCase(fieldName) && !mapper.isExcludeColumn(fieldName)) {
				field.setAccessible(true);
				Object val = field.get(obj);
				if(null != val)
				{
					if(idFieldName.equalsIgnoreCase(fieldName))
					{
						paramMap.put(field.getName(), val);
					}else
					{
						ColumnMapper columnMapper = columnMapperList.get(fieldName);
						
						updateSQLField.append(columnMapper.getColumn());
						updateSQLField.append("=");
	     				updateSQLField.append(":").append(columnMapper.getFiled());;
	     				updateSQLField.append(",");
						paramMap.put(field.getName(), val);
					}
				}
				field.setAccessible(false);
			}
		}
		String updateFieldSQL = updateSQLField.toString();
		
		updateSQL.append(updateFieldSQL.substring(0, updateFieldSQL.length() -1));
		updateSQL.append(updateSQLWhere.toString());
		
		int rowNum = jdbcTemplate.update(updateSQL.toString(), paramMap);
		return rowNum > 0;
	}
	
	protected <O> Pagination<O> getPaginationForOther(PaginationCondition pc,
			String countSql, String querySql, Map<String, Object> paramMap, RowMapper<O> rowMapper) {
		int total = JdbcTemplateReadOnly.queryForInt(countSql, paramMap);
		Pagination<O> result = new Pagination<O>();
		if(total == 0) {			
			result.setItems(new ArrayList<O>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(PaginationCondition.DEFAULT_PAGESIZE);
			result.setTotal(0);
		} else {
			List<O> itemList = JdbcTemplateReadOnly.query(querySql, paramMap, rowMapper);
			result.setItems(itemList);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
		}
		return result;
	}
	
	/**
	 * 判断记录是否存在
	 * @param countSql
	 * @param paramMap
	 * @return
	 */
	protected boolean isExistObject(String countSql,Map<String, Object> paramMap){
		int total = JdbcTemplateReadOnly.queryForInt(countSql, paramMap);
		return total>0?true:false;
	}
	
	protected <T> Pagination<T> getPagination(PaginationCondition pc,
			String countSql, String querySql, Map<String, Object> paramMap, RowMapper<T> rowMapper) {
		int total = JdbcTemplateReadOnly.queryForInt(countSql, paramMap);
		Pagination<T> result = new Pagination<T>();
		if(total == 0) {			
			result.setItems(new ArrayList<T>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(PaginationCondition.DEFAULT_PAGESIZE);
			result.setTotal(0);
		} else {
			List<T> itemList = JdbcTemplateReadOnly.query(querySql, paramMap, rowMapper);
			result.setItems(itemList);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
		}
		return result;
	}
	
	protected Pagination<Map<String, Object>> getPagination(PaginationCondition pc,
			String countSql, String querySql, Map<String, Object> paramMap) {
		int total = JdbcTemplateReadOnly.queryForInt(countSql, paramMap);
		Pagination<Map<String, Object>> result = new Pagination<Map<String, Object>>();
		if(total == 0) {			
			result.setItems(new ArrayList<Map<String, Object>>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(PaginationCondition.DEFAULT_PAGESIZE);
			result.setTotal(0);
		} else {
			List<Map<String, Object>> itemList = JdbcTemplateReadOnly.queryForList(querySql, paramMap);
			result.setItems(itemList);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
		}
		return result;
	}
	
	
	
	protected void writeLog(Exception e, String errorMessage) {
		if (errorMessage != null && !"".equals(errorMessage)) {
			System.out.println(errorMessage);
			
			logger.error(errorMessage);
		}
		
		e.printStackTrace();
		
		logger.error(e.getMessage());
	}
	
	/**
	 * 删除对象
	 * @param tableName
	 * @param id
	 * @return
	 */
	protected boolean deleteById(String tableName, Integer id) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete from  ");
		sql.append(tableName);
		sql.append(" WHERE id=:keyId");
		
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("keyId", id);
		
		int rowNum = jdbcTemplate.update(sql.toString(), paramMap);
		return rowNum > 0;
	}
	
	
	/**
	 * 逻辑删除对象
	 * @param tableName
	 * @param id
	 * @return
	 */
	protected boolean deleteByLogic(String tableName, Integer id) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("update  ");
		sql.append(tableName);
		sql.append(" set status = -1 ");
		sql.append(" WHERE id=:keyId");
		
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("keyId", id);
		
		int rowNum = jdbcTemplate.update(sql.toString(),paramMap);
		return rowNum > 0;
	}
	
	/**
	 * 更新对象
	 * @param tableName
	 * @param id
	 * @return
	 */
	protected boolean deleteByLogicCoupon(String tableName, Integer id) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("update  ");
		sql.append(tableName);
		sql.append(" set status = -1, ");
		sql.append(" use_date = now() ");
		sql.append(" WHERE id=:keyId");
		
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("keyId", id);
		
		int rowNum = jdbcTemplate.update(sql.toString(),paramMap);
		return rowNum > 0;
	}
	
}
