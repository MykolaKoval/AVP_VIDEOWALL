package com.atanor.vwserver.admin.ui;

public abstract class Utils {

	public static Integer NAVIGATION_DISPLAY_HEIGHT = 40;
	public static Integer HEADER_DISPLAY_HEIGHT = 40;
	public static Integer PREVIEW_DISPLAY_WIDTH = 200;
	public static Integer NAVIGATE_ICON_SIZE = 30;
	
	public static Integer MODAL_WINDOW_WIDTH = 320;
	
	public static final String ITEM_SKIPVALIDATION = "itemskipvalidation";
	
	public static String toToken(final Long id) {
		return id == null ? "" : String.valueOf(id);
	}

	public static Long fromToken(String token) {
		return token == null || token.isEmpty() ? null : Long.parseLong(token);
	}
}
