package com.atanor.vwserver.admin.mvp.view.header;

import javax.inject.Inject;
import javax.inject.Provider;

import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.modal.NewDisplayWindow;
import com.smartgwt.client.widgets.Window;

public class HeaderDisplayView extends AbstractHeaderView implements HeaderView {

	@Inject
	private Provider<NewDisplayWindow> provider;

	public HeaderDisplayView() {
		super("Displays");
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doNew() {
		final Window window = provider.get();
		window.show();
	}

}
