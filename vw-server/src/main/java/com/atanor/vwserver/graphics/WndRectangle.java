package com.atanor.vwserver.graphics;

import java.awt.Rectangle;

@SuppressWarnings("serial")
public class WndRectangle extends Rectangle {

	private String text;

	public WndRectangle(String text, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public Integer getTextX() {
		return new java.lang.Double(getX() + getWidth() / 2).intValue();
	}

	public Integer getTextY() {
		return new java.lang.Double(getY() + getHeight() / 2).intValue();
	}

}
