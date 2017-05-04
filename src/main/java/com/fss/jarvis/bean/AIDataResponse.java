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


	
	@XmlElement(name = "jarvis")
	public CustomerDetails customerDetails;
	
	


	public CustomerDetails getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(CustomerDetails customerDetails) {
		this.customerDetails = customerDetails;
	}
	
}
