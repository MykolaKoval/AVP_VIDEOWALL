package com.atanor.vwserver.admin.mvp.view.edit;

import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.Utils;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EditSourceView extends HLayout implements HeaderView {

	private static final String SOURCE_ID_GRID_ATTR = "sourceCode";
	private static final String SOURCE_DESCRIPTION_GRID_ATTR = "sourceDescription";

	public EditSourceView() {
		setHeight(Utils.getMainAreaHeight());
		setWidth(Utils.getEditAreaWidth());
		setBackgroundColor("lightgrey");
		
		final VLayout spacer = new VLayout();
		spacer.setWidth(Utils.PREVIEW_DISPLAY_WIDTH);

		final VLayout layout = new VLayout();
		layout.setWidth100();
		layout.setHeight(Utils.getMainAreaHeight());

		final ListGrid listGrid = new ListGrid();
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

}
