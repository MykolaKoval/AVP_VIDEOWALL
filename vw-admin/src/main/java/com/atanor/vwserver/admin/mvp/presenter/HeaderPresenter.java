package com.atanor.vwserver.admin.mvp.presenter;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.event.layout.LayoutWindowChangedEvent;
import com.atanor.vwserver.admin.mvp.event.layout.LayoutWindowChangedHandler;
import com.atanor.vwserver.admin.mvp.event.source.SourceChangedEvent;
import com.atanor.vwserver.admin.mvp.event.source.SourceChangedHandler;
import com.atanor.vwserver.admin.mvp.view.header.HeaderLayoutView;
import com.atanor.vwserver.admin.mvp.view.header.HeaderSourceView;
import com.google.web.bindery.event.shared.EventBus;

public class HeaderPresenter implements LayoutWindowChangedHandler, SourceChangedHandler {

	@Inject
	private HeaderLayoutView layoutView;
	@Inject
	private HeaderSourceView sourceView;

	@Inject
	public HeaderPresenter(final EventBus eventBus) {
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

}
