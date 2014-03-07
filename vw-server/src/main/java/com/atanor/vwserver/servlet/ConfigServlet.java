package com.atanor.vwserver.servlet;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.atanor.vwserver.common.rpc.dto.HardwareDto;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.atanor.vwserver.common.rpc.services.ConfigService;
import com.atanor.vwserver.domain.converter.HardwareConverter;
import com.atanor.vwserver.domain.converter.PresetConverter;
import com.atanor.vwserver.services.EquipmentConfigService;
import com.atanor.vwserver.services.hardware.HardwareFacade;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@Singleton
@SuppressWarnings("serial")
public class ConfigServlet extends RemoteServiceServlet implements ConfigService {

	@Inject
	private EquipmentConfigService equipService;

	@Inject
	private HardwareConverter hardwareConverter;

	@Inject
	private PresetConverter presetConverter;

	@Inject
	private HardwareFacade hardwareFacade;

	public HardwareDto getHardwareConfiguration() {
		return hardwareConverter.toDto(equipService.getActiveHardware());
	}

	@Override
	public Boolean applyPreset(PresetDto preset) {
		return hardwareFacade.sendPresetConfiguration(presetConverter.toEntity(preset));
	}

	@Override
	public PresetDto savePreset(PresetDto preset) {
		equipService.savePreset(presetConverter.toEntity(preset));
		return presetConverter.toDto(equipService.getPresetById(preset.getId()));
	}

}
