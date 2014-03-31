package com.atanor.vwserver.common;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

public class AppUtils {

	private static final String RESOLUTION_DELIMITER = "x";

	public static boolean isLandscape(final String orientation) {
		return "Landscape".equals(orientation);
	}

	public static String removeSpaces(final String line) {
		return line != null ? CharMatcher.WHITESPACE.removeFrom(line) : line;
	}

	public static Integer getPanelWidth(boolean isLandscape, String resolution) {
		final Iterable<String> it = Splitter.on(RESOLUTION_DELIMITER).split(resolution);
		final Integer width = Integer.parseInt(Iterables.getFirst(it, "0"));
		final Integer height = Integer.parseInt(Iterables.getLast(it));
		return isLandscape ? width : height;
	}

	public static Integer getPanelHeight(boolean isLandscape, String resolution) {
		final Iterable<String> it = Splitter.on(RESOLUTION_DELIMITER).split(resolution);
		final Integer width = Integer.parseInt(Iterables.getFirst(it, "0"));
		final Integer height = Integer.parseInt(Iterables.getLast(it));
		return isLandscape ? height : width;
	}
}
