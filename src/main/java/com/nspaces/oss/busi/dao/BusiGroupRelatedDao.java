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
import com.nspaces.oss.base.util.StringUtil;
import com.nspaces.oss.busi.domain.BusiGroupRelated;
import com.nspaces.oss.busi.dto.BusiGroupDeviceDTO;
import com.nspaces.oss.busi.dto.BusiGroupDeviceQueryDTO;
import com.nspaces.oss.busi.dto.BusiGroupRelatedDTO;
import com.nspaces.oss.busi.dto.BusiGroupRelatedQueryDTO;

/**
 * 
 * @author qt
 * 
 */
@Repository
public class BusiGroupRelatedDao extends BaseDAO {

	public static final Logger logger = LoggerFactory.getLogger(BusiGroupRelatedDao.class);

	private String tableName = "busi_group_related";//
	private String idColumnName = "id";

	/**
	 * 
	 * @param organize
	 * @return
	 * @throws Exception
	 */
	public BusiGroupRelated editBusiGroupRelated(BusiGroupRelated busiGroupRelated) throws Exception {
		if (null != busiGroupRelated.getId()) {
			super.update(busiGroupRelated, tableName, idColumnName);
		} else {
			Integer retId = super.insertAndReturnId(busiGroupRelated, tableName,idColumnName);
			busiGroupRelated.setId(retId);
		}

		return busiGroupRelated;
	}

	
	public List<BusiGroupRelatedDTO> findBuaiGroupName() throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select id as busiGroupId,name as busiGroupName from busi_group";
		List<BusiGroupRelatedDTO> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<BusiGroupRelatedDTO>(BusiGroupRelatedDTO.class));
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return null;
		}
		
	}
	
	public List<BusiGroupRelatedDTO> findBusiName() throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select id as busiId,name as busiName from business";
		List<BusiGroupRelatedDTO> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<BusiGroupRelatedDTO>(BusiGroupRelatedDTO.class));
		if(list!=null&&list.size()>0){
			return list;
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
	public boolean deleteBusiGroupRelated(Integer id) throws Exception {
		if (null != id) {
			return super.deleteById(tableName, id);
		}
		return false;
	}
	
	
	private String getQueryCondition(BusiGroupRelatedQueryDTO queryDTO)
	{
		 String strCondition = "";
		  
		  if(StringUtil.isNotEmpty(queryDTO.getBusiName()))
		  {
			  strCondition = strCondition + " where b.name like '%" + queryDTO.getBusiName() + "%'";
		  }
		  
		  return strCondition;
	}
	
	
	/**
	 * 得到公共客户库数据
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Pagination<BusiGroupRelatedDTO> queryBusiGroupRelatedByPage(BusiGroupRelatedQueryDTO queryDTO){
	
		logger.debug("start BusiGroupRelated query " + queryDTO.toString());
		
		StringBuilder sb = new StringBuilder();
		  sb.append(" select                                      ")
			.append("  bgr.*,                                       ")
			.append("  bg.name as busiGroupName,                        ")
		    .append("  b.name as busiName                         ")
			.append("  from busi_group_related as bgr")
			.append("  left join busi_group as bg on bg.id = bgr.busi_group_id")
			.append("  left join business as b on b.id = bgr.busi_id")
		    .append(" " + getQueryCondition(queryDTO) + " ");
	       
	
		StringBuilder countSQL = new StringBuilder(); 
		countSQL.append("select count(*)")
		         .append(" from busi_group_related as bgr  ")
		         .append("  left join business as b on b.id = bgr.busi_id")
				 .append(" " + getQueryCondition(queryDTO) + " ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySQL = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//得到总记录数
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<BusiGroupRelatedDTO> result = new Pagination<BusiGroupRelatedDTO>();
		if(total == 0) {			
			result.setItems(new ArrayList<BusiGroupRelatedDTO>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
			result.setTotal(0);
			result.setMaxPage(0);
		} else {
			List<BusiGroupRelatedDTO> list = JdbcTemplateReadOnly.query(querySQL, paramMap, new BeanPropertyRowMapper<BusiGroupRelatedDTO>(BusiGroupRelatedDTO.class));
			result.setItems(list);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			result.setMaxPage(total/pc.getPageSize()+1);
		}
		
		return result;
	}

}
