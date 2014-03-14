package com.atanor.vwserver.admin.mvp.view.header;

import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class HeaderDisplayView extends AbstractHeaderView implements HeaderView {

	public HeaderDisplayView() {
		super("Displays");

		final ToolStripButton newDisplay = createButton("New");
		addButton(newDisplay);

		final ToolStripButton editDisplay = createButton("Edit");
		addButton(editDisplay);

		final ToolStripButton cancelDisplay = createButton("Cancel");
		addButton(cancelDisplay);

		final ToolStripButton saveDisplay = createButton("Save");
		addButton(saveDisplay);

		addSeparator();
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}
}
