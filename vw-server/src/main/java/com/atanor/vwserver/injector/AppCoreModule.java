package com.atanor.vwserver.injector;

import com.atanor.vwserver.graphics.ImgGenerator;
import com.atanor.vwserver.services.IDisplayService;
import com.atanor.vwserver.services.ILayoutService;
import com.atanor.vwserver.services.IPresetService;
import com.atanor.vwserver.services.ISourceService;
import com.atanor.vwserver.services.hardware.IWallService;
import com.atanor.vwserver.services.impl.DefaultDisplayService;
import com.atanor.vwserver.services.impl.DefaultLayoutService;
import com.atanor.vwserver.services.impl.DefaultPresetService;
import com.atanor.vwserver.services.impl.DefaultSourceService;
import com.atanor.vwserver.services.impl.MockWallService;
import com.google.inject.AbstractModule;

public class AppCoreModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IDisplayService.class).to(DefaultDisplayService.class);
		bind(ILayoutService.class).to(DefaultLayoutService.class);
		bind(ISourceService.class).to(DefaultSourceService.class);
		bind(IPresetService.class).to(DefaultPresetService.class);
		bind(IWallService.class).to(MockWallService.class);
		bind(ImgGenerator.class);
	}

}
