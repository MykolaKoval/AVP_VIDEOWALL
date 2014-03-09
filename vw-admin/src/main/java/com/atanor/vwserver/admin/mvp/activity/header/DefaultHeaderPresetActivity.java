package com.atanor.vwserver.admin.mvp.activity.header;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.place.DefaultPresetPlace;
import com.atanor.vwserver.admin.mvp.view.preset.HeaderPresetView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DefaultHeaderPresetActivity extends AbstractActivity {

	private final HeaderPresetView view;

	@Inject
	public DefaultHeaderPresetActivity(final HeaderPresetView view) {
		this.view = view;
	}

	public DefaultHeaderPresetActivity withPlace(DefaultPresetPlace place) {
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.clean();
	}

}