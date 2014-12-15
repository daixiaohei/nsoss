package com.nspaces.oss.busi.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.nspaces.oss.base.dao.BaseDAO;
import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.base.dto.PaginationCondition;
import com.nspaces.oss.base.util.PaginationUtil;
import com.nspaces.oss.busi.domain.Street;
import com.nspaces.oss.busi.dto.StreetDTO;
import com.nspaces.oss.busi.dto.StreetQueryDTO;
@Repository
public class StreetDao extends BaseDAO{

	public static final Logger logger = LoggerFactory.getLogger(StreetDao.class);
	private String tableName = "street";
	private String idColumnName = "id";
	
	
	/**
	 * 插入或者更新网点信息
	 * @param spot
	 * @return
	 * @throws Exception
	 */
	public Street insertAndUpdate(Street street) throws Exception{
	
		if(0!=street.getId()){
			super.update(street, tableName, idColumnName);
		}else{
			Integer retId = super.insertAndReturnId(street, tableName, idColumnName);
			street.setId(retId);			
		}
		return street;
	}
	
	public Boolean deleteById(Integer id) throws Exception{
		if(null!=id){
			return super.deleteById(tableName, id);
		}else{
			return false;
		}
	}
	
	public Street findStreetByName(String streetName) throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select * from city  where name=:streetName";
		paramMap.put("streetName", streetName);
		
		List<Street> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<Street>(Street.class));
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
		
	}
	
	public List<Street> findStreetByCityNo(String cityNo) throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select * from street  where city_no=:cityNo";
		paramMap.put("cityNo", cityNo);
		
		List<Street> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<Street>(Street.class));
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return null;
		}
		
	}


	private String queryCondition(StreetQueryDTO queryDTO){
		
		String strCondition = "";
		if(null!=queryDTO.getCityNo()){
			strCondition += " and s.city_no= '"+queryDTO.getCityNo()+"' "; 
		}
		if(null!=queryDTO.getName()){
			strCondition += " and s.name like '%"+queryDTO.getName()+"%' ";
		}
		if(null!=queryDTO.getNameFull()){
			strCondition += " and s.name_full= '"+queryDTO.getNameFull()+"' ";
		}
		
		return strCondition;
		
	}
	
	
	public Pagination<StreetDTO> queryCityByPage(StreetQueryDTO queryDTO){
		
		logger.debug("query city start:"+queryDTO.toString());
		StringBuilder sb = new StringBuilder();
		sb.append("select s.* from street as s           ");
		sb.append("where 1=1");
		sb.append(" "+queryCondition(queryDTO)+" ");
		
		StringBuilder countSQL = new StringBuilder();
		countSQL.append("select count(s.id) from street as s ");
		countSQL.append("where 1=1");
		countSQL.append(" "+queryCondition(queryDTO)+" ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySql = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<StreetDTO> result = new Pagination<StreetDTO>();
		if(total==0){
			result.setTotal(0);
			result.setItems(new ArrayList<StreetDTO>());
			result.setMaxPage(0);
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
		}else{
			List<StreetDTO> cityList = JdbcTemplateReadOnly.query(querySql, paramMap, new BeanPropertyRowMapper(StreetDTO.class));
			
			result.setItems(cityList);
			result.setMaxPage(total/pc.getPageSize()+1);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			
		}
		
		return result;
	}
	
}
