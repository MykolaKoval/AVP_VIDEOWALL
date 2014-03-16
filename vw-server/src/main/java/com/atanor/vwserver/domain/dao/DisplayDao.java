package com.atanor.vwserver.domain.dao;

import javax.persistence.TypedQuery;

import com.atanor.vwserver.domain.entity.Display;

public class DisplayDao extends GenericDaoImpl<Display, Long> {

	@Override
	public Class<Display> getEntityClass() {
		return Display.class;
	}

	public Display findByName(final String name) {
		final TypedQuery<Display> query = getEntityManager().createQuery(
				"SELECT d FROM Display d WHERE d.name = :name", Display.class);
		return query.setParameter("name", name).getSingleResult();
	}

}
