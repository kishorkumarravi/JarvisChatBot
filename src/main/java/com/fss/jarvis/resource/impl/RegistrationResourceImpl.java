/**
 * 
 */
package com.fss.jarvis.resource.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fss.jarvis.bean.AIRequest;
import com.fss.jarvis.bean.AIResponse;
import com.fss.jarvis.resource.RegistrationResource;
import com.fss.jarvis.service.RegistrationService;

/**
 * @author Abdulla
 *
 */
@Path("/registration")
public class RegistrationResourceImpl implements RegistrationResource {

	
	public static Logger LOGGER = LoggerFactory.getLogger(RegistrationResourceImpl.class);
	
	@Autowired
	private RegistrationService registrationService;
	
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response doRegistration(AIRequest request) {
		AIResponse response = registrationService.insertUserDetails(request);
		return Response.status(Status.OK).entity(response).build();
	}

}
