package com.nspaces.oss.base.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;

/**
 * 操作Mongo数据库
 * @author whw
 *
 */
public abstract class MongoBaseDAO {

    protected static final Logger logger = LoggerFactory.getLogger(MongoBaseDAO.class);
	
	@Autowired
	@Qualifier("anotherMongoTemplate")
	protected MongoTemplate mongoTemplate;
	
	/**
	 * insert 一条记录
	 * @param object
	 */
	public <T> void insert(T object) {  
	    mongoTemplate.insert(object);  
	}  
	
	/*
	 * 批量插入
	 */
	 public <T> void insertAll(List<T> objs) {  
		 mongoTemplate.insertAll(objs);  
	 }  
	
	/**
	 * 删除一条记录
	 * @param object
	 */
	public <T> void remove(T object) {  
	    mongoTemplate.remove(object);  
	}  
	
	
	public <T> void delete(T t) {
		mongoTemplate.remove(t);
	}
	
	public <T> List<T> findAll(Class<T> c) {
		return mongoTemplate.findAll(c);
	}

	public <T> WriteResult updateObject(ObjectId id, String columnName,  String name, Class<T> t) {
		return mongoTemplate.updateFirst(
				new Query(Criteria.where("id").is(id)),
				Update.update(columnName, name), t);
	}
	
	public <T> WriteResult updateObject(ObjectId id, Update update, Class<T> t) {
		return mongoTemplate.updateFirst(
				new Query(Criteria.where("id").is(id)),
				update, t);
	}
	
	public <T> void updateMultiObject(Query query, Update update, Class<T> t) {
		mongoTemplate.updateMulti(query,update, t);
	}

	public <T> long count(Query query, Class<T> c) {
		return mongoTemplate.count(query, c);
	}
	
	
	/**
	 * 鎸夊鐞嗙姸鎬侊紝鍒嗛〉鏌ヨ
	 * @param page
	 * @param pageSize
	 * @param c
	 * @return
	 */
    public <T> List<T> findAllByQuery(Query query, Class<T> c, String collectionName)
    {
    	return mongoTemplate.find(query, c, collectionName);
    }
	
    /**
	 * 鎸夊鐞嗙姸鎬侊紝鍒嗛〉鏌ヨ
	 * @param page
	 * @param pageSize
	 * @param c
	 * @return
	 */
    public <T> List<T> findAllByQuery(Query query, Class<T> c)
    {
    	return mongoTemplate.find(query, c);
    }

}
