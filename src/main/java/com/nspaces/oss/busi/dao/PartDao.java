package com.nspaces.oss.busi.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.nspaces.oss.base.dao.BaseDAO;
import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.base.dto.PaginationCondition;
import com.nspaces.oss.base.util.PaginationUtil;
import com.nspaces.oss.busi.domain.Part;
import com.nspaces.oss.busi.domain.PartList;
import com.nspaces.oss.busi.dto.PartListDTO;
import com.nspaces.oss.busi.dto.PartListQueryDTO;
import com.nspaces.oss.busi.dto.PartQueryDTO;
import com.nspaces.oss.busi.dto.xml.PartDTO;
@Repository
public class PartDao extends BaseDAO{
	private String partTableName = "part";// 主表
	private String partListTableName = "part_list";// 主表
	private String idColumnName = "id";
	
	/**
	 * 插入和更新对象
	 * @param deviceList
	 * @return
	 * @throws Exception
	 */
	public Part insertAndUpdatePart(Part part) throws Exception
	{
		if(null != part.getId())
		{
			super.update(part, partTableName, idColumnName);
		}else
		{
			Integer retId = super.insertAndReturnId(part, partTableName, idColumnName);
			part.setId(retId);
		}
		
		return part;
	}
	
	/**
	 * 根据ID去查找对应的对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Part findPartById(Integer id) throws Exception
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select * from part where id=:id ";
		paramMap.put("id", id);
		List<Part> retList = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<Part>(Part.class));
		
		if(retList!=null && retList.size()>0){
			return retList.get(0);
		}else{
			 return null;
		}
	}
	
	/**
	 * 查询全部设备
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<PartDTO> findAllParts() throws Exception
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select p.name as part_name,p.part_no,p.id as part_id from part as p";
		List<PartDTO> retList = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<PartDTO>(PartDTO.class));
		
		if(retList!=null && retList.size()>0){
			return retList;
		}else{
			 return null;
		}
	}
	
	
	/**
	 *通用设备查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Part> findPartByPartType(Integer partType) throws Exception
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select * from part where part_type=:partType order by part_type, part_no";
		paramMap.put("partType", partType);
		List<Part> retList = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<Part>(Part.class));
		
		if(retList!=null && retList.size()>0){
			return retList;
		}else{
			 return null;
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Boolean deletePartListById(Integer id) throws Exception{
		if(null!=id){
			return super.deleteById(partListTableName, id);
		}else{
			return false;
		}
	}
	/**
	 * 插入和更新对象
	 * @param deviceList
	 * @return
	 * @throws Exception
	 */
	public PartList insertAndUpdatePartList(PartList partList) throws Exception
	{
		if(null != partList.getId())
		{
			super.update(partList, partListTableName, idColumnName);
		}else
		{
			Integer retId = super.insertAndReturnId(partList, partListTableName, idColumnName);
			partList.setId(retId);
		}
		
		return partList;
	}
	
	/**
	 * 根据ID去查找对应的对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PartList findPartListById(Integer id) throws Exception
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select * from part_list where id=:id ";
		paramMap.put("id", id);
		List<PartList> retList = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<PartList>(PartList.class));
		
		if(retList!=null && retList.size()>0){
			return retList.get(0);
		}else{
			 return null;
		}
	}
	
	/**
	 * 根据ID去查找对应的对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<PartListDTO> findPartListByDevice(Integer deviceListId) throws Exception
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select pl.*, ppt.part_no, ppt.name as part_name ,ppt.part_type from part_list pl left join part ppt on pl.part_id = ppt.id  where pl.device_list_id=:deviceListId ";
		paramMap.put("deviceListId", deviceListId);
		List<PartListDTO> retList = jdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<PartListDTO>(PartListDTO.class));
		
		if(retList!=null && retList.size()>0){
			return retList;
		}else{
			 return null;
		}
	}

	
	/**
	 * 查询条件
	 * @param queryDTO
	 * @return
	 */
	private String queryCondition(PartListQueryDTO queryDTO){
		
		String strCondition = "";
		if(null!=queryDTO.getPartType()){
			strCondition += " and p.part_type= '"+queryDTO.getPartType()+"' "; 
		}

		
		return strCondition;
		
	}
	
	/**
	 * 分页查询
	 * @param queryDTO
	 * @return
	 */
	public Pagination<PartListDTO> queryPartListByPage(PartListQueryDTO queryDTO){
		
		logger.debug("query partList start:"+queryDTO.toString());
		StringBuilder sb = new StringBuilder();
		sb.append("select p.* from part_list as p           ");
		sb.append("where 1=1");
		sb.append(" "+queryCondition(queryDTO)+" ");
		
		StringBuilder countSQL = new StringBuilder();
		countSQL.append("select count(p.id) from part_list as p ");
		countSQL.append("where 1=1");
		countSQL.append(" "+queryCondition(queryDTO)+" ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySql = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<PartListDTO> result = new Pagination<PartListDTO>();
		if(total==0){
			result.setTotal(0);
			result.setItems(new ArrayList<PartListDTO>());
			result.setMaxPage(0);
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
		}else{
			List<PartListDTO> cityList = JdbcTemplateReadOnly.query(querySql, paramMap, new BeanPropertyRowMapper(PartListDTO.class));
			
			result.setItems(cityList);
			result.setMaxPage(total/pc.getPageSize()+1);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			
		}
		
		return result;
	}
	
	/**
	 * 查询条件
	 * @param queryDTO
	 * @return
	 */
	private String queryCondition(PartQueryDTO queryDTO){
		
		String strCondition = "";
		if(null!=queryDTO.getName()){
			strCondition += " and p.name like '%"+queryDTO.getName()+"%'"; 
		}

		
		return strCondition;
		
	}
	
	/**
	 * 分页查询
	 * @param queryDTO
	 * @return
	 */
	public Pagination<Part> queryPartByPage(PartQueryDTO queryDTO){
		
		logger.debug("query part start:"+queryDTO.toString());
		StringBuilder sb = new StringBuilder();
		sb.append("select p.* from part as p           ");
		sb.append("where 1=1");
		sb.append(" "+queryCondition(queryDTO)+" ");
		
		StringBuilder countSQL = new StringBuilder();
		countSQL.append("select count(p.id) from part as p ");
		countSQL.append("where 1=1");
		countSQL.append(" "+queryCondition(queryDTO)+" ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySql = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<Part> result = new Pagination<Part>();
		if(total==0){
			result.setTotal(0);
			result.setItems(new ArrayList<Part>());
			result.setMaxPage(0);
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
		}else{
			List<Part> cityList = JdbcTemplateReadOnly.query(querySql, paramMap, new BeanPropertyRowMapper(Part.class));
			
			result.setItems(cityList);
			result.setMaxPage(total/pc.getPageSize()+1);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			
		}
		
		return result;
	}
	
}
