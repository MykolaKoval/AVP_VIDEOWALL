package com.atanor.vwserver.admin.mvp.view.edit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.event.ActivateGridEvent;
import com.atanor.vwserver.admin.mvp.event.CleanGridActivationEvent;
import com.atanor.vwserver.admin.ui.GridLabel;
import com.atanor.vwserver.admin.ui.Utils;
import com.atanor.vwserver.admin.ui.layout.LayoutWindow;
import com.atanor.vwserver.admin.ui.layout.LayoutWindowChanged;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.atanor.vwserver.common.rpc.dto.LayoutWindowDto;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.widgets.Canvas;

public class EditPresetView extends AbstractDisplayView implements LayoutWindowChanged {

	@Inject
	private EventBus eventBus;

	private final Map<Integer, List<GridLabel>> rowGridPanels = Maps.newLinkedHashMap();
	private final Map<Integer, List<GridLabel>> columnGridPanels = Maps.newLinkedHashMap();

	private Canvas display;
	private Canvas editor;

	public EditPresetView() {
		setHeight(Utils.getMainAreaHeight());
		setWidth(Utils.getEditAreaWidth());
	}

	public void clean() {
		if (display != null) {
			removeChild(display);
			display = null;
		}
		if (editor != null) {
			removeChild(editor);
			editor = null;
		}
	}

	public void onNewPreset(final LayoutDto layoutDto, final DisplayDto displayDto) {
		Preconditions.checkNotNull(displayDto, "Display dto can not be null");

		clean();

		display = createAndAdjustDisplay(displayDto);
		addChild(display);
		createDisplayGrid(displayDto, display.getWidth(), display.getHeight());

		editor = createEditor(display);
		addChild(editor);
		createLayoutWindows(layoutDto);
	}

	private Canvas createEditor(final Canvas canvas) {
		final Canvas editor = new Canvas();
		editor.setWidth(canvas.getWidth());
		editor.setHeight(canvas.getHeight());
		editor.setLeft(canvas.getLeft());
		editor.setTop(canvas.getTop());
		return editor;
	}

	private void createDisplayGrid(final DisplayDto dto, final Integer displayWidth, final Integer displayHeight) {
		final Long panelWidth = Math.round(Double.valueOf(displayWidth) / dto.getSegmentNumWidth());
		final Long panelHeight = Math.round(Double.valueOf(displayHeight) / dto.getSegmentNumHeight());

		createGridPanels(dto, panelWidth, panelHeight);
	}

	private void createGridPanels(final DisplayDto dto, Long panelWidth, Long panelHeight) {
		for (int row = 0, top = 0, left = 0; row < dto.getSegmentNumHeight(); row++) {
			for (int col = 0; col < dto.getSegmentNumWidth(); col++) {
				createGridPanel(row, col, top, left, panelWidth, panelHeight);
				left += panelWidth;
			}
			left = 0;
			top += panelHeight;
		}
	}

	private void createGridPanel(int row, int col, int top, int left, Long panelWidth, Long panelHeight) {
		final GridLabel panel = new GridLabel(row, col);
		addRowGridPanel(row, panel);
		addColumnGridPanel(col, panel);

		panel.setTop(top);
		panel.setLeft(left);
		panel.setWidth(Ints.checkedCast(panelWidth));
		panel.setHeight(Ints.checkedCast(panelHeight));
		panel.init();
		panel.setBorder("1px inset black");

		eventBus.addHandler(ActivateGridEvent.getType(), panel);
		eventBus.addHandler(CleanGridActivationEvent.getType(), panel);

		display.addChild(panel);
	}

	private void createLayoutWindows(final LayoutDto layoutDto) {
		if (layoutDto != null) {
			for (final LayoutWindowDto windowDto : layoutDto.getWindows()) {
				final LayoutWindowDto dto = toRealDimensions(windowDto);
				dto.setName(windowDto.getName());				
				createLayoutWindow(dto);
			}
		}
	}

	private void createLayoutWindow(final LayoutWindowDto windowDto) {
		final LayoutWindow win = new LayoutWindow(windowDto, this);
		editor.addChild(win);
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
	
	private static Integer toRealValue(final Integer percent, final Integer base) {
		return base * percent / 100;
	}
	
	@Override
	protected Canvas createDisplay(final DisplayDto display) {
		final Canvas canvas = new Canvas();
		canvas.setBackgroundColor("grey");
		return canvas;
	}

	public void setPreset(final PresetDto preset) {
	}

	private void addRowGridPanel(final Integer row, final GridLabel panel) {
		if (rowGridPanels.get(row) == null) {
			rowGridPanels.put(row, new ArrayList<GridLabel>());
		}
		rowGridPanels.get(row).add(panel);
	}

	private void addColumnGridPanel(final Integer col, final GridLabel panel) {
		if (columnGridPanels.get(col) == null) {
			columnGridPanels.put(col, new ArrayList<GridLabel>());
		}
		columnGridPanels.get(col).add(panel);
	}

	@Override
	public void onLayoutWindowSelected(final LayoutWindow window) {
		// TODO Auto-generated method stub

	}

}
