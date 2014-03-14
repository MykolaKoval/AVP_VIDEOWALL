package com.atanor.vwserver.admin.mvp.view.header;

import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class HeaderPresetView extends AbstractHeaderView implements HeaderView {

	public HeaderPresetView() {
		super("Presets");

		final ToolStripButton newPreset = createButton("New");
		addButton(newPreset);

		final ToolStripButton editPreset = createButton("Edit");
		addButton(editPreset);

		final ToolStripButton cancelPreset = createButton("Cancel");
		addButton(cancelPreset);

		final ToolStripButton savePreset = createButton("Save");
		addButton(savePreset);

		addSeparator();
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}
}
