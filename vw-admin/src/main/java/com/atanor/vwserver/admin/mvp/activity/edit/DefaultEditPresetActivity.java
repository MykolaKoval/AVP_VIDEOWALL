package com.atanor.vwserver.admin.mvp.activity.edit;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.view.EditView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DefaultEditPresetActivity extends AbstractActivity {

	private final EditView view;
	
	public DefaultEditPresetActivity() {
		view = Client.getEditPresetView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.clean();
	}

}
