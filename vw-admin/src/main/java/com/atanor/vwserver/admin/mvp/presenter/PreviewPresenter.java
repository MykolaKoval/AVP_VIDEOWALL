package com.atanor.vwserver.admin.mvp.presenter;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.event.SetModelEvent;
import com.atanor.vwserver.admin.mvp.event.SetModelHandler;
import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewDisplayView;
import com.google.web.bindery.event.shared.EventBus;

public class PreviewPresenter implements SetModelHandler {

	@Inject
	private DisplayStorage displayStorage;

	@Inject
	public PreviewDisplayView displayView;

	@Inject
	public PreviewPresenter(final EventBus eventBus) {
		eventBus.addHandler(SetModelEvent.getType(), this);
	}

	@Override
	public void onSetModel(SetModelEvent event) {

		switch (event.getModelType()) {
		case All:
			displayView.setDisplays(displayStorage.getDisplays());
			break;
		case Displays:
			displayView.setDisplays(displayStorage.getDisplays());
			break;
		default:
			break;
		}

	}

}
