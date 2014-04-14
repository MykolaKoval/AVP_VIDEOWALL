package com.atanor.vwserver.admin.mvp.view.header;

import javax.inject.Inject;
import javax.inject.Provider;

import com.atanor.vwserver.admin.mvp.event.layout.LayoutWindowChangedEvent;
import com.atanor.vwserver.admin.mvp.event.layout.WindowAction;
import com.atanor.vwserver.admin.ui.modal.NewPresetWindow;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class HeaderPresetView extends AbstractHeaderView {

	@Inject
	private Provider<NewPresetWindow> provider;

	@Inject
	private EventBus eventBus;

	private final ToolStripButton newWindowButton;
	private final ToolStripButton removeWindowButton;

	private final Label selectedPreset;
	private PresetDto preset;

	public HeaderPresetView() {
		super("Presets");

		final HLayout layout = new HLayout();
		layout.setWidth100();
		layout.setAlign(VerticalAlignment.CENTER);

		selectedPreset = new Label("");
		selectedPreset.setWidth(300);
		layout.addMembers(selectedPreset);

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
		this.preset = null;
		selectedPreset.setContents("");
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
		final Window window = provider.get();
		window.show();
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

	protected void doNewWindow() {
		final Window window = provider.get();
		window.show();
	}

	protected void doRemoveWindow() {
		eventBus.fireEvent(new LayoutWindowChangedEvent(WindowAction.REMOVE));
	}
}
