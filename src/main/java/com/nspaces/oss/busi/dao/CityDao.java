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
import com.nspaces.oss.busi.domain.City;
import com.nspaces.oss.busi.dto.CityDTO;
import com.nspaces.oss.busi.dto.CityQueryDTO;

@Repository
public class CityDao extends BaseDAO{

	public static final Logger logger = LoggerFactory.getLogger(CityDao.class);
	private String tableName = "city";
	private String idColumnName = "id";
	
	
	/**
	 * 插入或者更新网点信息
	 * @param spot
	 * @return
	 * @throws Exception
	 */
	public City insertAndUpdate(City city) throws Exception{
	
		if(null!=city.getId()){
			super.update(city, tableName, idColumnName);
		}else{
			Integer retId = super.insertAndReturnId(city, tableName, idColumnName);
			city.setId(retId);			
		}
		return city;
	}
	
	public Boolean deleteById(Integer id) throws Exception{
		if(null!=id){
			return super.deleteById(tableName, id);
		}else{
			return false;
		}
	}
	
	public City findCityByName(String cityName) throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select * from city  where name=:cityName";
		paramMap.put("cityName", cityName);
		
		List<City> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<City>(City.class));
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
		
	}
	/**
	 * 通过等级来查询所有城市，比如广州是一级城市，湛江是二级
	 * @param cityName
	 * @return
	 * @throws Exception
	 */
	public List<City> findCityByLevel(String cityLevel) throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select * from city  where level=:cityLevel";
		paramMap.put("cityLevel", cityLevel);
		
		List<City> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<City>(City.class));
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return null;
		}
		
	}
	/**
	 * 通过等级和父级城市的名称来查询所有孩子城市
	 * @param cityLevel
	 * @return
	 * @throws Exception
	 */
	public List<City> findAllChildrenCity(String cityLevel,String parentNo) throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select * from city  where level=:cityLevel and parent_no=:parentNo";
		paramMap.put("cityLevel", cityLevel);
		paramMap.put("parentNo", parentNo);
		
		List<City> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<City>(City.class));
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return null;
		}
		
	}
	private String queryCondition(CityQueryDTO queryDTO){
		
		String strCondition = "";
		if(null!=queryDTO.getCityNo()){
			strCondition += " and c.city_no= '"+queryDTO.getCityNo()+"' "; 
		}
		if(0!=queryDTO.getLevel()){
			strCondition += " and c.level= '"+queryDTO.getLevel()+"' ";
		}
		if(null!=queryDTO.getCityName()){
			strCondition += " and c.city_name like '%"+queryDTO.getCityName()+"%' ";
		}
		
		return strCondition;
		
	}
	
	
	public Pagination<CityDTO> queryCityByPage(CityQueryDTO queryDTO){
		
		logger.debug("query city start:"+queryDTO.toString());
		StringBuilder sb = new StringBuilder();
		sb.append("select c.* from city as c           ");
		sb.append("where 1=1");
		sb.append(" "+queryCondition(queryDTO)+" ");
		
		StringBuilder countSQL = new StringBuilder();
		countSQL.append("select count(c.city_no) from city as c ");
		countSQL.append("where 1=1");
		countSQL.append(" "+queryCondition(queryDTO)+" ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySql = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<CityDTO> result = new Pagination<CityDTO>();
		if(total==0){
			result.setTotal(0);
			result.setItems(new ArrayList<CityDTO>());
			result.setMaxPage(0);
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
		}else{
			List<CityDTO> cityList = JdbcTemplateReadOnly.query(querySql, paramMap, new BeanPropertyRowMapper(CityDTO.class));
			
			result.setItems(cityList);
			result.setMaxPage(total/pc.getPageSize()+1);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			
		}
		
		return result;
	}
	
	
}
