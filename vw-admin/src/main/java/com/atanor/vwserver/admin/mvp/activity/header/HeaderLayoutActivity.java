package com.atanor.vwserver.admin.mvp.activity.header;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.activity.ActionActivity;
import com.atanor.vwserver.admin.mvp.model.LayoutStorage;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.admin.mvp.view.header.HeaderLayoutView;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class HeaderLayoutActivity extends ActionActivity {

	@Inject
	private LayoutStorage storage;

	@Inject
	private HeaderLayoutView view;

	private Long layoutId;
	private Action action;

	public HeaderLayoutActivity withPlace(final LayoutPlace place) {
		this.layoutId = place.getLayoutId();
		this.action = place.getAction();
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);

		execute(action);

		if (layoutId == null) {
			return;
		}

		final LayoutDto layout = storage.get(layoutId);
		view.setLayout(layout);
	}

	@Override
	protected void doClean() {
		view.clean();
	}
	
	@Override
	protected void doUpdate() {
		view.clean();
	}
}
