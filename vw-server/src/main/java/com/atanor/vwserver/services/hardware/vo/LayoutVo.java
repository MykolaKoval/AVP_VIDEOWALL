package com.atanor.vwserver.services.hardware.vo;

import java.util.List;

public class LayoutVo {

	private String background;
	private List<WindowVo> windows;

	public String getBackground() {
		return background;
	}

	public void setBackground(final String background) {
		this.background = background;
	}

	public List<WindowVo> getWindows() {
		return windows;
	}

	public void setWindows(final List<WindowVo> windows) {
		this.windows = windows;
	}

}
