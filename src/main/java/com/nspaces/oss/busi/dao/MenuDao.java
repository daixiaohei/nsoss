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
import com.nspaces.oss.busi.dto.MenuDTO;
import com.nspaces.oss.busi.dto.MenuQueryDTO;

/**
 * 
 * @author qt
 * 
 */
@Repository
public class MenuDao extends BaseDAO {

	public static final Logger logger = LoggerFactory.getLogger(MenuDao.class);

	private String tableName = "memu";// 主表
	private String idColumnName = "id";

	/**
	 * 
	 * 描述：插入和更新菜单信息 qt 创建于 2014年7月17日
	 * 
	 * @param memu
	 * @return
	 * @throws Exception
	 */
	public Memu insertAndUpdate(Memu memu) throws Exception {
		if (null != memu.getId()) {
			super.update(memu, tableName, idColumnName);
		} else {
			Integer retId = super.insertAndReturnId(memu, tableName,
					idColumnName);
			memu.setId(retId);
		}

		return memu;
	}

	/**
	 * 
	 * 描述：删除菜单信息 qt 创建于 2014年7月17日
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteMemu(Integer id) throws Exception {
		if (null != id) {
			return super.deleteById(tableName, id);
		}
		return false;
	}
	
	/**
	 * 得到公共客户库数据
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Pagination<MenuDTO> queryMenuByPage(MenuQueryDTO queryDTO){
	
		logger.debug("start menu query " + queryDTO.toString());
		
		StringBuilder sb = new StringBuilder();
		  sb.append(" select ")
			.append("  u.id,u.name,u.url,u.pic_url,       ")
			.append("  case u.type when 0 then '系统管理' when 1 then '运营管理' else '无' end as type ")
			.append("  from memu as u where  1=1 ")
		    .append(" " + getQueryCondition(queryDTO) + " ");
	       
	
		StringBuilder countSQL = new StringBuilder(); 
		countSQL.append("select count(u.id)")
		         .append(" from memu as u where  1=1 ")
				 .append(" " + getQueryCondition(queryDTO) + " ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySQL = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//得到总记录数
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<MenuDTO> result = new Pagination<MenuDTO>();
		if(total == 0) {			
			result.setItems(new ArrayList<MenuDTO>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
			result.setTotal(0);
			result.setMaxPage(0);
		} else {
			List<MenuDTO> list = JdbcTemplateReadOnly.query(querySQL, paramMap, new BeanPropertyRowMapper<MenuDTO>(MenuDTO.class));
			result.setItems(list);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			result.setMaxPage(total/pc.getPageSize()+1);
		}
		
		return result;
	}
	
	private String getQueryCondition(MenuQueryDTO queryDTO)
	{
		 String strCondition = "";
		  
		  if(StringUtil.isNotEmpty(queryDTO.getName()))
		  {
			  strCondition = strCondition + " and u.name like '%" + queryDTO.getName() + "%'";
		  }
		  
		  return strCondition;
	}

}
