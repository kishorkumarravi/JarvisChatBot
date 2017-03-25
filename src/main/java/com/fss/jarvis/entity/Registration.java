/**
 * 
 */
package com.fss.jarvis.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Abdulla
 *
 */
@Document(collection = "registration")
public class Registration {
	
	@Id
	public String id;
	
	public String mobileNo;
	
	public String regName;
	
	public String emailId;

	
	
	public Registration() {
		
	}

	public Registration(String id, String mobileNo, String regName, String emailId) {
		this.id = id;
		this.mobileNo = mobileNo;
		this.regName = regName;
		this.emailId = emailId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
}
