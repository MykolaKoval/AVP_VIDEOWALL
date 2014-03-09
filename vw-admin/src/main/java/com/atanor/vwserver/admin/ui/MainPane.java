package com.atanor.vwserver.admin.ui;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.EditMapper;
import com.atanor.vwserver.admin.mvp.HeaderMapper;
import com.atanor.vwserver.admin.mvp.PreviewMapper;
import com.atanor.vwserver.admin.mvp.view.NavigateView;
import com.atanor.vwserver.admin.ui.layout.MainPaneLayout;
import com.google.gwt.user.client.ui.IsWidget;

public class MainPane extends RootPane<MainPaneLayout> implements IsWidget {

	@Inject
	public MainPane(final NavigateView navigateView) {
		super(new MainPaneLayout());
		asWidget().getNavigateDisplay().setWidget(navigateView);
		
		bind(new HeaderMapper(), asWidget().getHeaderDisplay());
		bind(new PreviewMapper(), asWidget().getPreviewDisplay());
		bind(new EditMapper(), asWidget().getEditDisplay());
	}

}
