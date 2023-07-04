package org.itsimulator.germes.app.rest.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.math.NumberUtils;
import org.itsimulator.germes.app.model.entity.geography.City;
import org.itsimulator.germes.app.model.entity.transport.TransportType;
import org.itsimulator.germes.app.rest.dto.CityDTO;
import org.itsimulator.germes.app.rest.service.base.BaseResource;
import org.itsimulator.germes.app.service.GeographicService;
import org.itsimulator.germes.app.service.impl.GeographicServiceImpl;
import org.itsimulator.germes.app.service.transform.Transformer;
import org.itsimulator.germes.app.service.transform.impl.SimpleDTOTransformer;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("cities")

/**
 * {@link CityResource} is REST web-service that handles city-related requests
 * @author Maxim
 */

public class CityResource extends BaseResource {
	
	/**
	 * Underlying source of data
	 */
	private final GeographicService service;
	
	/**
	 * DTO <-> Entity transformer
	 */
	private final Transformer transformer;
	
	public CityResource() {
		transformer = new SimpleDTOTransformer();
		
		service = new GeographicServiceImpl();
		City city = new City("Poltava");
		city.addStation(TransportType.AUTO);
		service.saveCity(city);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Returns all the existing cities
	 * @return
	 */
	public List<CityDTO> findCities() {
		return service.findCities().stream().map(city -> transformer.transform(city, CityDTO.class)).toList();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	
	/**
	 * Saves new city instatnce
	 * 
	 */
	public void saceCity(CityDTO cityDTO) {
		service.saveCity(transformer.untransform(cityDTO, City.class));
	}
	
	@Path("/{cityId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	/**
	 * Returns city with specified identifier
	 * @return
	 */
	public Response findCityById(@PathParam("cityId") final String cityId) {
		if (!NumberUtils.isNumber(cityId)) {
			return BAD_REQEST;
		}
		
		Optional<City> city = service.findCityById(NumberUtils.toInt(cityId));
		if (!city.isPresent()) {
			return NOT_FOUND;
		}
		return ok(transformer.transform(city.get(), CityDTO.class));
	}
	

}
