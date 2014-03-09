package com.atanor.vwserver.admin.mvp.activity.edit;

import javax.inject.Inject;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.atanor.vwserver.admin.mvp.presenter.EditPresetPresenter;
import com.atanor.vwserver.admin.mvp.view.preset.EditPresetView;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.smartgwt.client.util.SC;

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
		Client.getConfigService().savePreset(preset, new AsyncCallback<PresetDto>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not save preset configuration");
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(PresetDto preset) {
				view.setPresetConfiguration(preset);
				//Client.getNavigatePresetView().setPresetConfiguration(preset);
				Client.goTo(new PresetPlace(preset.getId()));
			}
		});

	}

	@Override
	public void applyPreset(PresetDto preset) {
		Client.getConfigService().applyPreset(preset, new AsyncCallback<Boolean>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not apply preset configuration");
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(Boolean result) {
				view.onPresetApplied();
			}
		});
	}

}
