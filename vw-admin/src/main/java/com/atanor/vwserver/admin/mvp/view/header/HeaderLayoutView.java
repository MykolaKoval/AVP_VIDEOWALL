package com.atanor.vwserver.admin.mvp.view.header;

import javax.inject.Inject;
import javax.inject.Provider;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.event.LayoutWindowChangedEvent;
import com.atanor.vwserver.admin.mvp.event.WindowAction;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.modal.NewLayoutWindow;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class HeaderLayoutView extends AbstractHeaderView implements HeaderView {

	@Inject
	private Provider<NewLayoutWindow> provider;

	@Inject
	private EventBus eventBus;

	public HeaderLayoutView() {
		super("Layouts");

		final HLayout layout = new HLayout();
		layout.setWidth100();
		layout.setAlign(VerticalAlignment.CENTER);
		addMember(layout);
		
		final ToolStripButton newWindowButton = createNewWindowButton();
		final ToolStripButton removeWindowButton = createRemoveWindowButton();
		addButton(newWindowButton);
		addButton(removeWindowButton);
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doNew() {

	}

	@Override
	protected void doCancel() {
		Client.goTo(new LayoutPlace(Action.CLEAN));
	}

	@Override
	protected void doSave() {

	}

	protected void doNewWindow() {
		final Window window = provider.get();
		window.show();
	}

	protected void doRemoveWindow() {
		eventBus.fireEvent(new LayoutWindowChangedEvent(WindowAction.REMOVE));
	}

	protected ToolStripButton createNewWindowButton() {
		final ToolStripButton button = createButton("New Window", "new.png");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doNewWindow();
			}
		});
		return button;
	}

	protected ToolStripButton createRemoveWindowButton() {
		final ToolStripButton button = createButton("Remove Window", "remove.png");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doRemoveWindow();
			}
		});
		return button;
	}

}
