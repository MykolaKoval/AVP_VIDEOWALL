package com.atanor.vwserver.admin.mvp.presenter;

import java.util.List;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.event.LayoutWindowChangedEvent;
import com.atanor.vwserver.admin.mvp.event.LayoutWindowChangedHandler;
import com.atanor.vwserver.admin.mvp.event.SetModelEvent;
import com.atanor.vwserver.admin.mvp.event.SourceChangedEvent;
import com.atanor.vwserver.admin.mvp.event.SourceChangedHandler;
import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.model.ModelType;
import com.atanor.vwserver.admin.mvp.view.header.HeaderLayoutView;
import com.atanor.vwserver.admin.mvp.view.header.HeaderSourceView;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.services.DisplayServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.util.SC;

public class HeaderPresenter implements LayoutWindowChangedHandler, SourceChangedHandler {

	@Inject
	private DisplayStorage displayStorage;

	@Inject
	private DisplayServiceAsync displayService;

	@Inject
	private HeaderLayoutView layoutView;
	@Inject
	private HeaderSourceView sourceView;
	
	private EventBus eventBus;

	@Inject
	public HeaderPresenter(final EventBus eventBus) {
		this.eventBus = eventBus;
		eventBus.addHandler(LayoutWindowChangedEvent.getType(), this);
		eventBus.addHandler(SourceChangedEvent.getType(), this);
	}

	@Override
	public void onLayoutWindowChanged(final LayoutWindowChangedEvent event) {
		switch (event.getAction()) {
		case CREATED:
			layoutView.onWindowCreated(event.getDto());
			break;
		case REMOVED:
			layoutView.onWindowRemoved(false);
			break;
		case REMOVED_LAST:
			layoutView.onWindowRemoved(true);
			break;
		default:
			break;
		}
	}

	@Override
	public void onSourceChanged(final SourceChangedEvent event) {
		switch (event.getAction()) {
		case SELECTED:
			sourceView.enableRemove();
			break;
		case UNSELECTED:
			sourceView.disableRemove();
			break;
		default:
			break;
		}		
	}
	
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
