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
import com.nspaces.oss.busi.domain.Organize;
import com.nspaces.oss.busi.domain.RoleMenu;
import com.nspaces.oss.busi.domain.Spot;
import com.nspaces.oss.busi.dto.OrganizeDTO;
import com.nspaces.oss.busi.dto.OrganizeQueryDTO;

/**
 * 
 * @author qt
 * 
 */
@Repository
public class OrganizeDao extends BaseDAO {

	public static final Logger logger = LoggerFactory.getLogger(OrganizeDao.class);

	private String tableName = "organize";// 主表
	private String idColumnName = "id";

	/**
	 * 
	 * 描述：插入和更新部门信息 qt 创建于 2014年7月22日
	 * 
	 * @param organize
	 * @return
	 * @throws Exception
	 */
	public Organize editOrganize(Organize organize) throws Exception {
		if (null != organize.getId()) {
			super.update(organize, tableName, idColumnName);
		} else {
			Integer retId = super.insertAndReturnId(organize, tableName,idColumnName);
			organize.setId(retId);
		}

		return organize;
	}
	
	
	

	/**
	 * 
	 * 描述：删除部门信息 qt 创建于 2014年7月22日
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteOrganize(Integer id) throws Exception {
		if (null != id) {
			return super.deleteById(tableName, id);
		}
		return false;
	}

	private String getQueryCondition(OrganizeQueryDTO queryDTO)
	{
		 String strCondition = "";
		  
		  if(StringUtil.isNotEmpty(queryDTO.getName()))
		  {
			  strCondition = strCondition + " where u.name like '%" + queryDTO.getName() + "%'";
		  }
		  
		  return strCondition;
	}
	/**
	 * 得到公共客户库数据
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Pagination<OrganizeDTO> queryOrganizeByPage(OrganizeQueryDTO queryDTO){
	
		logger.debug("start Organize query " + queryDTO.toString());
		
		StringBuilder sb = new StringBuilder();
		  sb.append(" select                                               ")
			.append("  u.*                                                 ")
			.append("  from organize as u ")
		    .append(" " + getQueryCondition(queryDTO) + " ");
	       
	
		StringBuilder countSQL = new StringBuilder(); 
		countSQL.append("select count(u.id)")
		         .append(" from organize as u")
				 .append(" " + getQueryCondition(queryDTO) + " ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySQL = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//得到总记录数
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<OrganizeDTO> result = new Pagination<OrganizeDTO>();
		if(total == 0) {			
			result.setItems(new ArrayList<OrganizeDTO>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
			result.setTotal(0);
			result.setMaxPage(0);
		} else {
			List<OrganizeDTO> list = JdbcTemplateReadOnly.query(querySQL, paramMap, new BeanPropertyRowMapper<OrganizeDTO>(OrganizeDTO.class));
			result.setItems(list);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			result.setMaxPage(total/pc.getPageSize()+1);
		}
		
		return result;
	}
	
}
