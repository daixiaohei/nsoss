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
import com.nspaces.oss.busi.domain.BusiDeviceSetting;
import com.nspaces.oss.busi.dto.BusiDeviceSettingDTO;
import com.nspaces.oss.busi.dto.BusiDeviceSettingQueryDTO;
@Repository
public class BusiDeviceSettingDao extends BaseDAO{

	public static final Logger logger = LoggerFactory.getLogger(BusiDeviceSettingDao.class);
	private String tableName = "busi_device_setting";
	private String idColumnName = "id";
	
	
	/**
	 * 插入或者更新网点信息
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public BusiDeviceSetting insertAndUpdate(BusiDeviceSetting setting) throws Exception{
	
		if(null!=setting.getId()){
			super.update(setting, tableName, idColumnName);
		}else{
			Integer retId = super.insertAndReturnId(setting, tableName, idColumnName);
			setting.setId(retId);			
		}
		return setting;
	}
	
	public Boolean deleteById(Integer id) throws Exception{
		if(null!=id){
			return super.deleteById(tableName, id);
		}else{
			return false;
		}
	}
	
	public BusiDeviceSetting findBusiDeviceSettingByMerchNo(String merchNo) throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String sql = "select * from busi_device_setting  where merchNo=:merchNo";
		paramMap.put("merchNo", merchNo);
		
		List<BusiDeviceSetting> list = jdbcTemplate.query(sql.toString(), paramMap,new BeanPropertyRowMapper<BusiDeviceSetting>(BusiDeviceSetting.class));
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
		
	}


	private String queryCondition(BusiDeviceSettingQueryDTO queryDTO){
		
		String strCondition = "";
		if(null!=queryDTO.getMerchNo()){
			strCondition += " and b.merch_no= '"+queryDTO.getMerchNo()+"' "; 
		}

		
		return strCondition;
		
	}
	
	
	public Pagination<BusiDeviceSettingDTO> queryBusiDeviceSettingByPage(BusiDeviceSettingQueryDTO queryDTO){
		
		logger.debug("query busiDeviceSetting start:"+queryDTO.toString());
		StringBuilder sb = new StringBuilder();
		sb.append("select b.* from busi_device_setting as b           ");
		sb.append("where 1=1");
		sb.append(" "+queryCondition(queryDTO)+" ");
		
		StringBuilder countSQL = new StringBuilder();
		countSQL.append("select count(b.id) from busi_device_setting as b ");
		countSQL.append("where 1=1");
		countSQL.append(" "+queryCondition(queryDTO)+" ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySql = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<BusiDeviceSettingDTO> result = new Pagination<BusiDeviceSettingDTO>();
		if(total==0){
			result.setTotal(0);
			result.setItems(new ArrayList<BusiDeviceSettingDTO>());
			result.setMaxPage(0);
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
		}else{
			List<BusiDeviceSettingDTO> cityList = JdbcTemplateReadOnly.query(querySql, paramMap, new BeanPropertyRowMapper(BusiDeviceSettingDTO.class));
			
			result.setItems(cityList);
			result.setMaxPage(total/pc.getPageSize()+1);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			
		}
		
		return result;
	}
	
	
}
