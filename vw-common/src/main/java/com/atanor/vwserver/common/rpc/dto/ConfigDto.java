package com.atanor.vwserver.common.rpc.dto;

import java.util.List;

@SuppressWarnings("serial")
public class ConfigDto extends AbstractDto {

	private List<DisplayDto> displays;

	public List<DisplayDto> getDisplays() {
		return displays;
	}

	public void setDisplays(final List<DisplayDto> displays) {
		this.displays = displays;
	}
	
}
