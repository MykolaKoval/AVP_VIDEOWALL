package com.atanor.vwserver.admin.mvp.view.display;

import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.Utils;
import com.smartgwt.client.widgets.layout.HLayout;

public class PreviewDisplayView extends HLayout implements HeaderView {

	public PreviewDisplayView() {
		setWidth(Utils.PREVIEW_DISPLAY_WIDTH);
		setHeight100();
		setBackgroundColor("blue");
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}
}
