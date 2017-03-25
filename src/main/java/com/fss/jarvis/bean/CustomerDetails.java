/**
 * 
 */
package com.fss.jarvis.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author mohammeda
 *
 */
@XmlRootElement
public class CustomerDetails {

	@XmlElement(name = "mobNo")
	public String mobileNo;
	
	@XmlElement(name = "emailId")
	public String emailId;
	
	@XmlElement(name = "regName")
	public String remitterName;
	
	@XmlElement(name = "isReg")
	public String regFlag;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(final String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(final String emailId) {
		this.emailId = emailId;
	}

	public String getRemitterName() {
		return remitterName;
	}

	public void setRemitterName(final String remitterName) {
		this.remitterName = remitterName;
	}

	public String getRegFlag() {
		return regFlag;
	}

	public void setRegFlag(final String regFlag) {
		this.regFlag = regFlag;
	}
	
	
	
}
