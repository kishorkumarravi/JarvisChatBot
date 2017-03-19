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

import com.fss.jarvis.bean.AIRequest;
import com.fss.jarvis.bean.Registration;
import com.fss.jarvis.resource.RegistrationResource;

/**
 * @author Abdulla
 *
 */
@Path("/registration")
public class RegistrationResourceImpl implements RegistrationResource {

	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response doRegistration(AIRequest request) {
		Registration registraton = new Registration();
		registraton.setMobileNo(request.getResultRequest().getParameters().getMobileNo());
		return Response.status(Status.OK).entity(registraton).build();
	}

}
