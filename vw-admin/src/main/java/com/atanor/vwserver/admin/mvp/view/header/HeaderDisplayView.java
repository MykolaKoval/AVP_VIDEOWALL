package com.atanor.vwserver.admin.mvp.view.header;

import javax.inject.Inject;
import javax.inject.Provider;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.mvp.presenter.HeaderPresenter;
import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.modal.NewDisplayWindow;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.smartgwt.client.widgets.Window;

public class HeaderDisplayView extends AbstractHeaderView implements HeaderView {

	@Inject
	private Provider<NewDisplayWindow> provider;

	@Inject
	private HeaderPresenter presenter;

	private DisplayDto display;

	public HeaderDisplayView() {
		super("Displays");
		cancelButton.disable();
		removeButton.disable();
	}

	@Override
	protected void addButtons() {
		addButton(createButton);
		addButton(cancelButton);
		addButton(removeButton);
	}

	@Override
	public void clean() {
		this.display = null;
		createButton.enable();
		cancelButton.disable();
		removeButton.disable();
	}

	@Override
	protected void doNew() {
		final Window window = provider.get();
		window.show();
	}

	@Override
	protected void doCancel() {
		Client.goTo(new DisplayPlace(Action.CLEAN));
	}

	@Override
	protected void doRemove() {
		presenter.removeDisplay(display);
		Client.goTo(new DisplayPlace(Action.CLEAN));
	}

	public void setDisplay(final DisplayDto display) {
		this.display = display;
		createButton.disable();
		cancelButton.enable();
		removeButton.enable();
	}
}
