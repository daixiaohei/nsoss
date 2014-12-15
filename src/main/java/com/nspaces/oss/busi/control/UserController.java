package com.nspaces.oss.busi.control;


import java.util.HashMap;
import java.util.List;
import java.util.Map;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.Memu;
import com.nspaces.oss.busi.domain.Role;
import com.nspaces.oss.busi.domain.User;
import com.nspaces.oss.busi.dto.RoleDTO;
import com.nspaces.oss.busi.dto.RoleQueryDTO;
import com.nspaces.oss.busi.dto.UserDTO;
import com.nspaces.oss.busi.dto.UserQueryDTO;
import com.nspaces.oss.busi.service.RoleService;
import com.nspaces.oss.busi.service.UserService;

/**
 * 
 * @author whw
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	/**
	 * 根据名称获取用户信息
	 * @param name
	 * @return
	 */
	@RequestMapping(value="findByName",method=RequestMethod.GET)
	@ResponseBody
	public User findByName(String name)
	{
		
		try {
			return userService.findUserByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error find by user name");
		}	
		
		return null;
	}
	
	/**
	 * 查询用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="queryUser",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<UserDTO> queryUser(UserQueryDTO queryDTO)
	{
		logger.info("queryUser by page");
		try {
			return userService.queryUserByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error queryUser by page");
		}	
		return null;
	}
	
	/**
	 * 新增or编辑 用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="saveUser",method=RequestMethod.POST)
	@ResponseBody
	public User saveUser(@RequestBody User user)
	{
		logger.info("saveUser");
		try {
			return userService.insertAndUpdate(user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error saveUser");
		}	
		return null;
	}
	
	/**
	 * 删除 用户信息 
	 * @param user
	 * @return
	 */
	@RequestMapping(value="deleteUser",method=RequestMethod.POST)
	@ResponseBody
	public String deleteUser(@RequestBody User user)
	{
		logger.info("deleteUser");
		try {
			return userService.deleteUserByLogic(user)?"true":"false";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error saveUser");
		}
		return "false";
	}
	
	/**
	 * 加载菜单 
	 * @param user
	 * @return
	 */
	@RequestMapping(value="loadMemu",method=RequestMethod.POST)
	@ResponseBody
	public List<Memu> loadMemu(@CookieValue Integer userid)
	{
		logger.info("loadMemu");
		try {
			return userService.loadMemu(userid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error loadMemu");
		}
		return null;
	}
	
	/**
	 * 用户登陆 
	 * @param user
	 * @return
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	public Map login(@RequestBody UserDTO userDTO)
	{
		logger.info("login");
        Map map = new HashMap();
		try {
			User user = userService.findUserByName(userDTO.getName());
			if(user != null){
				if(user.getPassword().equals(userDTO.getPassword())){
					map.put("code", "0");
					map.put("message", "验证通过");
					map.put("user", user);
				}else{
					map.put("code", "3");
					map.put("message", "密码错误");
				}
			}else{
				map.put("code", "4");
				map.put("message", "不存在此用户");
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error login");
			map.put("code", "5");
			map.put("message", "登陆异常");
			return map;
		}
	}
	
    
    
    /**
	 * 用户登陆 
	 * @param user
	 * @return
	 */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="loginex",method=RequestMethod.POST)
	@ResponseBody
	public Map loginEx(@RequestBody UserDTO userDTO)
	{
		logger.info("login");
        Map map = new HashMap();
		try {
			User user = userService.findUserByName(userDTO.getName());
			if(user != null){
				if(user.getPassword().equals(userDTO.getPassword())){
					map.put("code", "0");
					map.put("message", "验证通过");
				}else{
					map.put("code", "3");
					map.put("message", "密码错误");
				}
			}else{
				map.put("code", "4");
				map.put("message", "不存在此用户");
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error login");
			map.put("code", "5");
			map.put("message", "登陆异常");
			return map;
		}
	}
	
	/**
	 * 插入和更新角色信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "editRole", method = RequestMethod.POST)
	@ResponseBody
	public Role editRole(@RequestBody Role role) {
		logger.info(" edit role :");
		try {
			return roleService.editRole(role);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" editRole  " + e.getMessage());
		}

		return null;
	}

	/**
	 * 删除角色信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "deleteRole", method = RequestMethod.POST)
	@ResponseBody
	public void deleteRole(Integer id) {
		logger.info(" delete role id:"+id);
		try {
			
			roleService.deleteRole(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(" deleteRole  " + e.getMessage());
		}
	}
	/**
	 * 查询角色信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="queryRole",method=RequestMethod.POST)
	@ResponseBody
	public Pagination<RoleDTO>  queryRole(RoleQueryDTO queryDTO)
	{
		logger.info("queryRole by page");
		try {
			return  roleService.queryRoleByPage(queryDTO);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error queryRole by page");
		}	
		return null;
	}

}
