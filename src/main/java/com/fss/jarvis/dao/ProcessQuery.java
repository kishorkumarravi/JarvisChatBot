/**
 * 
 */
package com.fss.jarvis.dao;

import java.util.List;

import com.fss.jarvis.entity.AccountInfo;
import com.fss.jarvis.entity.Registration;
import com.fss.jarvis.entity.TransactionConfiguration;

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
	
}
