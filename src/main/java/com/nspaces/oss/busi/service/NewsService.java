package com.nspaces.oss.busi.service;

import java.util.List;

import com.nspaces.oss.busi.domain.News;
import com.nspaces.oss.busi.dto.NewsDTO;
import com.nspaces.oss.busi.dto.NewsQueryDTO;

public interface NewsService {
	
	public List<NewsDTO> findNewsDTO(NewsQueryDTO queryDTO) throws Exception;
	
	public List<NewsDTO> findPublishStatus() throws Exception;
	
	public News insertAndUpdate(News news) throws Exception;
	
	public boolean deleteNews(Integer id) throws Exception;
}
