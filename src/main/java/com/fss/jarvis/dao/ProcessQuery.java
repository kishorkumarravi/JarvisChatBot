/**
 * 
 */
package com.fss.jarvis.dao;

import com.fss.jarvis.entity.AccountInfo;
import com.fss.jarvis.entity.Registration;

/**
 * @author Abdulla
 * @param <T>
 *
 */
public interface ProcessQuery<T> {

	public void saveObject(T object);
	
	public Registration getUserDetails(String mobileNo);
	
	public AccountInfo getBalance(String mobileNo);
	
}
