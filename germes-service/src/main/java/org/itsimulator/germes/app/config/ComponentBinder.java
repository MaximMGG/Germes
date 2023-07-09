package org.itsimulator.germes.app.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.itsimulator.germes.app.service.GeographicService;
import org.itsimulator.germes.app.service.impl.GeographicServiceImpl;
import org.itsimulator.germes.app.service.transform.Transformer;
import org.itsimulator.germes.app.service.transform.impl.SimpleDTOTransformer;
import org.itsimulator.germes.persistance.repository.CityRepository;
import org.itsimulator.germes.persistance.repository.inmemory.InMemoryCityRepository;

import jakarta.inject.Singleton;

/**
 * Binds bean implementations and implemented interfaces
 * @author Maxim
 */
public class ComponentBinder extends AbstractBinder {
	
	@Override
	protected void configure() {
		bind(InMemoryCityRepository.class).to(CityRepository.class).in(Singleton.class);
		bind(SimpleDTOTransformer.class).to(Transformer.class).in(Singleton.class);
		bind(GeographicServiceImpl.class).to(GeographicService.class).in(Singleton.class);
		
	}

}
