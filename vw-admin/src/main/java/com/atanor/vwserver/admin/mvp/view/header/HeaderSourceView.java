package com.atanor.vwserver.admin.mvp.view.header;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.presenter.HeaderPresenter;
import com.atanor.vwserver.admin.mvp.view.HeaderView;

public class HeaderSourceView extends AbstractHeaderView implements HeaderView {

	@Inject
	private HeaderPresenter presenter;

	public HeaderSourceView() {
		super("Sources");
		removeButton.disable();
	}

	@Override
	protected void addButtons() {
		addButton(createButton);
		addButton(removeButton);
	}

	@Override
	public void clean() {
	}

	@Override
	protected void doNew() {

	}

	@Override
	protected void doRemove() {
	}

}
