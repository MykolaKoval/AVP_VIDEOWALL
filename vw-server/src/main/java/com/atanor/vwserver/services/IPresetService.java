package com.atanor.vwserver.services;

import java.util.List;

import com.atanor.vwserver.domain.entity.Preset;

public interface IPresetService {

	Long createPreset(Preset preset);

	void removePreset(Long id);

	List<Preset> getPresets();

	Preset getPreset(Long id);
}
