package org.itsimulator.germes.app.rest.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.glassfish.jersey.test.JerseyTest;
import org.itsimulator.germes.app.rest.dto.CityDTO;
import org.itsimulator.germes.app.rest.service.config.JerseyConfig;
import org.junit.Test;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * {@link CityResourceTest} is integration test that verifies
 * {@link CityResource}
 * @author Maxim
 */

public class CityResourceTest extends JerseyTest {
	
	@Override
	protected Application configure() {
		return new JerseyConfig();
	}
	
	@Test
	public void testFindCitiesSuccess() {
		List<Map<String, String>> cities = target("cities").request().get(List.class);
		assertNotNull(cities);
		assertEquals(cities.size(), 1);
		
		Map<String, String> city = cities.get(0);
		assertEquals(city.get("name"), "Poltava");
	}
	
	@Test
	public void testFindCityByIdSuccess() {
		CityDTO city = target("cities/1").request().get(CityDTO.class);
		assertNotNull(city);
		assertEquals(city.getId(), 1);
		assertEquals(city.getName(), "Poltava");
	}
	
	@Test
	public void testFindCityByIdNotFound() {
		Response response = target("cities/2").request().get(Response.class);
		assertNotNull(response);
		assertEquals(response.getStatus(), Response.Status.NOT_FOUND.getStatusCode());
	}
	@Test
	public void testFindCityByIdInvalidId() {
		Response response = target("cities/aaab").request().get(Response.class);
		assertNotNull(response);
		assertEquals(response.getStatus(), Response.Status.NO_CONTENT.getStatusCode());
	}
	@Test
	public void testSaveCitySuccess() {
		CityDTO city = new CityDTO();
		city.setName("Kiev");
		
		Response response = target("cities").request().post(Entity.entity(city, MediaType.APPLICATION_JSON));
		assertEquals(response.getStatus(), Response.Status.NO_CONTENT.getStatusCode());
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
