package com.atanor.vwserver.admin.mvp.view.header;

import com.atanor.vwserver.admin.ui.Utils;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public abstract class AbstractHeaderView extends ToolStrip {

	public AbstractHeaderView(final String labelName) {
		setHeight(Utils.HEADER_DISPLAY_HEIGHT);
		setWidth100();

		final Label label = new Label(labelName);
		label.setStyleName("controlLabel");

		final HLayout container = new HLayout();
		container.setHeight(Utils.HEADER_DISPLAY_HEIGHT);
		container.setWidth(Utils.PREVIEW_DISPLAY_WIDTH);
		container.setPadding(10);

		container.addMember(label);
		addMembers(container);

		final ToolStripButton newButton = createButton("New", "new.png");
		newButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doNew();
			}
		});
		addButton(newButton);

		final ToolStripButton editButton = createButton("Edit", "edit.png");
		editButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doEdit();
			}
		});
		addButton(editButton);

		final ToolStripButton cancelButton = createButton("Cancel", "undo.png");
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doCancel();
			}
		});
		addButton(cancelButton);

		final ToolStripButton saveButton = createButton("Save", "save.png");
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doSave();
			}
		});
		addButton(saveButton);

		addSeparator();
	}

	protected ToolStripButton createButton(final String title) {
		final ToolStripButton button = new ToolStripButton();
		button.setTitle(title);
		return button;
	}

	protected ToolStripButton createButton(final String title, String iconName) {
		final ToolStripButton button = new ToolStripButton();
		button.setTitle(title);
		button.setIcon(iconName);
		button.setIconOrientation("left");
		return button;
	}

	protected void doNew() {
	}

	protected void doEdit() {
	}

	protected void doCancel() {
	}
	
	protected void doSave() {
	}
	
}
