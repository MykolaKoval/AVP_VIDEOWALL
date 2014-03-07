package com.atanor.vwserver.admin.ui;

import com.atanor.vwserver.admin.mvp.EditPresetMapper;
import com.atanor.vwserver.admin.mvp.NavigatePresetMapper;
import com.atanor.vwserver.admin.ui.layout.MainPaneLayout;
import com.google.gwt.user.client.ui.IsWidget;

public class MainPane extends RootPane<MainPaneLayout> implements IsWidget {

	public MainPane() {
		super(new MainPaneLayout());

		bind(new NavigatePresetMapper(), asWidget().getNavigateDisplay());
		bind(new EditPresetMapper(), asWidget().getEditDisplay());
	}

}
