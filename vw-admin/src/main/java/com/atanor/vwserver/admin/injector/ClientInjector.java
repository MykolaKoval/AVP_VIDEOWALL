package com.atanor.vwserver.admin.injector;

import com.atanor.vwserver.common.rpc.services.ConfigServiceAsync;
import com.atanor.vwserver.admin.mvp.view.EditView;
import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.mvp.view.PreviewView;
import com.atanor.vwserver.admin.ui.MainPane;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.web.bindery.event.shared.EventBus;

@GinModules(ClientModule.class)
public interface ClientInjector extends Ginjector {

	EventBus getEventBus();

	PlaceController getPlaceController();

	PlaceHistoryHandler getHistoryHandler();
	
	HeaderView getHeaderPresetView();
	
	PreviewView getNavigatePresetView();

	EditView getEditPresetView();
	
	ConfigServiceAsync getConfigService();
	
	MainPane getMainPane();
}
