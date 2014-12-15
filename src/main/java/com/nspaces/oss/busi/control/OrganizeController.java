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
import com.nspaces.oss.busi.domain.Memu;
import com.nspaces.oss.busi.domain.Organize;
import com.nspaces.oss.busi.domain.RoleMenu;
import com.nspaces.oss.busi.domain.UserData;
import com.nspaces.oss.busi.domain.UserRole;
import com.nspaces.oss.busi.dto.MenuDTO;
import com.nspaces.oss.busi.dto.MenuQueryDTO;
import com.nspaces.oss.busi.dto.OrganizeDTO;
import com.nspaces.oss.busi.dto.OrganizeQueryDTO;
import com.nspaces.oss.busi.dto.RoleMenuDTO;
import com.nspaces.oss.busi.dto.RoleMenuQueryDTO;
import com.nspaces.oss.busi.dto.UserDataDTO;
import com.nspaces.oss.busi.dto.UserDataQueryDTO;
import com.nspaces.oss.busi.dto.UserRoleDTO;
import com.nspaces.oss.busi.dto.UserRoleQueryDTO;
import com.nspaces.oss.busi.service.MemuService;
import com.nspaces.oss.busi.service.OrganizeService;
import com.nspaces.oss.busi.service.RoleMenuService;
import com.nspaces.oss.busi.service.UserDataService;
import com.nspaces.oss.busi.service.UserRoleService;

/**
 * 部门管理control 角色菜单control  用户角色control 用户区域管理control  (ZK)菜单管理control
 * @author qt
 * 
 */
@Controller
@RequestMapping("/organize")
public class OrganizeController {

	public static final Logger logger = LoggerFactory
			.getLogger(OrganizeController.class);

	@Autowired
	OrganizeService organizeService;

	@Autowired
	RoleMenuService roleMenuService;
	
	@Autowired
	UserRoleService userRoleService;

	@Autowired
	MemuService memuService;
	
	@Autowired
	UserDataService userDataService;
	
	/**
	 * 查询菜单
	 * @param 
	 * @return
	 */
	@RequestMapping(value="queryMenu",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<MenuDTO> queryMenu(MenuQueryDTO queryDTO)
	{
		logger.info("queryMenu by page");
		try {
			return memuService.queryMenuByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error queryMenu by page");
		}	
		return null;
	}
	
	/**
	 * save菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value="saveMenu",method=RequestMethod.POST)
	@ResponseBody
	public Memu saveMenu(@RequestBody Memu menu)
	{
		logger.info("saveMenu");
		try {
			return memuService.insertAndUpdate(menu);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error saveMenu");
		}	
		return null;
	}
	
	/**
	 * delete菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteMenu",method=RequestMethod.POST)
	@ResponseBody
	public String deleteMenu(@RequestBody Memu menu)
	{
		logger.info("deleteMenu id:"+menu.getId());
		try {
			 return memuService.deleteMemu(menu.getId())?"true":"false";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error deleteMenu");
		}
		return "false";
	}
	
	/**
	 * 插入和更新部门信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "editOrganize", method = RequestMethod.POST)
	@ResponseBody
	public Organize editOrganize(@RequestBody Organize organize) {
		logger.info(" edit organize :");
		try {
			return organizeService.editOrganize(organize);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" editOrganize  " + e.getMessage());
		}

		return null;
	}

	/**
	 * 删除部门信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "deleteOrganize", method = RequestMethod.POST)
	@ResponseBody
	public void deleteOrganize(Integer id) {
		logger.info(" delete organize id:" + id);
		try {

			organizeService.deleteOrganize(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" deleteRole  " + e.getMessage());
		}
	}

	/**
	 * 查询部门信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "queryOrganize", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<OrganizeDTO> queryOrganize(OrganizeQueryDTO queryDTO) {
		logger.info("queryOrganize by page");
		try {
			return organizeService.queryOrganizeByPage(queryDTO);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error queryOrganize by page");
		}
		return null;
	}

	/**
	 * 新增or编辑 角色菜单信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "editRoleMenu", method = RequestMethod.POST)
	@ResponseBody
	public RoleMenu editRoleMenu(@RequestBody RoleMenu roleMenu) {
		logger.info("editRoleMenu");
		try {
			return roleMenuService.editRoleMenu(roleMenu);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error editRoleMenu");
		}
		return null;
	}

	/**
	 * 删除角色菜单信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "deleteRoleMenu", method = RequestMethod.POST)
	@ResponseBody
	public void deleteRoleMenu(HttpServletRequest request) {
		
		Integer id = Integer.valueOf(request.getParameter("id"));
		logger.info(" delete deleteRoleMenu id:" + id);
		try {

			roleMenuService.deleteRoleMenu(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" deleteRoleMenu  " + e.getMessage());
		}
	}
	
	/**
	 * 查询角色菜单信息
	 * 
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping(value = "queryRoleMenu", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<RoleMenuDTO> queryRoleMenu(RoleMenuQueryDTO queryDTO) {
		logger.info("queryRoleMenu by page");
		try {
			return roleMenuService.queryRoleMenuByPage(queryDTO);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error queryRoleMenu by page");
		}
		return null;
	}

	/**
	 * 角色菜单角色名
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findRoleName", method = RequestMethod.GET)
	@ResponseBody
	public List<RoleMenuDTO> findRoleName() {
		logger.info("findRoleName");
		try {
			List<RoleMenuDTO> roleMenuList = roleMenuService.findRoleName();
			if (roleMenuList != null && roleMenuList.size() > 0) {
				return roleMenuList;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findRoleName");
		}

		return null;
	}
	
	/**
	 * 角色菜单 菜单名
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findMenuName", method = RequestMethod.GET)
	@ResponseBody
	public List<RoleMenuDTO> findMenuName() {
		logger.info("findMenuName");
		try {
			List<RoleMenuDTO> roleMenuList = roleMenuService.findMenuName();
			if (roleMenuList != null && roleMenuList.size() > 0) {
				return roleMenuList;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findMenuName");
		}

		return null;
	}
	
	/**
	 * 新增or编辑 用户角色信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "editUserRole", method = RequestMethod.POST)
	@ResponseBody
	public UserRole editUserRole(@RequestBody UserRole userRole) {
		logger.info("editUserRole");
		try {
			return userRoleService.editUserRole(userRole);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error editUserRole");
		}
		return null;
	}

	/**
	 * 删除用户角色信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "deleteUserRole", method = RequestMethod.POST)
	@ResponseBody
	public void deleteUserRole(Integer id) {
		logger.info(" delete deleteUserRole id:" + id);
		try {

			userRoleService.deleteUserRole(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" deleteUserRole  " + e.getMessage());
		}
	}
	
	/**
	 * 查询用户角色信息
	 * 
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping(value = "queryUserRole", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<UserRoleDTO> queryUserRole(UserRoleQueryDTO queryDTO) {
		logger.info("queryUserRole by page");
		try {
			return userRoleService.queryUserRoleByPage(queryDTO);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error queryUserRole by page");
		}
		return null;
	}
	
	/**
	 * 用户角色    用户名
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findUserName", method = RequestMethod.GET)
	@ResponseBody
	public List<UserRoleDTO> findUserName() {
		
		logger.info("findUserName");
		try {
			List<UserRoleDTO> userRoleList = userRoleService.findUserName();
			if (userRoleList != null && userRoleList.size() > 0) {
				return userRoleList;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findUserName");
		}

		return null;
	}
	
	/**
	 * 用户角色    角色名
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findUserRoleName", method = RequestMethod.GET)
	@ResponseBody
	public List<UserRoleDTO> findUserRoleName() {
		
		logger.info("findUserRoleName");
		try {
			List<UserRoleDTO> userRoleList = userRoleService.findUserRoleName();
			if (userRoleList != null && userRoleList.size() > 0) {
				return userRoleList;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findUserRoleName");
		}

		return null;
	}
	
	
	/**
	 * 新增or编辑 用户区域信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "editUserData", method = RequestMethod.POST)
	@ResponseBody
	public UserData editUserData(@RequestBody UserData userData) {
		logger.info("editUserData");
		try {
			return userDataService.editUserData(userData);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error editUserData");
		}
		return null;
	}

	/**
	 * 删除用户区域信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "deleteUserData", method = RequestMethod.POST)
	@ResponseBody
	public void deleteUserData(Integer id) {
		logger.info(" delete deleteUserData id:" + id);
		try {

			userDataService.deleteUserData(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" deleteUserData  " + e.getMessage());
		}
	}
	
	/**
	 * 查询用户区域管理信息
	 * 
	 * @param queryDTO
	 * @return
	 */
	@RequestMapping(value = "queryUserData", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<UserDataDTO> queryUserData(UserDataQueryDTO queryDTO) {
		logger.info("queryUserData by page");
		try {
			return userDataService.queryUserDataByPage(queryDTO);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error queryUserData by page");
		}
		return null;
	}
	
	/**
	 * 用户数据区域    用户名
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findUserDataName", method = RequestMethod.GET)
	@ResponseBody
	public List<UserDataDTO> findUserDataName() {
		
		logger.info("findUserDataName");
		try {
			List<UserDataDTO> userDataList = userDataService.findUserName();
			if (userDataList != null && userDataList.size() > 0) {
				return userDataList;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findUserDataName");
		}

		return null;
	}
	
	/**
	 * 用户数据区域    城市名
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findUserCityName", method = RequestMethod.GET)
	@ResponseBody
	public List<UserDataDTO> findUserCityName() {
		
		logger.info("findUserCityName");
		try {
			List<UserDataDTO> userDataList = userDataService.findCityName();
			if (userDataList != null && userDataList.size() > 0) {
				return userDataList;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findUserCityName");
		}

		return null;
	}
}
