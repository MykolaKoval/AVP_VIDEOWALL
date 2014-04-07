package com.atanor.vwserver.injector;

import com.atanor.vwserver.common.AppConstants;
import com.atanor.vwserver.servlet.ConfigServlet;
import com.atanor.vwserver.servlet.DisplayServlet;
import com.atanor.vwserver.servlet.LayoutServlet;
import com.atanor.vwserver.servlet.SourceServlet;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

public class AppServletModule extends ServletModule {

	private static final String BASE_URL = "/VwAdmin/";

	@Override
	protected void configureServlets() {
		// PersistFilter provides a new instance of EntityManager for each
		// request to the servlet container (Open Session In View pattern)
		install(new JpaPersistModule("VWSERVER-JPA"));
		filter("/*").through(PersistFilter.class);

		serve(BASE_URL + AppConstants.CONFIG_PATH).with(ConfigServlet.class);
		serve(BASE_URL + AppConstants.DISPLAY_PATH).with(DisplayServlet.class);
		serve(BASE_URL + AppConstants.LAYOUT_PATH).with(LayoutServlet.class);
		serve(BASE_URL + AppConstants.SOURCE_PATH).with(SourceServlet.class);
	}

}
