package com.atanor.vwserver.common.rpc.services;

import java.util.List;

import com.atanor.vwserver.common.AppConstants;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.atanor.vwserver.common.rpc.exception.DuplicateEntityException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the Display service.
 */
@RemoteServiceRelativePath(AppConstants.PRESET_PATH)
public interface PresetService extends RemoteService {

	List<PresetDto> getPresets();

	PresetDto createPreset(PresetDto preset) throws DuplicateEntityException;

	void removePreset(PresetDto id);
}
