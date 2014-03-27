package com.atanor.vwserver.admin.mvp.view;

import com.atanor.vwserver.common.rpc.dto.HardwareDto;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.google.gwt.user.client.ui.IsWidget;

public interface EditView extends IsWidget {

	void setPreset(Long presetId);

	void setConfiguration(HardwareDto config);

	void setPresetConfiguration(PresetDto preset);

	void clean();

	void onPresetApplied();
}
