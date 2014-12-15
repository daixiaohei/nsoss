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
import com.nspaces.oss.busi.domain.Spot;
import com.nspaces.oss.busi.dto.SpotDTO;
import com.nspaces.oss.busi.dto.SpotQueryDTO;
@Repository
public class SpotDao extends BaseDAO{

	public static final Logger logger = LoggerFactory.getLogger(SpotDao.class);
	
	private String tableName = "spot";
	private String idColumnName = "id";
	
	/**
	 * 插入或者更新网点信息
	 * @param spot
	 * @return
	 * @throws Exception
	 */
	public Spot insertAndUpdate(Spot spot) throws Exception{
	
		if(0!=spot.getId()){
			super.update(spot, tableName, idColumnName);
		}else{
			Integer retId = super.insertAndReturnId(spot, tableName, idColumnName);
			spot.setId(retId);			
		}
		return spot;
	}
	
	public Boolean deleteById(Integer id) throws Exception{
		if(null!=id){
			return super.deleteById(tableName, id);
		}else{
			return false;
		}
	}
	
	public Spot findSpotByName(String spotName) throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select * from spot  where name=:spotName";
		paramMap.put("spotName", spotName);
		
		List<Spot> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<Spot>(Spot.class));
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
		
	}
	private String queryCondition(SpotQueryDTO queryDTO){
		
		String strCondition = "";
		if(null!=queryDTO.getCityNo()){
			strCondition += " and s.city_no= '"+queryDTO.getCityNo()+"' "; 
		}
		if(null!=queryDTO.getName()){
			strCondition += " and s.name like '%"+queryDTO.getName()+"%' ";
		}
		if(null!=queryDTO.getSpotNo()){
			strCondition += " and s.spot_no= '"+queryDTO.getSpotNo()+"' ";
		}
		
		return strCondition;
		
	}
	
	
	public Pagination<SpotDTO> querySpotByPage(SpotQueryDTO queryDTO){
		
		logger.debug("query spot start:"+queryDTO.toString());
		StringBuilder sb = new StringBuilder();
		sb.append("select s.* from spot as s           ");
		sb.append("where 1=1");
		sb.append(" "+queryCondition(queryDTO)+" ");
		
		StringBuilder countSQL = new StringBuilder();
		countSQL.append("select count(s.id) from spot as s ");
		countSQL.append("where 1=1");
		countSQL.append(" "+queryCondition(queryDTO)+" ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySql = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<SpotDTO> result = new Pagination<SpotDTO>();
		if(total==0){
			result.setTotal(0);
			result.setItems(new ArrayList<SpotDTO>());
			result.setMaxPage(0);
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
		}else{
			List<SpotDTO> spotList = JdbcTemplateReadOnly.query(querySql, paramMap, new BeanPropertyRowMapper(SpotDTO.class));
			
			result.setItems(spotList);
			result.setMaxPage(total/pc.getPageSize()+1);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			
		}
		
		return result;
	}
	

}
