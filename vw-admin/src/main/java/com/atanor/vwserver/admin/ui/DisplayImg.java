package com.atanor.vwserver.admin.ui;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class DisplayImg extends Img {

	private static final String SELECTED_BORDER = "5px solid yellow";
	private static final String DEFAULT_BORDER = "5px solid white";
	
	final DisplayDto display;

	public DisplayImg(final DisplayDto display) {
		this.display = display;
		setBorder(DEFAULT_BORDER);
		
		final String source = "data:image/png;base64," + display.getImage();
		setSrc(source);

		addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Client.goTo(new DisplayPlace(display.getId()));
			}
		});
	}

	public void select() {
		setBorder(SELECTED_BORDER);  
	}

	public void unselect() {
		setBorder(DEFAULT_BORDER); 
	}
}
