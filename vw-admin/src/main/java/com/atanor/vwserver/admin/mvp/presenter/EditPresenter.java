package com.atanor.vwserver.admin.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.event.LayoutWindowChangedEvent;
import com.atanor.vwserver.admin.mvp.event.LayoutWindowChangedHandler;
import com.atanor.vwserver.admin.mvp.event.SetModelEvent;
import com.atanor.vwserver.admin.mvp.model.LayoutStorage;
import com.atanor.vwserver.admin.mvp.model.ModelType;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.admin.mvp.view.edit.EditLayoutView;
import com.atanor.vwserver.admin.ui.modal.ModalCallbacks.SaveLayoutCallback;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.atanor.vwserver.common.rpc.services.LayoutServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.util.SC;

public class EditPresenter implements LayoutWindowChangedHandler {

	@Inject
	private LayoutStorage layoutStorage;

	@Inject
	public EditLayoutView layoutView;

	@Inject
	private LayoutServiceAsync layoutService;

	private EventBus eventBus;

	@Inject
	public EditPresenter(final EventBus eventBus) {
		this.eventBus = eventBus;
		eventBus.addHandler(LayoutWindowChangedEvent.getType(), this);
	}

	@Override
	public void onLayoutWindowChanged(final LayoutWindowChangedEvent event) {
		switch (event.getAction()) {
		case CREATE:
			layoutView.addNewWindow(event.getDto());
			break;
		case REMOVE:
			layoutView.removeAnySelectedWindow();
			break;
		default:
			break;
		}
	}

	public void saveLayout(final String name, final SaveLayoutCallback callback) {
		final LayoutDto layout = new LayoutDto();
		layout.setName(name);
		layout.setWindows(layoutView.getLayoutWindows());

		layoutService.createLayout(layout, new AsyncCallback<LayoutDto>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not create layout");
			}

			@Override
			public void onSuccess(LayoutDto layout) {
				refreshAndSelectLayout(layout.getId(), callback);
			}
		});
	}

	public void refreshLayouts() {
		layoutService.getLayouts(new AsyncCallback<List<LayoutDto>>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not receive layout configurations!");
			}

			@Override
			public void onSuccess(List<LayoutDto> result) {
				layoutStorage.replace(result);
				eventBus.fireEvent(new SetModelEvent(ModelType.Layouts));
			}
		});
	}

	private void refreshAndSelectLayout(final Long id, final SaveLayoutCallback callback) {
		layoutService.getLayouts(new AsyncCallback<List<LayoutDto>>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not receive layout configurations!");
			}

			@Override
			public void onSuccess(List<LayoutDto> result) {
				layoutStorage.replace(result);
				Client.goTo(new LayoutPlace(id, Action.UPDATE));
				callback.onLayoutSaved();
			}
		});
	}

}
