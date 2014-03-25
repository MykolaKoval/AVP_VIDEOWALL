package com.atanor.vwserver.common.rpc.dto;

@SuppressWarnings("serial")
public class LayoutWindowDto extends AbstractDto {

	private Long id;
	private String name;
	private Integer left;
	private Integer top;
	private Integer width;
	private Integer height;

	public LayoutWindowDto() {
	}

	public LayoutWindowDto(final Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Integer getLeft() {
		return left;
	}

	public void setLeft(final Integer left) {
		this.left = left;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(final Integer top) {
		this.top = top;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(final Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(final Integer height) {
		this.height = height;
	}

}
