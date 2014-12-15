package com.nspaces.oss.config;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nspaces.oss.busi.domain.User;


public class UserLoginDetails implements UserDetails {

	 
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;			//账号未过期
    private final boolean accountNonLocked;				//账号未锁定
    private final boolean credentialsNonExpired;		//证书未过期
    private final boolean enabled = true;						//是否可用 
    
    private User curUser;
    
    public User getCurUser() {
		return curUser;
	}

	public void setCurUser(User curUser) {
		this.curUser = curUser;
	}

	public UserLoginDetails(User curUser, Set<GrantedAuthority> authorities){
    	this(true, true, true, true, authorities);
    	this.curUser = curUser;
   }
    
	/**
	 * 
	 * @Description: 
	 * @Author:laigengbiao 
	 * @param enabled  是否可用
	 * @param accountNonExpired 账号未过期
	 * @param credentialsNonExpired 证书未过期
	 * @param accountNonLocked 账号未锁定 
	 * @param authorities 
	 * @throws
	 */
    public UserLoginDetails(boolean enabled, boolean accountNonExpired,boolean credentialsNonExpired, boolean accountNonLocked ,Set<GrantedAuthority> authorities) {
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = authorities;
    }
	

    @Override
	public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    } 
    
    public String getUsername() {
        return curUser.getName();
    } 
    
    public String getPassword() {
        return curUser.getPassword();
    } 
    
    
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void eraseCredentials() {
    	curUser.setPassword(null);
    } 
    
	@Override
	public boolean isEnabled() { 
		return enabled;
	} 
	
	

}
