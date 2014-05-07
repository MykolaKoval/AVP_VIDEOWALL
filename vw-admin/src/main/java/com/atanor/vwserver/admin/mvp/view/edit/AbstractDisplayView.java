package com.atanor.vwserver.admin.mvp.view.edit;

import com.atanor.vwserver.common.AppUtils;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.google.common.primitives.Ints;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;

public abstract class AbstractDisplayView extends Canvas {

	protected Canvas createAndAdjustDisplay(final DisplayDto dto) {

		final Long displayWidth = new Long(AppUtils.getDisplayWidth(dto));
		final Long displayHeight = new Long(AppUtils.getDisplayHeight(dto));

		final Double padding = adjustPadding(displayWidth, displayHeight);
		final Long panelDisplayWidth = Math.round(getElement().getClientWidth() * padding);

		final Double scaleFactor = panelDisplayWidth.doubleValue() / displayWidth.doubleValue();
		final Long panelDisplayHeight = Math.round(scaleFactor * displayHeight.doubleValue());

		final Canvas display = createDisplay(dto);
		display.setWidth(Ints.checkedCast(panelDisplayWidth));
		display.setHeight(Ints.checkedCast(panelDisplayHeight));

		alignInDesktop(display);

		return display;
	}
	
	protected Canvas createDisplay(final DisplayDto display) {
		final Img img = new Img();
		img.setHeight100();
		img.setWidth100();
		final String source = "data:image/png;base64," + display.getImage();
		img.setSrc(source);
		return img;
	}

	private Double adjustPadding(final Long displayWidth, final Long displayHeight) {
		Double padding = 0.7d;

		while (Boolean.TRUE) {
			final Long panelDisplayWidth = Math.round(getElement().getClientWidth() * padding);
			final Double scaleFactor = panelDisplayWidth.doubleValue() / displayWidth.doubleValue();
			final Long panelDisplayHeight = Math.round(scaleFactor * displayHeight.doubleValue());

			if (panelDisplayWidth + 100 <= getElement().getClientWidth()
					&& panelDisplayHeight + 100 <= getElement().getClientHeight()) {
				return padding;
			}
			padding -= 0.01d;
		}

		return padding;
	}

	private void alignInDesktop(final Canvas canvas) {
		final Long leftOffset = Math.round((getElement().getClientWidth() - canvas.getWidth()) / 2d);
		final Long topOffset = Math.round((getElement().getClientHeight() - canvas.getHeight()) / 2d);
		canvas.setLeft(Ints.checkedCast(leftOffset));
		canvas.setTop(Ints.checkedCast(topOffset));
	}

}
