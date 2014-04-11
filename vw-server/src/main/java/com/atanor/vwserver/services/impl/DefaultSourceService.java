package com.atanor.vwserver.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atanor.vwserver.common.rpc.exception.DuplicateEntityException;
import com.atanor.vwserver.domain.dao.SourceDao;
import com.atanor.vwserver.domain.entity.Source;
import com.atanor.vwserver.services.ISourceService;

public class DefaultSourceService implements ISourceService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultSourceService.class);

	@Inject
	private SourceDao dao;

	@Override
	public Long createSource(final Source source) {
		validate(source);

		final Long id = dao.insert(source);

		LOG.debug("Source '{}' was successfully created", source.getDescription());
		return id;
	}

	@Override
	public void removeSources(final List<Long> ids) {
		for (final Long id : ids) {
			removeSource(id);
		}
	}

	private void removeSource(final Long id) {
		final Source toRemove = dao.find(id);
		if (toRemove == null) {
			LOG.error("Error. Source with id {} not found", id);
			return;
		}
		
		final String desc = toRemove.getDescription();
		dao.delete(toRemove);
		LOG.debug("Source '{}' was successfully deleted", desc);

	}
	
	@Override
	public Source getSource(final Long id) {
		return dao.find(id);
	}

	@Override
	public List<Source> getSources() {
		return dao.findAll();
	}

	private void validate(final Source source) {
		Source entity = null;
		try {
			entity = dao.findByCodeOrDesc(source.getCode(), source.getDescription());
		} catch (Exception e) {
		}

		if (entity != null) {
			LOG.error("Duplicate source '{}' found", source.getDescription());
			throw new DuplicateEntityException();
		}
	}

}
