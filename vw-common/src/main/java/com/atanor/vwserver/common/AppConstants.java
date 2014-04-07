package com.atanor.vwserver.common;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AppConstants implements Serializable {

	public static final String CONFIG_PATH = "config";
	public static final String DISPLAY_PATH = "display";
	public static final String LAYOUT_PATH = "layout";
	public static final String SOURCE_PATH = "source";
	
	public static final Integer FULL_HD_DISPLAY_WIDTH = 1920;
	public static final Integer FULL_HD_DISPLAY_HEIGHT = 1080;
	public static final Double FULL_HD_SCALE_FACTOR = FULL_HD_DISPLAY_HEIGHT.doubleValue()
			/ FULL_HD_DISPLAY_WIDTH.doubleValue();
}
