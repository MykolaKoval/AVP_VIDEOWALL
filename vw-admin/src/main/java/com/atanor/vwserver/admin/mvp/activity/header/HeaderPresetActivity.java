package com.atanor.vwserver.admin.mvp.activity.header;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.atanor.vwserver.admin.mvp.view.impl.HeaderPresetView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class HeaderPresetActivity extends AbstractActivity {

	private Long presetId;
	private final HeaderPresetView view;

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
