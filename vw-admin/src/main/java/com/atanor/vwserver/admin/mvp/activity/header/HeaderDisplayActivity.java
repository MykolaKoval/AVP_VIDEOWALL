package com.atanor.vwserver.admin.mvp.activity.header;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.activity.ActionActivity;
import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.mvp.view.header.HeaderDisplayView;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class HeaderDisplayActivity extends ActionActivity {

	@Inject
	private DisplayStorage storage;

	@Inject
	private HeaderDisplayView view;

	private Long displayId;
	private Action action;

	public HeaderDisplayActivity withPlace(final DisplayPlace place) {
		this.displayId = place.getDisplayId();
		this.action = place.getAction();
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);

		execute(action);

		if (displayId == null) {
			return;
		}

		final DisplayDto display = storage.get(displayId);
		view.setDisplay(display);
	}

	@Override
	protected void doClean() {
		view.clean();
	}
}
