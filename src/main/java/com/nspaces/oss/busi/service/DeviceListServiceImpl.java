package com.nspaces.oss.busi.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.base.cache.SimpleDeviceCache;
import com.nspaces.oss.busi.dao.DeviceListDao;
import com.nspaces.oss.busi.domain.DeviceList;
import com.nspaces.oss.busi.dto.DeviceDrvDTO;
import com.nspaces.oss.busi.dto.DeviceListDTO;
import com.nspaces.oss.busi.dto.DeviceListQueryDTO;
import com.nspaces.oss.busi.dto.xml.DeviceCacheDTO;
import com.nspaces.oss.busi.dto.xml.DeviceIODTO;

@Service
public class DeviceListServiceImpl implements DeviceListService {

	@Autowired
	DeviceListDao deviceListDao;
	
	@Override
	public DeviceList insertAndUpdate(DeviceList deviceList) throws Exception {
		return deviceListDao.insertAndUpdate(deviceList);
	}

	@Override
	public DeviceIODTO findDevicePartByName(String ipAddr) throws Exception {
		return deviceListDao.findDevicePartByNameIO(ipAddr);
	}

	@Override
	public DeviceList findDeviceListByName(String ipAddr) throws Exception {
		return deviceListDao.findDeviceListByName(ipAddr);
	}

	@Override
	public List<DeviceDrvDTO> findDevicePartDrvByName(String userName) throws Exception {
		return deviceListDao.findDeviceDRVByName(userName);
	}
	
	@Override
	public boolean findAndCachePartIO() throws Exception
	{
		return deviceListDao.findAllDevicePartIo();
	}


	@Override
	public Pagination<DeviceListDTO> queryDeviceListByPage(
			DeviceListQueryDTO queryDTO) throws Exception {
		return deviceListDao.queryDeviceListByPage(queryDTO);
	}

	@Override
	public Boolean deleteById(Integer id) throws Exception {
		return deviceListDao.deleteById(id);
	}

	
	/**
	 * 查找感应器的设备明细
	 * @param ipAddr
	 * @return
	 * @throws Exception
	 */
	@Override
	public HashMap<Integer, DeviceCacheDTO> findDevicePartIdByDeviceIds(String deviceListIds) throws Exception
	{
		String[] keys = deviceListIds.split(",");
		
		Set<Integer> setKeys = new HashSet<Integer>();
		for(int i=0; i<keys.length;i++)
		{
			setKeys.add(Integer.parseInt(keys[i]));
		}
		return (HashMap<Integer, DeviceCacheDTO>)SimpleDeviceCache.getInstance().getCacheDevice().getAll(setKeys.iterator());
	}

	@Override
	public List<DeviceListDTO> queryAllDeviceListId() throws Exception {
		return deviceListDao.queryAllDeviceListId();
	}
	
	@Override
	public boolean updatePartListTime(Integer deviceListId, String portNum, String startTime, String endTime)
	{
		return deviceListDao.updatePartListTime(deviceListId, portNum, startTime, endTime);
	}
}
