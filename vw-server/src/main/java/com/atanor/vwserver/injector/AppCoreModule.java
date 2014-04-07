package com.atanor.vwserver.injector;

import com.atanor.vwserver.graphics.ImgGenerator;
import com.atanor.vwserver.services.DefaultDisplayService;
import com.atanor.vwserver.services.DefaultLayoutService;
import com.atanor.vwserver.services.DefaultSourceService;
import com.atanor.vwserver.services.EquipmentConfigService;
import com.atanor.vwserver.services.EquipmentConfigServiceImpl;
import com.atanor.vwserver.services.IDisplayService;
import com.atanor.vwserver.services.ILayoutService;
import com.atanor.vwserver.services.ISourceService;
import com.atanor.vwserver.services.hardware.HardwareFacade;
import com.atanor.vwserver.services.hardware.HardwareFacadeImpl;
import com.google.inject.AbstractModule;

public class AppCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IDisplayService.class).to(DefaultDisplayService.class);
		bind(ILayoutService.class).to(DefaultLayoutService.class);
		bind(ISourceService.class).to(DefaultSourceService.class);
		bind(EquipmentConfigService.class).to(EquipmentConfigServiceImpl.class);
		bind(HardwareFacade.class).to(HardwareFacadeImpl.class);
		bind(ImgGenerator.class);
	}

}
