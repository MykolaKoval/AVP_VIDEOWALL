package com.atanor.vwserver.services.hardware;

import com.atanor.vwserver.domain.entity.Preset;

public interface HardwareFacade {

	Boolean sendPresetConfiguration(Preset preset);
}
