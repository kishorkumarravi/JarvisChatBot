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
public class AIRequest {

	@XmlElement(name = "lang")
	public String language;
	
	@XmlElement(name = "sessionId")
	public String sessionId;
	
	@XmlElement(name = "result")
	public AIResultRequest resultRequest;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public AIResultRequest getResultRequest() {
		return resultRequest;
	}

	public void setResultRequest(AIResultRequest resultRequest) {
		this.resultRequest = resultRequest;
	}
	
	
	
}
