package org.itsimulator.germes.app.infra.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.itsimulator.germes.app.infra.exception.ConfigurationException;
import org.itsimulator.germes.app.infra.exception.flow.InvalidParameterException;
import org.junit.Test;

/**
 * Verifies functionality of the {@link ReflectionUtil} unit
 * 
 * @author Maxim
 */

public class ReflecionUtilTest {
	
	@Test
	public void createInstanceSuccess() {
		Object value = ReflectionUtil.createInstance(Source.class);
		assertNotNull(true);
	}
	
	@Test(expected = ConfigurationException.class)
	public void createInstanceFailure() {
		ReflectionUtil.createInstance(Restricted.class);
	}
	@Test
	public void findSimilarFieldsSuccess() {
		List<String> fields = ReflectionUtil.findSimilarFields(Source.class, Destination.class);
		
		assertNotNull(fields);
		assertTrue(fields.contains("value"));
	}
		

	@Test
	public void copyFieldsSuccess() {
		Source src = new Source();
		src.setValue(10);
		Destination dest = new Destination();
		List<String> fields = ReflectionUtil.findSimilarFields(src.getClass(), dest.getClass());
		
		ReflectionUtil.copyFields(src, dest, fields);
		assertEquals(dest.getValue(), 10);
	}
	@Test(expected=InvalidParameterException.class)
	public void copyFieldsDestinationNullFailure() {
		Source src = new Source();
		ReflectionUtil.copyFields(null, src, Collections.emptyList());
	}
}

class Destination {
	private int value;
	
	public int getValue() {
		return value;
	}
	
}

class Source {
	private int value;
	
	private String test;
	
	public void setValue(int value) {
		this.value = value;
	}
}

class Restricted {
	public Restricted(int value) {
		
	}
}
