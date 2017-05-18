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
	
	@XmlElement(name = "resolvedQuery")
	public String reqQuery;
	
	@XmlElement(name = "action")
	public String reqAction;
	
	@XmlElement(name = "fulfillment")
	public AIFulfillmentRequest fulFillmentRequest;

	public AIParamRequest getParameters() {
		return parameters;
	}

	public void setParameters(AIParamRequest parameters) {
		this.parameters = parameters;
	}

	public String getReqQuery() {
		return reqQuery;
	}

	public void setReqQuery(String reqQuery) {
		this.reqQuery = reqQuery;
	}

	public String getReqAction() {
		return reqAction;
	}

	public void setReqAction(String reqAction) {
		this.reqAction = reqAction;
	}

	public AIFulfillmentRequest getFulFillmentRequest() {
		return fulFillmentRequest;
	}

	public void setFulFillmentRequest(AIFulfillmentRequest fulFillmentRequest) {
		this.fulFillmentRequest = fulFillmentRequest;
	}
	
}
