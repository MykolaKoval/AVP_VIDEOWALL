package com.atanor.vwserver.admin.mvp.view.display;

import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.Utils;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

public class HeaderDisplayView extends HLayout implements HeaderView {

	public HeaderDisplayView(){
		setHeight(Utils.HEADER_DISPLAY_HEIGHT);
		setWidth100();
		setBackgroundColor("green");
		
		final Label label = new Label("Displays");
		addMembers(label, new LayoutSpacer());
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}
}
