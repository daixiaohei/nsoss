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
import com.nspaces.oss.busi.domain.RoleMenu;
import com.nspaces.oss.busi.domain.UserRole;
import com.nspaces.oss.busi.dto.RoleMenuDTO;
import com.nspaces.oss.busi.dto.UserRoleDTO;
import com.nspaces.oss.busi.dto.UserRoleQueryDTO;

/**
 * 
 * @author qt
 * 
 */
@Repository
public class UserRoleDao extends BaseDAO {

	public static final Logger logger = LoggerFactory.getLogger(UserRoleDao.class);

	private String tableName = "user_role";
	private String idColumnName = "id";

	/**
	 * 
	 * @param userRole
	 * @return
	 * @throws Exception
	 */
	public UserRole editUserRole(UserRole userRole) throws Exception {
		if (null != userRole.getId()) {
			super.update(userRole, tableName, idColumnName);
		} else {
			Integer retId = super.insertAndReturnId(userRole, tableName,
					idColumnName);
			userRole.setId(retId);
		}

		return userRole;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteUserRole(Integer id) throws Exception {
		if (null != id) {
			return super.deleteById(tableName, id);
		}
		return false;
	}
	
	public List<UserRoleDTO> findUserName() throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select id as userId,name as userName from user where status='1'";
		List<UserRoleDTO> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<UserRoleDTO>(UserRoleDTO.class));
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return null;
		}
		
	}
	
	public List<UserRoleDTO> findUserRoleName() throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select id as roleId,name as roleName from role";
		List<UserRoleDTO> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<UserRoleDTO>(UserRoleDTO.class));
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return null;
		}
		
	}
	
	private String getQueryCondition(UserRoleQueryDTO queryDTO)
	{
		 String strCondition = "";
		  
		  if(StringUtil.isNotEmpty(queryDTO.getUserName()))
		  {
			  strCondition = strCondition + " where u.name like '%" + queryDTO.getUserName() + "%'";
		  }
		  
		  return strCondition;
	}
	
	
	/**
	 * 得到公共客户库数据
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Pagination<UserRoleDTO> queryUserRoleByPage(UserRoleQueryDTO queryDTO){
	
		logger.debug("start queryUserRoleByPage query " + queryDTO.toString());
		
		StringBuilder sb = new StringBuilder();
		  sb.append(" select                                      ")
			.append("  ur.*,                                       ")
			.append("  u.name as userName,                        ")
		    .append("  r.name as roleName                         ")
			.append("  from user_role as ur")
			.append("  left join user as u on u.id = ur.user_id")
			.append("  left join role as r on r.id = ur.role_id")
		    .append(" " + getQueryCondition(queryDTO) + " ");
	       
	
		StringBuilder countSQL = new StringBuilder(); 
		countSQL.append("select count(*)")
		         .append(" from user_role as ur  ")
		         .append("  left join user as u on u.id = ur.user_id")
				 .append(" " + getQueryCondition(queryDTO) + " ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySQL = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//得到总记录数
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<UserRoleDTO> result = new Pagination<UserRoleDTO>();
		if(total == 0) {			
			result.setItems(new ArrayList<UserRoleDTO>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
			result.setTotal(0);
			result.setMaxPage(0);
		} else {
			List<UserRoleDTO> list = JdbcTemplateReadOnly.query(querySQL, paramMap, new BeanPropertyRowMapper<UserRoleDTO>(UserRoleDTO.class));
			result.setItems(list);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			result.setMaxPage(total/pc.getPageSize()+1);
		}
		
		return result;
	}

}
