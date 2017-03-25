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
public class AIResponse {

	@XmlElement(name = "data")
	public AIDataResponse aiDataRsp;

	@XmlElement(name = "speech")
	public String speech;
	
	@XmlElement(name = "displayText")
	public String displayText;

	public AIDataResponse getAiDataRsp() {
		return aiDataRsp;
	}

	public void setAiDataRsp(final AIDataResponse aiDataRsp) {
		this.aiDataRsp = aiDataRsp;
	}

	public String getSpeech() {
		return speech;
	}

	public void setSpeech(final String speech) {
		this.speech = speech;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(final String displayText) {
		this.displayText = displayText;
	}
	
	
}
