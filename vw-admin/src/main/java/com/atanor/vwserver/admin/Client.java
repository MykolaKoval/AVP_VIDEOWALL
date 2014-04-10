package com.atanor.vwserver.admin;

import com.atanor.vwserver.admin.injector.ClientInjector;
import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.model.LayoutStorage;
import com.atanor.vwserver.admin.mvp.model.SourceStorage;
import com.atanor.vwserver.admin.ui.MainPane;
import com.atanor.vwserver.common.rpc.services.ConfigServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.web.bindery.event.shared.EventBus;

public class Client {

	private static final ClientInjector injector = GWT.create(ClientInjector.class);

	public static void goTo(Place place) {
		injector.getPlaceController().goTo(place);
	}

	public static PlaceHistoryHandler getHistoryHandler() {
		return injector.getHistoryHandler();
	};

	public static PlaceController getPlaceController() {
		return injector.getPlaceController();
	}

	public static EventBus getEventBus() {
		return injector.getEventBus();
	}

	public static ConfigServiceAsync getConfigService() {
		return injector.getConfigService();
	}

	public static MainPane getMainPane() {
		return injector.getMainPane();
	}

	public static DisplayStorage getDisplayStorage() {
		return injector.getDisplayStorage();
	}
	
	public static LayoutStorage getLayoutStorage() {
		return injector.getLayoutStorage();
	}

	public static SourceStorage getSourceStorage() {
		return injector.getSourceStorage();
	}
}
