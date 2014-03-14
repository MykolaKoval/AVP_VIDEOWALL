package com.atanor.vwserver.admin.mvp.view.header;

import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.layout.HLayout;

public class HeaderPresetView extends AbstractHeaderView implements HeaderView {

	public HeaderPresetView() {
		super("Presets");
		setBackgroundColor("yellow");

		final HLayout layout = createLayout();
		final IButton newDisplay = new IButton("New");
		final IButton editDisplay = new IButton("Edit");
		final IButton cancelDisplay = new IButton("Cancel");
		final IButton saveDisplay = new IButton("Save");

		layout.addMembers(newDisplay, editDisplay, cancelDisplay, saveDisplay);
		addMember(layout);
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}
}
