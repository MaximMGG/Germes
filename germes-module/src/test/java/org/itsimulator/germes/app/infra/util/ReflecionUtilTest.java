package org.itsimulator.germes.app.infra.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.itsimulator.germes.app.infra.exception.ConfigurationException;
import org.itsimulator.germes.app.infra.exception.flow.InvalidParameterException;
import org.itsimulator.germes.app.infra.util.annotation.Ignore;
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
	@Test
	public void copyFindSimilarFieldsWithIgnoreSuccess() {
		List<String> fields = ReflectionUtil.findSimilarFields(Source.class, Destination.class);
		assertFalse(fields.contains("ignored"));
	}
	
	@Test
	public void copyFindSimilarForStaticAndFinalSuccess() {
		List<String> fields = ReflectionUtil.findSimilarFields(Source.class, Destination.class);
		assertFalse(fields.contains("staticField"));
		assertFalse(fields.contains("finalField"));
	}
	
	@Test
	public void copyFindSimilarFieldsForBaseFieldSuccess() {
		List<String> fields = ReflectionUtil.findSimilarFields(Source.class, Destination.class);
		assertTrue(fields.contains("baseField"));
	}
	
	
	@Test(expected=InvalidParameterException.class)
	public void copyFieldsDestinationNullFailure() {
		Source src = new Source();
		ReflectionUtil.copyFields(null, src, Collections.emptyList());
	}
}

class BaseSource {
	private int baseField;
}

class BaseDestination {
	private int baseField;
}



class Source extends BaseSource {
	private int value;
	
	private String test;
	
	@Ignore
	private int ignored = 2;
	
	private static int staticField;
	
	private final int finalField = 0;
	
	public void setValue(int value) {
		this.value = value;
	}
}

class Destination extends BaseDestination {
	private int value;
	
	private int ignored;
	
	private int staticField;
	
	private final int finalField = 0;
	
	public int getValue() {
		return value;
	}
	
}
class Restricted {
	public Restricted(int value) {
		
	}
}
