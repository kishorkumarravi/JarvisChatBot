/**
 * 
 */
package com.fss.jarvis.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.fss.jarvis.dao.ProcessQuery;
import com.fss.jarvis.entity.AccountInfo;
import com.fss.jarvis.entity.Registration;

/**
 * @author Abdulla
 * @param <T>
 *
 */
@Repository("processQuery")
public class ProcessQueryImpl<T> implements ProcessQuery<T>{

	public static Logger LOGGER = LoggerFactory.getLogger(ProcessQueryImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void saveObject(T object) {
		LOGGER.info("Inserting new Record of {}", object);
		mongoTemplate.insert(object);
	}

	@Override
	public Registration getUserDetails(String mobileNo) {
		
		Query query = new Query(Criteria.where("mobileNo").is(mobileNo));
		LOGGER.info("get User Details Query is {}", query);
		Registration registration = mongoTemplate.findOne(new Query(Criteria.where("mobileNo").is(mobileNo)), Registration.class);
		return registration;
	}

	@Override
	public AccountInfo getBalance(String mobileNo) {
		Query query = new Query(Criteria.where("mobileNo").is(mobileNo));
		LOGGER.info("get User Details Query is {}", query);
		AccountInfo accInfo = mongoTemplate.findOne(new Query(Criteria.where("mobileNo").is(mobileNo)), AccountInfo.class);
		return accInfo;
	}
	
}
