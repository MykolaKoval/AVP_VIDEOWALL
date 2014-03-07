package com.atanor.vwserver.domain.converter;

import org.apache.commons.lang3.Validate;

import com.atanor.vwserver.common.rpc.dto.PanelLayoutDto;
import com.atanor.vwserver.domain.entity.PanelLayout;

public class PanelLayoutConverter {

	public PanelLayoutDto toDto(final PanelLayout entity) {
		Validate.notNull(entity, "entity param can not be null");

		final PanelLayoutDto dto = new PanelLayoutDto();
		dto.setName(entity.getDescription());
		dto.setRowPanelQuantity(entity.getRowPanelQuantity());
		dto.setColumnPanelQuantity(entity.getColumnPanelQuantity());
		
		return dto;
	}

	public PanelLayout toEntity(final PanelLayoutDto dto) {
		Validate.notNull(dto, "dto param can not be null");

		return PanelLayout.getLayout(dto.getName());
	}

}
