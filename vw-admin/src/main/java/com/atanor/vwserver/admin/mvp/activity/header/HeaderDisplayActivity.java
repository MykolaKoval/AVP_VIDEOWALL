package com.atanor.vwserver.admin.mvp.activity.header;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.mvp.view.display.HeaderDisplayView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class HeaderDisplayActivity extends AbstractActivity {

	private Long displayId;
	private final HeaderDisplayView view;

	@Inject
	public HeaderDisplayActivity(final HeaderDisplayView view) {
		this.view = view;
	}

	public HeaderDisplayActivity withPlace(final DisplayPlace place) {
		this.displayId = place.getDisplayId();
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		// view.setPreset(presetId);
	}

}
