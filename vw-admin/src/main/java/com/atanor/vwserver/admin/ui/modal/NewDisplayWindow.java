package com.atanor.vwserver.admin.ui.modal;

import java.util.LinkedHashMap;

import javax.inject.Inject;

import com.atanor.vwserver.admin.ui.modal.ModalCallbacks.NewDisplayCallback;
import com.atanor.vwserver.admin.ui.modal.presenter.NewDisplayPresenter;
import com.atanor.vwserver.common.AppUtils;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.google.common.collect.Maps;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.events.ItemChangedEvent;
import com.smartgwt.client.widgets.form.events.ItemChangedHandler;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;

public class NewDisplayWindow extends ModalWindow implements NewDisplayCallback {

	private static final String[] RESOLUTIONS = { "1920 x 1080", "1600 x 900", "1366 x 768", "1280 x 720",
			"1024 x 576", "960 x 540", "854 x 480", "640 x 360" };

	private static final String[] ORIENTATIONS = { "Landscape", "Portrait" };

	@Inject
	private NewDisplayPresenter presenter;

	private TextItem name;
	private IntegerItem segmentNumHeight;
	private IntegerItem segmentNumWidth;
	private SelectItem resolution;
	private SelectItem orientation;

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

		name = createModalTextItem("Name");
		name.setCanEdit(false);

		segmentNumHeight = createModalIntegerItem("Segments (height)");
		segmentNumWidth = createModalIntegerItem("Segments (width)");

		resolution = createModalSelectItem("Resolution");
		
		final LinkedHashMap<String, String> resolutions = Maps.newLinkedHashMap();
		resolutions.put("1920x1080", "1920 x 1080");
		resolutions.put("1600x900", "1600 x 900");
		resolutions.put("1366x768", "1366 x 768");
		resolutions.put("1280x720", "1280 x 720");
		resolutions.put("1024x576", "1024 x 576");
		resolutions.put("960x540", "960 x 540");
		resolutions.put("854x480", "854 x 480");
		resolutions.put("640x360", "640 x 360");
		
		resolution.setValueMap(resolutions);
		
		orientation = createModalSelectItem("Orientation");
		orientation.setValueMap(ORIENTATIONS);

		form.setFields(name, segmentNumHeight, segmentNumWidth, resolution, orientation);

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
		final String res = AppUtils.removeSpaces(resolution.getValueAsString());
		final String orient = orientation.getValueAsString();
		name.setValue(generateDisplayName(segNumHeight, segNumWidth, res, orient));
	}

	private void cleanDiplayName() {
		name.clearValue();
	}

	private static String generateDisplayName(final String segNumHeight, final String segNumWidth,
			final String resolution, final String orientation) {
		return "H" + segNumHeight + "xW" + segNumWidth + "_" + resolution + "_" + orientation;
	}

	private DisplayDto buildDisplay() {
		final DisplayDto display = new DisplayDto();
		display.setName(name.getValueAsString());
		display.setOrientation(orientation.getValueAsString());
		display.setResolution(resolution.getValueAsString());
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
