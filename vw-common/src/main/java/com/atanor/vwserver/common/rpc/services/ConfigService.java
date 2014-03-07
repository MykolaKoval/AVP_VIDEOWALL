package com.atanor.vwserver.common.rpc.services;

import com.atanor.vwserver.common.AppConstants;
import com.atanor.vwserver.common.rpc.dto.HardwareDto;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the Config service.
 */
@RemoteServiceRelativePath(AppConstants.CONFIG_PATH)
public interface ConfigService extends RemoteService {

	HardwareDto getHardwareConfiguration();

	Boolean applyPreset(PresetDto preset);

	PresetDto savePreset(PresetDto preset);
}
