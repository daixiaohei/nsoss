package com.nspaces.oss.busi.control;

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
import com.nspaces.oss.busi.domain.Business;
import com.nspaces.oss.busi.domain.Cooperation;
import com.nspaces.oss.busi.domain.Coupon;
import com.nspaces.oss.busi.domain.CouponBusiness;
import com.nspaces.oss.busi.domain.CouponDevice;
import com.nspaces.oss.busi.domain.DeviceList;
import com.nspaces.oss.busi.dto.CouponBusinessDTO;
import com.nspaces.oss.busi.dto.CouponCodeDTO;
import com.nspaces.oss.busi.dto.CouponDTO;
import com.nspaces.oss.busi.dto.CouponDeviceDTO;
import com.nspaces.oss.busi.dto.CouponQueryDTO;
import com.nspaces.oss.busi.service.CouponCodeService;
import com.nspaces.oss.busi.service.CouponService;

/**
 * @author ZK
 */
@Controller
@RequestMapping("/coupon")
public class CouponController {

	public static final Logger logger = LoggerFactory.getLogger(CouponController.class);
	
	@Autowired
	CouponService couponService;
	@Autowired
	CouponCodeService couponCodeService;

	/**
	 * 查询优惠券信息
	 * @return
	 */
	@RequestMapping(value="queryCoupon",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<CouponDTO> queryCoupon(CouponQueryDTO queryDTO)
	{
		logger.info("queryCoupon by page");
		try {
			return couponService.queryCouponByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error queryCoupon by page");
		}	
		return null;
	}
	
	/**
	 * 加载合作公司
	 * @return
	 */
	@RequestMapping(value="loadCoop",method=RequestMethod.GET)
	@ResponseBody
	public List<Cooperation> loadCoop()
	{
		logger.info("loadCoop");
		try {
			return couponService.loadCoop();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error loadCoop");
		}	
		return null;
	}
	
	/**
	 * 加载业务
	 * @return
	 */
	@RequestMapping(value="loadBusi",method=RequestMethod.GET)
	@ResponseBody
	public List<Business> loadBusi()
	{
		logger.info("loadBusi");
		try {
			return couponService.loadBusi();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error loadBusi");
		}	
		return null;
	}
	
	/**
	 * 加载网点
	 * @return
	 */
	@RequestMapping(value="loadDevi",method=RequestMethod.GET)
	@ResponseBody
	public List<DeviceList> loadDevi()
	{
		logger.info("loadDevi");
		try {
			return couponService.loadDevi();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error loadDevi");
		}	
		return null;
	}
	
	/**
	 * 新增or编辑 优惠券
	 * @param user
	 * @return
	 */
	@RequestMapping(value="saveCoupon",method=RequestMethod.POST)
	@ResponseBody
	public Coupon saveCoupon(@RequestBody Coupon coupon)
	{
		logger.info("saveCoupon");
		try {
			return couponService.insertAndUpdate(coupon);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error saveCoupon");
		}	
		return null;
	}
	
	/**
	 * 新增or编辑 优惠券绑定业务
	 * @param user
	 * @return
	 */
	@RequestMapping(value="saveCouponBusi",method=RequestMethod.POST)
	@ResponseBody
	public CouponBusinessDTO saveCouponBusi(@RequestBody CouponBusiness couponBusiness)
	{
		logger.info("save CouponBusiness");
		try {
			CouponBusinessDTO cb = couponService.insertAndUpdateCB(couponBusiness);
			return cb;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error save CouponBusiness");
		}	
		return null;
	}
	
	/**
	 * 新增or编辑 优惠券绑定网点
	 * @param user
	 * @return
	 */
	@RequestMapping(value="saveCouponDevi",method=RequestMethod.POST)
	@ResponseBody
	public CouponDeviceDTO saveCouponDevi(@RequestBody CouponDevice couponDevice)
	{
		logger.info("save couponDevice");
		try {
			return couponService.insertAndUpdateCD(couponDevice);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error save couponDevice");
		}	
		return null;
	}
	
	/**
	 * 删除 优惠券 
	 * @param user
	 * @return
	 */
	@RequestMapping(value="deleteCoupon",method=RequestMethod.POST)
	@ResponseBody
	public String deleteCoupon(@RequestBody Coupon coupon)
	{
		logger.info("deleteCoupon");
		try {
			return couponService.deleteCouponByLogic(coupon)?"true":"false";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error deleteCoupon");
		}
		return "false";
	}
	
	/**
	 * 删除 优惠券绑定网点 
	 * @param user
	 * @return
	 */
	@RequestMapping(value="deleteCouponDevi",method=RequestMethod.POST)
	@ResponseBody
	public String deleteCouponDevi(@RequestBody CouponDevice couponDevice)
	{
		logger.info("deleteCouponDevi");
		try {
			return couponService.deleteCouponDevi(couponDevice)?"true":"false";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error deleteCoupon");
		}
		return "false";
	}
	
	/**
	 * 删除 优惠券绑定网点 
	 * @param user
	 * @return
	 */
	@RequestMapping(value="deleteCouponBusi",method=RequestMethod.POST)
	@ResponseBody
	public String deleteCouponBusi(@RequestBody CouponBusiness couponBusiness)
	{
		logger.info("deleteCouponBusi");
		try {
			return couponService.deleteCouponBusi(couponBusiness)?"true":"false";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error deleteCouponBusi");
		}
		return "false";
	}
	
	/**
	 * 查询优惠券代码信息
	 * @return
	 */
	@RequestMapping(value="queryCouponCode",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<CouponCodeDTO> queryCouponCode(CouponQueryDTO queryDTO)
	{
		logger.info("queryCoupon by page");
		try {
			return couponService.queryCouponCodeByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error queryCoupon by page");
		}	
		return null;
	}
	
	/**
	 * 查询优惠券绑定业务信息
	 * @return
	 */
	@RequestMapping(value="queryCouponBusi",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<CouponBusinessDTO> queryCouponBusi(CouponQueryDTO queryDTO)
	{
		logger.info("queryCouponBusinessByPage by page");
		try {
			return couponService.queryCouponBusinessByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error queryCouponBusinessByPage by page");
		}	
		return null;
	}
	
	/**
	 * 查询优惠券绑定设备信息
	 * @return
	 */
	@RequestMapping(value="queryCouponDevi",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<CouponDeviceDTO> queryCouponDevi(CouponQueryDTO queryDTO)
	{
		logger.info("queryCouponDeviceByPage by page");
		try {
			return couponService.queryCouponDeviceByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error queryCouponDeviceByPage by page");
		}	
		return null;
	}
	
	/**
	 * 查询优惠券编码的状态
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findCodeNo", method = RequestMethod.POST)
	@ResponseBody
	public List<CouponCodeDTO> findCodeNo(HttpServletRequest request,String codeNo){

		logger.info("findCodeNo");
		try {
			List<CouponCodeDTO> list = couponCodeService.findCodeNo(codeNo);
			if (list != null && list.size() > 0) {
				return list;
			} else {
					return null;

				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findCodeNo");
		}

		return null;
	}
	
	/**
	 * 更新优惠券代码的状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value="updateCodeNoStatus", method=RequestMethod.POST)
	@ResponseBody
	public Boolean updateCodeNoStatus(Integer id){
		try {
			return couponCodeService.updateCodeNoStatus(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error updateCodeNoStatus by id");
		}
		return false;
	}
	
	@RequestMapping(value="initCouponCode", method=RequestMethod.GET)
	@ResponseBody
	public String initCouponCode(){
		try {
			couponCodeService.initCoponCode();
			return "初始化成功";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error init");
		}
		return "初始化失败";
	}
	
}
