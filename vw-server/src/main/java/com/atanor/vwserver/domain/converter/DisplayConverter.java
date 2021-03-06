package com.atanor.vwserver.domain.converter;

import org.apache.commons.lang3.Validate;

import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.domain.entity.Display;

public class DisplayConverter extends AbstractConverter<DisplayDto, Display> {

	@Override
	public DisplayDto toDto(final Display entity) {
		Validate.notNull(entity, "entity param can not be null");

		final DisplayDto dto = new DisplayDto(entity.getId());
		dto.setName(entity.getName());
		dto.setOrientation(entity.getOrientation());
		dto.setResolution(entity.getResolution());
		dto.setSegmentNumHeight(entity.getSegmentNumHeight());
		dto.setSegmentNumWidth(entity.getSegmentNumWidth());
		dto.setImage(entity.getImgBlob());
		
		return dto;
	}

	@Override
	public Display toEntity(final DisplayDto dto) {
		Validate.notNull(dto, "dto param can not be null");

		final Display entity = new Display(dto.getId());
		entity.setName(dto.getName());
		entity.setOrientation(dto.getOrientation());
		entity.setResolution(dto.getResolution());
		entity.setSegmentNumHeight(dto.getSegmentNumHeight());
		entity.setSegmentNumWidth(dto.getSegmentNumWidth());

		return entity;
	}

}
