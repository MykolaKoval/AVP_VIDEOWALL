package com.atanor.vwserver.admin.mvp.view.header;

import javax.inject.Inject;
import javax.inject.Provider;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.event.layout.LayoutAction;
import com.atanor.vwserver.admin.mvp.event.layout.LayoutChangedEvent;
import com.atanor.vwserver.admin.mvp.event.layout.LayoutWindowChangedEvent;
import com.atanor.vwserver.admin.mvp.event.layout.WindowAction;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.admin.ui.modal.NewLayoutWindow;
import com.atanor.vwserver.admin.ui.modal.SaveLayoutWindow;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.atanor.vwserver.common.rpc.dto.LayoutWindowDto;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class HeaderLayoutView extends AbstractHeaderView {

	@Inject
	private Provider<NewLayoutWindow> newWinProvider;

	@Inject
	private Provider<SaveLayoutWindow> saveLayoutProvider;

	@Inject
	private EventBus eventBus;

	private final ToolStripButton newWindowButton;
	private final ToolStripButton removeWindowButton;

	private final Label selectedLayout;
	private LayoutDto layout;

	public HeaderLayoutView() {
		super("Layouts");

		final HLayout layout = new HLayout();
		layout.setWidth100();
		layout.setAlign(VerticalAlignment.CENTER);

		selectedLayout = new Label("");
		selectedLayout.setWidth(300);
		layout.addMembers(selectedLayout);

		addMember(layout);

		newWindowButton = createNewWindowButton();
		newWindowButton.setDisabled(true);
		removeWindowButton = createRemoveWindowButton();
		removeWindowButton.setDisabled(true);

		addButton(newWindowButton);
		addButton(removeWindowButton);

		saveButton.setDisabled(true);
		editButton.setDisabled(true);
		cancelButton.setDisabled(true);
		removeButton.setDisabled(true);
	}

	public void clean() {
		this.layout = null;
		selectedLayout.setContents("");
		createButton.enable();
		editButton.disable();
		cancelButton.disable();
		saveButton.disable();
		removeButton.disable();
		newWindowButton.disable();
		removeWindowButton.disable();
	}

	@Override
	protected void doNew() {
		selectedLayout.setContents("Unsaved*");
		cancelButton.enable();
		newWindowButton.enable();
		createButton.disable();
		eventBus.fireEvent(new LayoutChangedEvent(LayoutAction.CREATE));
	}

	@Override
	protected void doCancel() {
		if (hasSelectedLayout()) {
			Client.goTo(new LayoutPlace(Action.CLEAN));
			return;
		}

		SC.ask("Undo", "All changes for unsaved layout will be lost. Are you sure?", new BooleanCallback() {

			@Override
			public void execute(Boolean result) {
				if (result) {
					Client.goTo(new LayoutPlace(Action.CLEAN));
				}
			}
		});
	}

	@Override
	protected void doSave() {
		final Window window = saveLayoutProvider.get();
		window.show();
	}

	@Override
	protected void doRemove() {
		SC.ask("Remove Layout", "Layout will be removed. Are you sure?", new BooleanCallback() {

			@Override
			public void execute(Boolean value) {
				if(value){
					eventBus.fireEvent(new LayoutChangedEvent(LayoutAction.REMOVE));
				}
			}
		});
	}
	
	protected void doNewWindow() {
		final Window window = newWinProvider.get();
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

	public void onWindowCreated(final LayoutWindowDto dto) {
		saveButton.enable();
		removeWindowButton.enable();
	}

	public void onWindowRemoved(boolean isLast) {
		if (isLast) {
			saveButton.disable();
			removeWindowButton.disable();
		}
	}

	public void setLayout(final LayoutDto layout) {
		this.layout = layout;
		createButton.disable();
		cancelButton.enable();
		removeButton.enable();
		selectedLayout.setContents(genContent(layout.getName()));
	}

	private static String genContent(final String name) {
		return "Layout: " + name;
	}

	private boolean hasSelectedLayout() {
		return layout != null;
	}

}
