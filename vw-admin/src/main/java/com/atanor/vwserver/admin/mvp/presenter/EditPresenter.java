package com.atanor.vwserver.admin.mvp.presenter;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.event.LayoutWindowChangedEvent;
import com.atanor.vwserver.admin.mvp.event.LayoutWindowChangedHandler;
import com.atanor.vwserver.admin.mvp.view.edit.EditLayoutView;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.google.web.bindery.event.shared.EventBus;

public class EditPresenter implements LayoutWindowChangedHandler {

	@Inject
	public EditLayoutView layoutView;

	@Inject
	public EditPresenter(final EventBus eventBus) {
		eventBus.addHandler(LayoutWindowChangedEvent.getType(), this);
	}

	public void saveLayout(final LayoutDto layout) {

	}

	@Override
	public void onLayoutWindowChanged(final LayoutWindowChangedEvent event) {
		switch (event.getAction()) {
		case CREATE:
			layoutView.addNewWindow(event.getDto());
			break;
		case REMOVE:
			layoutView.removeAnySelectedWindow();
			break;
		default:
			break;
		}
	}

}
