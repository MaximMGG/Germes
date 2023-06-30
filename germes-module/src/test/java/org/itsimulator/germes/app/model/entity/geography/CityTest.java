package org.itsimulator.germes.app.model.entity.geography;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.itsimulator.germes.app.model.entity.transport.TransportType;
import org.junit.Before;
import org.junit.Test;

public class CityTest {

	private City city;
	
	@Before
	public void setup() {
		city = new City("Poltava");
	}
	
	@Test
	public void testAddValidStationSuccess() {
		Station station = city.addStation(TransportType.AUTO);
		
		assertTrue(containsStation(city, station));
		assertEquals(city, station.getCity());
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddStationNullTranportTypeFailure() {
		city.addStation(null);
		
		assertTrue(false);
	}
	
	@Test
	public void testRemovStationSuccess() {
		Station station = city.addStation(TransportType.RAILWAY);
		
		city.removeStation(station);
		
		assertTrue(city.getStations().isEmpty());
	}
	
	@Test(expected=NullPointerException.class)
	public void testRemoveNullStationFailure() {
		city.removeStation(null);
		
		assertTrue(false);
	}
	
	
	public boolean containsStation(City city, Station station) {
		return city.getStations().contains(station);
	}
}
