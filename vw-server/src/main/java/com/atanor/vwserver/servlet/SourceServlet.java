package com.atanor.vwserver.servlet;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atanor.vwserver.common.rpc.dto.SourceDto;
import com.atanor.vwserver.common.rpc.exception.DuplicateEntityException;
import com.atanor.vwserver.common.rpc.services.SourceService;
import com.atanor.vwserver.domain.converter.SourceConverter;
import com.atanor.vwserver.services.ISourceService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class SourceServlet extends RemoteServiceServlet implements SourceService {

	private static final Logger LOG = LoggerFactory.getLogger(SourceServlet.class);

	@Inject
	private ISourceService service;

	@Inject
	private SourceConverter converter;

	@Override
	public List<SourceDto> getSources() {
		return converter.toListDto(service.getSources());
	}

	@Override
	public SourceDto createSource(final SourceDto dto) throws DuplicateEntityException {
		LOG.debug("Create {} source called..", dto.getDescription());
		final Long id = service.createSource(converter.toEntity(dto));
		return converter.toDto(service.getSource(id));
	}

	@Override
	public void removeSources(final List<Long> ids) {
		LOG.debug("Remove sources called..");
		service.removeSources(ids);
	}

}
