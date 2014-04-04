package com.atanor.vwserver.admin.mvp.view.preview;

import com.atanor.vwserver.admin.ui.Utils;
import com.smartgwt.client.widgets.layout.HLayout;

public class PreviewSourceView extends HLayout {

	public PreviewSourceView() {
		setWidth(Utils.PREVIEW_DISPLAY_WIDTH);
		setHeight(Utils.getMainAreaHeight());
		setBackgroundColor("lightgrey");
	}

}
