package com.nspaces.oss.busi.control;

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
import com.nspaces.oss.busi.domain.BusiDeviceSetting;
import com.nspaces.oss.busi.dto.BusiDeviceSettingDTO;
import com.nspaces.oss.busi.dto.BusiDeviceSettingQueryDTO;
import com.nspaces.oss.busi.dto.BusinessDTO;
import com.nspaces.oss.busi.dto.DeviceListDTO;
import com.nspaces.oss.busi.service.BusiDeviceSettingService;
import com.nspaces.oss.busi.service.BusinessService;
import com.nspaces.oss.busi.service.DeviceListService;

@Controller
@RequestMapping("/busiDeviceSetting")
public class BusiDeviceController {


	public static final Logger logger = LoggerFactory.getLogger(BusiDeviceController.class);

	@Autowired
	public BusiDeviceSettingService settingService;
	@Autowired
	public BusinessService businessService;
	@Autowired
	public DeviceListService deviceListService;
	
	
	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	@RequestMapping(value="findbyname", method=RequestMethod.GET)
	@ResponseBody
	public BusiDeviceSetting findByName(String merchNo){
		
		try {
			return  settingService.findBusiDeviceSettingByMerchNo(merchNo);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error find by BusiDeviceSetting name");
		}
		return null;
	}
	
	@RequestMapping(value="deleteById", method=RequestMethod.POST)
	@ResponseBody
	public Boolean deleteById(Integer id){
		
		try {
			return settingService.deleteById(id);
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
	@RequestMapping(value="querybusiDeviceSettingByPage",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<BusiDeviceSettingDTO> findCityByPage(BusiDeviceSettingQueryDTO queryDTO){
		
		logger.debug("beging to querybusiDeviceSettingByPage--");
		try {
			return  settingService.queryBusiDeviceSettingByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in query querybusiDeviceSettingByPage");
		}
		return null;
	}
	
	
    /**
     * 增加
     * @param queryDTO
     * @return
     */
	@RequestMapping(value="saveBusiDeviceSetting",method=RequestMethod.POST)
	@ResponseBody
	public BusiDeviceSetting saveBusiDeviceSetting(@RequestBody BusiDeviceSetting setting){
		
		logger.debug("beging to saveBusiDeviceSetting--");
		logger.info("saveBusiDeviceSetting: setting.toString():"+setting.toString());
		try {
			return settingService.insertAndUpdate(setting);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in save saveBusiDeviceSetting");
		}
		return null;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="queryAllBusinessId",method=RequestMethod.GET)
	@ResponseBody
	public List<BusinessDTO> queryAllBusinessId(){
		logger.info("begin to save queryAllBusinessId");
		try {
			return businessService.queryAllBusinessId();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in queryAllBusinessId ");
		}
		return null;
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="queryAllDeviceListId",method=RequestMethod.GET)
	@ResponseBody
	public List<DeviceListDTO> queryAllDeviceListId(){
		logger.info("begin to save queryAllDeviceListId");
		try {
			return deviceListService.queryAllDeviceListId();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in queryAllDeviceListId ");
		}
		return null;
	}
}
