package com.atanor.vwserver.admin.mvp.activity.edit;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.activity.ActionActivity;
import com.atanor.vwserver.admin.mvp.place.SourcePlace;
import com.atanor.vwserver.admin.mvp.view.edit.EditSourceView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class EditSourceActivity extends ActionActivity {

	@Inject
	private EditSourceView view;

	public EditSourceActivity withPlace(final SourcePlace place) {
		return this;
	}

	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		panel.setWidget(view);

	}

}
