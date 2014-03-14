package com.atanor.vwserver.admin.mvp.view.header;

import com.atanor.vwserver.admin.ui.Utils;
import com.smartgwt.client.widgets.Label;
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
	}

	protected ToolStripButton createButton(final String title) {
		final ToolStripButton button = new ToolStripButton();
		button.setTitle(title);
		return button;
	}

}
