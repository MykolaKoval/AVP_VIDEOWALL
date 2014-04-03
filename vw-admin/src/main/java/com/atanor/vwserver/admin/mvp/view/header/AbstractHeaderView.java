package com.atanor.vwserver.admin.mvp.view.header;

import com.atanor.vwserver.admin.ui.Utils;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public abstract class AbstractHeaderView extends ToolStrip {

	protected final ToolStripButton createButton;
	protected final ToolStripButton editButton;
	protected final ToolStripButton cancelButton;
	protected final ToolStripButton saveButton;
	protected final ToolStripButton removeButton;

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

		createButton = createNewButton();
		editButton = createEditButton();
		cancelButton = createCancelButton();
		saveButton = createSaveButton();
		removeButton = createRemoveButton();

		addButtons();

		addSeparator();
	}

	protected void addButtons() {
		addButton(createButton);
		addButton(saveButton);
		addButton(cancelButton);
		addButton(removeButton);
	}

	protected ToolStripButton createNewButton() {
		final ToolStripButton button = createButton("New", "new.png");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doNew();
			}
		});
		return button;
	}

	protected ToolStripButton createEditButton() {
		final ToolStripButton button = createButton("Edit", "edit.png");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doEdit();
			}
		});
		return button;
	}

	protected ToolStripButton createCancelButton() {
		final ToolStripButton button = createButton("Cancel", "undo.png");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doCancel();
			}
		});
		return button;
	}

	protected ToolStripButton createSaveButton() {
		final ToolStripButton button = createButton("Save", "save.png");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doSave();
			}
		});
		return button;
	}

	protected ToolStripButton createRemoveButton() {
		final ToolStripButton button = createButton("Remove", "remove.png");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				doRemove();
			}
		});
		return button;
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

	protected void doRemove() {
	}

}
