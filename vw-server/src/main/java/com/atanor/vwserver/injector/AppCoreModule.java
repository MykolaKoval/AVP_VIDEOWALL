package com.atanor.vwserver.injector;

import com.atanor.vwserver.services.EquipmentConfigService;
import com.atanor.vwserver.services.EquipmentConfigServiceImpl;
import com.atanor.vwserver.services.hardware.HardwareFacade;
import com.atanor.vwserver.services.hardware.HardwareFacadeImpl;
import com.google.inject.AbstractModule;

public class AppCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(EquipmentConfigService.class).to(EquipmentConfigServiceImpl.class);
		bind(HardwareFacade.class).to(HardwareFacadeImpl.class);
	}

}
