package org.itsimulator.germes.app.rest.service;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("cities")

/**
 * {@link CityResource} is REST web-service that handles city-related requests
 * @author Maxim
 */

public class CityResource  {
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> findCities() {
		return List.of("Poltava", "Odessa");
	}
}
