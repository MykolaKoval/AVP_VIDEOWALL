package com.atanor.vwserver.admin.mvp.views.impl;

import com.atanor.vwserver.admin.mvp.views.NavigateView;
import com.atanor.vwserver.admin.ui.Utils;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class DefaultNavigateView extends VLayout implements NavigateView {

	public DefaultNavigateView() {
		setWidth(Utils.NAVIGATION_DISPLAY_WIDTH);
		setHeight100();
		setBackgroundColor("grey");
		setMembersMargin(20);
		setDefaultLayoutAlign(Alignment.CENTER);

		final Canvas presetControl = createNavigateControl("presets.png", "Presets");
		final Canvas layoutControl = createNavigateControl("layouts.png", "Layouts");
		final Canvas displayControl = createNavigateControl("displays.png", "Displays");

		addMembers(new LayoutSpacer(), presetControl, layoutControl, displayControl, new LayoutSpacer());
	}

	private Canvas createNavigateControl(final String imgSource, final String tooltip) {
		final Img img = new Img();
		img.setSrc(imgSource);
		img.setCursor(Cursor.HAND);
		img.setTooltip(tooltip);
		img.setWidth(Utils.NAVIGATE_ICON_SIZE);
		img.setHeight(Utils.NAVIGATE_ICON_SIZE);

		return img;
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}
}
