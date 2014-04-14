package com.atanor.vwserver.admin.mvp.activity.header;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.atanor.vwserver.admin.mvp.view.header.HeaderPresetView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class HeaderPresetActivity extends AbstractActivity {

	@Inject
	private final HeaderPresetView view;
	
	private Long presetId;
	
	@Inject
	public HeaderPresetActivity(final HeaderPresetView view) {
		this.view = view;
	}

	public HeaderPresetActivity withPlace(final PresetPlace place) {
		this.presetId = place.getPresetId();
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		// view.setPreset(presetId);
	}

}
