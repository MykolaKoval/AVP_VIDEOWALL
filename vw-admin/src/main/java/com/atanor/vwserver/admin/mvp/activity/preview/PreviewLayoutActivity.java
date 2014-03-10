package com.atanor.vwserver.admin.mvp.activity.preview;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewLayoutView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PreviewLayoutActivity extends AbstractActivity {

	private Long layoutId;
	private final PreviewLayoutView view;

	@Inject
	public PreviewLayoutActivity(final PreviewLayoutView view) {
		this.view = view;
	}

	public PreviewLayoutActivity withPlace(final LayoutPlace place) {
		this.layoutId = place.getLayoutId();
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

}
