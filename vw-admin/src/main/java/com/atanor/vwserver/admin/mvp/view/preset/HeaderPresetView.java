package com.atanor.vwserver.admin.mvp.view.preset;

import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.Utils;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

public class HeaderPresetView extends HLayout implements HeaderView {

	public HeaderPresetView(){
		setHeight(Utils.HEADER_DISPLAY_HEIGHT);
		setWidth100();
		setBackgroundColor("yellow");
		
		final Label label = new Label("Presets");
		addMembers(label, new LayoutSpacer());
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}
}
