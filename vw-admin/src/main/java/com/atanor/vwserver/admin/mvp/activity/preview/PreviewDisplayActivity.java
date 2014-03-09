package com.atanor.vwserver.admin.mvp.activity.preview;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.mvp.view.display.PreviewDisplayView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PreviewDisplayActivity extends AbstractActivity {

	private Long displayId;
	private final PreviewDisplayView view;

	@Inject
	public PreviewDisplayActivity(final PreviewDisplayView view) {
		this.view = view;
	}

	public PreviewDisplayActivity withPlace(final DisplayPlace place) {
		this.displayId = place.getDisplayId();
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

}
