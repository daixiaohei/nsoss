package com.nspaces.oss.mcs.service;

import java.util.Date;
import java.util.List;

import com.nspaces.oss.mcs.domain.DeviceLog;
import com.nspaces.oss.mcs.domain.DeviceMcs;
import com.nspaces.oss.mcs.domain.DeviceOut;

public interface DeviceMcsService {
	public void insertDeviceMcs(DeviceMcs deviceMcs) throws Exception;
	/**
	 * 
	 * @param deviceMcs
	 * @throws Exception
	 */
	public void insertDeviceOut(DeviceOut deviceOut) throws Exception;
	
	/**
	 * 
	 * @param deviceMcs
	 * @throws Exception
	 */
	public void updateDeviceLog(Integer deviceListId) throws Exception;
	
	
	//
	public List<DeviceMcs> findDeviceMcs(String deviceListIds, long dateTime) throws Exception;
	
	//
	public List<DeviceLog> findAllByDeviceLog(List<Integer> deviceListIds, long dateTime, int level);
}
