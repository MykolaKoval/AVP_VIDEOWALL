package com.atanor.vwserver.admin.ui;

public abstract class Utils {

	public static Integer HEADER_DISPLAY_HEIGHT = 60;
	public static Integer NAVIGATION_DISPLAY_WIDTH = 60;
	public static Integer PREVIEW_DISPLAY_WIDTH = 200;
	public static Integer NAVIGATION_PREVIEW_FULL_WIDTH = NAVIGATION_DISPLAY_WIDTH + PREVIEW_DISPLAY_WIDTH ;
	public static Integer NAVIGATE_ICON_SIZE = 45;

	public static String toToken(final Long id) {
		return id == null ? "" : String.valueOf(id);
	}

	public static Long fromToken(String token) {
		return token == null || token.isEmpty() ? null : Long.parseLong(token);
	}
}
