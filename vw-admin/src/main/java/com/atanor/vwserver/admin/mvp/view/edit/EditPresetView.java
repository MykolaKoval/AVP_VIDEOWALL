package com.atanor.vwserver.admin.mvp.view.edit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.event.ActivateGridEvent;
import com.atanor.vwserver.admin.mvp.event.CleanGridActivationEvent;
import com.atanor.vwserver.admin.ui.GridLabel;
import com.atanor.vwserver.admin.ui.Utils;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.widgets.Canvas;

public class EditPresetView extends AbstractDisplayView {

	@Inject
	private EventBus eventBus;
	
	private final Map<Integer, List<GridLabel>> rowGridPanels = Maps.newLinkedHashMap();
	private final Map<Integer, List<GridLabel>> columnGridPanels = Maps.newLinkedHashMap();
	
	private Canvas display;
	
	public EditPresetView() {
		setHeight(Utils.getMainAreaHeight());
		setWidth(Utils.getEditAreaWidth());
	}

	public void clean() {
		if (display != null) {
			removeChild(display);
			display = null;
		}
	}

	public void onNewPreset(final LayoutDto layoutDto, final DisplayDto displayDto) {
		Preconditions.checkNotNull(displayDto, "Display dto can not be null");
		
		clean();
		display = createAndAdjustDisplay(displayDto);
		addChild(display);
		
		createDisplayGrid(displayDto);
	}

	private void createDisplayGrid(final DisplayDto dto) {
		final Long panelWidth = Math.round(Double.valueOf(display.getWidth()) / dto.getSegmentNumWidth());
		final Long panelHeight = Math.round(Double.valueOf(display.getHeight()) / dto.getSegmentNumHeight());

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

}
