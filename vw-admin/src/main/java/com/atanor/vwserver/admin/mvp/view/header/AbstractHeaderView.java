package com.atanor.vwserver.admin.mvp.view.header;

import com.atanor.vwserver.admin.ui.Utils;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;

public abstract class AbstractHeaderView extends HLayout {

	public AbstractHeaderView(final String labelName) {
		setHeight(Utils.HEADER_DISPLAY_HEIGHT);
		setWidth100();

		final Label label = new Label(labelName);
		label.setStyleName("controlLabel");

		final HLayout container = new HLayout();
		container.setHeight(Utils.HEADER_DISPLAY_HEIGHT);
		container.setWidth(Utils.NAVIGATION_PREVIEW_FULL_WIDTH);
		container.setPadding(10);

		container.addMember(label);
		addMembers(container);
	}

	protected HLayout createLayout() {
		final HLayout layout = new HLayout();
		layout.setWidth100();
		layout.setHeight100();
		layout.setDefaultLayoutAlign(Alignment.CENTER);
		layout.setMembersMargin(5);
		return layout;
	}

}
