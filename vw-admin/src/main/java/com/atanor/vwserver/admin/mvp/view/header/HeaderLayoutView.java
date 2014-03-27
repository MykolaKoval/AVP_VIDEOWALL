package com.atanor.vwserver.admin.mvp.view.header;

import javax.inject.Inject;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.admin.mvp.presenter.EditPresenter;
import com.atanor.vwserver.admin.mvp.view.HeaderView;

public class HeaderLayoutView extends AbstractHeaderView implements HeaderView {

	@Inject
	private EditPresenter editPresenter;

	public HeaderLayoutView() {
		super("Layouts");
	}

	@Override
	public void clean() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doNew() {
		editPresenter.addLayoutWindow();
	}
	
	@Override
	protected void doCancel() {
		Client.goTo(new LayoutPlace(Action.CLEAN));
	}
	
}
