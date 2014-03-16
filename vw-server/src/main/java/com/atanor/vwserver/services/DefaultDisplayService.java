package com.atanor.vwserver.services;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atanor.vwserver.common.rpc.exception.DuplicateEntityException;
import com.atanor.vwserver.domain.dao.DisplayDao;
import com.atanor.vwserver.domain.entity.Display;

public class DefaultDisplayService implements IDisplayService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultDisplayService.class);

	@Inject
	private DisplayDao dao;

	@Override
	public Long createDisplay(final Display display) {
		valdate(display);
		final Long id = dao.insert(display);
		LOG.debug("Display {} was successfully created", display.getName());
		return id;
	}

	private void valdate(final Display display) {
		Display entity = null;
		try {
			entity = dao.findByName(display.getName());
		} catch (Exception e) {
		}

		if (entity != null) {
			LOG.error("Duplicate display {} found", display.getName());
			throw new DuplicateEntityException();
		}
	}

	@Override
	public void deleteDisplay(Display display) {
		final String name = display.getName();
		dao.delete(display);
		LOG.debug("Display {} was successfully deleted", name);
	}

	@Override
	public Display getDisplay(final Long id) {
		return dao.find(id);
	}

	@Override
	public List<Display> getDisplays() {
		return dao.findAll();
	}

}
