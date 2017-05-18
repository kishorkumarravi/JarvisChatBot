/**
 * 
 */
package com.fss.jarvis.dao;

import java.util.List;

import com.fss.jarvis.entity.AccountInfo;
import com.fss.jarvis.entity.LocatorDetails;
import com.fss.jarvis.entity.Registration;
import com.fss.jarvis.entity.SearchedResult;
import com.fss.jarvis.entity.TransactionConfiguration;
import com.fss.jarvis.entity.TransactionDetails;

/**
 * @author Abdulla
 * @param <T>
 *
 */
public interface ProcessQuery<T> {

	public void saveObject(T object);
	
	public Registration getUserDetails(String mobileNo);
	
	public List<AccountInfo> getBalance(String mobileNo, String accounttype);
	
	long getNextSequenceId(String key);
	
	public TransactionConfiguration getMpinFlag(String tType);
	
	public List<String> getAccountType(String mobileNo);
	
	public List<TransactionDetails> getTxnDetails();
	
	public List<LocatorDetails> getAtmAddress(String city);
	
	public List<SearchedResult> getSearchedQuery(String mobileNo);
 	
}
