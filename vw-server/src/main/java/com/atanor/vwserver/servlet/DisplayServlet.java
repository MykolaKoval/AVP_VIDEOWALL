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
	private IDisplayService service;

	@Inject
	private DisplayConverter converter;

	@Override
	public List<DisplayDto> getDisplays() {
		return converter.toListDto(service.getDisplays());
	}

	@Override
	public DisplayDto createDisplay(final DisplayDto dto) {
		LOG.debug("Create {} display called..", dto.getName());
		final Long id = service.createDisplay(converter.toEntity(dto));
		return converter.toDto(service.getDisplay(id));
	}

	@Override
	public void removeDisplay(final DisplayDto dto) {
		LOG.debug("Remove {} display called..", dto.getName());
		service.removeDisplay(dto.getId());
	}

}
