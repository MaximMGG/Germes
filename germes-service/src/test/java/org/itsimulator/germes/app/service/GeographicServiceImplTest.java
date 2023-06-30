package org.itsimulator.germes.app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.service.impl.GeographicServiceImpl;
import org.junit.Before;
import org.junit.Test;

public class GeographicServiceImplTest {
	private GeographicService service;
	
	@Before
	public void setup() {
		service = new GeographicServiceImpl();
	}
	
	@Test
	public void testNoDataReturnedAtStart() {
		List<City> cities = service.findCities();
		
		assertTrue(cities.isEmpty());
	}
	
	@Test void testSaveNewCitySuccess() {
		City city = new City("Poltava");
	
		service.saveCity(city);
		
		List<City> cities = service.findCities();
		assertTrue(cities.contains(city));
		assertEquals(cities.size(), 1);
		assertEquals(cities.get(0).getName(), "Poltava");
	}
}
