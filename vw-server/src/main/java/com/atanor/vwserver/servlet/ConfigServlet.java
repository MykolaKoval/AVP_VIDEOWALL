package com.atanor.vwserver.servlet;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.atanor.vwserver.common.rpc.dto.ConfigDto;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.services.ConfigService;
import com.atanor.vwserver.domain.converter.DisplayConverter;
import com.atanor.vwserver.services.IDisplayService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class ConfigServlet extends RemoteServiceServlet implements ConfigService {

	@Inject
	private IDisplayService displayService;

	@Inject
	private DisplayConverter displayConverter;

	@Override
	public ConfigDto getConfiguration() {
		final ConfigDto config = new ConfigDto();

		final List<DisplayDto> displays = getDisplays();

		config.setDisplays(displays);
		return config;
	}

	private List<DisplayDto> getDisplays() {
		return displayConverter.toListDto(displayService.getDisplays());
	}

}
