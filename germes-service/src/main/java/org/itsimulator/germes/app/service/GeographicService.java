package org.itsimulator.germes.app.service;

import java.util.List;

import org.itsimulator.germes.app.model.entity.geography.City;

/**
 * Entry point to perform operations 
 * over geographic entites
 * @author Maxim
 */
public interface GeographicService {
	
	
	/**
	 * Returns list of existing cities
	 * @return
	 */
	
	List<City> findCities();
	
	/**
	 * Saves specified city instance
	 */
	void saveCity(City city);

}
