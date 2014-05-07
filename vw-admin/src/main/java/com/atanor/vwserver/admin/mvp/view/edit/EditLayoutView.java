package com.atanor.vwserver.admin.mvp.view.edit;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.event.layout.LayoutWindowChangedEvent;
import com.atanor.vwserver.admin.mvp.event.layout.WindowAction;
import com.atanor.vwserver.admin.ui.Utils;
import com.atanor.vwserver.admin.ui.layout.LayoutWindow;
import com.atanor.vwserver.admin.ui.layout.LayoutWindowChanged;
import com.atanor.vwserver.common.AppConstants;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.atanor.vwserver.common.rpc.dto.LayoutWindowDto;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;

public class EditLayoutView extends Canvas implements LayoutWindowChanged {

	@Inject
	private EventBus eventBus;

	private final Canvas display;
	private final List<LayoutWindow> windows = Lists.newArrayList();

	private Img layoutImg;

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
		final Long displayHeight = Math.round(AppConstants.FULL_HD_SCALE_FACTOR * displayWidth);
		canvas.setWidth(displayWidth.intValue());
		canvas.setHeight(displayHeight.intValue());
		canvas.setOverflow(Overflow.HIDDEN);
		return canvas;
	}

	private Img createLayout(final LayoutDto layout) {
		final Img img = new Img();
		img.setHeight100();
		img.setWidth100();
		final String source = "data:image/png;base64," + layout.getImage();
		img.setSrc(source);
		return img;
	}

	private void alignInDesktop(final Canvas canvas) {
		final Long leftOffset = Math.round((getWidth() - canvas.getWidth()) / 2d);
		final Long topOffset = Math.round((getHeight() - canvas.getHeight()) / 2d);
		canvas.setLeft(Ints.checkedCast(leftOffset));
		canvas.setTop(Ints.checkedCast(topOffset));
	}

	public void clean() {
		display.setBackgroundColor("white");
		for (LayoutWindow window : windows) {
			display.removeChild(window);
		}
		windows.clear();

		if (layoutImg != null) {
			removeChild(layoutImg);
			layoutImg = null;
		}
	}

	public void setLayout(final LayoutDto layout) {
		Preconditions.checkNotNull(display, "Display can not be null");

		clean();

		layoutImg = createLayout(layout);
		layoutImg.setLeft(display.getLeft());
		layoutImg.setTop(display.getTop());
		layoutImg.setWidth(display.getWidth());
		layoutImg.setHeight(display.getHeight());

		addChild(layoutImg);
	}

	public void addNewWindow(final LayoutWindowDto dto) {
		Preconditions.checkNotNull(dto, "window dto can not be null");

		final LayoutWindowDto appliedDto = toRealDimensions(dto);
		appliedDto.setName(generateWinName(windows.size()));

		final LayoutWindow win = new LayoutWindow(appliedDto, this);
		win.setKeepInParentRect(new Rectangle(0, 0, display.getWidth(), display.getHeight()));

		onLayoutWindowSelected(win);

		windows.add(win);
		display.addChild(win);

		eventBus.fireEvent(new LayoutWindowChangedEvent(WindowAction.CREATED, appliedDto));
	}

	private LayoutWindowDto toRealDimensions(final LayoutWindowDto dto) {
		final LayoutWindowDto result = new LayoutWindowDto();
		result.setName(dto.getName());
		result.setLeft(toRealValue(dto.getLeft(), display.getWidth()));
		result.setTop(toRealValue(dto.getTop(), display.getHeight()));
		result.setHeight(toRealValue(dto.getHeight(), display.getHeight()));
		result.setWidth(toRealValue(dto.getWidth(), display.getWidth()));
		return result;
	}

	private LayoutWindowDto toPercentDimensions(final LayoutWindowDto dto) {
		final LayoutWindowDto result = new LayoutWindowDto();
		result.setName(dto.getName());
		result.setLeft(toPercentValue(dto.getLeft(), display.getWidth()));
		result.setTop(toPercentValue(dto.getTop(), display.getHeight()));
		result.setHeight(toPercentValue(dto.getHeight(), display.getHeight()));
		result.setWidth(toPercentValue(dto.getWidth(), display.getWidth()));
		return result;
	}

	private static Integer toRealValue(final Integer percent, final Integer base) {
		return base * percent / 100;
	}

	private static Integer toPercentValue(final Integer value, final Integer base) {
		return Math.round(value.floatValue() / base.floatValue() * 100);
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
		return String.valueOf(++winCount);
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

	public List<LayoutWindowDto> getLayoutWindows() {
		final List<LayoutWindowDto> result = Lists.newArrayList();
		for (final LayoutWindow window : windows) {
			window.updateDto();
			result.add(toPercentDimensions(window.getDto()));
		}
		return result;
	}

	public void onNewLayout() {
		display.setBackgroundColor("grey");
	}
}
