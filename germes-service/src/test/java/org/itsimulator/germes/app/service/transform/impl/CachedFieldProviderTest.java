package org.itsimulator.germes.app.service.transform.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.internal.compiler.parser.diagnose.DiagnoseParser;
import org.itsimulator.germes.app.infra.util.ReflectionUtil;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;

import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;

/**
 * Verifies functionality of the {@link SimpleDTOTransformer}
 * unit
 * @author Maxim
 */

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(fullyQualifiedNames = "org.itsimulator.germes.app.infra.util.*")
@RunWith(JMockit.class)
public class CachedFieldProviderTest {
	
	@InjectMocks
	private FieldProvider provider;
	
	@Before
	public void setup() {
		provider = new CachedFieldProvider();
	}
	
	@Test
	public void testGetFieldNamesSuccess() {
		List<String> fields = provider.getFieldNames(Source.class, Destination.class);
		assertFalse(fields.isEmpty());
		assertTrue(fields.contains("value"));
	}
	
	@Test
	public void testGetFieldNamesCachedSuccess() {
		List<String> fields = provider.getFieldNames(Source.class, Destination.class);
		List<String> fields2 = provider.getFieldNames(Source.class, Destination.class);
		assertFalse(fields.isEmpty());
		assertEquals(fields, fields2);
	}
	
	@Test
	@Ignore
	public void testGetFieldNamesAreCached() {
		List<String> fields = provider.getFieldNames(Source.class, Destination.class);
		
		PowerMockito.mockStatic(ReflectionUtil.class);
		when(ReflectionUtil.findSimilarFields(Source.class, Destination.class)).thenReturn(Collections.emptyList());
		
		assertTrue(ReflectionUtil.findSimilarFields(Source.class, Destination.class).isEmpty());
		List<String> fields2 = provider.getFieldNames(Source.class, Destination.class);
		assertFalse(fields2.isEmpty());
		assertEquals(fields, fields2);
	}
	
	public static final class MockReglectionUtil extends MockUp<ReflectionUtil> {
		protected static List<String> fields;
		
		@Mock
		public static List<String> findSimilarFields(Class<?> clz1, Class<?> clz2) {
			return fields;
		}
	}

	@Test
	public void testGetFieldNamesAreCachedUsengMockupsAPI() {
		new MockReglectionUtil();
		
		MockReglectionUtil.fields = Collections.singletonList("name");
		
		List<String> fields = provider.getFieldNames(Source.class, Destination.class);
		
		MockReglectionUtil.fields = Collections.emptyList();
		
		assertTrue(ReflectionUtil.findSimilarFields(Source.class, Destination.class).isEmpty());
		List<String> fields2 = provider.getFieldNames(Source.class, Destination.class);
		assertFalse(fields.isEmpty());
		assertEquals(fields, fields2);
	}
	
}

class Source {
	int value;
}
class Destination {
	int value;
}
