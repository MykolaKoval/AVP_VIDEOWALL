package com.atanor.vwserver.admin.injector;

import com.atanor.vwserver.common.rpc.services.ConfigServiceAsync;
import com.atanor.vwserver.admin.mvp.views.EditPresetView;
import com.atanor.vwserver.admin.mvp.views.NavigatePresetView;
import com.atanor.vwserver.admin.ui.MainPane;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

@GinModules(ClientModule.class)
public interface ClientInjector extends Ginjector {

	EventBus getEventBus();

	PlaceController getPlaceController();

	NavigatePresetView getNavigatePresetView();

	EditPresetView getEditPresetView();
	
	ConfigServiceAsync getConfigService();
	
	MainPane getMainPane();
}
