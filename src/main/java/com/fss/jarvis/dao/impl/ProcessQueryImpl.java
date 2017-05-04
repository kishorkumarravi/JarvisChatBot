/**
 * 
 */
package com.fss.jarvis.dao.impl;

import java.util.ArrayList;
import java.util.List;

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
import com.fss.jarvis.entity.LocatorDetails;
import com.fss.jarvis.entity.Registration;
import com.fss.jarvis.entity.SequenceId;
import com.fss.jarvis.entity.TransactionConfiguration;
import com.fss.jarvis.entity.TransactionDetails;

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
	public List<AccountInfo> getBalance(String mobileNo, String accounttype) {
		Criteria criteria = new Criteria();
		if (accounttype.equalsIgnoreCase("ALL")) {
			criteria = Criteria.where("mobileNo").is(mobileNo);
		} else {
        criteria.andOperator(Criteria.where("mobileNo").is(mobileNo),Criteria.where("accountType").is(accounttype));
		}
		Query query = new Query(criteria);
		LOGGER.info("get User Details Query is {}", query);
		List<AccountInfo> accInfo = mongoTemplate.find((query), AccountInfo.class);
		return accInfo;
	}
	
	@Override
	public TransactionConfiguration getMpinFlag(String tType) {
		Query query = new Query(Criteria.where("messageId").is(tType));
		LOGGER.info("getMpinFlag is {}", query);
		TransactionConfiguration tranConfig = mongoTemplate.findOne(query, TransactionConfiguration.class);
		return tranConfig;
	}

	@Override
	public List<String> getAccountType(String mobileNo) {
		Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("mobileNo").is(mobileNo));
		Query query = new Query(criteria);
		LOGGER.info("get User Details Query is {}", query);
		List<AccountInfo> accInfo = mongoTemplate.find((query), AccountInfo.class);
		List<String> accList = new ArrayList<String>();
		for (AccountInfo accIfo : accInfo) {
			accList.add(accIfo.getAccountType());
		}
		return accList;
	}

	@Override
	public List<TransactionDetails> getTxnDetails() {
		List<TransactionDetails> txnDetails = mongoTemplate.findAll(TransactionDetails.class);
		return txnDetails;
	}

	@Override
	public List<LocatorDetails> getAtmAddress(String city) {
		Criteria criteria = new Criteria();
		List<LocatorDetails> atmList = null;
		if (city != null && !"".equals(city)) {
			criteria.andOperator(Criteria.where("city").is(city));
			Query query = new Query(criteria);
			LOGGER.info("getAtmAddress Details Query is {}", query);
			atmList = mongoTemplate.find((query), LocatorDetails.class);
		} else {
			atmList = mongoTemplate.findAll(LocatorDetails.class);
		}
		if (atmList.size() == 0) {
			atmList = mongoTemplate.findAll(LocatorDetails.class);
		}
		/*
		 * List<String> atmAddrList = new ArrayList<String>(); for
		 * (LocatorDetails atmAddr : atmList) {
		 * atmAddrList.add(atmAddr.getAddress()); }
		 */
		return atmList;
	}
}
