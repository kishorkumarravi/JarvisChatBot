/**
 * 
 */
package com.fss.jarvis.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Abdullah
 *
 */
@XmlRootElement
public class AIDataResponse {

	@XmlElement(name = "speech")
	public String speech;
	
	@XmlElement(name = "jarvis")
	public CustomerDetails customerDetails;

	@XmlElement(name = "displayText")
	public String displayText;
	
	public String getSpeech() {
		return speech;
	}

	public void setSpeech(final String speech) {
		this.speech = speech;
	}

	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(final String displayText) {
		this.displayText = displayText;
	}
	
}
