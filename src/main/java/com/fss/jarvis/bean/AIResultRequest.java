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
public class AIResultRequest {

	@XmlElement(name = "parameters")
	public AIParamRequest parameters;

	public AIParamRequest getParameters() {
		return parameters;
	}

	public void setParameters(AIParamRequest parameters) {
		this.parameters = parameters;
	}
	
}
