package com.atanor.vwserver.servlet;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.atanor.vwserver.common.rpc.exception.DuplicateEntityException;
import com.atanor.vwserver.common.rpc.services.PresetService;
import com.atanor.vwserver.domain.converter.PresetConverter;
import com.atanor.vwserver.services.IPresetService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class PresetServlet extends RemoteServiceServlet implements PresetService {

	private static final Logger LOG = LoggerFactory.getLogger(PresetServlet.class);

	@Inject
	private IPresetService service;

	@Inject
	private PresetConverter converter;

	@Override
	public List<PresetDto> getPresets() {
		return converter.toListDto(service.getPresets());
	}

	@Override
	public PresetDto createPreset(final PresetDto dto) throws DuplicateEntityException {
		LOG.debug("Create {} preset called..", dto.getName());
		final Long id = service.createPreset(converter.toEntity(dto));
		return converter.toDto(service.getPreset(id));
	}

	@Override
	public void removePreset(final PresetDto dto) {
		LOG.debug("Remove {} preset called..", dto.getName());
		service.removePreset(dto.getId());
	}

}
