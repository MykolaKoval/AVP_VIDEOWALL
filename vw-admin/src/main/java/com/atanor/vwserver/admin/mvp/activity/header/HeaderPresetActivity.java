package com.atanor.vwserver.admin.mvp.activity.header;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.activity.ActionActivity;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.atanor.vwserver.admin.mvp.view.header.HeaderPresetView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class HeaderPresetActivity extends ActionActivity {

	@Inject
	private HeaderPresetView view;

	private Long presetId;
	private Action action;

	public HeaderPresetActivity withPlace(final PresetPlace place) {
		this.presetId = place.getPresetId();
		this.action = place.getAction();
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		
		execute(action);
	}

	@Override
	protected void doClean() {
		view.clean();
	}
}
