package com.atanor.vwserver.admin.ui.modal.presenter;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.event.preset.PresetAction;
import com.atanor.vwserver.admin.mvp.event.preset.PresetChangedEvent;
import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.model.LayoutStorage;
import com.atanor.vwserver.admin.ui.modal.ModalCallbacks.NewPresetCallback;
import com.google.web.bindery.event.shared.EventBus;

public class NewPresetPresenter {

	@Inject
	private EventBus eventBus;

	@Inject
	private LayoutStorage layoutStorage;
	@Inject
	private DisplayStorage displayStorage;

	public void prepopulate(final NewPresetCallback callback) {
		callback.initDisplays(displayStorage.getAll());
		callback.initLayouts(layoutStorage.getAll());
	}

	public void createTemplate(final Long layoutId, final Long displayId) {
		eventBus.fireEvent(new PresetChangedEvent(PresetAction.CREATE, layoutId, displayId));
	}
}
