package com.atanor.vwserver.domain.dao;

import javax.persistence.TypedQuery;

import com.atanor.vwserver.domain.entity.Layout;

public class LayoutDao extends GenericDaoImpl<Layout, Long> {

	@Override
	public Class<Layout> getEntityClass() {
		return Layout.class;
	}

	public Layout findByName(final String name) {
		final TypedQuery<Layout> query = getEntityManager().createQuery("SELECT l FROM Layout l WHERE l.name = :name",
				Layout.class);
		return query.setParameter("name", name).getSingleResult();
	}

}
