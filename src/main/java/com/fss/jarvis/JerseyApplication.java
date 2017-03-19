/**
 * 
 */
package com.fss.jarvis;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author Abdulla
 *
 */
@Configuration
@ApplicationPath("/resource")
public class JerseyApplication extends ResourceConfig {

	public JerseyApplication() {
		packages("com.fss.jarvis");

	}


}
