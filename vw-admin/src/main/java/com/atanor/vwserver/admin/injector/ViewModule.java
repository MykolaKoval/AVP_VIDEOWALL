package com.atanor.vwserver.admin.injector;

import com.atanor.vwserver.admin.mvp.view.NavigateView;
import com.atanor.vwserver.admin.mvp.view.display.EditDisplayView;
import com.atanor.vwserver.admin.mvp.view.display.HeaderDisplayView;
import com.atanor.vwserver.admin.mvp.view.display.PreviewDisplayView;
import com.atanor.vwserver.admin.mvp.view.layout.EditLayoutView;
import com.atanor.vwserver.admin.mvp.view.layout.HeaderLayoutView;
import com.atanor.vwserver.admin.mvp.view.layout.PreviewLayoutView;
import com.atanor.vwserver.admin.mvp.view.navigate.DefaultNavigateView;
import com.atanor.vwserver.admin.mvp.view.preset.EditPresetView;
import com.atanor.vwserver.admin.mvp.view.preset.HeaderPresetView;
import com.atanor.vwserver.admin.mvp.view.preset.PreviewPresetView;
import com.atanor.vwserver.admin.ui.MainPane;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class ViewModule extends AbstractGinModule {

	@Override
	protected void configure() {

		bind(MainPane.class).in(Singleton.class);

		bind(NavigateView.class).to(DefaultNavigateView.class).in(Singleton.class);

		bind(HeaderPresetView.class).in(Singleton.class);
		bind(HeaderDisplayView.class).in(Singleton.class);
		bind(HeaderLayoutView.class).in(Singleton.class);
		
		bind(PreviewPresetView.class).in(Singleton.class);
		bind(PreviewDisplayView.class).in(Singleton.class);
		bind(PreviewLayoutView.class).in(Singleton.class);
		
		bind(EditPresetView.class).in(Singleton.class);
		bind(EditDisplayView.class).in(Singleton.class);
		bind(EditLayoutView.class).in(Singleton.class);
	}

}
