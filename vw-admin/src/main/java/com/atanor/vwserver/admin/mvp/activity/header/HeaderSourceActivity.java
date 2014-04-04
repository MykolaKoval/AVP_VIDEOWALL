package com.atanor.vwserver.admin.mvp.activity.header;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.place.SourcePlace;
import com.atanor.vwserver.admin.mvp.view.header.HeaderSourceView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class HeaderSourceActivity extends AbstractActivity {

	@Inject
	private HeaderSourceView view;

	public HeaderSourceActivity withPlace(final SourcePlace place) {
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

}
