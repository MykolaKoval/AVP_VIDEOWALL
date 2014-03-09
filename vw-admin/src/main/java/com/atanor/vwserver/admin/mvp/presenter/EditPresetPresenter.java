package com.atanor.vwserver.admin.mvp.presenter;

import com.atanor.vwserver.common.rpc.dto.PresetDto;

public interface EditPresetPresenter {
	
	void savePreset(PresetDto preset);
	void applyPreset(PresetDto preset);
}
