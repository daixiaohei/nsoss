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
import com.nspaces.oss.busi.domain.Memu;
import com.nspaces.oss.busi.domain.User;
import com.nspaces.oss.busi.dto.UserDTO;
import com.nspaces.oss.busi.dto.UserQueryDTO;


/**
 * 后台用户，运维角色，营养师
 * @author 628
 *
 */
@Repository
public class UserDao extends BaseDAO {

	public static final Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	private String tableName = "user";//主表
	private String idColumnName = "id";

	public boolean deleteUserByLogic(User user){
		if(null != user.getId()){
			return super.deleteByLogic(tableName, user.getId());
		}
		return false;
	}
	
	/**
	 * 
	 * 描述：插入和更新用户信息
	 * whw  创建于 2014年5月17日
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User insertAndUpdate(User user) throws Exception
	{
		if(null != user.getId())
		{
			super.update(user, tableName, idColumnName);
		}else
		{
			Integer retId = super.insertAndReturnId(user, tableName, idColumnName);
			user.setId(retId);
		}
		
		return user;
	}
	
	/**
	 * 
	 * 描述： 根据用户名查找用户
	 * whw  创建于 2014年5月17日
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public User findUserByName(String name) throws Exception
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select * from user where name=:userName and status > 0";
		paramMap.put("userName", name);
		List<User> retList = jdbcTemplate.query(sql.toString(), paramMap, new BeanPropertyRowMapper<User>(User.class));
		
		if(retList!=null && retList.size()>0){
			 return retList.get(0);
		}else{
			 return null;
		}
		
	}
	
	/**
	 * 
	 * 描述： 根据用户id得到菜单名
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Memu> loadMemu(Integer userId)
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "SELECT m.name,m.url,m.type from nstms.user u left join  nstms.user_role ur on u.id=ur.user_id left join  nstms.role_menu rm on ur.role_id=rm.role_id left join nstms.memu m on m.id=rm.menu_id where u.id=:userId";
		paramMap.put("userId", userId);
		List<Memu> retList = jdbcTemplate.query(sql.toString(), paramMap, new BeanPropertyRowMapper<Memu>(Memu.class));
		
		if(retList!=null && retList.size()>0){
			 return retList;
		}else{
			 return null;
		}
	}
	
	private String getQueryCondition(UserQueryDTO queryDTO)
	{
		 String strCondition = "";
		  if(null != queryDTO.getDeptId() && 0 != queryDTO.getDeptId())
		  {
			  strCondition = strCondition + " and u.dept_id = " + queryDTO.getDeptId();
		  }
		  
		  if(StringUtil.isNotEmpty(queryDTO.getName()))
		  {
			  strCondition = strCondition + " and u.name like '%" + queryDTO.getName() + "%'";
		  }
		  
		  return strCondition;
	}
	
	
	/**
	 * 得到公共客户库数据
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Pagination<UserDTO> queryUserByPage(UserQueryDTO queryDTO){
	
		logger.debug("start user query " + queryDTO.toString());
		
		StringBuilder sb = new StringBuilder();
		  sb.append(" select                                               ")
			.append("  u.*                                                 ")
			//.append("  dp.name as dept_name                                ")
			//.append("  from user as u left join  department as dp on u.dept_id = dp.id where  u.status > -1 ")
			.append("  from user as u where  u.status > -1 ")
		    .append(" " + getQueryCondition(queryDTO) + " ");
	       
	
		StringBuilder countSQL = new StringBuilder(); 
		countSQL.append("select count(u.id)")
		         //.append(" from user as u left join  department as dp on u.dept_id = dp.id where  u.status > -1 ")
		         .append(" from user as u where  u.status > -1 ")
				 .append(" " + getQueryCondition(queryDTO) + " ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySQL = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//得到总记录数
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<UserDTO> result = new Pagination<UserDTO>();
		if(total == 0) {			
			result.setItems(new ArrayList<UserDTO>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
			result.setTotal(0);
			result.setMaxPage(0);
		} else {
			List<UserDTO> list = JdbcTemplateReadOnly.query(querySQL, paramMap, new BeanPropertyRowMapper<UserDTO>(UserDTO.class));
			result.setItems(list);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			result.setMaxPage(total/pc.getPageSize()+1);
		}
		
		return result;
	}
	
	
	
}
