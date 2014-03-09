package com.atanor.vwserver.admin.mvp.activities.preview;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.views.PreviewView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PreviewPresetActivity extends AbstractActivity {

	private final Long presetId;
	private final PreviewView view;

	public PreviewPresetActivity(final Long presetId) {
		this.presetId = presetId;
		this.view = Client.getNavigatePresetView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.setPreset(presetId);
	}

}
