package com.nspaces.oss.busi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.nspaces.oss.base.dao.BaseDAO;
import com.nspaces.oss.busi.domain.Memu;
import com.nspaces.oss.busi.domain.News;
import com.nspaces.oss.busi.dto.NewsDTO;
import com.nspaces.oss.busi.dto.NewsQueryDTO;

@Repository
public class NewsDao extends BaseDAO{

	public static final Logger logger = LoggerFactory.getLogger(NewsDao.class);
	private String tableName = "news";
	private String idColumnName = "id";
	
	
	/**
	 * 
	 * 描述：插入和更新新闻信息 
	 * 
	 * @param news
	 * @return
	 * @throws Exception
	 */
	public News insertAndUpdate(News news) throws Exception {
		if (null != news.getId()) {
			super.update(news, tableName, idColumnName);
		} else {
			Integer retId = super.insertAndReturnId(news, tableName,
					idColumnName);
			news.setId(retId);
		}

		return news;
	}
	
	
	/**
	 * 查询新闻信息
	 * 
	 * @param dataQueryDTO
	 * @return
	 * @throws Exception
	 */
	public List<NewsDTO> findNewsDTO(NewsQueryDTO dataQueryDTO)
			throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		StringBuffer sbd = new StringBuffer();
		 sbd.append(" select ");
		 sbd.append("  n.id,n.title,n.author,n.content,n.digest,n.img,n.publish_date as publishDate,n.keyword,       ");
		 sbd.append("  case n.publish_status when 0 then '发布' when 1 then '关闭' else '无' end as publishStatus ");
		 sbd.append("  from news as n ");
		 sbd.append("  where n.publish_status>=0");
		

		if (null != dataQueryDTO.getTitle()
				&& !dataQueryDTO.getTitle().equals("")) {
			sbd.append(" and n.title =:title  ");
		}
		if ((null != dataQueryDTO.getPublishDate() && !dataQueryDTO
				.getPublishDate().equals(""))) {
			sbd.append(" and date_format(n.publish_date, '%Y-%m-%d %H:%i:%s') >= :publishDate                                 ");
		}
		if ((null != dataQueryDTO.getPublishDate1() && (!dataQueryDTO.getPublishDate1()
				.equals("")))) {
			sbd.append("and date_format(n.publish_date, '%Y-%m-%d %H:%i:%s') <= :publishDate1                                 ");
		}
		if (null != dataQueryDTO.getPublishStatusId()
				&& !dataQueryDTO.getPublishStatusId().equals("")) {
			sbd.append(" and n.publish_status = :publishStatusId ");
		}

		paramMap.put("title", dataQueryDTO.getTitle());
		paramMap.put("publishDate", dataQueryDTO.getPublishDate());
		paramMap.put("publishDate1", dataQueryDTO.getPublishDate1());
		paramMap.put("publishStatusId", dataQueryDTO.getPublishStatusId());
		paramMap.put("publishStatus", dataQueryDTO.getPublishStatus());

        System.out.println(sbd.toString());

		List<NewsDTO> retList = jdbcTemplate.query(sbd.toString(),
				paramMap, new BeanPropertyRowMapper<NewsDTO>(
						NewsDTO.class));

		if (retList != null && retList.size() > 0) {
			return retList;
		} else {
			return null;
		}
	}
	
	
	/**
	 * 新闻信息   发布状态
	 * @return
	 * @throws Exception
	 */
	public List<NewsDTO> findPublishStatus() throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sql = "select distinct(n.publish_status) as publishStatusId,case n.publish_status when 0 then '发布' when 1 then '关闭' else '无' end as publishStatus from news as n";
		List<NewsDTO> list = jdbcTemplate.query(sql.toString(), paramMap,
				new BeanPropertyRowMapper<NewsDTO>(NewsDTO.class));
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}

	}
	
	
	/**
	 * 
	 * 描述：删除新闻信息 qt 创建于 2014年7月17日
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteNews(Integer id) throws Exception {
		if (null != id) {
			return super.deleteById(tableName, id);
		}
		return false;
	}
	
	
}
