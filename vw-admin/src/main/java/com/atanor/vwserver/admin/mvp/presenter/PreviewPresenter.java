package com.atanor.vwserver.admin.mvp.presenter;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.event.SetModelEvent;
import com.atanor.vwserver.admin.mvp.event.SetModelHandler;
import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.model.LayoutStorage;
import com.atanor.vwserver.admin.mvp.model.PresetStorage;
import com.atanor.vwserver.admin.mvp.model.SourceStorage;
import com.atanor.vwserver.admin.mvp.view.edit.EditSourceView;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewDisplayView;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewLayoutView;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewPresetView;
import com.google.web.bindery.event.shared.EventBus;

public class PreviewPresenter implements SetModelHandler {

	@Inject
	private PresetStorage presetStorage;
	@Inject
	private DisplayStorage displayStorage;
	@Inject
	private LayoutStorage layoutStorage;
	@Inject
	private SourceStorage sourceStorage;

	@Inject
	public PreviewPresetView presetView;
	@Inject
	public PreviewDisplayView displayView;
	@Inject
	public PreviewLayoutView layoutView;
	@Inject
	public EditSourceView sourceView;

	@Inject
	public PreviewPresenter(final EventBus eventBus) {
		eventBus.addHandler(SetModelEvent.getType(), this);
	}

	@Override
	public void onSetModel(SetModelEvent event) {

		switch (event.getModelType()) {
		case All:
			presetView.setPresets(presetStorage.getAll());
			displayView.setDisplays(displayStorage.getAll());
			layoutView.setLayouts(layoutStorage.getAll());
			sourceView.setSources(sourceStorage.getAll());
			break;
		case Presets:
			presetView.setPresets(presetStorage.getAll());
		case Displays:
			displayView.setDisplays(displayStorage.getAll());
			break;
		case Layouts:
			layoutView.setLayouts(layoutStorage.getAll());
		case Sources:
			sourceView.setSources(sourceStorage.getAll());
		default:
			break;
		}

	}

}
