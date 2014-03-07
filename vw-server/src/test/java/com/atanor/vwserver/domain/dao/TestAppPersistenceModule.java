package com.atanor.vwserver.domain.dao;

import com.atanor.vwserver.injector.AppPersistenceModule;
import com.google.inject.persist.jpa.JpaPersistModule;

public class TestAppPersistenceModule extends AppPersistenceModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("TEST-VWSERVER-JPA"));
		bind(TestJPAInitializer.class).asEagerSingleton();
		super.configure();
	}

}
