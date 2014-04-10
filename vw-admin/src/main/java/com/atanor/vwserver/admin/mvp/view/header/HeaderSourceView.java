package com.atanor.vwserver.admin.mvp.view.header;

import javax.inject.Inject;
import javax.inject.Provider;

import com.atanor.vwserver.admin.mvp.event.SourceAction;
import com.atanor.vwserver.admin.mvp.event.SourceChangedEvent;
import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.modal.NewSourceWindow;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.widgets.Window;

public class HeaderSourceView extends AbstractHeaderView implements HeaderView {

	@Inject
	private Provider<NewSourceWindow> provider;

	@Inject
	private EventBus eventBus;

	public HeaderSourceView() {
		super("Sources");
		removeButton.disable();
	}

	@Override
	protected void addButtons() {
		addButton(createButton);
		addButton(removeButton);
	}

	@Override
	public void clean() {
		createButton.enable();
		removeButton.disable();
	}

	@Override
	protected void doNew() {
		final Window window = provider.get();
		window.show();
	}

	@Override
	protected void doRemove() {
		eventBus.fireEvent(new SourceChangedEvent(SourceAction.REMOVE));
	}

	public void enableRemove() {
		removeButton.enable();
	}

	public void disableRemove() {
		removeButton.disable();
	}
}
