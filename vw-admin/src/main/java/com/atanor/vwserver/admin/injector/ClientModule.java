package com.atanor.vwserver.admin.injector;

import com.google.gwt.inject.client.AbstractGinModule;

public class ClientModule extends AbstractGinModule {

	@Override
	protected void configure() {

		install(new MvpModule());
		install(new ViewModule());
	}

}
