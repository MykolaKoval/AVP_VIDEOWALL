package com.atanor.vwserver.domain.converter;

import java.util.List;

import com.atanor.vwserver.common.rpc.dto.AbstractDto;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.domain.entity.AbstractEntity;
import com.atanor.vwserver.domain.entity.Display;
import com.google.common.collect.Lists;

@SuppressWarnings("rawtypes")
public abstract class AbstractConverter<D extends AbstractDto, E extends AbstractEntity> implements Converter<D, E> {

	protected static <D extends AbstractDto, E extends AbstractEntity> List<D> convertEntityList(
			final Converter<D, E> converter, final List<E> entities) {
		final List<D> converted = Lists.newArrayList();
		if (entities != null) {
			for (E entity : entities) {
				converted.add(converter.toDto(entity));
			}
		}

		return converted;
	}

	protected static <D extends AbstractDto, E extends AbstractEntity> List<E> convertDtoList(
			final Converter<D, E> converter, final List<D> dtos) {
		final List<E> converted = Lists.newArrayList();
		if (dtos != null) {
			for (D dto : dtos) {
				converted.add(converter.toEntity(dto));
			}
		}

		return converted;
	}

	public List<D> toListDto(final List<E> entities) {
		return convertEntityList(this, entities);
	}

	public List<E> toListEntities(final List<D> dtos) {
		return convertDtoList(this, dtos);
	}
}
