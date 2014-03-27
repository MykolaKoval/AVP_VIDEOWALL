package com.atanor.vwserver.admin.mvp.model;

import java.util.Collection;
import java.util.Map;

import com.atanor.vwserver.common.rpc.dto.AbstractDto;
import com.google.common.collect.Maps;

public class AbstractStorage<E extends AbstractDto> {

	private final Map<Long, E> storage = Maps.newLinkedHashMap();

	public void add(final E entity) {
		storage.put(entity.getId(), entity);
	}

	public void addAll(final Collection<E> entities) {
		for (E entity : entities) {
			add(entity);
		}
	}

	public void replace(final Collection<E> entities) {
		clean();
		for (E entity : entities) {
			add(entity);
		}
	}

	public E get(final Long id) {
		return storage.get(id);
	}

	public Collection<E> getAll() {
		return storage.values();
	}

	public void remove(final Long id) {
		if (storage.containsKey(id)) {
			storage.remove(id);
		}
	}

	public void clean() {
		storage.clear();
	}
}
