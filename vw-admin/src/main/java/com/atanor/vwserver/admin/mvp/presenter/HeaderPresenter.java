package com.atanor.vwserver.admin.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.event.SetModelEvent;
import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.model.ModelType;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.services.DisplayServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.util.SC;

public class HeaderPresenter {

	@Inject
	private DisplayStorage displayStorage;

	@Inject
	private DisplayServiceAsync displayService;

	@Inject
	private EventBus eventBus;

	public void removeDisplay(final DisplayDto display) {
		displayService.removeDisplay(display, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Display can not be removed!");
			}

			@Override
			public void onSuccess(Void result) {
				displayStorage.remove(display.getId());
				refreshDisplays();
			}
		});
	}

	private void refreshDisplays() {
		displayService.getDisplays(new AsyncCallback<List<DisplayDto>>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not receive display configurations!");
			}

			@Override
			public void onSuccess(List<DisplayDto> displays) {
				displayStorage.replace(displays);
				eventBus.fireEvent(new SetModelEvent(ModelType.Displays));
			}
		});
	}

}
