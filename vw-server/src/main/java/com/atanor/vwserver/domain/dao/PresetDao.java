package com.atanor.vwserver.domain.dao;

import com.atanor.vwserver.domain.entity.Preset;


public class PresetDao extends GenericDaoImpl<Preset, Long> {

	@Override
	public Class<Preset> getEntityClass() {
		return Preset.class;
	}

}
