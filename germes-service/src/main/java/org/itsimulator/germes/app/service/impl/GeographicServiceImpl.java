package org.itsimulator.germes.app.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.itsimulator.germes.app.infra.util.CommonUtil;
import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.entity.geography.Station;
import org.itsimulator.germes.app.model.search.criteria.StationCriteria;
import org.itsimulator.germes.app.model.search.range.RangeCriteria;
import org.itsimulator.germes.app.service.GeographicService;
import org.itsimulator.germes.persistance.repository.CityRepository;
import org.itsimulator.germes.persistance.repository.inmemory.InMemoryCityRepository;

/**
 * Default implementation of the {@link GeographicService}
 * @author Maxim
 *
 */

public class GeographicServiceImpl implements GeographicService {
	private final CityRepository cityRepository;
	

	
	public GeographicServiceImpl() {
		cityRepository = new InMemoryCityRepository();
	}
	
	@Override
	public List<City> findCities() {
		return cityRepository.findAll();
	}
	
	@Override
	public void saveCity(City city) {
		cityRepository.save(city);
	}
	

	@Override
	public Optional<City> findCityById(final int id) {
		return Optional.ofNullable(cityRepository.findById(id));
	}
	
	@Override
	public List<Station> searchStations(final StationCriteria criteria, final RangeCriteria rangeCriteria) {
		Set<Station> stations = new HashSet<>();
		
		cityRepository.findAll().forEach(city -> stations.addAll(city.getStations()));
		
		return stations.stream().filter(station -> station.match(criteria)).toList();
	
	}

	
}
