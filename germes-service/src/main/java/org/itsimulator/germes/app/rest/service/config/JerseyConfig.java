package org.itsimulator.germes.app.rest.service.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.itsimulator.germes.app.config.ComponentFeature;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("api")

/**
 * REST web-service configuration for Jersey
 * @author Maxim
 */

public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		super(ComponentFeature.class);
		packages("org.itsimulator.germes.app.rest");
	}
}
