package com.atanor.vwserver.admin.injector;

import com.atanor.vwserver.admin.mvp.AppPlaceController;
import com.atanor.vwserver.admin.mvp.views.EditView;
import com.atanor.vwserver.admin.mvp.views.HeaderView;
import com.atanor.vwserver.admin.mvp.views.NavigateView;
import com.atanor.vwserver.admin.mvp.views.PreviewView;
import com.atanor.vwserver.admin.mvp.views.impl.DefaultNavigateView;
import com.atanor.vwserver.admin.mvp.views.impl.EditPresetView;
import com.atanor.vwserver.admin.mvp.views.impl.HeaderPresetView;
import com.atanor.vwserver.admin.mvp.views.impl.PreviewPresetView;
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
		bind(HeaderView.class).to(HeaderPresetView.class).in(Singleton.class);
		bind(NavigateView.class).to(DefaultNavigateView.class).in(Singleton.class);
		bind(PreviewView.class).to(PreviewPresetView.class).in(Singleton.class);
		bind(EditView.class).to(EditPresetView.class).in(Singleton.class);
		bind(PlaceController.class).to(AppPlaceController.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
	}

}
