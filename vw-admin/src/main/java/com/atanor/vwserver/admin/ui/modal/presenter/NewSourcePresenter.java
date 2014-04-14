package com.atanor.vwserver.admin.ui.modal.presenter;

import java.util.List;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.event.source.SourceAction;
import com.atanor.vwserver.admin.mvp.event.source.SourceChangedEvent;
import com.atanor.vwserver.admin.mvp.model.SourceStorage;
import com.atanor.vwserver.admin.ui.modal.ModalCallbacks.NewSourceCallback;
import com.atanor.vwserver.common.rpc.dto.SourceDto;
import com.atanor.vwserver.common.rpc.exception.DuplicateEntityException;
import com.atanor.vwserver.common.rpc.services.SourceServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.util.SC;

public class NewSourcePresenter {

	@Inject
	private EventBus eventBus;

	@Inject
	private SourceStorage storage;

	@Inject
	private SourceServiceAsync sourceService;

	public void createSource(final SourceDto dto, final NewSourceCallback callback) {
		sourceService.createSource(dto, new AsyncCallback<SourceDto>() {

			@Override
			public void onSuccess(SourceDto display) {
				refreshAndSelect(display.getId(), callback);
			}

			@Override
			public void onFailure(Throwable caught) {
				if (caught instanceof DuplicateEntityException) {
					SC.warn("Invalid Data", "Source with such parameters is already exist.");
					return;
				}

				SC.say("Error. Can not create source.");
			}
		});
	}

	private void refreshAndSelect(final Long id, final NewSourceCallback callback) {
		sourceService.getSources(new AsyncCallback<List<SourceDto>>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not receive sources info.");
			}

			@Override
			public void onSuccess(List<SourceDto> sources) {
				storage.replace(sources);
				eventBus.fireEvent(new SourceChangedEvent(SourceAction.UPDATE));
				callback.onSourceCreated();
			}
		});
	}
}
