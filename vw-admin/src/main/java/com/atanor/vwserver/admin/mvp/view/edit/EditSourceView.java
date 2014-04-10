package com.atanor.vwserver.admin.mvp.view.edit;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.event.SourceAction;
import com.atanor.vwserver.admin.mvp.event.SourceChangedEvent;
import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.Utils;
import com.atanor.vwserver.common.rpc.dto.SourceDto;
import com.google.common.collect.Lists;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EditSourceView extends HLayout implements HeaderView {

	private static final String DTO_GRID_ATTR = "dto";
	private static final String SOURCE_ID_GRID_ATTR = "sourceCode";
	private static final String SOURCE_DESCRIPTION_GRID_ATTR = "sourceDescription";

	@Inject
	private EventBus eventBus;
	
	private final ListGrid listGrid;

	public EditSourceView() {
		setHeight(Utils.getMainAreaHeight());
		setWidth(Utils.getEditAreaWidth());
		setBackgroundColor("lightgrey");

		final VLayout spacer = new VLayout();
		spacer.setWidth(Utils.PREVIEW_DISPLAY_WIDTH);

		final VLayout layout = new VLayout();
		layout.setWidth100();
		layout.setHeight(Utils.getMainAreaHeight());

		listGrid = new ListGrid();
		listGrid.setWidth100();
		listGrid.setHeight(Utils.getMainAreaHeight() - 100);
		listGrid.setCanHover(true);
		listGrid.setShowHover(true);
		listGrid.setShowHoverComponents(true);
		listGrid.setShowRowNumbers(true);
		listGrid.setSelectionType(SelectionStyle.SIMPLE);
		listGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		listGrid.addSelectionChangedHandler(new SelectionChangedHandler() {

			@Override
			public void onSelectionChanged(SelectionEvent event) {
				if (isAnyRecordSelected(listGrid)) {
					eventBus.fireEvent(new SourceChangedEvent(SourceAction.SELECTED));
				} else {
					eventBus.fireEvent(new SourceChangedEvent(SourceAction.UNSELECTED));
				}
			}
		});
		final ListGridField id = new ListGridField(SOURCE_ID_GRID_ATTR, "Source ID");
		final ListGridField description = new ListGridField(SOURCE_DESCRIPTION_GRID_ATTR, "Source Description");
		listGrid.setFields(id, description);

		layout.addMembers(new LayoutSpacer(), listGrid, new LayoutSpacer());

		addMembers(layout, spacer);
	}

	@Override
	public void clean() {

	}

	public void setSources(final Collection<SourceDto> sources) {
		final List<ListGridRecord> records = createGridRecords(sources);
		listGrid.setData(records.toArray(new ListGridRecord[] {}));
	}

	private List<ListGridRecord> createGridRecords(final Collection<SourceDto> sources) {
		final List<ListGridRecord> records = Lists.newArrayList();
		for (SourceDto dto : sources) {
			ListGridRecord record = new ListGridRecord();
			record.setAttribute(DTO_GRID_ATTR, dto);
			record.setAttribute(SOURCE_ID_GRID_ATTR, dto.getCode());
			record.setAttribute(SOURCE_DESCRIPTION_GRID_ATTR, dto.getDescription());

			records.add(record);
		}
		return records;
	}

	private boolean isAnyRecordSelected(final ListGrid listGrid) {
		for (final ListGridRecord record : listGrid.getRecords()) {
			if (listGrid.isSelected(record)) {
				return true;
			}
		}
		return false;
	}
	
	public List<SourceDto> getSelectedSources() {
		final List<SourceDto> result = Lists.newArrayList();
		for (final ListGridRecord record : listGrid.getRecords()) {
			if (listGrid.isSelected(record)) {
				result.add((SourceDto) record.getAttributeAsObject(DTO_GRID_ATTR));
			}
		}
		return result;
	}
}
