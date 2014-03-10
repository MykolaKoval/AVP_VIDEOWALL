package com.atanor.vwserver.admin.mvp.activity.preview;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.place.DefaultPresetPlace;
import com.atanor.vwserver.admin.mvp.view.PreviewView;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewPresetView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DefaultPreviewPresetActivity extends AbstractActivity {

	private final PreviewView view;

	@Inject
	public DefaultPreviewPresetActivity(final PreviewPresetView view) {
		this.view = view;
	}

	public DefaultPreviewPresetActivity withPlace(DefaultPresetPlace place) {
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.clean();
	}

}
