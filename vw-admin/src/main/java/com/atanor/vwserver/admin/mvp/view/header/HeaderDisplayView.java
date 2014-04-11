package com.atanor.vwserver.admin.mvp.view.header;

import javax.inject.Inject;
import javax.inject.Provider;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.place.Action;
import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.mvp.presenter.HeaderPresenter;
import com.atanor.vwserver.admin.ui.modal.NewDisplayWindow;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.HLayout;

public class HeaderDisplayView extends AbstractHeaderView {

	@Inject
	private Provider<NewDisplayWindow> provider;

	@Inject
	private HeaderPresenter presenter;

	private final Label selectedDisplay;
	private DisplayDto display;

	public HeaderDisplayView() {
		super("Displays");
		cancelButton.disable();
		removeButton.disable();

		final HLayout layout = new HLayout();
		layout.setWidth100();
		layout.setAlign(VerticalAlignment.CENTER);

		selectedDisplay = new Label("");
		selectedDisplay.setWidth(300);
		layout.addMembers(selectedDisplay);

		addMember(layout);
	}

	@Override
	protected void addButtons() {
		addButton(createButton);
		addButton(cancelButton);
		addButton(removeButton);
	}

	public void clean() {
		this.display = null;
		createButton.enable();
		cancelButton.disable();
		removeButton.disable();
		selectedDisplay.setContents("");
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
		selectedDisplay.setContents(genContent(display.getName()));
	}

	private static String genContent(final String name) {
		return "Display: " + name;
	}
}
