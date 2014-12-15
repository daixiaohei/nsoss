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
import com.nspaces.oss.busi.domain.Business;
import com.nspaces.oss.busi.dto.BusinessDTO;
import com.nspaces.oss.busi.dto.BusinessQueryDTO;
import com.nspaces.oss.busi.dto.RoleDTO;


/**
 * 
 * @author qt
 *
 */
@Repository
public class BusinessDao extends BaseDAO {

	public static final Logger logger = LoggerFactory.getLogger(BusinessDao.class);
	
	private String tableName = "business";//主表
	private String idColumnName = "id";

	
	/**
	 * 
	 */
	public List<BusinessDTO> queryAllBusinessId() throws Exception{
		StringBuilder sb = new StringBuilder();
		  sb.append(" select                                               ")
			.append("  b.id  ,b.name                                               ")
			.append("  from business as b ");
		  Map<String, Object> paramMap = new HashMap<String, Object>();
		  List<BusinessDTO> list = JdbcTemplateReadOnly.query(sb.toString(), paramMap, new BeanPropertyRowMapper<BusinessDTO>(BusinessDTO.class));
		  if(list!=null){
			  return list;
		  }
		  return null;
	}
	/**
	 * 
	 * 描述：插入和更新业务信息
	 * qt  创建于 2014年7月17日
	 * @param Business
	 * @return
	 * @throws Exception
	 */
	public Business editBusiness(Business business) throws Exception
	{
		if(null != business.getId())
		{
			super.update(business, tableName, idColumnName);
		}else
		{
			Integer retId = super.insertAndReturnId(business, tableName, idColumnName);
			business.setId(retId);
		}
		
		return business;
	}
	
	/**
	 * 
	 * 删除业务信息
	 * qt  创建于 2014年7月17日
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteBusiness(Integer id) throws Exception
	{
		if(null!=id){
		return super.deleteByLogic(tableName, id);
		}
		return false;
	
	}
	
	
	private String getQueryCondition(BusinessQueryDTO queryDTO)
	{
		 String strCondition = "";
		  
		  if(StringUtil.isNotEmpty(queryDTO.getName()))
		  {
			  strCondition = strCondition + " and b.name like '%" + queryDTO.getName() + "%'";
		  }
		  
		  return strCondition;
	}
	/**
	 * 得到公共客户库数据
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Pagination<BusinessDTO> queryBusinessByPage(BusinessQueryDTO queryDTO){
	
		logger.debug("start business query " + queryDTO.toString());
		
		StringBuilder sb = new StringBuilder();
		  sb.append(" select                                              ")
			.append("  b.id,b.name,b.busi_no as busiNo,b.memo,b.url,b.logo,b.parent_id as parentId,b.sort_num as sortNum,")
			.append("  case b.leaf when 0 then '是' when 1 then '否' end as leaf, ")
			.append("  case b.parent_id when 1 then '广佛通' when 0 then '无' end as parent_id ")
			.append("  from business as b ")
		    .append(" where b.status > -1 " + getQueryCondition(queryDTO) + " ");
	       
	
		StringBuilder countSQL = new StringBuilder(); 
		countSQL.append("select count(b.id)")
		         .append(" from business as b")
				 .append(" where  b.status > -1 " + getQueryCondition(queryDTO) + " ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySQL = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//得到总记录数
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<BusinessDTO> result = new Pagination<BusinessDTO>();
		if(total == 0) {			
			result.setItems(new ArrayList<BusinessDTO>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
			result.setTotal(0);
			result.setMaxPage(0);
		} else {
			List<BusinessDTO> list = JdbcTemplateReadOnly.query(querySQL, paramMap, new BeanPropertyRowMapper<BusinessDTO>(BusinessDTO.class));
			result.setItems(list);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			result.setMaxPage(total/pc.getPageSize()+1);
		}
		
		return result;
	}
	
}
