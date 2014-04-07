package com.atanor.vwserver.domain.dao;

import javax.persistence.TypedQuery;

import com.atanor.vwserver.domain.entity.Source;

public class SourceDao extends GenericDaoImpl<Source, Long> {

	@Override
	public Class<Source> getEntityClass() {
		return Source.class;
	}

	public Source findByCodeAndDesc(final String code, final String desc) {
		final TypedQuery<Source> query = getEntityManager().createQuery(
				"SELECT s FROM Source s WHERE s.code = :code AND s.description = :desc", Source.class);
		query.setParameter("code", code);
		query.setParameter("desc", desc);
		return query.getSingleResult();
	}

}
