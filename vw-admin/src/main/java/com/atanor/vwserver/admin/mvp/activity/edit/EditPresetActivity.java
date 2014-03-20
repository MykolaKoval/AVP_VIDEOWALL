package com.atanor.vwserver.admin.mvp.activity.edit;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.atanor.vwserver.admin.mvp.presenter.EditPresetPresenter;
import com.atanor.vwserver.admin.mvp.view.edit.EditPresetView;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class EditPresetActivity extends AbstractActivity implements EditPresetPresenter {

	private Long presetId;
	private final EditPresetView view;

	@Inject
	public EditPresetActivity(final EditPresetView view) {
		this.view = view;
	}

	public EditPresetActivity withPlace(PresetPlace place) {
		this.presetId = place.getPresetId();
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.setPresenter(this);
		view.setPreset(presetId);
	}

	@Override
	public void savePreset(final PresetDto preset) {

	}

	@Override
	public void applyPreset(PresetDto preset) {

	}

}
