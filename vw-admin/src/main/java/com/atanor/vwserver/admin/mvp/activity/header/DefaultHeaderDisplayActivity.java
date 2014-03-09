package com.atanor.vwserver.admin.mvp.activity.header;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DefaultHeaderDisplayActivity extends AbstractActivity {

	private final HeaderView view;

	public DefaultHeaderDisplayActivity() {
		view = Client.getHeaderPresetView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.clean();
	}

}
