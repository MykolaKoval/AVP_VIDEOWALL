package com.atanor.vwserver.domain.converter;

import com.atanor.vwserver.common.rpc.dto.AbstractDto;
import com.atanor.vwserver.domain.entity.AbstractEntity;

@SuppressWarnings("rawtypes")
public interface Converter<D extends AbstractDto, E extends AbstractEntity> {

	D toDto(E entity);

	E toEntity(D dto);

}
