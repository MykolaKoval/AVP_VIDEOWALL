package com.atanor.vwserver.servlet;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.atanor.vwserver.common.rpc.exception.DuplicateEntityException;
import com.atanor.vwserver.common.rpc.services.LayoutService;
import com.atanor.vwserver.domain.converter.LayoutConverter;
import com.atanor.vwserver.services.ILayoutService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class LayoutServlet extends RemoteServiceServlet implements LayoutService {

	private static final Logger LOG = LoggerFactory.getLogger(LayoutServlet.class);

	@Inject
	private ILayoutService service;

	@Inject
	private LayoutConverter converter;

	@Override
	public List<LayoutDto> getLayouts() {
		return converter.toListDto(service.getLayouts());
	}

	@Override
	public LayoutDto createLayout(final LayoutDto dto) throws DuplicateEntityException {
		LOG.debug("Create {} layout called..", dto.getName());
		final Long id = service.createLayout(converter.toEntity(dto));
		return converter.toDto(service.getLayout(id));
	}

	@Override
	public void removeLayout(final LayoutDto dto) {
		LOG.debug("Remove {} layout called..", dto.getName());
		service.removeLayout(dto.getId());
	}

}
