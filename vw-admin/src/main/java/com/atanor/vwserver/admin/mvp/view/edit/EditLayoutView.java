package com.atanor.vwserver.admin.mvp.view.edit;

import java.util.Map;

import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.Utils;
import com.atanor.vwserver.admin.ui.layout.LayoutWindow;
import com.atanor.vwserver.admin.ui.layout.LayoutWindowChanged;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;

public class EditLayoutView extends Canvas implements HeaderView, LayoutWindowChanged {

	private final Canvas display;
	private final Map<String, Canvas> windows = Maps.newHashMap();

	public EditLayoutView() {
		setHeight(Utils.getMainAreaHeight());
		setWidth(Utils.getEditAreaWidth());

		display = createDisplay();
		alignInDesktop(display);

		addChild(display);
	}

	private Canvas createDisplay() {
		final Canvas canvas = new Canvas();
		final Long displayWidth = Math.round(getWidth() * 0.7);
		final Long displayHeight = Math.round(getHeight() * 0.7);
		canvas.setWidth(displayWidth.intValue());
		canvas.setHeight(displayHeight.intValue());
		canvas.setBackgroundColor("grey");
		canvas.setOverflow(Overflow.HIDDEN);
		return canvas;
	}


	private void alignInDesktop(final Canvas canvas) {
		final Long leftOffset = Math.round((getWidth() - canvas.getWidth()) / 2d);
		final Long topOffset = Math.round((getHeight() - canvas.getHeight()) / 2d);
		canvas.setLeft(Ints.checkedCast(leftOffset));
		canvas.setTop(Ints.checkedCast(topOffset));
	}

	@Override
	public void clean() {
		for (Map.Entry<String, Canvas> entry : windows.entrySet()) {
			display.removeChild(entry.getValue());
		}
		windows.clear();
	}

	public void setLayout(final LayoutDto layout) {

	}

	public void addLayoutWindow() {
		final LayoutWindow win = new LayoutWindow(generateWinName(), this);
		win.setKeepInParentRect(new Rectangle(0, 0, display.getWidth(), display.getHeight()));
		onLayoutWindowSelected(win.getName());
		windows.put(win.getName(), win);
		display.addChild(win);
	}

	private String generateWinName() {
		return "Window " + (windows.size() + 1);
	}

	@Override
	public void onLayoutWindowSelected(final String name) {
		for (Map.Entry<String, Canvas> entry : windows.entrySet()) {
			if(entry.getKey().equals(name)){
				continue;
			}
			entry.getValue().setOpacity(50);
		}
	}
}
