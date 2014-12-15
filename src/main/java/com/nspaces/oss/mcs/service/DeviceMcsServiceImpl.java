package com.nspaces.oss.mcs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.mcs.dao.DeviceMongoBaseDAO;
import com.nspaces.oss.mcs.domain.DeviceMcs;
import com.nspaces.oss.mcs.domain.DeviceOut;

@Service
public class DeviceMcsServiceImpl implements DeviceMcsService {

	@Autowired
	private DeviceMongoBaseDAO deviceMongoBaseDAO;
	
	@Override
	public void insertDeviceMcs(DeviceMcs deviceMcs) throws Exception {
		deviceMongoBaseDAO.insertDeviceMcs(deviceMcs);
	}

	@Override
	public void insertDeviceOut(DeviceOut deviceOut) throws Exception {
		// TODO Auto-generated method stub
		deviceMongoBaseDAO.insertDeviceOut(deviceOut);
	}
	
	@Override
	public List<DeviceMcs> findDeviceMcs(String deviceListIds, long dateTime) throws Exception
	{
		String[] arrStr = deviceListIds.split(",");
		List<Integer> listIDS = new ArrayList<Integer>();
		for(int i=0;i<arrStr.length;i++)
		{
			listIDS.add(Integer.parseInt(arrStr[i]));
		}
		return deviceMongoBaseDAO.findAllBy(listIDS, dateTime);
	}
}
