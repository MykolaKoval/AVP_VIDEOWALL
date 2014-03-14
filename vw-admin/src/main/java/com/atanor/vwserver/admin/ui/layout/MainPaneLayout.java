package com.atanor.vwserver.admin.ui.layout;

import com.atanor.vwserver.admin.ui.DisplayPanel;
import com.atanor.vwserver.admin.ui.Utils;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;

public class MainPaneLayout extends DockLayoutPanel {

	private final DisplayPanel headerDisplay;
	private final DisplayPanel navigateDisplay;
	private final DisplayPanel previewDisplay;
	private final DisplayPanel editDisplay;

	public MainPaneLayout() {
		super(Unit.PX);

		// ============ Navigation ============
		{
			navigateDisplay = new DisplayPanel();
			addNorth(navigateDisplay, Utils.NAVIGATION_DISPLAY_HEIGHT);
		}

		// ============ Header ============
		{
			headerDisplay = new DisplayPanel();
			// headerDisplay.setStyleName("header");
			addNorth(headerDisplay, Utils.HEADER_DISPLAY_HEIGHT);
		}

		// ============ Preview ============
		{
			previewDisplay = new DisplayPanel();
			previewDisplay.setStyleName("previewer");
			addWest(previewDisplay, Utils.PREVIEW_DISPLAY_WIDTH);
		}

		// ============ Editor ============
		{
			editDisplay = new DisplayPanel();
			add(editDisplay);
		}
	}

	public DisplayPanel getNavigateDisplay() {
		return navigateDisplay;
	}

	public DisplayPanel getEditDisplay() {
		return editDisplay;
	}

	public DisplayPanel getHeaderDisplay() {
		return headerDisplay;
	}

	public DisplayPanel getPreviewDisplay() {
		return previewDisplay;
	}

	@Override
	public void onResize() {
		// do nothing since throws exception
	}

}