package com.nspaces.oss.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nspaces.oss.busi.domain.User;
import com.nspaces.oss.busi.service.UserService;



@Service
public class WebUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		//根据用户名得到待登陆用户信息，并且得到了当前登录用户一共拥有哪些角色（role_id集合）
		User curUser = null;
		
		try
		{
			curUser = userService.findUserByName(username);
		}catch(Exception e)
		{
			throw new UsernameNotFoundException(username + " found Exception !");
		}
		
		if (null == curUser ){//如果用户不存在就88啦
			throw new UsernameNotFoundException(username + " not exist!");
		}else if(curUser.getStatus() == -1){
			throw new UsernameNotFoundException("User account : " + username + " has exception!");//账号异常
		}
		
		
	    boolean accountNonExpired = true;  
        boolean credentialsNonExpired = true;  
        boolean accountNonLocked = true;
        boolean enabled = true;
        //
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();  

        String roleName = "ROLE_ADMIN";
        
        
		GrantedAuthority ga = new SimpleGrantedAuthority(roleName);//将和SOPInvocationSecurityMetadataSourceService中的resource的Map主键绑定
		authorities.add(ga);       
        
       
		UserLoginDetails userDetails = new UserLoginDetails(curUser,authorities);

		return userDetails;
	}

}
