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
import com.nspaces.oss.busi.domain.BusiGroupDevice;
import com.nspaces.oss.busi.dto.BusiGroupDeviceDTO;
import com.nspaces.oss.busi.dto.BusiGroupDeviceQueryDTO;
import com.nspaces.oss.busi.dto.RoleMenuDTO;
import com.nspaces.oss.busi.dto.RoleMenuQueryDTO;

/**
 * 
 * @author qt
 * 
 */
@Repository
public class BusiGroupDeviceDao extends BaseDAO {

	public static final Logger logger = LoggerFactory.getLogger(BusiGroupDeviceDao.class);

	private String tableName = "busi_group_device";//
	private String idColumnName = "id";

	/**
	 * 
	 * @param organize
	 * @return
	 * @throws Exception
	 */
	public BusiGroupDevice editBusiGroupDevice(BusiGroupDevice busiGroupDevice) throws Exception {
		if (null != busiGroupDevice.getId()) {
			super.update(busiGroupDevice, tableName, idColumnName);
		} else {
			Integer retId = super.insertAndReturnId(busiGroupDevice, tableName,idColumnName);
			busiGroupDevice.setId(retId);
		}

		return busiGroupDevice;
	}

	
	public List<BusiGroupDeviceDTO> findBuaiGroupName() throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select id as busiGroupId,name as busiGroupName from busi_group";
		List<BusiGroupDeviceDTO> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<BusiGroupDeviceDTO>(BusiGroupDeviceDTO.class));
		if(list!=null&&list.size()>0){
			return list;
		}else{
			return null;
		}
		
	}
	
	public List<BusiGroupDeviceDTO> findDeviceListName() throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select id as deviceListId,name as deviceListName from device_list";
		List<BusiGroupDeviceDTO> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<BusiGroupDeviceDTO>(BusiGroupDeviceDTO.class));
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
	public boolean deleteBusiGroupDevice(Integer id) throws Exception {
		if (null != id) {
			return super.deleteById(tableName, id);
		}
		return false;
	}
	
	
	private String getQueryCondition(BusiGroupDeviceQueryDTO queryDTO)
	{
		 String strCondition = "";
		  
		  if(StringUtil.isNotEmpty(queryDTO.getBusiGroupName()))
		  {
			  strCondition = strCondition + " where b.name like '%" + queryDTO.getBusiGroupName() + "%'";
		  }
		  
		  return strCondition;
	}
	
	
	/**
	 * 得到公共客户库数据
	 * @param queryDTO
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Pagination<BusiGroupDeviceDTO> queryBusiGroupDeviceByPage(BusiGroupDeviceQueryDTO queryDTO){
	
		logger.debug("start BusiGroupDevice query " + queryDTO.toString());
		
		StringBuilder sb = new StringBuilder();
		  sb.append(" select                                      ")
			.append("  bd.*,                                       ")
			.append("  b.name as busiGroupName,                        ")
		    .append("  d.name as deviceListName                         ")
			.append("  from busi_group_device as bd")
			.append("  left join busi_group as b on b.id = bd.busi_group_id")
			.append("  left join device_list as d on d.id = bd.device_list_id")
		    .append(" " + getQueryCondition(queryDTO) + " ");
	       
	
		StringBuilder countSQL = new StringBuilder(); 
		countSQL.append("select count(*)")
		         .append(" from busi_group_device as bd  ")
		         .append("  left join busi_group as b on b.id = bd.busi_group_id")
				 .append(" " + getQueryCondition(queryDTO) + " ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySQL = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//得到总记录数
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<BusiGroupDeviceDTO> result = new Pagination<BusiGroupDeviceDTO>();
		if(total == 0) {			
			result.setItems(new ArrayList<BusiGroupDeviceDTO>());
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
			result.setTotal(0);
			result.setMaxPage(0);
		} else {
			List<BusiGroupDeviceDTO> list = JdbcTemplateReadOnly.query(querySQL, paramMap, new BeanPropertyRowMapper<BusiGroupDeviceDTO>(BusiGroupDeviceDTO.class));
			result.setItems(list);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			result.setMaxPage(total/pc.getPageSize()+1);
		}
		
		return result;
	}

}
