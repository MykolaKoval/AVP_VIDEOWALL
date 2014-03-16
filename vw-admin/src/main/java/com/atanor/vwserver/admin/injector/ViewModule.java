package com.atanor.vwserver.admin.injector;

import com.atanor.vwserver.admin.mvp.view.NavigateView;
import com.atanor.vwserver.admin.mvp.view.edit.EditDisplayView;
import com.atanor.vwserver.admin.mvp.view.edit.EditLayoutView;
import com.atanor.vwserver.admin.mvp.view.edit.EditPresetView;
import com.atanor.vwserver.admin.mvp.view.header.HeaderDisplayView;
import com.atanor.vwserver.admin.mvp.view.header.HeaderLayoutView;
import com.atanor.vwserver.admin.mvp.view.header.HeaderPresetView;
import com.atanor.vwserver.admin.mvp.view.navigate.DefaultNavigateView;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewDisplayView;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewLayoutView;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewPresetView;
import com.atanor.vwserver.admin.ui.MainPane;
import com.atanor.vwserver.admin.ui.modal.NewDisplayWindow;
import com.atanor.vwserver.admin.ui.modal.presenter.NewDisplayPresenter;
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
		
		// Modal Windows
		bind(NewDisplayWindow.class);
		bind(NewDisplayPresenter.class);
	}

}
