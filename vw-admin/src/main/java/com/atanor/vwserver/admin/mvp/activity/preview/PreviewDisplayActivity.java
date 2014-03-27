package com.atanor.vwserver.admin.mvp.activity.preview;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.activity.ActionActivity;
import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewDisplayView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PreviewDisplayActivity extends ActionActivity {

	@Inject
	private DisplayStorage storage;

	@Inject
	private PreviewDisplayView view;

	private Long displayId;
	private Action action;

	public PreviewDisplayActivity withPlace(final DisplayPlace place) {
		this.displayId = place.getDisplayId();
		this.action = place.getAction();
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);

		execute(action);
		view.selectDisplay(displayId);
	}

	@Override
	protected void doClean() {
		view.clean();
	}
	
	@Override
	protected void doUpdate() {
		view.setDisplays(storage.getAll());
	}
}
