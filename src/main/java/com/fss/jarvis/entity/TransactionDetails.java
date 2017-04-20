/**
 * 
 */
package com.fss.jarvis.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Abdulla
 *
 */
@Document(collection = "tranDetails")
public class TransactionDetails {

	public String txnId;
	
	public String date;
	
	public String amount;
	
	public String remarks;
	
	public String mobileNo;
	
	public String accountNo;

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
}
