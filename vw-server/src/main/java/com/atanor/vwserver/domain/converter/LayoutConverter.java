package com.atanor.vwserver.domain.converter;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;

import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.atanor.vwserver.domain.entity.Layout;
import com.atanor.vwserver.domain.entity.LayoutWindow;

public class LayoutConverter extends AbstractConverter<LayoutDto, Layout> {

	@Inject
	private LayoutWindowConverter winConverter;

	@Override
	public LayoutDto toDto(final Layout entity) {
		Validate.notNull(entity, "entity param can not be null");

		final LayoutDto dto = new LayoutDto(entity.getId());
		dto.setName(entity.getName());
		dto.setImage(entity.getImgBlob());
		dto.setWindows(convertEntityList(winConverter, entity.getWindows()));

		return dto;
	}

	@Override
	public Layout toEntity(final LayoutDto dto) {
		Validate.notNull(dto, "dto param can not be null");

		final Layout entity = new Layout(dto.getId());
		entity.setName(dto.getName());

		final List<LayoutWindow> windows = convertDtoList(winConverter, dto.getWindows());
		updateReferences(entity, windows);
		entity.setWindows(windows);

		return entity;
	}

	private void updateReferences(final Layout layout, final List<LayoutWindow> windows) {
		for (LayoutWindow window : windows) {
			window.setLayout(layout);
		}
	}
}
