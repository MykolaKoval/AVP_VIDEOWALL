package com.atanor.vwserver.admin.injector;

import com.atanor.vwserver.admin.mvp.presenter.EditPresenter;
import com.atanor.vwserver.admin.mvp.presenter.HeaderPresenter;
import com.atanor.vwserver.admin.mvp.presenter.PreviewPresenter;
import com.atanor.vwserver.admin.mvp.view.NavigateView;
import com.atanor.vwserver.admin.mvp.view.edit.EditDisplayView;
import com.atanor.vwserver.admin.mvp.view.edit.EditLayoutView;
import com.atanor.vwserver.admin.mvp.view.edit.EditPresetView;
import com.atanor.vwserver.admin.mvp.view.edit.EditSourceView;
import com.atanor.vwserver.admin.mvp.view.header.HeaderDisplayView;
import com.atanor.vwserver.admin.mvp.view.header.HeaderLayoutView;
import com.atanor.vwserver.admin.mvp.view.header.HeaderPresetView;
import com.atanor.vwserver.admin.mvp.view.header.HeaderSourceView;
import com.atanor.vwserver.admin.mvp.view.navigate.DefaultNavigateView;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewDisplayView;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewLayoutView;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewPresetView;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewSourceView;
import com.atanor.vwserver.admin.ui.MainPane;
import com.atanor.vwserver.admin.ui.modal.NewDisplayWindow;
import com.atanor.vwserver.admin.ui.modal.NewLayoutWindow;
import com.atanor.vwserver.admin.ui.modal.NewPresetWindow;
import com.atanor.vwserver.admin.ui.modal.NewSourceWindow;
import com.atanor.vwserver.admin.ui.modal.SaveLayoutWindow;
import com.atanor.vwserver.admin.ui.modal.presenter.NewDisplayPresenter;
import com.atanor.vwserver.admin.ui.modal.presenter.NewPresetPresenter;
import com.atanor.vwserver.admin.ui.modal.presenter.NewSourcePresenter;
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
		bind(HeaderSourceView.class).in(Singleton.class);
		bind(HeaderPresenter.class).asEagerSingleton();
		
		bind(PreviewPresetView.class).in(Singleton.class);
		bind(PreviewDisplayView.class).in(Singleton.class);
		bind(PreviewLayoutView.class).in(Singleton.class);
		bind(PreviewSourceView.class).in(Singleton.class);
		bind(PreviewPresenter.class).asEagerSingleton();
		
		bind(EditPresetView.class).in(Singleton.class);
		bind(EditDisplayView.class).in(Singleton.class);
		bind(EditLayoutView.class).in(Singleton.class);
		bind(EditSourceView.class).in(Singleton.class);
		bind(EditPresenter.class).asEagerSingleton();
		
		// Modal Windows
		bind(NewPresetWindow.class);
		bind(NewDisplayWindow.class);
		bind(NewLayoutWindow.class);
		bind(SaveLayoutWindow.class);
		bind(NewSourceWindow.class);
		
		bind(NewPresetPresenter.class);
		bind(NewDisplayPresenter.class);
		bind(NewSourcePresenter.class);
	}

}
