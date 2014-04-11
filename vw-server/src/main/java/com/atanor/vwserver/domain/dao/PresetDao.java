package com.atanor.vwserver.domain.dao;

import javax.persistence.TypedQuery;

import com.atanor.vwserver.domain.entity.Preset;

public class PresetDao extends GenericDaoImpl<Preset, Long> {

	@Override
	public Class<Preset> getEntityClass() {
		return Preset.class;
	}

	public Preset findByName(final String name) {
		final TypedQuery<Preset> query = getEntityManager().createQuery("SELECT p FROM Preset l WHERE p.name = :name",
				Preset.class);
		return query.setParameter("name", name).getSingleResult();
	}

}
