package com.atanor.vwserver.admin.mvp.activity.preview;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.place.SourcePlace;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewSourceView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PreviewSourceActivity extends AbstractActivity {

	@Inject
	private PreviewSourceView view;

	public PreviewSourceActivity withPlace(final SourcePlace place) {
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);

	}

}
