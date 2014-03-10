package com.atanor.vwserver.admin.mvp.activity.edit;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.admin.mvp.view.edit.EditLayoutView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class EditLayoutActivity extends AbstractActivity {

	private Long layoutId;
	private final EditLayoutView view;

	@Inject
	public EditLayoutActivity(final EditLayoutView view) {
		this.view = view;
	}

	public EditLayoutActivity withPlace(LayoutPlace place) {
		this.layoutId = place.getLayoutId();
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

}
