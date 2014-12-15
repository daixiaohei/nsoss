package com.nspaces.oss.base.converter;

public interface Converter<DTO, VO> {

	public DTO convertToDTO(VO vo);
	
	public VO convertToVO(DTO dto);
}
