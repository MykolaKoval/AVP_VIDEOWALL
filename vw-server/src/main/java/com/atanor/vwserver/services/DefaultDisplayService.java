package com.atanor.vwserver.services;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atanor.vwserver.common.rpc.exception.DuplicateEntityException;
import com.atanor.vwserver.domain.dao.DisplayDao;
import com.atanor.vwserver.domain.entity.Display;
import com.atanor.vwserver.graphics.ImgGenerator;
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
