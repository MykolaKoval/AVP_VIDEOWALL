package com.atanor.vwserver.services;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atanor.vwserver.common.rpc.exception.DuplicateEntityException;
import com.atanor.vwserver.domain.dao.LayoutDao;
import com.atanor.vwserver.domain.entity.Layout;
import com.atanor.vwserver.graphics.ImgGenerator;
import com.atanor.vwserver.util.ImageEncoder;

public class DefaultLayoutService implements ILayoutService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultLayoutService.class);

	@Inject
	private ImgGenerator imgGenerator;
	
	@Inject
	private LayoutDao dao;

	@Override
	public Long createLayout(final Layout layout) {
		valdate(layout);

		final BufferedImage img = imgGenerator.generate(layout);
		layout.setImgBlob(ImageEncoder.encodeImage(img));
		layout.setCreateTS(new Date());

		final Long id = dao.insert(layout);

		LOG.debug("Layout {} was successfully created", layout.getName());
		return id;
	}

	private void valdate(final Layout layout) {
		Layout entity = null;
		try {
			entity = dao.findByName(layout.getName());
		} catch (Exception e) {
		}

		if (entity != null) {
			LOG.error("Duplicate layout {} found", layout.getName());
			throw new DuplicateEntityException();
		}
	}

	@Override
	public void removeLayout(final Long id) {
		final Layout toRemove = dao.find(id);
		if (toRemove == null) {
			throw new IllegalStateException(String.format("Layout with id %s not found", id));
		}
		final String name = toRemove.getName();
		dao.delete(toRemove);
		LOG.debug("Layout {} was successfully deleted", name);
	}

	@Override
	public List<Layout> getLayouts() {
		return dao.findAll();
	}

	@Override
	public Layout getLayout(Long id) {
		return dao.find(id);
	}

}
