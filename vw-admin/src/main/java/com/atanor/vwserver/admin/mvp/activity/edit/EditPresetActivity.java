package com.atanor.vwserver.admin.mvp.activity.edit;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.activity.ActionActivity;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.atanor.vwserver.admin.mvp.view.edit.EditPresetView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class EditPresetActivity extends ActionActivity {

	@Inject
	private EditPresetView view;

	private Long presetId;
	private Action action;

	public EditPresetActivity withPlace(final PresetPlace place) {
		this.presetId = place.getPresetId();
		this.action = place.getAction();
		return this;
	}

	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		panel.setWidget(view);

		execute(action);
	}

	@Override
	protected void doClean() {
		view.clean();
	}
}
