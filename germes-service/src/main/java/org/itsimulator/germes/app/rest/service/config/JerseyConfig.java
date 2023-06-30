package org.itsimulator.germes.app.rest.service.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")

/**
 * REST web-service configuration for Jersey
 * @author Maxim
 */

public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		packages("org.itsimulator.app.rest");
	}
}