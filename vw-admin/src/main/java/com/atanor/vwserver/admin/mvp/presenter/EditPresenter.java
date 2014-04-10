package com.atanor.vwserver.admin.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.event.LayoutWindowChangedEvent;
import com.atanor.vwserver.admin.mvp.event.LayoutWindowChangedHandler;
import com.atanor.vwserver.admin.mvp.event.SetModelEvent;
import com.atanor.vwserver.admin.mvp.event.SourceChangedEvent;
import com.atanor.vwserver.admin.mvp.event.SourceChangedHandler;
import com.atanor.vwserver.admin.mvp.model.LayoutStorage;
import com.atanor.vwserver.admin.mvp.model.ModelType;
import com.atanor.vwserver.admin.mvp.model.SourceStorage;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.admin.mvp.view.edit.EditLayoutView;
import com.atanor.vwserver.admin.mvp.view.edit.EditSourceView;
import com.atanor.vwserver.admin.ui.modal.ModalCallbacks.SaveLayoutCallback;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.atanor.vwserver.common.rpc.dto.SourceDto;
import com.atanor.vwserver.common.rpc.services.LayoutServiceAsync;
import com.atanor.vwserver.common.rpc.services.SourceServiceAsync;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.util.SC;

public class EditPresenter implements LayoutWindowChangedHandler, SourceChangedHandler {

	@Inject
	private LayoutStorage layoutStorage;
	@Inject
	private SourceStorage sourceStorage;

	@Inject
	public EditLayoutView layoutView;
	@Inject
	public EditSourceView sourceView;

	@Inject
	private LayoutServiceAsync layoutService;
	@Inject
	private SourceServiceAsync sourceService;

	private EventBus eventBus;

	@Inject
	public EditPresenter(final EventBus eventBus) {
		this.eventBus = eventBus;
		eventBus.addHandler(LayoutWindowChangedEvent.getType(), this);
		eventBus.addHandler(SourceChangedEvent.getType(), this);
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

	@Override
	public void onSourceChanged(final SourceChangedEvent event) {
		switch (event.getAction()) {
		case UPDATE:
			sourceView.setSources(sourceStorage.getAll());
			break;
		case REMOVE:
			removeSourcesAndRefresh(getIds(sourceView.getSelectedSources()));
			break;
		default:
			break;
		}
	}

	private static List<Long> getIds(final List<SourceDto> sources) {
		final List<Long> result = Lists.newArrayList();
		for (final SourceDto source : sources) {
			result.add(source.getId());
		}
		return result;
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

	private void removeSourcesAndRefresh(final List<Long> ids) {
		sourceService.removeSources(ids, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not remove sources!");
			}

			@Override
			public void onSuccess(Void result) {
				refreshSources();
			}
		});
	}

	private void refreshSources() {
		sourceService.getSources(new AsyncCallback<List<SourceDto>>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not receive sources.");
			}

			@Override
			public void onSuccess(List<SourceDto> result) {
				sourceStorage.replace(result);
				sourceView.setSources(sourceStorage.getAll());
			}
		});
	}
}
