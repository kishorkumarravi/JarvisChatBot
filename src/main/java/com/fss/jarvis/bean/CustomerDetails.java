/**
 * 
 */
package com.fss.jarvis.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fss.jarvis.entity.LocatorDetails;

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
	
	@XmlElement(name = "isMpin")
	public String mPinFlag;
	
	@XmlElement(name = "isArray")
	public List<String> strArray;
	
	@XmlElement(name = "isArrayKey")
	public String strArrayKey;
	
	@XmlElement(name = "isSlideArray")
	public List<AISlideArrayResponse> strSlideArray;
	
	@XmlElement(name = "query")
	public String resolvedQuery;
	
	@XmlElement(name = "parameters")
	public AIParamRequest paramRequest;
	
	@XmlElement(name = "isLoc")
	public String  locationFlag;
	
	@XmlElement(name = "isMapArray")
	public List<LocatorDetails> strMapArray;
	
	public String speech;

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

	public String getmPinFlag() {
		return mPinFlag;
	}

	public void setmPinFlag(String mPinFlag) {
		this.mPinFlag = mPinFlag;
	}

	public List<String> getStrArray() {
		return strArray;
	}

	public void setStrArray(List<String> strArray) {
		this.strArray = strArray;
	}

	public String getStrArrayKey() {
		return strArrayKey;
	}

	public void setStrArrayKey(String strArrayKey) {
		this.strArrayKey = strArrayKey;
	}

	public List<AISlideArrayResponse> getStrSlideArray() {
		return strSlideArray;
	}

	public void setStrSlideArray(List<AISlideArrayResponse> strSlideArray) {
		this.strSlideArray = strSlideArray;
	}

	public String getResolvedQuery() {
		return resolvedQuery;
	}

	public void setResolvedQuery(String resolvedQuery) {
		this.resolvedQuery = resolvedQuery;
	}

	public AIParamRequest getParamRequest() {
		return paramRequest;
	}

	public void setParamRequest(AIParamRequest paramRequest) {
		this.paramRequest = paramRequest;
	}

	public String getSpeech() {
		return speech;
	}

	public void setSpeech(String speech) {
		this.speech = speech;
	}

	public String getLocationFlag() {
		return locationFlag;
	}

	public void setLocationFlag(String locationFlag) {
		this.locationFlag = locationFlag;
	}

	public List<LocatorDetails> getStrMapArray() {
		return strMapArray;
	}

	public void setStrMapArray(List<LocatorDetails> strMapArray) {
		this.strMapArray = strMapArray;
	}
	
	
}
