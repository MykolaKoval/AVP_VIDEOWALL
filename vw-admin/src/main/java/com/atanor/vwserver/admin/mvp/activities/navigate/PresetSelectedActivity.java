package com.atanor.vwserver.admin.mvp.activities.navigate;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.views.NavigatePresetView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PresetSelectedActivity extends AbstractActivity {

	private final Long presetId;
	private final NavigatePresetView view;

	public PresetSelectedActivity(final Long presetId) {
		this.presetId = presetId;
		this.view = Client.getNavigatePresetView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.setPreset(presetId);
	}

}
