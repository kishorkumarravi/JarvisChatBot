/**
 * 
 */
package com.fss.jarvis.bean;

import java.util.Locale;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Abdulla
 *
 */
@XmlRootElement
public class AIParamRequest {

	@XmlElement(name = "number")
	public String mobileNo;
	
	@XmlElement(name = "email")
	public String emailId;
	
	@XmlElement(name = "name")
	public String regname;

	@XmlElement(name = "tType")
	public String tranType;
	
	@XmlElement(name = "accountType")
	public String accountType;
	
	@XmlElement(name = "mpin")
	public String mpin;
	
	@XmlElement(name = "city")
	public String atmBrchCity;
	
	@XmlElement(name = "toLocate")
	public String atmBrchIdentifier;
	
	@XmlElement(name = "isNear")
	public String nearIdentifier;
	
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

	public String getRegname() {
		return regname;
	}

	public void setRegname(final String regname) {
		this.regname = regname;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(final String tranType) {
		this.tranType = tranType;
	}

	public String getAccountType() {
		if (accountType != null) {
			accountType = accountType.toLowerCase(Locale.ENGLISH);
		}
		return accountType;
	}

	public void setAccountType(final String accountType) {
		this.accountType = accountType;
	}

	public String getMpin() {
		return mpin;
	}

	public void setMpin(String mpin) {
		this.mpin = mpin;
	}

	public String getAtmBrchCity() {
		return atmBrchCity;
	}

	public void setAtmBrchCity(String atmBrchCity) {
		this.atmBrchCity = atmBrchCity;
	}

	public String getAtmBrchIdentifier() {
		return atmBrchIdentifier;
	}

	public void setAtmBrchIdentifier(String atmBrchIdentifier) {
		this.atmBrchIdentifier = atmBrchIdentifier;
	}

	public String getNearIdentifier() {
		return nearIdentifier;
	}

	public void setNearIdentifier(String nearIdentifier) {
		this.nearIdentifier = nearIdentifier;
	}
	
}
