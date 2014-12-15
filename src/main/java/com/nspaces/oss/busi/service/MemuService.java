package com.nspaces.oss.busi.service;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.Memu;
import com.nspaces.oss.busi.dto.MenuDTO;
import com.nspaces.oss.busi.dto.MenuQueryDTO;


public interface MemuService {

	/**
	 * 插入或者更新菜单
	 * @param memu
	 * @return
	 * @throws Exception
	 */
	public Memu insertAndUpdate(Memu memu) throws Exception;
	
	/**
	 * 删除菜单
	 * @param memu
	 * @return
	 * @throws Exception
	 */
	public boolean deleteMemu(Integer id) throws Exception;
	
	public Pagination<MenuDTO> queryMenuByPage(MenuQueryDTO queryDTO);
}
