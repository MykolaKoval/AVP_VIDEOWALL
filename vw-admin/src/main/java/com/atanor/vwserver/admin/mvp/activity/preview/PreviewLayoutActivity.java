package com.atanor.vwserver.admin.mvp.activity.preview;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.activity.ActionActivity;
import com.atanor.vwserver.admin.mvp.model.LayoutStorage;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewLayoutView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PreviewLayoutActivity extends ActionActivity {

	@Inject
	private LayoutStorage storage;

	@Inject
	private PreviewLayoutView view;

	private Long layoutId;
	private Action action;

	public PreviewLayoutActivity withPlace(final LayoutPlace place) {
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
		view.selectLayout(layoutId);
	}

	@Override
	protected void doClean() {
		view.clean();
	}

	@Override
	protected void doUpdate() {
		view.setLayouts(storage.getAll());
	}

}
