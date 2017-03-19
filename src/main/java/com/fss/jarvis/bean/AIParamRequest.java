/**
 * 
 */
package com.fss.jarvis.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Abdulla
 *
 */
@XmlRootElement
public class AIParamRequest {

	@XmlElement(name = "mobileNo")
	public String mobileNo;
	
	@XmlElement(name = "emailId")
	public String emailId;
	
	@XmlElement(name = "name")
	public String regname;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getRegname() {
		return regname;
	}

	public void setRegname(String regname) {
		this.regname = regname;
	}
	
	
	
	
}
