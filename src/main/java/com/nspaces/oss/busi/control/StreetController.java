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
import com.nspaces.oss.busi.domain.Street;
import com.nspaces.oss.busi.dto.StreetDTO;
import com.nspaces.oss.busi.dto.StreetQueryDTO;
import com.nspaces.oss.busi.service.StreetService;
@Controller
@RequestMapping("/street")
public class StreetController {


	public static final Logger logger = LoggerFactory.getLogger(StreetController.class);

	@Autowired
	public StreetService streetService;
	
	
	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	@RequestMapping(value="findbyname", method=RequestMethod.GET)
	@ResponseBody
	public Street findByName(String name){
		
		try {
			return  streetService.findStreetByName(name);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error find by street name");
		}
		return null;
	}
	
	@RequestMapping(value="deleteById", method=RequestMethod.POST)
	@ResponseBody
	public Boolean deleteById(Integer id){
		
		try {
			return streetService.deleteById(id);
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
	@RequestMapping(value="queryStreetByPage",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<StreetDTO> findStreetByPage(StreetQueryDTO queryDTO){
		
		logger.debug("beging to queryStreetByPage--");

		try {
			return streetService.queryStreetByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in query queryStreetByPage");
		}
		return null;
	}
	
	
    /**
     * 增加
     * @param queryDTO
     * @return
     */
	@RequestMapping(value="saveStreet",method=RequestMethod.POST)
	@ResponseBody
	public Street saveSpot(@RequestBody Street street){
		
		logger.debug("beging to saveStreet--");
		logger.info("saveCity: street.toString():"+street.toString());
		try {
			return streetService.insertAndUpdate(street);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in save city");
		}
		return null;
	}
}
