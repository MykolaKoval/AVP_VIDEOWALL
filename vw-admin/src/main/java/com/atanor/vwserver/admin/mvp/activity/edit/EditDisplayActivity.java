package com.atanor.vwserver.admin.mvp.activity.edit;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.activity.ActionActivity;
import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.mvp.view.edit.EditDisplayView;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class EditDisplayActivity extends ActionActivity {

	@Inject
	private DisplayStorage storage;

	@Inject
	private EditDisplayView view;

	private Long displayId;
	private Action action;

	public EditDisplayActivity withPlace(final DisplayPlace place) {
		this.displayId = place.getDisplayId();
		this.action = place.getAction();
		return this;
	}

	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		panel.setWidget(view);

		execute(action);
		final DisplayDto display = storage.getDisplay(displayId);
		if (display != null) {
			view.setDisplay(display);
		}
	}

	@Override
	protected void doClean() {
		view.clean();
	}
}
