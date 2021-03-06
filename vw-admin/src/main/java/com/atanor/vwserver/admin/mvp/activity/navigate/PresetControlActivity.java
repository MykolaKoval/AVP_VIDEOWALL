package com.atanor.vwserver.admin.mvp.activity.navigate;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.view.Control;
import com.atanor.vwserver.admin.mvp.view.NavigateView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PresetControlActivity extends AbstractActivity {

	private final NavigateView view;

	@Inject
	public PresetControlActivity(final NavigateView view) {
		this.view = view;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.select(Control.Presets);
	}

}
