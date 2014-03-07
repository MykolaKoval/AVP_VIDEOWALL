package com.atanor.vwserver.services;

import com.atanor.vwserver.domain.entity.Hardware;
import com.atanor.vwserver.domain.entity.Preset;

public interface EquipmentConfigService {

	Hardware getActiveHardware();

	Boolean savePreset(Preset preset);
	
	Preset getPresetById(Long presetId);
}
