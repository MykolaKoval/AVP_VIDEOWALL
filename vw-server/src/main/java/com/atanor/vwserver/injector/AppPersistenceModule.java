package com.atanor.vwserver.injector;

import com.atanor.vwserver.domain.dao.DisplayDao;
import com.atanor.vwserver.domain.dao.GenericDao;
import com.atanor.vwserver.domain.dao.LayoutDao;
import com.atanor.vwserver.domain.dao.PresetDao;
import com.atanor.vwserver.domain.dao.SourceDao;
import com.atanor.vwserver.domain.entity.Display;
import com.atanor.vwserver.domain.entity.Layout;
import com.atanor.vwserver.domain.entity.Preset;
import com.atanor.vwserver.domain.entity.Source;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class AppPersistenceModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<GenericDao<Preset, Long>>() {}).to(new TypeLiteral<PresetDao>() {});
		bind(new TypeLiteral<GenericDao<Display, Long>>() {}).to(new TypeLiteral<DisplayDao>() {});
		bind(new TypeLiteral<GenericDao<Layout, Long>>() {}).to(new TypeLiteral<LayoutDao>() {});
		bind(new TypeLiteral<GenericDao<Source, Long>>() {}).to(new TypeLiteral<SourceDao>() {});
	}

}
