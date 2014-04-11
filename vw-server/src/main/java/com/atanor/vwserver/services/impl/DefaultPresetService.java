package com.atanor.vwserver.services.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atanor.vwserver.common.rpc.exception.DuplicateEntityException;
import com.atanor.vwserver.domain.dao.PresetDao;
import com.atanor.vwserver.domain.dao.SourceDao;
import com.atanor.vwserver.domain.entity.Preset;
import com.atanor.vwserver.domain.entity.PresetWindow;
import com.atanor.vwserver.domain.entity.Source;
import com.atanor.vwserver.services.IPresetService;

public class DefaultPresetService implements IPresetService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultPresetService.class);

	@Inject
	private SourceDao sourceDao;

	@Inject
	private PresetDao presetDao;

	@Override
	public Long createPreset(final Preset preset) {
		Validate.notNull(preset, "preset can not be null");
		validate(preset);
		validateWindows(preset.getWindows());

		fillSources(preset.getWindows());
		preset.setCreateTS(new Date());
		final Long id = presetDao.insert(preset);

		return id;
	}

	private void validateWindows(final List<PresetWindow> windows) {
		for (final PresetWindow window : windows) {
			if (!isSourceExist(window.getSource())) {
				throw new IllegalStateException("Source for PresetWindow is not exist");
			}
		}
	}

	private void fillSources(final List<PresetWindow> windows) {
		for (final PresetWindow window : windows) {
			final Source src = sourceDao.find(window.getSource().getId());
			window.setSource(src);
		}
	}

	private boolean isSourceExist(final Source source) {
		return source != null && sourceDao.find(source.getId()) != null;
	}

	private void validate(final Preset preset) {
		Preset entity = null;
		try {
			entity = presetDao.findByName(preset.getName());
		} catch (Exception e) {
		}

		if (entity != null) {
			LOG.error("Duplicate preset '{}' found", preset.getName());
			throw new DuplicateEntityException();
		}
	}

	@Override
	public void removePreset(final Long id) {
		final Preset toRemove = presetDao.find(id);
		if (toRemove == null) {
			throw new IllegalStateException(String.format("Preset with id %s not found", id));
		}
		final String name = toRemove.getName();
		presetDao.delete(toRemove);
		LOG.debug("Preset '{}' was successfully deleted", name);
	}

	@Override
	public List<Preset> getPresets() {
		return presetDao.findAll();
	}

	@Override
	public Preset getPreset(Long id) {
		return presetDao.find(id);
	}

}
