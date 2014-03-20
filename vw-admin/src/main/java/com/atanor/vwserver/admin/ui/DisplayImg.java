package com.atanor.vwserver.admin.ui;

import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.smartgwt.client.widgets.Img;

public class DisplayImg extends Img {

	final DisplayDto display;
	
	public DisplayImg(final DisplayDto display){
		this.display = display;
		
		final String source = "data:image/png;base64," + display.getImage();
		setSrc(source);
	}
}
