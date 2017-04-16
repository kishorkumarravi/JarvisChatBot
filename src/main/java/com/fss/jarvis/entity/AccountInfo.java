/**
 * 
 */
package com.fss.jarvis.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Abdulla
 *
 */
@Document(collection = "accountsinfo")
public class AccountInfo {

	public String mobileNo;
	
	public String balance;
	
	public String accountType;
	
	public String accountNo;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(final String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(final String balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(final String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
}
