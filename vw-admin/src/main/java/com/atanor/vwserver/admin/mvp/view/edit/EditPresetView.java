package com.atanor.vwserver.admin.mvp.view.edit;

import com.atanor.vwserver.admin.ui.Utils;
import com.atanor.vwserver.common.AppConstants;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.google.common.primitives.Ints;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;

public class EditPresetView extends Canvas {

	private final Canvas display;

	public EditPresetView() {
		setHeight(Utils.getMainAreaHeight());
		setWidth(Utils.getEditAreaWidth());

		display = createDisplay();
		alignInDesktop(display);

		addChild(display);
	}

	public void clean(){
		display.setBackgroundColor("white");
	}
	
	public void onNewPreset(final LayoutDto layoutDto, final DisplayDto displayDto) {
		display.setBackgroundColor("grey");
	}

	public void setPreset(final PresetDto preset) {

	}
	
	private Canvas createDisplay() {
		final Canvas canvas = new Canvas();
		final Long displayWidth = Math.round(getWidth() * 0.7);
		final Long displayHeight = Math.round(AppConstants.FULL_HD_SCALE_FACTOR * displayWidth);
		canvas.setWidth(displayWidth.intValue());
		canvas.setHeight(displayHeight.intValue());
		canvas.setOverflow(Overflow.HIDDEN);
		return canvas;
	}

	private void alignInDesktop(final Canvas canvas) {
		final Long leftOffset = Math.round((getWidth() - canvas.getWidth()) / 2d);
		final Long topOffset = Math.round((getHeight() - canvas.getHeight()) / 2d);
		canvas.setLeft(Ints.checkedCast(leftOffset));
		canvas.setTop(Ints.checkedCast(topOffset));
	}

}
