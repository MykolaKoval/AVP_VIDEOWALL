package com.atanor.vwserver.admin.ui;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.common.AppUtils;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.google.common.primitives.Ints;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class DisplayImg extends HLayout {

	private static final String SELECTED_BORDER = "5px solid yellow";
	private static final String DEFAULT_BORDER = "5px solid lightgrey";

	private final Img img;
	private final DisplayDto dto;

	public DisplayImg(final DisplayDto dto) {
		this.dto = dto;

		setHeight(30);
		setAlign(Alignment.CENTER);

		img = new Img();
		final String source = "data:image/png;base64," + dto.getImage();
		img.setSrc(source);
		img.setBorder(DEFAULT_BORDER);

		fitImageSize(img, dto);
		addMember(img);

		addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Client.goTo(new DisplayPlace(dto.getId()));
			}
		});
	}

	public void select() {
		img.setBorder(SELECTED_BORDER);
	}

	public void unselect() {
		img.setBorder(DEFAULT_BORDER);
	}

	public boolean selected() {
		return img.getBorder().equals(SELECTED_BORDER);
	}

	private void fitImageSize(final Img img, final DisplayDto display) {
		Integer displayWidth = AppUtils.getDisplayWidth(display);
		Integer displayHeight = AppUtils.getDisplayHeight(display);

		if (displayWidth > displayHeight) {
			final Double scaleFactor = Utils.PREVIEW_AREA_ELEMENT_WIDTH / displayWidth.doubleValue();
			displayWidth = Utils.PREVIEW_AREA_ELEMENT_WIDTH;
			displayHeight = Ints.checkedCast(Math.round(scaleFactor * displayHeight.doubleValue()));
		} else {
			final Double scaleFactor = Utils.PREVIEW_AREA_ELEMENT_HEIGHT / displayHeight.doubleValue();
			displayHeight = Utils.PREVIEW_AREA_ELEMENT_HEIGHT;
			displayWidth = Ints.checkedCast(Math.round(scaleFactor * displayWidth.doubleValue()));
		}
		img.setWidth(displayWidth);
		img.setHeight(displayHeight);
	}

	public DisplayDto getDto() {
		return dto;
	}

}
