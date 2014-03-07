package com.atanor.vwserver.admin;

import com.atanor.vwserver.common.rpc.dto.HardwareDto;
import com.atanor.vwserver.admin.mvp.AppPlacesHistoryMapper;
import com.atanor.vwserver.admin.mvp.places.NoPresetSelectedPlace;
import com.google.common.base.Preconditions;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.util.SC;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class VwAdmin implements EntryPoint {

	private static PlaceHistoryHandler historyHandler;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlacesHistoryMapper historyMapper = GWT.create(AppPlacesHistoryMapper.class);
		historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(Client.getPlaceController(), Client.getEventBus(), new NoPresetSelectedPlace());

		RootPanel.get().add(RootLayoutPanel.get());
		RootLayoutPanel.get().add(Client.getMainPane().asWidget());

		initConfiguration();
	}

	private static void initConfiguration() {
		Client.getConfigService().getHardwareConfiguration(new AsyncCallback<HardwareDto>() {

			@Override
			public void onSuccess(HardwareDto config) {
				Preconditions.checkNotNull(config, "Hardware configuration is null");

				Client.getNavigateDisplay().setWidget(Client.getNavigatePresetView());
				Client.getEditDisplay().setWidget(Client.getEditPresetView());

				// prepare views
				Client.getNavigatePresetView().setConfiguration(config);
				Client.getEditPresetView().setConfiguration(config);

				// Goes to the place represented on URL else default place
				historyHandler.handleCurrentHistory();
			}

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Configuration is not available!");
				caught.printStackTrace();
			}
		});
	}
}
