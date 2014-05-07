package com.atanor.vwserver.admin.mvp.view.edit;

import com.atanor.vwserver.admin.ui.Utils;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.google.common.base.Preconditions;
import com.smartgwt.client.widgets.Canvas;

public class EditPresetView extends AbstractDisplayView {

	private Canvas display;

	public EditPresetView() {
		setHeight(Utils.getMainAreaHeight());
		setWidth(Utils.getEditAreaWidth());
	}

	public void clean() {
		if (display != null) {
			removeChild(display);
			display = null;
		}
	}

	public void onNewPreset(final LayoutDto layoutDto, final DisplayDto displayDto) {
		Preconditions.checkNotNull(displayDto, "Display dto can not be null");
		
		clean();
		display = createAndAdjustDisplay(displayDto);
		addChild(display);
	}

	public void setPreset(final PresetDto preset) {
	}

}
