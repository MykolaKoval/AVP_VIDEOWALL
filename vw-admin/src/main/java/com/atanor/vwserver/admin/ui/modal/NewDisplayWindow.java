package com.atanor.vwserver.admin.ui.modal;

import javax.inject.Inject;

import com.atanor.vwserver.admin.ui.Utils;
import com.atanor.vwserver.admin.ui.modal.ModalCallbacks.NewDisplayCallback;
import com.atanor.vwserver.admin.ui.modal.presenter.NewDisplayPresenter;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.events.ItemChangedEvent;
import com.smartgwt.client.widgets.form.events.ItemChangedHandler;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;

public class NewDisplayWindow extends ModalWindow implements NewDisplayCallback {

	@Inject
	private NewDisplayPresenter presenter;

	private TextItem name;
	private IntegerItem segmentNumHeight;
	private IntegerItem segmentNumWidth;
	private IntegerItem segmentHeight;
	private IntegerItem segmentWidth;

	public NewDisplayWindow() {
		super("New Display");
	}

	@Override
	protected DynamicForm createForm() {
		final DynamicForm form = new DynamicForm();
		form.setHeight100();
		form.setWidth100();
		form.setPadding(5);
		form.setNumCols(2);
		form.setColWidths("70%", "*");
		form.setLayoutAlign(VerticalAlignment.BOTTOM);

		name = new TextItem();
		name.setAttribute(Utils.ITEM_SKIPVALIDATION, true);
		name.setTitle("Name");
		name.setCanEdit(false);

		segmentNumHeight = createModalIntegerItem("Segments (height)");
		segmentNumWidth = createModalIntegerItem("Segments (width)");
		segmentHeight = createModalIntegerItem("Segment height, px");
		segmentWidth = createModalIntegerItem("Segment width, px");

		form.setFields(name, segmentNumHeight, segmentNumWidth, segmentHeight, segmentWidth);

		return form;
	}

	@Override
	protected HLayout createControlLayout() {
		final HLayout layout = createModalControlsLayout();

		final IButton cancelButton = createModalButton("Cancel");
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				destroy();
			}
		});

		final IButton createButton = createModalButton("Create");
		createButton.setDisabled(true);
		createButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				presenter.createDisplay(buildDisplay(), getCallback());
			}
		});

		getForm().addItemChangedHandler(new ItemChangedHandler() {

			@Override
			public void onItemChanged(ItemChangedEvent event) {
				if (isAllValuesValid(getForm())) {
					createButton.enable();
					setDiplayName();
				} else {
					createButton.disable();
					cleanDiplayName();
				}
			}
		});

		layout.addMembers(cancelButton, createButton);
		return layout;
	}

	private void setDiplayName() {
		final String segNumHeight = segmentNumHeight.getValueAsString();
		final String segNumWidth = segmentNumWidth.getValueAsString();
		final String segHeight = segmentHeight.getValueAsString();
		final String segWidth = segmentWidth.getValueAsString();
		name.setValue(generateDisplayName(segNumHeight, segNumWidth, segHeight, segWidth));
	}

	private void cleanDiplayName() {
		name.clearValue();
	}

	private static String generateDisplayName(final String segNumHeight, final String segNumWidth,
			final String segmHeight, final String segWidth) {
		return "H" + segNumHeight + "xW" + segNumWidth + "_" + segmHeight + "x" + segWidth;
	}

	private DisplayDto buildDisplay() {
		final DisplayDto display = new DisplayDto();
		display.setName(name.getValueAsString());
		display.setSegmentHeight(Integer.parseInt(segmentHeight.getValueAsString()));
		display.setSegmentWidth(Integer.parseInt(segmentWidth.getValueAsString()));
		display.setSegmentNumHeight(Integer.parseInt(segmentNumHeight.getValueAsString()));
		display.setSegmentNumWidth(Integer.parseInt(segmentNumWidth.getValueAsString()));
		return display;
	}

	private NewDisplayCallback getCallback() {
		return this;
	}

	@Override
	public void onDisplayCreated() {
		destroy();
	}

}
