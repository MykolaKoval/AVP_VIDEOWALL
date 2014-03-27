package com.atanor.vwserver.admin.mvp.view.edit;

import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.Utils;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;

public class EditDisplayView extends Canvas implements HeaderView {

	private Img displayImg;

	public EditDisplayView() {
		setHeight(Utils.getMainAreaHeight());
		setWidth(Utils.getEditAreaWidth());
	}

	public void setDisplay(final DisplayDto display) {
		Preconditions.checkNotNull(display, "Display can not be null");

		clean();

		final Long displayWidth = new Long(display.getSegmentWidth() * display.getSegmentNumWidth());
		final Long displayHeight = new Long(display.getSegmentHeight() * display.getSegmentNumHeight());

		final Double padding = adjustPadding(displayWidth, displayHeight);
		final Long panelDisplayWidth = Math.round(getElement().getClientWidth() * padding);

		final Double scaleFactor = panelDisplayWidth.doubleValue() / displayWidth.doubleValue();
		final Long panelDisplayHeight = Math.round(scaleFactor * displayHeight.doubleValue());

		displayImg = createDisplay(display);

		displayImg.setWidth(Ints.checkedCast(panelDisplayWidth));
		displayImg.setHeight(Ints.checkedCast(panelDisplayHeight));
		alignInDesktop(displayImg);

		addChild(displayImg);
	}

	private Img createDisplay(final DisplayDto display) {
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

	@Override
	public void clean() {
		if (displayImg != null) {
			removeChild(displayImg);
			displayImg = null;
		}
	}
}
