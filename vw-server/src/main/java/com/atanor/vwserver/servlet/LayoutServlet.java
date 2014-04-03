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
	private ILayoutService layoutService;

	@Inject
	private LayoutConverter layoutConverter;

	@Override
	public List<LayoutDto> getLayouts() {
		return layoutConverter.toListDto(layoutService.getLayouts());
	}

	@Override
	public LayoutDto createLayout(final LayoutDto dto) throws DuplicateEntityException {
		LOG.debug("Create {} layout called..", dto.getName());
		final Long id = layoutService.createLayout(layoutConverter.toEntity(dto));
		return layoutConverter.toDto(layoutService.getLayout(id));
	}

	@Override
	public void removeLayout(LayoutDto display) {
		// TODO Auto-generated method stub

	}

}
