package com.atanor.vwserver.injector;

import com.atanor.vwserver.domain.dao.GenericDao;
import com.atanor.vwserver.domain.dao.HardwareDao;
import com.atanor.vwserver.domain.dao.PresetDao;
import com.atanor.vwserver.domain.entity.Hardware;
import com.atanor.vwserver.domain.entity.Preset;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class AppPersistenceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<GenericDao<Hardware, Long>>() {}).to(new TypeLiteral<HardwareDao>() {});
		bind(new TypeLiteral<GenericDao<Preset, Long>>() {}).to(new TypeLiteral<PresetDao>() {});
	}

}
