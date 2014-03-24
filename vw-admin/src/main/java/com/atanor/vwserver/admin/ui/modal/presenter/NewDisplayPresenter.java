package com.atanor.vwserver.admin.ui.modal.presenter;

import java.util.List;

import javax.inject.Inject;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.ui.modal.ModalCallbacks.NewDisplayCallback;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.exception.DuplicateEntityException;
import com.atanor.vwserver.common.rpc.services.DisplayServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.util.SC;

public class NewDisplayPresenter {

	@Inject
	private EventBus eventBus;

	@Inject
	private DisplayStorage storage;

	@Inject
	private DisplayServiceAsync displayService;

	public void createDisplay(final DisplayDto dto, final NewDisplayCallback callback) {
		displayService.createDisplay(dto, new AsyncCallback<DisplayDto>() {

			@Override
			public void onSuccess(DisplayDto display) {
				refreshAndSelect(display.getId(), callback);
			}

			@Override
			public void onFailure(Throwable caught) {
				if (caught instanceof DuplicateEntityException) {
					SC.warn("Invalid Data", "Display with such parameters is already exist.");
					return;
				}

				SC.say("Error. Can not create display.");
			}
		});
	}

	private void refreshAndSelect(final Long id, final NewDisplayCallback callback) {
		displayService.getDisplays(new AsyncCallback<List<DisplayDto>>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not receive displays info.");
			}

			@Override
			public void onSuccess(List<DisplayDto> displays) {
				storage.replace(displays);
				Client.goTo(new DisplayPlace(id, Action.UPDATE));
				callback.onDisplayCreated();
			}
		});
	}
}
