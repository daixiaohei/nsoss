package com.nspaces.oss.busi.control;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

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
import com.nspaces.oss.busi.domain.Spot;
import com.nspaces.oss.busi.domain.Street;
import com.nspaces.oss.busi.dto.SpotDTO;
import com.nspaces.oss.busi.dto.SpotQueryDTO;
import com.nspaces.oss.busi.service.CityService;
import com.nspaces.oss.busi.service.SpotService;
import com.nspaces.oss.busi.service.StreetService;

@Controller
@RequestMapping("/spot")
public class SpotController {

	public static final Logger logger = LoggerFactory.getLogger(SpotController.class);
	
	@Autowired
	public SpotService spotService;
	@Autowired
	public CityService cityService;
	@Autowired
	public StreetService streetService;
	
	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	@RequestMapping(value="findbyname", method=RequestMethod.GET)
	@ResponseBody
	public Spot findByName(String name){
		
		try {
			return  spotService.findSpotByName(name);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error find by spot name");
		}
		return null;
	}
	
	@RequestMapping(value="deleteById", method=RequestMethod.POST)
	@ResponseBody
	public Boolean deleteById(Integer id){
		
		try {
			return spotService.deleteById(id);
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
	@RequestMapping(value="querySpotByPage",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<SpotDTO> findSpotByPage(SpotQueryDTO queryDTO){
		
		logger.debug("beging to querySpotByPage--");
		try {
			return spotService.querySpotByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in query querySpotByPage");
		}
		return null;
	}
	
	
    /**
     * 增加
     * @param queryDTO
     * @return
     */
	@RequestMapping(value="saveSpot",method=RequestMethod.POST)
	@ResponseBody
	public Spot saveSpot(@RequestBody Spot spot){
		
		logger.debug("beging to saveSpot--");
		logger.info("saveSpot: Spot.toString():"+spot.toString());
		try {
			return spotService.insertAndUpdate(spot);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in save spot");
		}
		return null;
	}
	/**
	 * 得到一级城市
	 * @return
	 */
	@RequestMapping(value="initStreet",method=RequestMethod.GET)
	@ResponseBody
	public List<City> initStreet( HttpServletRequest request){
		String level = request.getParameter("level");
		logger.info("begin to query city of  level:"+level);
		try {
		List<City> cityList = 	cityService.findCityByLevel(level);
		if(cityList!=null&&cityList.size()>0){
			return cityList;
		}else{
			return null;
		}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in initStreet of spotController");
		}

		return null;
		
	}
	@RequestMapping(value="queryStreetsByCityNo",method=RequestMethod.GET)
	@ResponseBody
	public List<Street> queryStreetsByCityNo(HttpServletRequest request){
		String cityNo = request.getParameter("cityNo");
		logger.info("begin to query street cityNO is:"+cityNo);
		try {
		List<Street> streetList = 	streetService.findStreetByCityNo(cityNo);
		if(streetList!=null&&streetList.size()>0){
			return streetList;
		}else{
			return null;
		}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error in queryStreetsByCityNo of spotController");
		}

		return null;
	}
	public static void main(String[] args) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Spot spot = new Spot();
		PropertyDescriptor pd = new PropertyDescriptor("address", Spot.class);
		Method method = pd.getWriteMethod();
		method.invoke(spot, "qujie");
		System.out.println(spot.toString());
		
	}

}
