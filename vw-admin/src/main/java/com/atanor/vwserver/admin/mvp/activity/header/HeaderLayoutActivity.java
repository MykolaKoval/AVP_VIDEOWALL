package com.atanor.vwserver.admin.mvp.activity.header;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.atanor.vwserver.admin.mvp.view.header.HeaderLayoutView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class HeaderLayoutActivity extends AbstractActivity {

	private Long layoutId;
	private final HeaderLayoutView view;

	@Inject
	public HeaderLayoutActivity(final HeaderLayoutView view) {
		this.view = view;
	}

	public HeaderLayoutActivity withPlace(final LayoutPlace place) {
		this.layoutId = place.getLayoutId();
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		// view.setPreset(presetId);
	}

}
