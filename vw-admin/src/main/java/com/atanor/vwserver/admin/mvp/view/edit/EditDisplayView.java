package com.atanor.vwserver.admin.mvp.view.edit;

import com.atanor.vwserver.admin.ui.Utils;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.google.common.base.Preconditions;
import com.smartgwt.client.widgets.Canvas;

public class EditDisplayView extends AbstractDisplayView {

	private Canvas displayImg;

	public EditDisplayView() {
		setHeight(Utils.getMainAreaHeight());
		setWidth(Utils.getEditAreaWidth());
	}

	public void setDisplay(final DisplayDto displayDto) {
		Preconditions.checkNotNull(displayDto, "Display dto can not be null");

		clean();
		displayImg = createAndAdjustDisplay(displayDto);
		addChild(displayImg);
	}

	public void clean() {
		if (displayImg != null) {
			removeChild(displayImg);
			displayImg = null;
		}
	}

}
