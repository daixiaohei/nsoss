package com.nspaces.oss.busi.service;

import java.util.HashMap;
import java.util.List;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.DeviceList;
import com.nspaces.oss.busi.dto.DeviceDrvDTO;
import com.nspaces.oss.busi.dto.DeviceListDTO;
import com.nspaces.oss.busi.dto.DeviceListQueryDTO;
import com.nspaces.oss.busi.dto.xml.DeviceCacheDTO;
import com.nspaces.oss.busi.dto.xml.DeviceIODTO;

public interface DeviceListService {

	/**
	 * 插入或者更新DeviceList
	 * @param deviceList
	 * @return
	 * @throws Exception
	 */
	public DeviceList insertAndUpdate(DeviceList deviceList) throws Exception;
	
	/**
	 * 查找感应器的设备明细
	 * @param ipAddr
	 * @return
	 * @throws Exception
	 */
	public DeviceIODTO findDevicePartByName(String ipAddr) throws Exception;
	
	/**
	 * 根据机器名称查询机器信息
	 * @param ipAddr
	 * @return
	 * @throws Exception
	 */
	public DeviceList findDeviceListByName(String ipAddr) throws Exception;
	
	
	/**
	 * 查找感应器的设备明细
	 * @param ipAddr
	 * @return
	 * @throws Exception
	 */
	public List<DeviceDrvDTO> findDevicePartDrvByName(String userName) throws Exception;
	
	
	/**
	 * 查找感应器的设备明细
	 * @param ipAddr
	 * @return
	 * @throws Exception
	 */
	public HashMap<Integer, DeviceCacheDTO> findDevicePartIdByDeviceIds(String deviceListIds) throws Exception;
	
	
	/**
	 * 缓存感应器
	 * @param ipAddr
	 * @return
	 * @throws Exception
	 */
	public boolean findAndCachePartIO() throws Exception;
	
	/**
	 * 分页查询
	 * @param queryDTO
	 * @return
	 * @throws Exception
	 */
	public  Pagination<DeviceListDTO> queryDeviceListByPage(DeviceListQueryDTO queryDTO) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Boolean deleteById(Integer id) throws Exception;
	
	/**
	 * 
	 */
	public List<DeviceListDTO> queryAllDeviceListId() throws Exception;
	
	
	public boolean updatePartListTime(Integer deviceListId, String portNum, String startTime, String endTime);
	
}
