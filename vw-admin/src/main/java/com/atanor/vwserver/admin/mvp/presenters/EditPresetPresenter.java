package com.atanor.vwserver.admin.mvp.presenters;

import com.atanor.vwserver.common.rpc.dto.PresetDto;

public interface EditPresetPresenter {
	
	void savePreset(PresetDto preset);
	void applyPreset(PresetDto preset);
}
