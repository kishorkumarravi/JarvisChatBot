/**
 * 
 */
package com.fss.jarvis.resource;

import javax.ws.rs.core.Response;

import com.fss.jarvis.bean.AIRequest;

/**
 * @author Abdulla
 *
 */
public interface RegistrationResource {

	public Response doRegistration(AIRequest request);
}
