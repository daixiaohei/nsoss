package com.nspaces.oss.busi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nspaces.oss.busi.dao.NewsDao;
import com.nspaces.oss.busi.domain.News;
import com.nspaces.oss.busi.dto.NewsDTO;
import com.nspaces.oss.busi.dto.NewsQueryDTO;
@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsDao newsDao;
	
	@Override
	public List<NewsDTO> findNewsDTO(NewsQueryDTO queryDTO) throws Exception {
		
		return newsDao.findNewsDTO(queryDTO);
	}
	
	@Override
	public List<NewsDTO> findPublishStatus() throws Exception{
		return newsDao.findPublishStatus();
	}
	
	@Override
	public News insertAndUpdate(News news) throws Exception{
		return newsDao.insertAndUpdate(news);
	}

	@Override
	public boolean deleteNews(Integer id) throws Exception{
		return newsDao.deleteNews(id);
	}
}
