package com.atanor.vwserver.admin.injector;

import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.model.LayoutStorage;
import com.atanor.vwserver.admin.mvp.model.PresetStorage;
import com.atanor.vwserver.admin.mvp.model.SourceStorage;
import com.atanor.vwserver.admin.ui.MainPane;
import com.atanor.vwserver.common.rpc.services.ConfigServiceAsync;
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

	ConfigServiceAsync getConfigService();

	MainPane getMainPane();

	DisplayStorage getDisplayStorage();

	LayoutStorage getLayoutStorage();

	SourceStorage getSourceStorage();

	PresetStorage getPresetStorage();
}
