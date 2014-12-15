package com.nspaces.oss.busi.control;

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

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.base.util.JaxbBinder;
import com.nspaces.oss.busi.domain.DeviceList;
import com.nspaces.oss.busi.dto.DeviceDrvDTO;
import com.nspaces.oss.busi.dto.DeviceListDTO;
import com.nspaces.oss.busi.dto.DeviceListQueryDTO;
import com.nspaces.oss.busi.dto.xml.DeviceCacheDTO;
import com.nspaces.oss.busi.dto.xml.DeviceIODTO;
import com.nspaces.oss.busi.dto.xml.PartDTO;
import com.nspaces.oss.busi.service.DeviceListService;

@Controller
@RequestMapping("/device")
public class DeviceListController {
	
	public static final Logger logger = LoggerFactory.getLogger(DeviceListController.class);
	
	
	@Autowired
	DeviceListService deviceListService;
	
	@RequestMapping(value="partio",method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String findDevicePartByName(String ipAddr)
	{
		logger.info(" findDevice i/o part remote addr:" + ipAddr );
		try
		{
			DeviceIODTO deviceIODTO = deviceListService.findDevicePartByName(ipAddr);
			//JaxbBinder binder = new JaxbBinder(DeviceIODTO.class);  
            //String retStr = binder.toXml(deviceIODTO, JaxbBinder.ENCODING, JaxbBinder.DECLARATION);  
			String retStr = writeXML(deviceIODTO);
             System.out.println(retStr);
             return retStr;
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error(" findDevicePartByName " + e.getMessage());
		}
		return "";
	}
	
    /**
     * 增加
     * @param queryDTO
     * @return
     */
	@RequestMapping(value="saveDeviceList",method=RequestMethod.POST)
	@ResponseBody
	public DeviceList saveDeviceList(@RequestBody DeviceList deviceList){
		
		logger.debug("beging to deviceList--");
		logger.info("savedeviceList: deviceList.toString():"+deviceList.toString());
		try {
			return deviceListService.insertAndUpdate(deviceList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in save deviceList");
		}
		return null;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deleteById", method=RequestMethod.POST)
	@ResponseBody
	public Boolean deleteById(Integer id){
		
		try {
			return deviceListService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error delete by id");
		}
		return false;
	}
    /**
     * 分页查询
     * @param queryDTO
     * @return
     */
	@RequestMapping(value="queryDeviceListByPage",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<DeviceListDTO> queryDeviceListByPage(DeviceListQueryDTO queryDTO){
		
		logger.debug("beging to queryDeviceListByPage--");
		try {
			return  deviceListService.queryDeviceListByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in query queryPartListByPage");
		}
		return null;
	}
	
	
	/**
	 * 转换成I/OXML
	 * @param deviceIODTO
	 * @return
	 */
	private String writeXML(DeviceIODTO deviceIODTO)
	{
		StringBuffer sw = new StringBuffer();
		sw.append(JaxbBinder.DECLARATION);
		sw.append("\n");
		if(deviceIODTO.getPartCount() >0)
		{
			//root
			sw.append("<IO port=\"9012\" id=\"" + deviceIODTO.getDeviceListId() + "\" addr=\"" + deviceIODTO.getIpAddr() + "\" deviceName=\"" + deviceIODTO.getDeviceName()  + "\" cpu=\"" + deviceIODTO.getCpu()+ "\" >\n");
			String subElement = "";
			int nSubElement = 0;
			int nDetail = 0;
		    //填写输入
			sw.append("<INPUT>\n");
			for(PartDTO partDTO:deviceIODTO.getPartDTO())
			{
				if(partDTO.getPartType() == 2)
				{	
					String partNumFront = partDTO.getPortNum().substring(0,partDTO.getPortNum().indexOf("."));
					String partNumLast = partDTO.getPortNum().substring(partDTO.getPortNum().indexOf(".")+1);
					if(!subElement.equals(partNumFront))
					{
						if(nSubElement==0)
						{
							sw.append("<" + partNumFront + ">\n");
						}else
						{
							sw.append("</" + subElement + ">\n");
							sw.append("<" + partNumFront + ">\n");
						}
						
						nSubElement += 1;
						
						nDetail=0;
						
						subElement = partNumFront;
					}
					
					
				   sw.append("<" + subElement +"" + partNumLast + "  name=\"" + partDTO.getPartName() +"\"  push=\"" + partDTO.getPush()+"\" >");

					sw.append("" + partDTO.getPortNum() + "");
					sw.append("</" + subElement +"" + partNumLast + ">\n");	
					
				}
			}
			sw.append("</" + subElement + ">\n");
			sw.append("</INPUT>\n");
			
			nSubElement = 0;
			nDetail = 0;
			sw.append("<OUTPUT>\n");
			for(PartDTO partDTO:deviceIODTO.getPartDTO())
			{
				if(partDTO.getPartType() == 4)
				{	
					String partNumFront = partDTO.getPortNum().substring(0,partDTO.getPortNum().indexOf("."));
					String partNumLast = partDTO.getPortNum().substring(partDTO.getPortNum().indexOf(".")+1);
					if(!subElement.equals(partNumFront))
					{
						if(nSubElement==0)
						{
							sw.append("<" + partNumFront + ">\n");
						}else
						{
							sw.append("</" + subElement + ">\n");
							sw.append("<" + partNumFront + ">\n");
						}
						
						nSubElement += 1;
						
						nDetail=0;
						
						subElement = partNumFront;
					}
					
					if(1==partDTO.getTimeSet())
					{
						sw.append("<" + subElement +"" + partNumLast + "  name=\"" + partDTO.getPartName() +"\" timeSet=\"1\" "+ " timeId=\"" + partDTO.getTimeId() + "\" startTime=\"" + partDTO.getStartTime() + "\" endTime=\"" + partDTO.getEndTime() + "\">");
					}else
					{
						sw.append("<" + subElement +"" + partNumLast + "  name=\"" + partDTO.getPartName() +"\" timeSet=\"0\">");
					}
					sw.append("" + partDTO.getPortNum() + "");
					sw.append("</" + subElement +"" + partNumLast + ">\n");	
					nDetail += 1;
				}
			}
			sw.append("</" + subElement + ">\n");
			sw.append("</OUTPUT>\n");
		
			sw.append("</IO>\n");
		}
		
		return sw.toString();
	}
	
	
	
	@RequestMapping(value="partdrv",method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public List<DeviceDrvDTO> findDevicePartDrvByName(String userName)
	{
		logger.info(" findDevice DRV part remote ");
		try
		{
			return deviceListService.findDevicePartDrvByName(userName);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error(" findDevicePartByName " + e.getMessage());
		}
		return null;
	}
	
	
	@RequestMapping(value="partconf",method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public HashMap<Integer,DeviceCacheDTO> findDevicePartIOByName(String deviceListIds)
	{
		logger.info(" findDevice DRV part remote ");
		try
		{
			return deviceListService.findDevicePartIdByDeviceIds(deviceListIds);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error(" findDevicePartByName " + e.getMessage());
		}
		return null;
	}
	
	
	@RequestMapping(value="all",method=RequestMethod.GET,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String findAndCache()
	{
		logger.info(" findAndCache DRV part remote ");
		try
		{
			boolean ret =  deviceListService.findAndCachePartIO();
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error(" findAndCache " + e.getMessage());
			return "error";
		}
		return "success";
	}
}
