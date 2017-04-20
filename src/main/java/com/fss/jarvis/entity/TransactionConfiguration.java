/**
 * 
 */
package com.fss.jarvis.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Abdulla
 *
 */
@Document(collection = "tranConfig")
public class TransactionConfiguration {

	public String messageId;
	
	public String mpinRequiredFlg;
	
	public String messageDesc;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getMpinRequiredFlg() {
		return mpinRequiredFlg;
	}

	public void setMpinRequiredFlg(String mpinRequiredFlg) {
		this.mpinRequiredFlg = mpinRequiredFlg;
	}

	public String getMessageDesc() {
		return messageDesc;
	}

	public void setMessageDesc(String messageDesc) {
		this.messageDesc = messageDesc;
	}

	

	
	
	
	
}
