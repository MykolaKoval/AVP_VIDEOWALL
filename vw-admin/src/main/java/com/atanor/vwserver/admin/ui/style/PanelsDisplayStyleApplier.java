package com.atanor.vwserver.admin.ui.style;

import com.smartgwt.client.widgets.Label;

public class PanelsDisplayStyleApplier {

	public void applyStyle(Label canvas) {
		canvas.setBorder("1px inset black");
		canvas.setBackgroundColor("darkgrey");
		canvas.setCanDragResize(false);
		canvas.setCanDragReposition(false);
	}

}
