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
import com.nspaces.oss.busi.dto.RoleMenuDTO;
import com.nspaces.oss.busi.dto.RoleMenuQueryDTO;
import com.nspaces.oss.busi.dto.UserDTO;
import com.nspaces.oss.busi.dto.UserQueryDTO;

/**
 * 
 * @author qt
 * 
 */
@Repository
public class RoleMenuDao extends BaseDAO {

	public static final Logger logger = LoggerFactory.getLogger(RoleMenuDao.class);

	private String tableName = "role_menu";//
	private String idColumnName = "id";

	/**
	 * 
	 * @param organize
	 * @return
	 * @throws Exception
	 */
	public RoleMenu editRoleMenu(RoleMenu roleMenu) throws Exception {
		if (null != roleMenu.getId()) {
			super.update(roleMenu, tableName, idColumnName);
		} else {
			Integer retId = super.insertAndReturnId(roleMenu, tableName,idColumnName);
			roleMenu.setId(retId);
		}

		return roleMenu;
	}

	
	public List<RoleMenuDTO> findRoleName() throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select id as roleId,name as roleName from role";
		List<RoleMenuDTO> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<RoleMenuDTO>(RoleMenuDTO.class));
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return null;
		}
		
	}
	
	public List<RoleMenuDTO> findMenuName() throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select id as menuId,name as menuName from memu";
		List<RoleMenuDTO> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<RoleMenuDTO>(RoleMenuDTO.class));
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
	public boolean deleteRoleMenu(Integer id) throws Exception {
		if (null != id) {
			return super.deleteById(tableName, id);
		}
		return false;
	}
	
	
	private String getQueryCondition(RoleMenuQueryDTO queryDTO)
	{
		 String strCondition = "";
		  
		  if(StringUtil.isNotEmpty(queryDTO.getRoleName()))
		  {
			  strCondition = strCondition + " where r.name like '%" + queryDTO.getRoleName() + "%'";
		  }
		  
		  return strCondition;
	}
	
	
	/**
	 * 得到公共客户库数据
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Pagination<RoleMenuDTO> queryRoleMenuByPage(RoleMenuQueryDTO queryDTO){
	
		logger.debug("start roleMenu query " + queryDTO.toString());
		
		StringBuilder sb = new StringBuilder();
		  sb.append(" select                                      ")
			.append("  rm.*,                                       ")
			.append("  m.name as menuName,                        ")
		    .append("  r.name as roleName                         ")
			.append("  from role_menu as rm")
			.append("  left join memu as m on m.id = rm.menu_id")
			.append("  left join role as r on r.id = rm.role_id")
		    .append(" " + getQueryCondition(queryDTO) + " ");
	       
	
		StringBuilder countSQL = new StringBuilder(); 
		countSQL.append("select count(*)")
		         .append(" from role_menu as rm  ")
		         .append("  left join role as r on r.id = rm.role_id")
				 .append(" " + getQueryCondition(queryDTO) + " ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySQL = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//得到总记录数
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<RoleMenuDTO> result = new Pagination<RoleMenuDTO>();
		if(total == 0) {			
			result.setItems(new ArrayList<RoleMenuDTO>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
			result.setTotal(0);
			result.setMaxPage(0);
		} else {
			List<RoleMenuDTO> list = JdbcTemplateReadOnly.query(querySQL, paramMap, new BeanPropertyRowMapper<RoleMenuDTO>(RoleMenuDTO.class));
			result.setItems(list);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			result.setMaxPage(total/pc.getPageSize()+1);
		}
		
		return result;
	}

}
