package com.atanor.vwserver.domain.converter;

import org.apache.commons.lang3.Validate;

import com.atanor.vwserver.common.rpc.dto.LayoutWindowDto;
import com.atanor.vwserver.domain.entity.LayoutWindow;

public class LayoutWindowConverter extends AbstractConverter<LayoutWindowDto, LayoutWindow> {

	@Override
	public LayoutWindowDto toDto(final LayoutWindow entity) {
		Validate.notNull(entity, "entity param can not be null");

		final LayoutWindowDto dto = new LayoutWindowDto(entity.getId());
		dto.setName(entity.getName());
		dto.setLeft(entity.getLeft());
		dto.setTop(entity.getTop());
		dto.setWidth(entity.getWidth());
		dto.setHeight(entity.getHeight());

		return dto;
	}

	@Override
	public LayoutWindow toEntity(final LayoutWindowDto dto) {
		Validate.notNull(dto, "dto param can not be null");

		final LayoutWindow entity = new LayoutWindow(dto.getId());
		entity.setName(dto.getName());
		entity.setLeft(dto.getLeft());
		entity.setTop(dto.getTop());
		entity.setWidth(dto.getWidth());
		entity.setHeight(dto.getHeight());

		return entity;
	}

}
