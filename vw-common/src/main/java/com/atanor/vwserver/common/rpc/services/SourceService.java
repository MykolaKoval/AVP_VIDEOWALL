package com.atanor.vwserver.common.rpc.services;

import java.util.List;

import com.atanor.vwserver.common.AppConstants;
import com.atanor.vwserver.common.rpc.dto.SourceDto;
import com.atanor.vwserver.common.rpc.exception.DuplicateEntityException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the Display service.
 */
@RemoteServiceRelativePath(AppConstants.SOURCE_PATH)
public interface SourceService extends RemoteService {

	List<SourceDto> getSources();

	SourceDto createSource(SourceDto source) throws DuplicateEntityException;

	void removeSources(List<Long> ids);
}
