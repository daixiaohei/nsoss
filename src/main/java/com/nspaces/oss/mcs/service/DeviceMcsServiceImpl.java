package com.nspaces.oss.mcs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.base.cache.SimpleDeviceCache;
import com.nspaces.oss.base.util.DateUtil;
import com.nspaces.oss.busi.dto.xml.DeviceCacheDTO;
import com.nspaces.oss.busi.dto.xml.PartDTO;
import com.nspaces.oss.mcs.control.DeviceMcsController;
import com.nspaces.oss.mcs.dao.DeviceMongoBaseDAO;
import com.nspaces.oss.mcs.domain.DeviceLog;
import com.nspaces.oss.mcs.domain.DeviceMcs;
import com.nspaces.oss.mcs.domain.DeviceOut;
import com.nspaces.oss.mcs.domain.PortData;

@Service
public class DeviceMcsServiceImpl implements DeviceMcsService {

	public static final Logger logger = LoggerFactory.getLogger(DeviceMcsController.class);
	
	@Autowired
	private DeviceMongoBaseDAO deviceMongoBaseDAO;
	
	@Override
	public void insertDeviceMcs(DeviceMcs deviceMcs) throws Exception {
		deviceMongoBaseDAO.insertDeviceMcs(deviceMcs);
		
		
		//记录级别历史记录，方便后续查询
		try
		{
		DeviceCacheDTO curCacheDTO = SimpleDeviceCache.getInstance().getCacheDevice().get(deviceMcs.getDeviceListId());
		
		if(null != curCacheDTO)
		{
			for(PortData curD:deviceMcs.getDatas())
			{
				PartDTO curPartDTO = curCacheDTO.getMapPart().get(curD.getPortNum());
				
				if(curD.getPortValue() == curPartDTO.getPush() && curPartDTO.getLevel() >= 1)
				{
					DeviceLog deviceLog = new DeviceLog();
					deviceLog.setDeviceListId(curCacheDTO.getDeviceListId());
					deviceLog.setDeviceName(curCacheDTO.getName());
					deviceLog.setPartNum(curPartDTO.getPortNum());
					deviceLog.setPartName(curPartDTO.getPartName());
					deviceLog.setLevel(curPartDTO.getLevel());
					deviceLog.setPartValue(curD.getPortValue());
					if(curD.getPortValue() == 0)
						deviceLog.setPartValueName(curPartDTO.getValue0Name());
					else
						deviceLog.setPartValueName(curPartDTO.getValue1Name());
					deviceLog.setCreateTime(DateUtil.format(deviceMcs.getCreatedTime(), "yyyy-MM-dd hh:mm:ss"));
					deviceLog.setCreateAt(deviceMcs.getCreatedAt());
					
					deviceMongoBaseDAO.insertDeviceLog(deviceLog);
					
				}
			}
		}
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.info(" add his level :" + e.getMessage());
		}
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

	@Override
	public List<DeviceLog> findAllByDeviceLog(List<Integer> deviceListIds,
			long dateTime, int level) {
		long  curdateTime = dateTime - 86400000;
		if(null != deviceListIds)
			return deviceMongoBaseDAO.findAllByDeviceLog(deviceListIds, curdateTime, level);
		else
			return deviceMongoBaseDAO.findAllByDeviceLogEx(curdateTime, level);
	}

	@Override
	public void updateDeviceLog(Integer deviceListId) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
