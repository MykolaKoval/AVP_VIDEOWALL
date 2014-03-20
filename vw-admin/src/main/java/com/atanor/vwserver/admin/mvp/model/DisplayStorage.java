package com.atanor.vwserver.admin.mvp.model;

import java.util.Collection;
import java.util.Map;

import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.google.common.collect.Maps;

public class DisplayStorage {

	private final Map<Long, DisplayDto> storage = Maps.newHashMap();

	public void addDisplay(final DisplayDto display) {
		storage.put(display.getId(), display);
	}

	public void addDisplays(final Collection<DisplayDto> displays) {
		for (DisplayDto display : displays) {
			addDisplay(display);
		}
	}

	public DisplayDto getDisplay(final Long id) {
		return storage.get(id);
	}

	public Collection<DisplayDto> getDisplays() {
		return storage.values();
	}

	public void clean() {
		storage.clear();
	}
}
