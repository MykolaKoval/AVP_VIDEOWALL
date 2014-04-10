package com.atanor.vwserver.services.impl;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atanor.vwserver.common.rpc.exception.DuplicateEntityException;
import com.atanor.vwserver.domain.dao.DisplayDao;
import com.atanor.vwserver.domain.entity.Display;
import com.atanor.vwserver.graphics.ImgGenerator;
import com.atanor.vwserver.services.IDisplayService;
import com.atanor.vwserver.util.ImageEncoder;

public class DefaultDisplayService implements IDisplayService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultDisplayService.class);

	@Inject
	private ImgGenerator imgGenerator;

	@Inject
	private DisplayDao dao;

	@Override
	public Long createDisplay(final Display display) {
		valdate(display);

		final BufferedImage img = imgGenerator.generate(display);
		display.setImgBlob(ImageEncoder.encodeImage(img));
		display.setCreateTS(new Date());
		
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
	public void removeDisplay(final Long id) {
		final Display toRemove = dao.find(id);
		if (toRemove == null) {
			throw new IllegalStateException(String.format("Display with id %s not found", id));
		}
		final String name = toRemove.getName();
		dao.delete(toRemove);
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
