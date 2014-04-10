package com.atanor.vwserver.admin;

import com.atanor.vwserver.admin.mvp.event.SetModelEvent;
import com.atanor.vwserver.common.rpc.dto.ConfigDto;
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

		initConfiguration();
	}

	private static void initConfiguration() {
		Client.getConfigService().getConfiguration(new AsyncCallback<ConfigDto>() {

			@Override
			public void onSuccess(ConfigDto config) {
				Preconditions.checkNotNull(config, "Application configuration is null");

				Client.getDisplayStorage().addAll(config.getDisplays());
				Client.getLayoutStorage().addAll(config.getLayouts());
				Client.getSourceStorage().addAll(config.getSources());
				Client.getEventBus().fireEvent(new SetModelEvent());

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
