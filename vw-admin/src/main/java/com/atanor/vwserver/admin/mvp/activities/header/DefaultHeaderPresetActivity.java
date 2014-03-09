package com.atanor.vwserver.admin.mvp.activities.header;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.views.HeaderView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DefaultHeaderPresetActivity extends AbstractActivity {

	private final HeaderView view;

	public DefaultHeaderPresetActivity() {
		view = Client.getHeaderPresetView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.clean();
	}

}
