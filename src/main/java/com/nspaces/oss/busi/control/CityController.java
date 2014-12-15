package com.nspaces.oss.busi.control;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.City;
import com.nspaces.oss.busi.dto.CityDTO;
import com.nspaces.oss.busi.dto.CityQueryDTO;
import com.nspaces.oss.busi.service.CityService;

@Controller
@RequestMapping("/city")
public class CityController {


	public static final Logger logger = LoggerFactory.getLogger(CityController.class);

	@Autowired
	public CityService cityService;
	
	
	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	@RequestMapping(value="findbyname", method=RequestMethod.GET)
	@ResponseBody
	public City findByName(String name){
		
		try {
			return  cityService.findCityByName(name);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error find by city name");
		}
		return null;
	}
	
	@RequestMapping(value="deleteById", method=RequestMethod.POST)
	@ResponseBody
	public Boolean deleteById(Integer id){
		
		try {
			return cityService.deleteById(id);
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
	@RequestMapping(value="queryCityByPage",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<CityDTO> findCityByPage(CityQueryDTO queryDTO){
		
		logger.debug("beging to queryCityByPage--");
		try {
			return  cityService.queryCityByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in query queryCityByPage");
		}
		return null;
	}
	
	
    /**
     * 增加
     * @param queryDTO
     * @return
     */
	@RequestMapping(value="saveCity",method=RequestMethod.POST)
	@ResponseBody
	public City saveCity(@RequestBody City city){
		
		logger.debug("beging to saveCity--");
		logger.info("saveCity: city.toString():"+city.toString());
		try {
			return cityService.insertAndUpdate(city);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in save city");
		}
		return null;
	}
}
