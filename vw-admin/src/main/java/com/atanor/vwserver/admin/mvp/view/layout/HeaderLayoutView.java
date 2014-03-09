package com.atanor.vwserver.admin.mvp.view.layout;

import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.Utils;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

public class HeaderLayoutView extends HLayout implements HeaderView {

	public HeaderLayoutView(){
		setHeight(Utils.HEADER_DISPLAY_HEIGHT);
		setWidth100();
		setBackgroundColor("pink");
		
		final Label label = new Label("Layouts");
		addMembers(label, new LayoutSpacer());
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub
		
	}
}
