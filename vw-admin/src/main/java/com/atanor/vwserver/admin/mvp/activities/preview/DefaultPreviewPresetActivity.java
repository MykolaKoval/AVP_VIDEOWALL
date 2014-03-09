package com.atanor.vwserver.admin.mvp.activities.preview;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.views.PreviewView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DefaultPreviewPresetActivity extends AbstractActivity {

	private final PreviewView view;
	
	public DefaultPreviewPresetActivity() {
		view = Client.getNavigatePresetView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.clean();
	}

}
