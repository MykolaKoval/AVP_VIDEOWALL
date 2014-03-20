package com.atanor.vwserver.common.rpc.services;

import com.atanor.vwserver.common.AppConstants;
import com.atanor.vwserver.common.rpc.dto.ConfigDto;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the Config service.
 */
@RemoteServiceRelativePath(AppConstants.CONFIG_PATH)
public interface ConfigService extends RemoteService {

	ConfigDto getConfiguration();

}
