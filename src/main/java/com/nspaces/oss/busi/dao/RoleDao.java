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
import com.nspaces.oss.busi.domain.Role;
import com.nspaces.oss.busi.dto.RoleDTO;
import com.nspaces.oss.busi.dto.RoleQueryDTO;
import com.nspaces.oss.busi.dto.UserDTO;
import com.nspaces.oss.busi.dto.UserQueryDTO;


/**
 * 
 * @author qt
 *
 */
@Repository
public class RoleDao extends BaseDAO {

	public static final Logger logger = LoggerFactory.getLogger(RoleDao.class);
	
	private String tableName = "role";//主表
	private String idColumnName = "id";

	/**
	 * 
	 * 描述：插入和更新角色信息
	 * qt  创建于 2014年7月17日
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public Role editRole(Role role) throws Exception
	{
		if(null != role.getId())
		{
			super.update(role, tableName, idColumnName);
		}else
		{
			Integer retId = super.insertAndReturnId(role, tableName, idColumnName);
			role.setId(retId);
		}
		
		return role;
	}
	
	/**
	 * 
	 * 删除角色信息
	 * qt  创建于 2014年7月17日
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteRole(Integer id) throws Exception
	{
		if(null!=id){
		return super.deleteById(tableName, id);
		}
		return false;
	
	}
	
	
	/**
	 * 
	 * qt  创建于 2014年7月17日
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<Role> findRoleByName(String name) throws Exception
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select * from role where name=:name";
		paramMap.put("name", name);
		List<Role> retList = jdbcTemplate.query(sql.toString(), paramMap, new BeanPropertyRowMapper<Role>(Role.class));
		
		if(retList!=null && retList.size()>0){
			 return retList;
		}else{
			 return null;
		}
	}
	
	private String getQueryCondition(RoleQueryDTO queryDTO)
	{
		 String strCondition = "";
		  
		  if(StringUtil.isNotEmpty(queryDTO.getRoleName()))
		  {
			  strCondition = strCondition + " where u.name like '%" + queryDTO.getRoleName() + "%'";
		  }
		  
		  return strCondition;
	}
	/**
	 * 得到公共客户库数据
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Pagination<RoleDTO> queryRoleByPage(RoleQueryDTO queryDTO){
	
		logger.debug("start role query " + queryDTO.toString());
		
		StringBuilder sb = new StringBuilder();
		  sb.append(" select                                               ")
			.append("  u.*                                                 ")
			.append("  from role as u ")
		    .append(" " + getQueryCondition(queryDTO) + " ");
	       
	
		StringBuilder countSQL = new StringBuilder(); 
		countSQL.append("select count(u.id)")
		         .append(" from role as u")
				 .append(" " + getQueryCondition(queryDTO) + " ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySQL = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//得到总记录数
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<RoleDTO> result = new Pagination<RoleDTO>();
		if(total == 0) {			
			result.setItems(new ArrayList<RoleDTO>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
			result.setTotal(0);
			result.setMaxPage(0);
		} else {
			List<RoleDTO> list = JdbcTemplateReadOnly.query(querySQL, paramMap, new BeanPropertyRowMapper<RoleDTO>(RoleDTO.class));
			result.setItems(list);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			result.setMaxPage(total/pc.getPageSize()+1);
		}
		
		return result;
	}
	
}
