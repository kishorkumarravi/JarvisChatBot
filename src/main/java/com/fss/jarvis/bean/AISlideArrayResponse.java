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
public class AISlideArrayResponse {

	@XmlElement(name = "field1")
	public String dataOne;
	
	@XmlElement(name = "field2")
	public String dataTwo;
	
	@XmlElement(name = "field3")
	public String dataThree;
	
	@XmlElement(name = "field4")
	public String dataFour;

	public String getDataOne() {
		return dataOne;
	}

	public void setDataOne(String dataOne) {
		this.dataOne = dataOne;
	}

	public String getDataTwo() {
		return dataTwo;
	}

	public void setDataTwo(String dataTwo) {
		this.dataTwo = dataTwo;
	}

	public String getDataThree() {
		return dataThree;
	}

	public void setDataThree(String dataThree) {
		this.dataThree = dataThree;
	}

	public String getDataFour() {
		return dataFour;
	}

	public void setDataFour(String dataFour) {
		this.dataFour = dataFour;
	}
	
	
	
}
