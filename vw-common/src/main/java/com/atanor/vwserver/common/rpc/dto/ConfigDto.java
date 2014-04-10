package com.atanor.vwserver.common.rpc.dto;

import java.util.List;

@SuppressWarnings("serial")
public class ConfigDto extends AbstractDto {

	private List<DisplayDto> displays;
	private List<LayoutDto> layouts;
	private List<SourceDto> sources;
	
	public List<DisplayDto> getDisplays() {
		return displays;
	}

	public void setDisplays(final List<DisplayDto> displays) {
		this.displays = displays;
	}

	public List<LayoutDto> getLayouts() {
		return layouts;
	}

	public void setLayouts(final List<LayoutDto> layouts) {
		this.layouts = layouts;
	}

	
	public List<SourceDto> getSources() {
		return sources;
	}

	public void setSources(final List<SourceDto> sources) {
		this.sources = sources;
	}

	@Override
	public Long getId() {
		throw new IllegalStateException("Operation is not implemented");
	}

}
