package com.atanor.vwserver.domain.converter;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;

import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.atanor.vwserver.domain.entity.Preset;
import com.atanor.vwserver.domain.entity.PresetWindow;

public class PresetConverter extends AbstractConverter<PresetDto, Preset> {

	@Inject
	private PresetWindowConverter winConverter;

	@Override
	public PresetDto toDto(final Preset entity) {
		Validate.notNull(entity, "entity param can not be null");

		final PresetDto dto = new PresetDto(entity.getId());
		dto.setName(entity.getName());
		dto.setWindows(convertEntityList(winConverter, entity.getWindows()));

		return dto;
	}

	@Override
	public Preset toEntity(final PresetDto dto) {
		Validate.notNull(dto, "dto param can not be null");

		final Preset entity = new Preset(dto.getId());
		entity.setName(dto.getName());
		
		final List<PresetWindow> windows = convertDtoList(winConverter, dto.getWindows());
		updatePresetReference(entity, windows);
		entity.setWindows(windows);
		
		return entity;
	}

	private void updatePresetReference(final Preset preset, final List<PresetWindow> windows) {
		for (PresetWindow window : windows) {
			window.setPreset(preset);
		}
	}
}
