package com.atanor.vwserver.admin;

import com.atanor.vwserver.common.rpc.dto.HardwareDto;
import com.google.common.base.Preconditions;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.util.SC;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class VwAdmin implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		RootPanel.get().add(RootLayoutPanel.get());
		RootLayoutPanel.get().add(Client.getMainPane().asWidget());

		//initConfiguration();
		Client.getHistoryHandler().handleCurrentHistory();
	}

	private static void initConfiguration() {
		Client.getConfigService().getHardwareConfiguration(new AsyncCallback<HardwareDto>() {

			@Override
			public void onSuccess(HardwareDto config) {
				Preconditions.checkNotNull(config, "Hardware configuration is null");

//				Client.getPreviewDisplay().setWidget(Client.getNavigatePresetView());
//				Client.getEditDisplay().setWidget(Client.getEditPresetView());
//
//				// prepare views
//				Client.getNavigatePresetView().setConfiguration(config);
//				Client.getEditPresetView().setConfiguration(config);

				// Goes to the place represented on URL else default place
				Client.getHistoryHandler().handleCurrentHistory();
			}

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Configuration is not available!");
				caught.printStackTrace();
			}
		});
	}
	
}
