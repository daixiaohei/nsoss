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
import com.nspaces.oss.busi.domain.BusiGroup;
import com.nspaces.oss.busi.dto.BusiGroupDTO;
import com.nspaces.oss.busi.dto.BusiGroupQueryDTO;
import com.nspaces.oss.busi.dto.BusinessDTO;


/**
 * 
 * @author qt
 *
 */
@Repository
public class BusiGroupDao extends BaseDAO {

	public static final Logger logger = LoggerFactory.getLogger(BusiGroupDao.class);
	
	private String tableName = "busi_group";//主表
	private String idColumnName = "id";

	/**
	 * 
	 * 描述：插入和更新业务分组信息
	 * qt  创建于 2014年7月17日
	 * @param Business
	 * @return
	 * @throws Exception
	 */
	public BusiGroup editBusiGroup(BusiGroup busiGroup) throws Exception
	{
		if(null != busiGroup.getId())
		{
			super.update(busiGroup, tableName, idColumnName);
		}else
		{
			Integer retId = super.insertAndReturnId(busiGroup, tableName, idColumnName);
			busiGroup.setId(retId);
		}
		
		return busiGroup;
	}
	
	/**
	 * 
	 * 删除业务分组信息
	 * qt  创建于 2014年7月17日
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteBusiGroup(Integer id) throws Exception
	{
		if(null!=id){
		return super.deleteById(tableName, id);
		}
		return false;
	
	}
	
	
	private String getQueryCondition(BusiGroupQueryDTO queryDTO)
	{
		 String strCondition = "";
		  
		  if(StringUtil.isNotEmpty(queryDTO.getName()))
		  {
			  strCondition = strCondition + " where b.name like '%" + queryDTO.getName() + "%'";
		  }
		  
		  return strCondition;
	}
	/**
	 * 得到公共客户库数据
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Pagination<BusiGroupDTO> queryBusiGroupByPage(BusiGroupQueryDTO queryDTO){
	
		logger.debug("start BusiGroup query " + queryDTO.toString());
		
		StringBuilder sb = new StringBuilder();
		  sb.append(" select                                              ")
			.append("  b.*")
			.append("  from busi_group as b ")
		    .append(" " + getQueryCondition(queryDTO) + " ");
	       
	
		StringBuilder countSQL = new StringBuilder(); 
		countSQL.append("select count(b.id)")
		         .append(" from busi_group as b")
				 .append(" " + getQueryCondition(queryDTO) + " ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySQL = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//得到总记录数
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<BusiGroupDTO> result = new Pagination<BusiGroupDTO>();
		if(total == 0) {			
			result.setItems(new ArrayList<BusiGroupDTO>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
			result.setTotal(0);
			result.setMaxPage(0);
		} else {
			List<BusiGroupDTO> list = JdbcTemplateReadOnly.query(querySQL, paramMap, new BeanPropertyRowMapper<BusiGroupDTO>(BusiGroupDTO.class));
			result.setItems(list);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			result.setMaxPage(total/pc.getPageSize()+1);
		}
		
		return result;
	}
	
}
