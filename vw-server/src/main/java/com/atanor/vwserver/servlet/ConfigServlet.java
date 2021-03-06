package com.atanor.vwserver.servlet;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.atanor.vwserver.common.rpc.dto.ConfigDto;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.atanor.vwserver.common.rpc.dto.SourceDto;
import com.atanor.vwserver.common.rpc.services.ConfigService;
import com.atanor.vwserver.domain.converter.DisplayConverter;
import com.atanor.vwserver.domain.converter.LayoutConverter;
import com.atanor.vwserver.domain.converter.PresetConverter;
import com.atanor.vwserver.domain.converter.SourceConverter;
import com.atanor.vwserver.services.IDisplayService;
import com.atanor.vwserver.services.ILayoutService;
import com.atanor.vwserver.services.IPresetService;
import com.atanor.vwserver.services.ISourceService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class ConfigServlet extends RemoteServiceServlet implements ConfigService {

	@Inject
	private IDisplayService displayService;
	@Inject
	private ILayoutService layoutService;
	@Inject
	private ISourceService sourceService;
	@Inject
	private IPresetService presetService;

	@Inject
	private DisplayConverter displayConverter;
	@Inject
	private LayoutConverter layoutConverter;
	@Inject
	private SourceConverter sourceConverter;
	@Inject
	private PresetConverter presetConverter;

	@Override
	public ConfigDto getConfiguration() {
		final ConfigDto config = new ConfigDto();

		config.setDisplays(getDisplays());
		config.setLayouts(getLayouts());
		config.setSources(getSources());
		config.setPresets(getPresets());
		
		return config;
	}

	private List<DisplayDto> getDisplays() {
		return displayConverter.toListDto(displayService.getDisplays());
	}

	private List<LayoutDto> getLayouts() {
		return layoutConverter.toListDto(layoutService.getLayouts());
	}

	private List<SourceDto> getSources() {
		return sourceConverter.toListDto(sourceService.getSources());
	}

	private List<PresetDto> getPresets() {
		return presetConverter.toListDto(presetService.getPresets());
	}
}
