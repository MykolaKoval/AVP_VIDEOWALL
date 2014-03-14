package com.atanor.vwserver.admin.mvp.view.header;

import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class HeaderLayoutView extends AbstractHeaderView implements HeaderView {

	public HeaderLayoutView() {
		super("Layouts");

		final ToolStripButton newLayout = createButton("New");
		addButton(newLayout);

		final ToolStripButton editLayout = createButton("Edit");
		addButton(editLayout);

		final ToolStripButton cancelLayout = createButton("Cancel");
		addButton(cancelLayout);

		final ToolStripButton saveLayout = createButton("Save");
		addButton(saveLayout);

		addSeparator();
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}
}
