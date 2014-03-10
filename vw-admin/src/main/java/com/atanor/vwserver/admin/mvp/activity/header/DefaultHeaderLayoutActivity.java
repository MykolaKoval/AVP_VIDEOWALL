package com.atanor.vwserver.admin.mvp.activity.header;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.view.header.HeaderLayoutView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DefaultHeaderLayoutActivity extends AbstractActivity {

	private final HeaderLayoutView view;

	@Inject
	public DefaultHeaderLayoutActivity(final HeaderLayoutView view) {
		this.view = view;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.clean();
	}

}
