package org.itsimulator.germes.app.config;

import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;

/**
 * Register DI bindings
 * @author Maxim
 */
public class ComponentFeature implements Feature {
	@Override
	public boolean configure(final FeatureContext context) {
		context.register(new ComponentBinder());
		return true;
	}

}
