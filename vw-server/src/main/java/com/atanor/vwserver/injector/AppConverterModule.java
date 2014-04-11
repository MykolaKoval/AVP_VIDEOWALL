package com.atanor.vwserver.injector;

import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.atanor.vwserver.common.rpc.dto.LayoutWindowDto;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.atanor.vwserver.common.rpc.dto.PresetWindowDto;
import com.atanor.vwserver.common.rpc.dto.SourceDto;
import com.atanor.vwserver.domain.converter.Converter;
import com.atanor.vwserver.domain.converter.DisplayConverter;
import com.atanor.vwserver.domain.converter.LayoutConverter;
import com.atanor.vwserver.domain.converter.LayoutWindowConverter;
import com.atanor.vwserver.domain.converter.PresetConverter;
import com.atanor.vwserver.domain.converter.PresetWindowConverter;
import com.atanor.vwserver.domain.converter.SourceConverter;
import com.atanor.vwserver.domain.entity.Display;
import com.atanor.vwserver.domain.entity.Layout;
import com.atanor.vwserver.domain.entity.LayoutWindow;
import com.atanor.vwserver.domain.entity.Preset;
import com.atanor.vwserver.domain.entity.PresetWindow;
import com.atanor.vwserver.domain.entity.Source;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class AppConverterModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<Converter<PresetDto, Preset>>() {}).to(new TypeLiteral<PresetConverter>() {});
		bind(new TypeLiteral<Converter<LayoutDto, Layout>>() {}).to(new TypeLiteral<LayoutConverter>() {});
		bind(new TypeLiteral<Converter<PresetWindowDto, PresetWindow>>() {}).to(new TypeLiteral<PresetWindowConverter>() {});
		bind(new TypeLiteral<Converter<LayoutWindowDto, LayoutWindow>>() {}).to(new TypeLiteral<LayoutWindowConverter>() {});
		bind(new TypeLiteral<Converter<DisplayDto, Display>>() {}).to(new TypeLiteral<DisplayConverter>() {});
		bind(new TypeLiteral<Converter<SourceDto, Source>>() {}).to(new TypeLiteral<SourceConverter>() {});
	}

}
