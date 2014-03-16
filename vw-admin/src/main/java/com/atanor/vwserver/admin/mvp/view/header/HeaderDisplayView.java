package com.atanor.vwserver.admin.mvp.view.header;

import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.modal.NewDisplayWindow;
import com.smartgwt.client.widgets.Window;

public class HeaderDisplayView extends AbstractHeaderView implements HeaderView {

	public HeaderDisplayView() {
		super("Displays");
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doNew() {
		final Window window = new NewDisplayWindow();
		window.show();
	}

}
