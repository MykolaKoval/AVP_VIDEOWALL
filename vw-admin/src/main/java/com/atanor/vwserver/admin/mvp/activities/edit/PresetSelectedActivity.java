package com.atanor.vwserver.admin.mvp.activities.edit;

import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.places.PresetSelectedPlace;
import com.atanor.vwserver.admin.mvp.presenters.EditPresetPresenter;
import com.atanor.vwserver.admin.mvp.views.EditPresetView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.smartgwt.client.util.SC;

public class PresetSelectedActivity extends AbstractActivity implements EditPresetPresenter {

	private final Long presetId;
	private final EditPresetView view;
	
	public PresetSelectedActivity(final Long presetId) {
		this.presetId = presetId;
		this.view = Client.getEditPresetView();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		view.setPresenter(this);
		view.setPreset(presetId);
	}

	@Override
	public void savePreset(final PresetDto preset) {
		Client.getConfigService().savePreset(preset, new AsyncCallback<PresetDto>(){

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not save preset configuration");
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(PresetDto preset) {
				Client.getEditPresetView().setPresetConfiguration(preset);
				Client.getNavigatePresetView().setPresetConfiguration(preset);
				Client.goTo(new PresetSelectedPlace(preset.getId()));
			}});
		
	}

	@Override
	public void applyPreset(PresetDto preset) {
		Client.getConfigService().applyPreset(preset, new AsyncCallback<Boolean>(){

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not apply preset configuration");
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(Boolean result) {
				view.onPresetApplied();
			}});
	}

}
