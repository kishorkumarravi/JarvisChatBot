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
@Document(collection = "tranConfig")
public class TransactionConfiguration {

	public String messageId;
	
	public String mpinRequiredFlg;
	
	public String messageDesc;
	
	@Id
	public long id;

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	
	
	
	
}
