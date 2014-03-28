package com.atanor.vwserver.admin.mvp.view.edit;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.event.LayoutWindowChangedEvent;
import com.atanor.vwserver.admin.mvp.event.WindowAction;
import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.Utils;
import com.atanor.vwserver.admin.ui.layout.LayoutWindow;
import com.atanor.vwserver.admin.ui.layout.LayoutWindowChanged;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.atanor.vwserver.common.rpc.dto.LayoutWindowDto;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;

public class EditLayoutView extends Canvas implements HeaderView, LayoutWindowChanged {

	@Inject
	private EventBus eventBus;

	private final Canvas display;
	private final List<LayoutWindow> windows = Lists.newArrayList();

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
		for (LayoutWindow window : windows) {
			display.removeChild(window);
		}
		windows.clear();
	}

	public void setLayout(final LayoutDto layout) {

	}

	public void addNewWindow(final LayoutWindowDto dto) {
		Preconditions.checkNotNull(dto, "window dto can not be null");
		dto.setName(generateWinName(windows.size()));

		fetchSize(dto);

		final LayoutWindow win = new LayoutWindow(dto, this);
		win.setKeepInParentRect(new Rectangle(0, 0, display.getWidth(), display.getHeight()));

		onLayoutWindowSelected(win);

		windows.add(win);
		display.addChild(win);

		eventBus.fireEvent(new LayoutWindowChangedEvent(WindowAction.CREATED, dto));
	}

	private void fetchSize(final LayoutWindowDto dto) {
		dto.setLeft(display.getWidth() * dto.getLeft() / 100);
		dto.setTop(display.getHeight() * dto.getTop() / 100);
		dto.setHeight(display.getHeight() * dto.getHeight() / 100);
		dto.setWidth(display.getWidth() * dto.getWidth() / 100);
	}

	public void removeAnySelectedWindow() {
		for (Iterator<LayoutWindow> it = windows.iterator(); it.hasNext();) {
			LayoutWindow window = (LayoutWindow) it.next();
			if (window.selected()) {
				display.removeChild(window);
				it.remove();
				window.destroy();
			}
		}
		renameWindows();
		if (windows.size() > 0) {
			windows.get(0).select();
		}

		final WindowAction action = windows.isEmpty() ? WindowAction.REMOVED_LAST : WindowAction.REMOVED;
		eventBus.fireEvent(new LayoutWindowChangedEvent(action));
	}

	private void renameWindows() {
		final List<LayoutWindow> result = Lists.newArrayList(windows);
		for (int i = 0; i < result.size(); i++) {
			result.get(i).setName(generateWinName(i));
		}
		windows.clear();
		windows.addAll(result);
	}

	private String generateWinName(int winCount) {
		return "Window " + (++winCount);
	}

	@Override
	public void onLayoutWindowSelected(final LayoutWindow window) {
		for (LayoutWindow win : windows) {
			if (win.getName().equals(window.getName())) {
				continue;
			}
			win.unselect();
		}
	}
}
