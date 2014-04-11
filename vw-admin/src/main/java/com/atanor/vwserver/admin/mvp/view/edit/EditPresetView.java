package com.atanor.vwserver.admin.mvp.view.edit;

import com.atanor.vwserver.admin.ui.Utils;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.smartgwt.client.widgets.layout.VLayout;

public class EditPresetView extends VLayout {

	public EditPresetView() {
		setHeight(Utils.getMainAreaHeight());
		setWidth(Utils.getEditAreaWidth());
	}

	public void setPreset(final PresetDto preset){
		
	}
}
