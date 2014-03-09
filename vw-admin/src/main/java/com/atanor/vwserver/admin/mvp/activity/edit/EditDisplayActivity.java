package com.atanor.vwserver.admin.mvp.activity.edit;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.mvp.view.display.EditDisplayView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class EditDisplayActivity extends AbstractActivity {

	private Long displayId;
	private final EditDisplayView view;

	@Inject
	public EditDisplayActivity(final EditDisplayView view) {
		this.view = view;
	}

	public EditDisplayActivity withPlace(DisplayPlace place) {
		this.displayId = place.getDisplayId();
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

}
