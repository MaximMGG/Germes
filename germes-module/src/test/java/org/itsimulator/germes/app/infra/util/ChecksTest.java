package org.itsimulator.germes.app.infra.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.itsimulator.germes.app.infra.exception.flow.InvalidParameterException;
import org.junit.Test;

/**
 * Verifies functionality of {@link Checks} class
 * 
 * @author Maxim
 *
 */

public class ChecksTest {

	@Test
	public void testCheckParameterGetException() {
		try {
			Checks.checkParameter(false, "test");
			
			assertTrue(false);
			
		} catch (Exception e) {
			assertSame(e.getClass(), InvalidParameterException.class);
			assertEquals(e.getMessage(), "test");
		}
	}
	
	@Test
	public void testCheckParameterNoException() {
		Checks.checkParameter(true, "test");
		
		assertTrue(true);
	}
	
	
	
	
}
