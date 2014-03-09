package com.atanor.vwserver.admin.mvp.view.layout;

import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.Utils;
import com.smartgwt.client.widgets.layout.HLayout;

public class PreviewLayoutView extends HLayout implements HeaderView {

	public PreviewLayoutView() {
		setWidth(Utils.PREVIEW_DISPLAY_WIDTH);
		setHeight100();
		setBackgroundColor("orange");
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}
}
