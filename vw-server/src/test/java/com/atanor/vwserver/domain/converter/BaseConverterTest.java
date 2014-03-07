package com.atanor.vwserver.domain.converter;

import javax.inject.Inject;

import org.junit.Before;

import com.atanor.vwserver.injector.AppConverterModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class BaseConverterTest<C extends Converter<?, ?>> {

	@Inject
	C converter;

	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(new AppConverterModule());
		injector.injectMembers(this);
	}

}
