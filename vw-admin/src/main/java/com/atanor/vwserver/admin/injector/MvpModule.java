package com.atanor.vwserver.admin.injector;

import com.atanor.vwserver.admin.mvp.AppPlaceController;
import com.atanor.vwserver.admin.mvp.AppPlacesHistoryMapper;
import com.atanor.vwserver.admin.mvp.EditMapper;
import com.atanor.vwserver.admin.mvp.HeaderMapper;
import com.atanor.vwserver.admin.mvp.NavigateMapper;
import com.atanor.vwserver.admin.mvp.PreviewMapper;
import com.atanor.vwserver.admin.mvp.activity.edit.EditDisplayActivity;
import com.atanor.vwserver.admin.mvp.activity.edit.EditLayoutActivity;
import com.atanor.vwserver.admin.mvp.activity.edit.EditPresetActivity;
import com.atanor.vwserver.admin.mvp.activity.edit.EditSourceActivity;
import com.atanor.vwserver.admin.mvp.activity.header.HeaderDisplayActivity;
import com.atanor.vwserver.admin.mvp.activity.header.HeaderLayoutActivity;
import com.atanor.vwserver.admin.mvp.activity.header.HeaderPresetActivity;
import com.atanor.vwserver.admin.mvp.activity.navigate.DisplayControlActivity;
import com.atanor.vwserver.admin.mvp.activity.navigate.LayoutControlActivity;
import com.atanor.vwserver.admin.mvp.activity.navigate.PresetControlActivity;
import com.atanor.vwserver.admin.mvp.activity.preview.PreviewDisplayActivity;
import com.atanor.vwserver.admin.mvp.activity.preview.PreviewLayoutActivity;
import com.atanor.vwserver.admin.mvp.activity.preview.PreviewPresetActivity;
import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.model.LayoutStorage;
import com.atanor.vwserver.admin.mvp.model.PresetStorage;
import com.atanor.vwserver.admin.mvp.model.SourceStorage;
import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class MvpModule extends AbstractGinModule {

	@Override
	protected void configure() {

		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

		bind(PlaceHistoryMapper.class).to(AppPlacesHistoryMapper.class).in(Singleton.class);
		bind(PlaceController.class).to(AppPlaceController.class).in(Singleton.class);

		bind(HeaderMapper.class);
		bind(NavigateMapper.class);
		bind(PreviewMapper.class);
		bind(EditMapper.class);

		// Navigate
		bind(DisplayControlActivity.class);
		bind(LayoutControlActivity.class);
		bind(PresetControlActivity.class);

		// Header
		bind(HeaderDisplayActivity.class);
		bind(HeaderLayoutActivity.class);
		bind(HeaderPresetActivity.class);

		// Preview
		bind(PreviewDisplayActivity.class);
		bind(PreviewLayoutActivity.class);
		bind(PreviewPresetActivity.class);

		// Edit
		bind(EditDisplayActivity.class);
		bind(EditLayoutActivity.class);
		bind(EditPresetActivity.class);
		bind(EditSourceActivity.class);

		// Storages
		bind(PresetStorage.class).in(Singleton.class);
		bind(DisplayStorage.class).in(Singleton.class);
		bind(LayoutStorage.class).in(Singleton.class);
		bind(SourceStorage.class).in(Singleton.class);
	}

	/**
	 * Creates a new PlaceHistoryHandler. This object is responsible handling
	 * navigation based on the browser URL. You only need one of those for the
	 * entire app.
	 * 
	 * @param placeController
	 *            the place controller.
	 * @param historyMapper
	 *            This is used to map the URL to a Place object and vice versa.
	 * @param eventBus
	 *            the event bus.
	 * @return
	 */
	@Provides
	@Singleton
	public PlaceHistoryHandler getHistoryHandler(PlaceController placeController, PlaceHistoryMapper historyMapper,
			EventBus eventBus) {
		final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, new PresetPlace());

		return historyHandler;
	}

}
