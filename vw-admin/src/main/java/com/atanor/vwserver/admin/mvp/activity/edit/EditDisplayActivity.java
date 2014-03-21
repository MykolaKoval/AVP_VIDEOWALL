package com.atanor.vwserver.admin.mvp.activity.edit;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.mvp.view.edit.EditDisplayView;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class EditDisplayActivity extends AbstractActivity {

	@Inject
	private DisplayStorage storage;

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

		if (displayId == null) {
			return;
		}

		final DisplayDto display = storage.getDisplay(displayId);
		view.setDisplay(display);
	}

}
