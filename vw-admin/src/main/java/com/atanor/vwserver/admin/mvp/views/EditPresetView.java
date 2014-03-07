package com.atanor.vwserver.admin.mvp.views;

import com.atanor.vwserver.common.rpc.dto.HardwareDto;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.atanor.vwserver.admin.mvp.presenters.EditPresetPresenter;
import com.google.gwt.user.client.ui.IsWidget;

public interface EditPresetView extends IsWidget {

	void setPresenter(EditPresetPresenter presenter);

	void setPreset(Long presetId);

	void setConfiguration(HardwareDto config);
	
	void setPresetConfiguration(PresetDto preset);
	
	void cleanState();
	
	void onPresetApplied();
}
