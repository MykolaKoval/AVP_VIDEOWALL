package com.atanor.vwserver.admin.ui.modal.presenter;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.model.LayoutStorage;
import com.atanor.vwserver.admin.ui.modal.ModalCallbacks.NewPresetCallback;

public class NewPresetPresenter {

	@Inject
	private LayoutStorage layoutStorage;
	@Inject
	private DisplayStorage displayStorage;

	public void prepopulate(final NewPresetCallback callback) {
		callback.initDisplays(displayStorage.getAll());
		callback.initLayouts(layoutStorage.getAll());
	}
}
