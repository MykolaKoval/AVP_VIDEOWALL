package com.atanor.vwserver.domain.converter;

import org.apache.commons.lang3.Validate;

import com.atanor.vwserver.common.rpc.dto.SourceDto;
import com.atanor.vwserver.domain.entity.Source;

public class SourceConverter extends AbstractConverter<SourceDto, Source> {

	@Override
	public SourceDto toDto(final Source entity) {
		Validate.notNull(entity, "entity param can not be null");

		final SourceDto dto = new SourceDto(entity.getId());
		dto.setCode(entity.getCode());
		dto.setDescription(entity.getDescription());

		return dto;
	}

	@Override
	public Source toEntity(final SourceDto dto) {
		Validate.notNull(dto, "dto param can not be null");

		final Source entity = new Source(dto.getId());
		entity.setCode(dto.getCode());
		entity.setDescription(dto.getDescription());

		return entity;
	}

}
