package com.atanor.vwserver.admin.ui.modal.presenter;

import javax.inject.Inject;

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
	private DisplayServiceAsync displayService;

	public void createDisplay(final DisplayDto dto, final NewDisplayCallback callback) {
		displayService.createDisplay(dto, new AsyncCallback<DisplayDto>() {

			@Override
			public void onSuccess(DisplayDto display) {
				callback.onDisplayCreated();
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
}
