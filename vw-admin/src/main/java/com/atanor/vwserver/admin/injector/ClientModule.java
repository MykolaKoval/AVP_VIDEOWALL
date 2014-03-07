package com.atanor.vwserver.admin.injector;

import com.atanor.vwserver.admin.mvp.AppPlaceController;
import com.atanor.vwserver.admin.mvp.views.EditPresetView;
import com.atanor.vwserver.admin.mvp.views.NavigatePresetView;
import com.atanor.vwserver.admin.mvp.views.impl.EditPresetViewImpl;
import com.atanor.vwserver.admin.mvp.views.impl.NavigatePresetViewImpl;
import com.atanor.vwserver.admin.ui.MainPane;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ClientModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(MainPane.class).in(Singleton.class);
		bind(NavigatePresetView.class).to(NavigatePresetViewImpl.class).in(Singleton.class);
		bind(EditPresetView.class).to(EditPresetViewImpl.class).in(Singleton.class);
		bind(PlaceController.class).to(AppPlaceController.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
	}

}
