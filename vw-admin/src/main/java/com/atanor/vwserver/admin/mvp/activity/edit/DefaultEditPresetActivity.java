package com.atanor.vwserver.admin.mvp.activity.edit;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.place.DefaultPresetPlace;
import com.atanor.vwserver.admin.mvp.view.impl.EditPresetView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DefaultEditPresetActivity extends AbstractActivity {

	private final EditPresetView view;

	@Inject
	public DefaultEditPresetActivity(final EditPresetView view) {
		this.view = view;
	}

	public DefaultEditPresetActivity withPlace(DefaultPresetPlace place) {
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.clean();
	}

}
