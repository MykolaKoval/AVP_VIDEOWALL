package com.atanor.vwserver.admin.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.event.display.DisplayChangedEvent;
import com.atanor.vwserver.admin.mvp.event.display.DisplayChangedHandler;
import com.atanor.vwserver.admin.mvp.event.layout.LayoutChangedEvent;
import com.atanor.vwserver.admin.mvp.event.layout.LayoutChangedHandler;
import com.atanor.vwserver.admin.mvp.event.layout.LayoutWindowChangedEvent;
import com.atanor.vwserver.admin.mvp.event.layout.LayoutWindowChangedHandler;
import com.atanor.vwserver.admin.mvp.event.preset.PresetChangedEvent;
import com.atanor.vwserver.admin.mvp.event.preset.PresetChangedHandler;
import com.atanor.vwserver.admin.mvp.event.source.SourceChangedEvent;
import com.atanor.vwserver.admin.mvp.event.source.SourceChangedHandler;
import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.model.LayoutStorage;
import com.atanor.vwserver.admin.mvp.model.SourceStorage;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.admin.mvp.view.edit.EditLayoutView;
import com.atanor.vwserver.admin.mvp.view.edit.EditPresetView;
import com.atanor.vwserver.admin.mvp.view.edit.EditSourceView;
import com.atanor.vwserver.admin.mvp.view.header.HeaderPresetView;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewDisplayView;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewLayoutView;
import com.atanor.vwserver.admin.ui.modal.ModalCallbacks.SaveLayoutCallback;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.atanor.vwserver.common.rpc.dto.SourceDto;
import com.atanor.vwserver.common.rpc.services.DisplayServiceAsync;
import com.atanor.vwserver.common.rpc.services.LayoutServiceAsync;
import com.atanor.vwserver.common.rpc.services.SourceServiceAsync;
import com.google.common.collect.Lists;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.util.SC;

public class EditPresenter implements PresetChangedHandler, LayoutChangedHandler, LayoutWindowChangedHandler,
		DisplayChangedHandler, SourceChangedHandler {

	@Inject
	private LayoutStorage layoutStorage;
	@Inject
	private DisplayStorage displayStorage;
	@Inject
	private SourceStorage sourceStorage;

	@Inject
	public HeaderPresetView headerPresetView;
	@Inject
	public EditPresetView editPresetView;
	@Inject
	public EditLayoutView editLayoutView;
	@Inject
	public PreviewLayoutView previewLayoutView;
	@Inject
	public PreviewDisplayView previewDisplayView;
	@Inject
	public EditSourceView editSourceView;

	@Inject
	private LayoutServiceAsync layoutService;
	@Inject
	private DisplayServiceAsync displayService;
	@Inject
	private SourceServiceAsync sourceService;

	@Inject
	public EditPresenter(final EventBus eventBus) {
		eventBus.addHandler(PresetChangedEvent.getType(), this);
		eventBus.addHandler(LayoutChangedEvent.getType(), this);
		eventBus.addHandler(LayoutWindowChangedEvent.getType(), this);
		eventBus.addHandler(DisplayChangedEvent.getType(), this);
		eventBus.addHandler(SourceChangedEvent.getType(), this);
	}

	@Override
	public void onPresetChanged(final PresetChangedEvent event) {
		switch (event.getAction()) {
		case CREATE:
			final LayoutDto layout = layoutStorage.get(event.getLayoutId());
			final DisplayDto display = displayStorage.get(event.getDisplayId()); 
			headerPresetView.onNewPreset();
			editPresetView.onNewPreset(layout, display);
			break;
		default:
			break;
		}
	}

	@Override
	public void onLayoutChanged(final LayoutChangedEvent event) {
		switch (event.getAction()) {
		case CREATE:
			editLayoutView.onNewLayout();
			break;
		case REMOVE:
			final LayoutDto dto = previewLayoutView.getSelectedLayout();
			removeAndRefreshLayouts(dto);
			break;
		default:
			break;
		}
	}

	@Override
	public void onLayoutWindowChanged(final LayoutWindowChangedEvent event) {
		switch (event.getAction()) {
		case CREATE:
			editLayoutView.addNewWindow(event.getDto());
			break;
		case REMOVE:
			editLayoutView.removeAnySelectedWindow();
			break;
		default:
			break;
		}
	}

	@Override
	public void onSourceChanged(final DisplayChangedEvent event) {
		switch (event.getAction()) {
		case REMOVE:
			final DisplayDto dto = previewDisplayView.getSelectedDisplay();
			removeAndRefreshDisplays(dto);
			break;
		default:
			break;
		}
	}

	@Override
	public void onSourceChanged(final SourceChangedEvent event) {
		switch (event.getAction()) {
		case UPDATE:
			editSourceView.setSources(sourceStorage.getAll());
			break;
		case REMOVE:
			removeSourcesAndRefresh(getIds(editSourceView.getSelectedSources()));
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
		layout.setWindows(editLayoutView.getLayoutWindows());

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
				editSourceView.setSources(sourceStorage.getAll());
			}
		});
	}

	private void removeAndRefreshLayouts(final LayoutDto dto) {
		layoutService.removeLayout(dto, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Could not remove layout.");
			}

			@Override
			public void onSuccess(Void result) {
				refreshLayouts();
			}
		});
	}

	private void refreshLayouts() {
		layoutService.getLayouts(new AsyncCallback<List<LayoutDto>>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not receive layouts!");
			}

			@Override
			public void onSuccess(List<LayoutDto> result) {
				layoutStorage.replace(result);
				Client.goTo(new LayoutPlace(Action.UPDATE));
			}
		});
	}

	private void removeAndRefreshDisplays(final DisplayDto display) {
		displayService.removeDisplay(display, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Display can not be removed!");
			}

			@Override
			public void onSuccess(Void result) {
				refreshDisplays();
			}
		});
	}

	private void refreshDisplays() {
		displayService.getDisplays(new AsyncCallback<List<DisplayDto>>() {

			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error. Can not receive displays.");
			}

			@Override
			public void onSuccess(List<DisplayDto> displays) {
				displayStorage.replace(displays);
				Client.goTo(new DisplayPlace(Action.UPDATE));
			}
		});
	}

}
