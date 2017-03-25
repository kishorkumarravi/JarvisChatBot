/**
 * 
 */
package com.fss.jarvis.service;

import com.fss.jarvis.bean.AIRequest;
import com.fss.jarvis.bean.AIResponse;

/**
 * @author Abdulla
 *
 */
public interface RegistrationService {

	public AIResponse insertUserDetails(AIRequest request);
	
}
