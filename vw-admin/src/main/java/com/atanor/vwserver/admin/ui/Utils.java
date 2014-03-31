package com.atanor.vwserver.admin.ui;

import com.google.gwt.user.client.Window;

public abstract class Utils {

	public static Integer NAVIGATION_DISPLAY_HEIGHT = 40;
	public static Integer HEADER_DISPLAY_HEIGHT = 40;
	public static Integer PREVIEW_DISPLAY_WIDTH = 220;
	public static Integer NAVIGATE_ICON_SIZE = 30;

	public static Integer MODAL_WINDOW_WIDTH = 320;
	
	// 10 - padding, 16 - slider width 
	public static Integer PREVIEW_AREA_ELEMENT_WIDTH = PREVIEW_DISPLAY_WIDTH - 2 * 10 - 16;
	public static Integer PREVIEW_AREA_ELEMENT_HEIGHT = 100;

	public static final String ITEM_SKIPVALIDATION = "itemskipvalidation";

	public static String toToken(final Long id) {
		return id == null ? "" : String.valueOf(id);
	}

	public static Long fromToken(String token) {
		return token == null || token.isEmpty() ? null : Long.parseLong(token);
	}

	public static Integer getMainAreaHeight() {
		return Window.getClientHeight() - Utils.NAVIGATION_DISPLAY_HEIGHT - Utils.HEADER_DISPLAY_HEIGHT;
	}

	public static Integer getEditAreaWidth() {
		return Window.getClientWidth() - Utils.PREVIEW_DISPLAY_WIDTH;
	}

}
