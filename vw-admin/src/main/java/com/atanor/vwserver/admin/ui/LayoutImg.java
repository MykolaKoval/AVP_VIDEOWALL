package com.atanor.vwserver.admin.ui;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.common.AppConstants;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class LayoutImg extends HLayout {

	private static final String SELECTED_BORDER = "5px solid yellow";
	private static final String DEFAULT_BORDER = "5px solid lightgrey";

	private final Img img;
	private final LayoutDto dto;
	
	public LayoutImg(final LayoutDto dto) {
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
				Client.goTo(new LayoutPlace(dto.getId()));
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

	public LayoutDto getDto() {
		return dto;
	}

	private void fitImageSize(final Img img, final LayoutDto layout) {
		final Integer layoutWidth = Utils.PREVIEW_AREA_ELEMENT_WIDTH;
		final Integer layoutHeight = Math.round(AppConstants.FULL_HD_SCALE_FACTOR.floatValue() * layoutWidth);

		img.setWidth(layoutWidth);
		img.setHeight(layoutHeight);
	}
}
