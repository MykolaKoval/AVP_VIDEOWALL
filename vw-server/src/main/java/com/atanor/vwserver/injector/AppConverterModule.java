package com.atanor.vwserver.injector;

import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.dto.HardwareDto;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.atanor.vwserver.common.rpc.dto.WindowDto;
import com.atanor.vwserver.domain.converter.Converter;
import com.atanor.vwserver.domain.converter.DisplayConverter;
import com.atanor.vwserver.domain.converter.HardwareConverter;
import com.atanor.vwserver.domain.converter.PresetConverter;
import com.atanor.vwserver.domain.converter.WindowConverter;
import com.atanor.vwserver.domain.entity.Display;
import com.atanor.vwserver.domain.entity.Hardware;
import com.atanor.vwserver.domain.entity.Preset;
import com.atanor.vwserver.domain.entity.Window;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class AppConverterModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<Converter<PresetDto, Preset>>() {}).to(new TypeLiteral<PresetConverter>() {});
		bind(new TypeLiteral<Converter<WindowDto, Window>>() {}).to(new TypeLiteral<WindowConverter>() {});
		bind(new TypeLiteral<Converter<DisplayDto, Display>>() {}).to(new TypeLiteral<DisplayConverter>() {});
		bind(new TypeLiteral<Converter<HardwareDto, Hardware>>() {}).to(new TypeLiteral<HardwareConverter>() {});
	}

}
