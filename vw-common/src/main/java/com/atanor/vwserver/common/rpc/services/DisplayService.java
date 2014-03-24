package com.atanor.vwserver.common.rpc.services;

import java.util.List;

import com.atanor.vwserver.common.AppConstants;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.exception.DuplicateEntityException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the Display service.
 */
@RemoteServiceRelativePath(AppConstants.DISPLAY_PATH)
public interface DisplayService extends RemoteService {

	List<DisplayDto> getDisplays();

	DisplayDto createDisplay(DisplayDto display) throws DuplicateEntityException;
	
	void removeDisplay(DisplayDto display);
}
