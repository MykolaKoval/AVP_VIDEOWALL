package com.atanor.vwserver.common.rpc.services;

import java.util.List;

import com.atanor.vwserver.common.AppConstants;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.atanor.vwserver.common.rpc.exception.DuplicateEntityException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the Display service.
 */
@RemoteServiceRelativePath(AppConstants.LAYOUT_PATH)
public interface LayoutService extends RemoteService {

	List<LayoutDto> getLayouts();

	LayoutDto createLayout(LayoutDto layout) throws DuplicateEntityException;
	
	void removeLayout(LayoutDto display);
}
