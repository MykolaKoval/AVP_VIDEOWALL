package com.atanor.vwserver.domain.converter;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;

import com.atanor.vwserver.common.rpc.dto.PresetWindowDto;
import com.atanor.vwserver.domain.entity.PresetWindow;

public class PresetWindowConverter extends AbstractConverter<PresetWindowDto, PresetWindow> {

	@Inject
	private SourceConverter sourceConverter;
	
	@Override
	public PresetWindowDto toDto(final PresetWindow entity) {
		Validate.notNull(entity, "entity param can not be null");
		
		final PresetWindowDto dto = new PresetWindowDto(entity.getId());
		dto.setName(entity.getName());
		dto.setSource(sourceConverter.toDto(entity.getSource()));
		dto.setXTopLeft(entity.getXTopLeft());
		dto.setYTopLeft(entity.getYTopLeft());
		dto.setXBottomRight(entity.getXBottomRight());
		dto.setYBottomRight(entity.getYBottomRight());
		dto.setZIndex(entity.getZIndex());
		
		return dto;
	}

	@Override
	public PresetWindow toEntity(final PresetWindowDto dto) {
		Validate.notNull(dto, "dto param can not be null");
		
		final PresetWindow entity = new PresetWindow(dto.getId());
		entity.setName(dto.getName());
		entity.setSource(sourceConverter.toEntity(dto.getSource()));
		entity.setXTopLeft(dto.getXTopLeft());
		entity.setYTopLeft(dto.getYTopLeft());
		entity.setXBottomRight(dto.getXBottomRight());
		entity.setYBottomRight(dto.getYBottomRight());
		entity.setZIndex(dto.getZIndex());
		
		entity.setModified(dto.isModified());
		entity.setSelected(dto.isSelected());
		
		return entity;
	}

}
