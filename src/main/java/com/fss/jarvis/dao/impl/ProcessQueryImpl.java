/**
 * 
 */
package com.fss.jarvis.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.fss.jarvis.dao.ProcessQuery;
import com.fss.jarvis.entity.AccountInfo;
import com.fss.jarvis.entity.Registration;
import com.fss.jarvis.entity.SequenceId;

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
	
	@Autowired
	private MongoOperations mongoOperation;

	@Override
	public long getNextSequenceId(String key)  {

	  //get sequence id
	  Query query = new Query(Criteria.where("_id").is(key));

	  //increase sequence id by 1
	  Update update = new Update();
	  update.inc("seq", 1);

	  //return new increased id
	  FindAndModifyOptions options = new FindAndModifyOptions();
	  options.returnNew(true);

	  //this is the magic happened.
	  SequenceId seqId = mongoOperation.findAndModify(query, update, options, SequenceId.class);
	  
	  return seqId.getSeq();

	}
	
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
