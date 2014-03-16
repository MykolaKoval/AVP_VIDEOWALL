package com.atanor.vwserver.servlet;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.services.DisplayService;
import com.atanor.vwserver.domain.converter.DisplayConverter;
import com.atanor.vwserver.services.IDisplayService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class DisplayServlet extends RemoteServiceServlet implements DisplayService {

	private static final Logger LOG = LoggerFactory.getLogger(DisplayServlet.class);

	@Inject
	private IDisplayService displayService;

	@Inject
	private DisplayConverter displayConverter;

	@Override
	public List<DisplayDto> getDisplays() {
		return displayConverter.toListDto(displayService.getDisplays());
	}

	@Override
	public DisplayDto createDisplay(final DisplayDto dto) {
		LOG.debug("Create {} display called..", dto.getName());
		final Long id = displayService.createDisplay(displayConverter.toEntity(dto));
		return displayConverter.toDto(displayService.getDisplay(id));
	}

}
