package com.atanor.vwserver.common.rpc.dto;

import java.util.List;

@SuppressWarnings("serial")
public class LayoutDto extends AbstractDto {

	private Long id;
	private String name;
	private String image;
	private List<LayoutWindowDto> windows;

	public LayoutDto() {
	}

	public LayoutDto(final Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getImage() {
		return image;
	}

	public void setImage(final String image) {
		this.image = image;
	}

	public List<LayoutWindowDto> getWindows() {
		return windows;
	}

	public void setWindows(final List<LayoutWindowDto> windows) {
		this.windows = windows;
	}

}
