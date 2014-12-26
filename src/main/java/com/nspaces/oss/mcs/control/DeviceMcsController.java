package com.nspaces.oss.mcs.control;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nspaces.oss.base.cache.SimpleDeviceCache;
import com.nspaces.oss.base.util.DateUtil;
import com.nspaces.oss.base.util.StringUtil;
import com.nspaces.oss.busi.dto.xml.DeviceCacheDTO;
import com.nspaces.oss.busi.dto.xml.PartDTO;
import com.nspaces.oss.busi.service.DeviceListService;
import com.nspaces.oss.mcs.domain.DeviceLog;
import com.nspaces.oss.mcs.domain.DeviceMcs;
import com.nspaces.oss.mcs.domain.DeviceOut;
import com.nspaces.oss.mcs.domain.PortData;
import com.nspaces.oss.mcs.service.DeviceMcsService;

@Controller
@RequestMapping("/mcs")
public class DeviceMcsController {

	public static final Logger logger = LoggerFactory.getLogger(DeviceMcsController.class);
	
	@Autowired
	DeviceMcsService deviceMcsService;
	
	@Autowired
	DeviceListService deviceListService;
	
	/**
	 * 插入IO输入状态设备状态
	 * @param deviceMcs
	 * @return
	 */
	@RequestMapping(value="addmcs",method=RequestMethod.POST)
	@ResponseBody
	public String addDeviceMcs(@RequestBody DeviceMcs deviceMcs)
	{
		logger.info("add device mcs input");
		try
		{
			Date curDate = new Date();
			deviceMcs.setCreatedAt(curDate.getTime());
			deviceMcs.setCreatedTime(curDate);
			
			//过滤空闲的
			for(int i=0;i<deviceMcs.getDatas().size();i++)
			{
				PortData cur = deviceMcs.getDatas().get(i);
				if(cur.getPortValue() == 2)
					deviceMcs.getDatas().remove(i);
			}
			
			deviceMcsService.insertDeviceMcs(deviceMcs);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("add device mcs error");
			return "-1";
		}
		return "1";
	}
	
	
	/**
	 * 插入IO输入状态设备状态
	 * @param deviceMcs
	 * @return
	 */
	@RequestMapping(value="history",method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<DeviceLog> findDeviceMcsHis(String deviceListIds, String curDate, Integer level)
	{
		Date searchDate = new Date();
		
		String[] arrStr = deviceListIds.split(",");
		List<Integer> listIDS = new ArrayList<Integer>();
		for(int i=0;i<arrStr.length;i++)
		{
			
			if(StringUtil.isNotEmpty(arrStr[i]) )
				listIDS.add(Integer.parseInt(arrStr[i]));
		}
		
		if(null == level)
		{
			level =1;
		}

		if(null == curDate)
		{
			searchDate= new Date();
		}else
		{
			try {
				searchDate = DateUtil.parseToDate(curDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return deviceMcsService.findAllByDeviceLog(listIDS, searchDate.getTime(), level);
	}
	
	@RequestMapping(value="dealDevice",method=RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String dealDevice(Integer deviceListId)
	{
		try
		{
			deviceMcsService.updateDeviceLog(deviceListId);
			return "success";
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.info("更新异常信息出错！");
			return "error";
		}
	}
	
	/**
	 * 插入IO输入状态设备状态
	 * @param deviceMcs
	 * @return
	 */
	@RequestMapping(value="search",method=RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<DeviceCacheDTO> findDeviceMcs(String deviceListIds, Long curDate)
	{
		
		try
		{
			Date curTemp = null;
			if(null == curDate)
			{
				curTemp = new Date();
				curDate=curTemp.getTime()-72000;
				System.out.println(curDate);
			}else
			{
				curDate = curDate -2900;
			}
			
			//logger.info("curDate:" + curDate.intValue() + " date" + curDate);
			
			List<DeviceCacheDTO> retMap = new ArrayList<DeviceCacheDTO>();
			
			List<DeviceMcs> listDeviceMcs = deviceMcsService.findDeviceMcs(deviceListIds, curDate);
			
			
			if(null != listDeviceMcs && listDeviceMcs.size() >0)
			{

				logger.info("count + :" + listDeviceMcs.size());
				
				for(DeviceMcs curDeviceMcs:listDeviceMcs)
				{
					DeviceCacheDTO curCacheDTO = SimpleDeviceCache.getInstance().getCacheDevice().get(curDeviceMcs.getDeviceListId());
					
					if(null == curCacheDTO && SimpleDeviceCache.getInstance().getCacheDevice().isEmpty())
					{
						try
						{
							deviceListService.findAndCachePartIO();
							curCacheDTO = SimpleDeviceCache.getInstance().getCacheDevice().get(curDeviceMcs.getDeviceListId());
						}catch(Exception e)
						{
							logger.error("cache  device list error");
						}
					}
					
					if(null != curCacheDTO)
					{
						DeviceCacheDTO newCacheDTO = copyAndNewCacheDTO(curCacheDTO);
						newCacheDTO.setCreateDate(DateUtil.format( new Date(curDeviceMcs.getCreatedAt()), "yyyy-MM-dd HH:mm:ss"));
						
						for(PortData portData : curDeviceMcs.getDatas())
						{
							PartDTO partDTO = curCacheDTO.getMapPart().get(portData.getPortNum());
							if(null != partDTO)
							{
								PartDTO newPartDTO = copyAndNewPartDTO(partDTO,portData.getPortValue());
								newCacheDTO.getMapPart().put(newPartDTO.getPortNum(), newPartDTO);
							}
							
						}
						
						retMap.add(newCacheDTO);
					}
				}
				
			}
			
			return retMap;
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("findDeviceMcs mcs error");
			return null;
		}
	}
	
	private DeviceCacheDTO copyAndNewCacheDTO(DeviceCacheDTO deviceCacheDTO)
	{
		DeviceCacheDTO newCacheDTO = new DeviceCacheDTO();
		newCacheDTO.setDeviceListId(deviceCacheDTO.getDeviceListId());
		newCacheDTO.setIpAddr(deviceCacheDTO.getIpAddr());
		newCacheDTO.setName(deviceCacheDTO.getName());
		newCacheDTO.setRemoteAddr(deviceCacheDTO.getRemoteAddr());
		
		return newCacheDTO;
	}
	
	private PartDTO copyAndNewPartDTO(PartDTO curPartDTO, Integer curValue)
	{
		
        PartDTO newPartDTO = new PartDTO();
        newPartDTO.setLevel(curPartDTO.getLevel());
        newPartDTO.setPartName(curPartDTO.getPartName());
        newPartDTO.setPartNo(curPartDTO.getPartNo());
        newPartDTO.setPortNum(curPartDTO.getPortNum());
        newPartDTO.setCurValue(new Integer(curValue));
        
        if(curPartDTO.getPush() == curValue)
        {
        	newPartDTO.setAlertStatus(1);
        }
        
        if(curValue==1)
        {
        	newPartDTO.setCurValueName(curPartDTO.getValue1Name());
        }else
        {
        	newPartDTO.setCurValueName(curPartDTO.getValue0Name());
        }
        return newPartDTO;
	}
	
	/**
	 * 终端控制
	 * @param deviceMcs
	 * @return
	 */
	@RequestMapping(value="addout",method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addDeviceOut(@RequestBody DeviceOut deviceOut)
	{
		logger.info("add device mcs");
		try
		{
			deviceOut.setCreatedAt(new Date());
			deviceMcsService.insertDeviceOut(deviceOut);
			
			if(StringUtil.isNotEmpty(deviceOut.getStartTime()))
			{
				deviceListService.updatePartListTime(deviceOut.getDeviceListId(), deviceOut.getPortNum(), deviceOut.getStartTime(), deviceOut.getEndTime());
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("add device mcs error");
			return "-1";
		}
		return "1";
	}
	
	@RequestMapping(value="addmcs1",method=RequestMethod.GET)
	@ResponseBody
	public String addDeviceMcsTest()
	{
		try
		{
			DeviceMcs deviceMcs = new DeviceMcs();
			deviceMcs.setDeviceListId(34);
			PortData portData = new PortData();
			portData.setPortNum("I3.0");
			portData.setPortValue(0);
			
			PortData portData1 = new PortData();
			portData1.setPortNum("I2.1");
			portData1.setPortValue(1);
			deviceMcs.getDatas().add(portData);
			deviceMcs.getDatas().add(portData1);
			
			Date curDate = new Date();
			deviceMcs.setCreatedAt(curDate.getTime());
			deviceMcs.setCreatedTime(curDate);
			System.out.println(curDate.getTime());
			deviceMcsService.insertDeviceMcs(deviceMcs);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("add device mcs error");
			return "-1";
		}
		return "1";
	}
	
	
	@RequestMapping(value="addout1",method=RequestMethod.GET)
	@ResponseBody
	public String addDeviceOutTest()
	{
		try
		{
			DeviceOut deviceOut = new DeviceOut();
			deviceOut.setPartName("门");
			deviceOut.setPartNo("io001");
			deviceOut.setPortNum("IO.3");
			deviceOut.setPortValue(1);
			deviceOut.setCreatedAt(new Date());
			
			deviceMcsService.insertDeviceOut(deviceOut);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("add device mcs error");
			return "-1";
		}
		return "1";
	}
}
