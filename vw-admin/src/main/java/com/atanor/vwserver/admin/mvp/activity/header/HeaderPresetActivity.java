package com.atanor.vwserver.admin.mvp.activity.header;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.mvp.view.PreviewView;
import com.atanor.vwserver.admin.mvp.view.impl.HeaderPresetView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class HeaderPresetActivity extends AbstractActivity {

	private final Long presetId;
	private final HeaderView view;

	public HeaderPresetActivity(final Long presetId) {
		this.presetId = presetId;
		this.view = new HeaderPresetView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		//view.setPreset(presetId);
	}

}
