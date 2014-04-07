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
	private ISourceService sourceService;

	@Inject
	private SourceConverter sourceConverter;

	@Override
	public List<SourceDto> getSources() {
		return sourceConverter.toListDto(sourceService.getSources());
	}

	@Override
	public SourceDto createSource(final SourceDto dto) throws DuplicateEntityException {
		LOG.debug("Create {} source called..", dto.getDescription());
		final Long id = sourceService.createSource(sourceConverter.toEntity(dto));
		return sourceConverter.toDto(sourceService.getSource(id));
	}

	@Override
	public void removeSource(final SourceDto dto) {
		LOG.debug("Remove {} source called..", dto.getDescription());
		sourceService.removeSource(dto.getId());
	}

}
