package com.nspaces.oss.busi.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.nspaces.oss.base.cache.SimpleDeviceCache;
import com.nspaces.oss.base.dao.BaseDAO;
import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.base.dto.PaginationCondition;
import com.nspaces.oss.base.util.PaginationUtil;
import com.nspaces.oss.busi.domain.DeviceList;
import com.nspaces.oss.busi.dto.DeviceDrvDTO;
import com.nspaces.oss.busi.dto.DeviceListDTO;
import com.nspaces.oss.busi.dto.DeviceListQueryDTO;
import com.nspaces.oss.busi.dto.PartDrvDTO;
import com.nspaces.oss.busi.dto.xml.DeviceCacheDTO;
import com.nspaces.oss.busi.dto.xml.DeviceIODTO;
import com.nspaces.oss.busi.dto.xml.PartDTO;

/**
 * 2014-07-16
 * @author whw
 *
 */
@Repository
public class DeviceListDao extends BaseDAO {
	
	private static final String deviceListTableName = "device_list";
	private static final String idColumnName = "id";//主键
	
	
	/**
	 * 
	 * whw  创建于 2014年7月17日
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public DeviceList findDeviceListByName(String ipAddr) throws Exception
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select * from device_list where ip_addr=:ipAddr";
		paramMap.put("ipAddr", ipAddr);
		List<DeviceList> retList = jdbcTemplate.query(sql.toString(), paramMap, new BeanPropertyRowMapper<DeviceList>(DeviceList.class));
		
		if(retList!=null && retList.size()>0){
			 return retList.get(0);
		}else{
			 return null;
		}
	}
	
	/**
	 * 插入和更新对象
	 * @param deviceList
	 * @return
	 * @throws Exception
	 */
	public DeviceList insertAndUpdate(DeviceList deviceList) throws Exception
	{
		if(null != deviceList.getId())
		{
			super.update(deviceList, deviceListTableName, idColumnName);
		}else
		{
			Integer retId = super.insertAndReturnId(deviceList, deviceListTableName, idColumnName);
			deviceList.setId(retId);
		}
		
		return deviceList;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Boolean deleteById(Integer id) throws Exception{
		if(null!=id){
			return super.deleteById(deviceListTableName, id);
		}else{
			return false;
		}
	}
	/**
	 * 查询条件
	 * @param queryDTO
	 * @return
	 */
	private String queryCondition(DeviceListQueryDTO queryDTO){
		
		String strCondition = "";
		if(null!=queryDTO.getName()){
			strCondition += " and d.name like '%"+queryDTO.getName()+"%' "; 
		}
		if(null!=queryDTO.getDeviceType()){
			strCondition += " and d.device_type= '"+queryDTO.getDeviceType()+"' ";
		}
		if(null!=queryDTO.getIpAddr()){
			strCondition += " and d.ip_addr= '"+queryDTO.getIpAddr()+"' ";
		}
		if(null!=queryDTO.getRemoteAddr()){
			strCondition += " and d.remote_addr= '"+queryDTO.getRemoteAddr()+"' ";
		}
		
		return strCondition;
		
	}
	
	/**
	 * 分页查询
	 * @param queryDTO
	 * @return
	 */
	public Pagination<DeviceListDTO> queryDeviceListByPage(DeviceListQueryDTO queryDTO){
		
		logger.debug("query deviceList start:"+queryDTO.toString());
		StringBuilder sb = new StringBuilder();
		sb.append("select d.* from device_list as d           ");
		sb.append("where 1=1");
		sb.append(" "+queryCondition(queryDTO)+" ");
		
		StringBuilder countSQL = new StringBuilder();
		countSQL.append("select count(d.id) from device_list as d ");
		countSQL.append("where 1=1");
		countSQL.append(" "+queryCondition(queryDTO)+" ");
		
		//加入分页查询部分
		PaginationCondition pc = queryDTO.getPaginationCondition();
		String querySql = PaginationUtil.translatePaginationSQL(sb.toString(), pc.getOffset(), pc.getPageSize());
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		int total = JdbcTemplateReadOnly.queryForInt(countSQL.toString(), paramMap);
		
		Pagination<DeviceListDTO> result = new Pagination<DeviceListDTO>();
		if(total==0){
			result.setTotal(0);
			result.setItems(new ArrayList<DeviceListDTO>());
			result.setMaxPage(0);
			result.setPage(PaginationCondition.DEFAULT_PAGE);
			result.setPageSize(pc.getPageSize());
		}else{
			List<DeviceListDTO> cityList = JdbcTemplateReadOnly.query(querySql, paramMap, new BeanPropertyRowMapper(DeviceListDTO.class));
			
			result.setItems(cityList);
			result.setMaxPage(total/pc.getPageSize()+1);
			result.setPage(pc.getPage());
			result.setPageSize(pc.getPageSize());
			result.setTotal(total);
			
		}
		
		return result;
	}
	
	/**
	 * 
	 * whw  创建于 2014年7月17日
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public DeviceIODTO findDevicePartByNameIO(String ipAddr) throws Exception
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select * from device_list where ip_addr=:ipAddr and device_type=2 ";
		paramMap.put("ipAddr", ipAddr);
		List<DeviceList> retList = jdbcTemplate.query(sql.toString(), paramMap, new BeanPropertyRowMapper<DeviceList>(DeviceList.class));
		
		DeviceList curDeviceList=null;
		if(retList!=null && retList.size()>0){
			curDeviceList = retList.get(0);
		}else{
			 return null;
		}
		
		//查找子设备信息2输入设备， 4输出设备
		Map<String, Object> paramPartMap = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" select ppt.id as part_id, ppt.`name` as part_name, ppt.part_no, pl.port_num ,ppt.part_type,ppt.time_set, pl.start_time,pl.end_time, ppt.level, pl.push,pl.vb_addr,pl.time_id ");
		sb.append(" from part_list pl left join part ppt on pl.part_id = ppt.id                    ");
		sb.append(" where pl.device_list_id =:deviceListId and (ppt.part_type = 2 or ppt.part_type = 4) order by ppt.part_type, port_num                     ");
		paramPartMap.put("deviceListId", curDeviceList.getId());
		
		List<PartDTO> partList = jdbcTemplate.query(sb.toString(), paramPartMap, new BeanPropertyRowMapper<PartDTO>(PartDTO.class));
		
		DeviceIODTO deviceIODTO = new DeviceIODTO();
		deviceIODTO.setDeviceListId(curDeviceList.getId());
		deviceIODTO.setIpAddr(curDeviceList.getIpAddr());
		deviceIODTO.setRemoteAddr(curDeviceList.getRemoteAddr());
		deviceIODTO.setDeviceName(curDeviceList.getName());
		deviceIODTO.setCpu(curDeviceList.getLogicNo());//设置PLC的CPU的信号
	
		if(partList!=null && partList.size()>0){
			deviceIODTO.setPartDTO(partList);
			deviceIODTO.setPartCount(partList.size());
		}
		
		return deviceIODTO;
	}
	
	
	/**
	 * 
	 * whw  创建于 2014年7月17日
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<DeviceDrvDTO> findDeviceDRVByName(String userName) throws Exception
	{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		StringBuffer sbd = new StringBuffer();

		sbd.append("SELECT * FROM device_list                                                                                "); 
		sbd.append(" WHERE spot_id IN ( SELECT sp.id FROM spot sp, user usr, user_data ud, city ct WHERE usr.id = ud.user_id AND usr.name = :userName and sp.city_no = ct.city_no and ct.id = ud.city_id ) "); 
		sbd.append(" AND device_type = 2                                                                               ");
		paramMap.put("userName", userName);
		List<DeviceList> retList = jdbcTemplate.query(sbd.toString(), paramMap, new BeanPropertyRowMapper<DeviceList>(DeviceList.class));
		
		
		if(retList!=null && retList.size()>0){
			
		}else{
			 return null;
		}
		
		//查找子设备信息
		Map<String, Object> paramPartMap = new HashMap<String, Object>();
		
		List<DeviceDrvDTO> listDeviceDrv = new ArrayList<DeviceDrvDTO>();
	
		for(DeviceList curDeviceList : retList)	
		{
			StringBuffer sb = new StringBuffer();
			sb.append(" select ppt.id as part_id, ppt.`name` as part_name, ppt.part_no,pl.part_name_nick, pl.port_num ,ppt.level, pl.push ,pl.vb_addr ");
			sb.append(" from part_list pl left join part ppt on pl.part_id = ppt.id                 ");
			sb.append(" where pl.device_list_id =:deviceListId and ppt.part_type = 3 order by port_num                     ");
			paramPartMap.put("deviceListId", curDeviceList.getId());
			
			List<PartDrvDTO> partList = jdbcTemplate.query(sb.toString(), paramPartMap, new BeanPropertyRowMapper<PartDrvDTO>(PartDrvDTO.class));
			
			DeviceDrvDTO deviceDrvDTO = new DeviceDrvDTO();
			deviceDrvDTO.setName(curDeviceList.getName());
			deviceDrvDTO.setIpAddr(curDeviceList.getIpAddr());
			deviceDrvDTO.setRemoteAddr(curDeviceList.getRemoteAddr());
			deviceDrvDTO.setDrvUsername(curDeviceList.getDrvUsername());
			deviceDrvDTO.setDrvPwd(curDeviceList.getDrvPwd());
			deviceDrvDTO.setId(String.valueOf(curDeviceList.getId()));
			deviceDrvDTO.setRemotePort(curDeviceList.getRemotePort());
			
			if(partList!=null && partList.size()>0){
				deviceDrvDTO.setPartDrvs(partList);
			}
			
			listDeviceDrv.add(deviceDrvDTO);
		}
		
		return listDeviceDrv;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean findAllDevicePartIo() throws Exception
	{
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select * from device_list where device_type=2 ";
		
		List<DeviceList> retList = jdbcTemplate.query(sql.toString(), paramMap, new BeanPropertyRowMapper<DeviceList>(DeviceList.class));
		
		
		if(retList!=null && retList.size()>0){
			
		}else{
			 return false;
		}
		
		//查找子设备信息
		Map<String, Object> paramPartMap = new HashMap<String, Object>();

		for(DeviceList curDeviceList : retList)	
		{
			StringBuffer sb = new StringBuffer();
			sb.append(" select ppt.id as part_id, ppt.`name` as part_name,ppt.value0_name, ppt.value1_name, ppt.part_no,pl.part_name_nick, pl.port_num,ppt.part_type,ppt.time_set,pl.start_time, pl.end_time, ppt.level, pl.push,pl.vb_addr,pl.time_id ");
			sb.append(" from part_list pl left join part ppt on pl.part_id = ppt.id                 ");
			sb.append(" where pl.device_list_id =:deviceListId and (ppt.part_type = 2 or ppt.part_type = 4) order by ppt.part_type, port_num                     ");
			paramPartMap.put("deviceListId", curDeviceList.getId());
			
			List<PartDTO> partList = jdbcTemplate.query(sb.toString(), paramPartMap, new BeanPropertyRowMapper<PartDTO>(PartDTO.class));
			
			DeviceCacheDTO deviceCacheDTO = new DeviceCacheDTO();
			deviceCacheDTO.setName(curDeviceList.getName());
			deviceCacheDTO.setIpAddr(curDeviceList.getIpAddr());
			deviceCacheDTO.setRemoteAddr(curDeviceList.getRemoteAddr());
			deviceCacheDTO.setDeviceListId(curDeviceList.getId());
			
			if(partList!=null && partList.size()>0){
				for(PartDTO partDTO:partList)
				{
					deviceCacheDTO.getMapPart().put(partDTO.getPortNum(), partDTO);
				}
			}
			
			logger.info("Id:" + curDeviceList.getId() + " " + deviceCacheDTO.toString());
			//cache
			SimpleDeviceCache.getInstance().getCacheDevice().put(curDeviceList.getId(), deviceCacheDTO);
		}
		
		return true;
	}
	
	/**
	 * 更新设备时间
	 * @param deviceListId
	 * @param portNum
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean updatePartListTime(Integer deviceListId, String portNum, String startTime, String endTime)
	{
		  StringBuilder sb = new StringBuilder();
		  sb.append(" update part_list set start_time = :startTime, end_time = :endTime where device_list_id = :deviceListId and port_num = :portNum  ");
		  Map<String, Object> paramMap = new HashMap<String, Object>();
		  paramMap.put("startTime", startTime);
		  paramMap.put("endTime", endTime);
		  paramMap.put("deviceListId", deviceListId);
		  paramMap.put("portNum", portNum);
		  
		  int rowNum = jdbcTemplate.update(sb.toString(),paramMap);
		  return rowNum > 0;
	}
	
	public List<DeviceListDTO> queryAllDeviceListId() {
		
		StringBuilder sb = new StringBuilder();
		  sb.append(" select                                                         ")
			.append("  dl.id,dl.name                                                 ")
			.append("  from device_list as dl ");
		  Map<String, Object> paramMap = new HashMap<String, Object>();
		  List<DeviceListDTO> list = JdbcTemplateReadOnly.query(sb.toString(), paramMap, new BeanPropertyRowMapper<DeviceListDTO>(DeviceListDTO.class));
		  if(list!=null){
			  return list;
		  }
		  return null;
	}
	
	
	
}
