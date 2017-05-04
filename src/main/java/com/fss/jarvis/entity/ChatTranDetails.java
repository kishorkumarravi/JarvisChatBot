/**
 * 
 */
package com.fss.jarvis.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Abdulla
 *
 */
@Document(collection = "chatTranDetails")
public class ChatTranDetails {

	public String mobileNo;
	
	public String query;
	
	public String tType;
	
	public String aiAction;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String gettType() {
		return tType;
	}

	public void settType(String tType) {
		this.tType = tType;
	}

	public String getAiAction() {
		return aiAction;
	}

	public void setAiAction(String aiAction) {
		this.aiAction = aiAction;
	}
	
	
	
}
