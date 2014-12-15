package com.nspaces.oss.base.util;

import java.util.ArrayList;
import java.util.List;

import com.nspaces.oss.base.converter.CommonConverterTools;
import com.nspaces.oss.base.converter.Converter;
import com.nspaces.oss.base.dto.PageInfo;
import com.nspaces.oss.base.dto.Pagination;



public class PaginationUtil {
	
	public static String translateCountSQL(String sql) {
		return " select count(1) from (" + sql + ") temp ";
	}
	
	public static String translatePaginationSQL(String sql, int offset, int length) {
		if (offset < 0) {
			offset = 0;
		}
		String size = "";
		if(length != 0) {//pagesize为0时，取消分布
			size = " limit " + offset + "," + length;
		}
		return sql + size;
	}
	
	public static <DTO, VO> Pagination<DTO> convert(Pagination<VO> paginationVO, CommonConverterTools commonConverter,Class<DTO> clazz) {
		Pagination<DTO> result = new Pagination<DTO>();
		result.setMaxPage(paginationVO.getMaxPage());
		result.setPage(paginationVO.getPage());
		result.setPageSize(paginationVO.getPageSize());
		result.setTotal(paginationVO.getTotal());
		for(VO vo : paginationVO.getItems()) {
			result.getItems().add(commonConverter.convert(clazz, vo));
		}
		return result;
	}
	public static <DTO, VO> Pagination<DTO> convert(Pagination<VO> paginationVO, Converter<DTO, VO> converter) {
		Pagination<DTO> result = new Pagination<DTO>();
		result.setMaxPage(paginationVO.getMaxPage());
		result.setPage(paginationVO.getPage());
		result.setPageSize(paginationVO.getPageSize());
		result.setTotal(paginationVO.getTotal());
		for(VO vo : paginationVO.getItems()) {
			result.getItems().add(converter.convertToDTO(vo));
		}
		return result;
	}

	public static <T> PageInfo generatePageInfo(Pagination<T> p) {
		PageInfo page = new PageInfo();
		if(p == null) {
			return page;
		}
		page.setMaxPage(p.getMaxPage());
		page.setPage(p.getPage());
		page.setPageSize(p.getPageSize());
		page.setTotal(p.getTotal());
		return page;
	}
	
	public static <T> List<T> generateItems(Pagination<T> p) {
		if(p == null) {
			return new ArrayList<T>();
		}
		return p.getItems();
	}
}
