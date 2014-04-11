package com.atanor.vwserver.common.rpc.dto;

@SuppressWarnings("serial")
public class PresetWindowDto extends AbstractDto {

	private Long id;
	private String name;
	private Integer xTopLeft;
	private Integer yTopLeft;
	private Integer xBottomRight;
	private Integer yBottomRight;
	private Integer zIndex;
	private SourceDto source;
	
	// state attributes
	private Boolean modified = Boolean.FALSE;
	private Boolean selected = Boolean.FALSE;

	public PresetWindowDto() {
	}

	public PresetWindowDto(final Long id) {
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

	public Integer getXTopLeft() {
		return xTopLeft;
	}

	public void setXTopLeft(final Integer xTopLeft) {
		this.xTopLeft = xTopLeft;
	}

	public Integer getYTopLeft() {
		return yTopLeft;
	}

	public void setYTopLeft(final Integer yTopLeft) {
		this.yTopLeft = yTopLeft;
	}

	public Integer getXBottomRight() {
		return xBottomRight;
	}

	public void setXBottomRight(final Integer xBottomRight) {
		this.xBottomRight = xBottomRight;
	}

	public Integer getYBottomRight() {
		return yBottomRight;
	}

	public void setYBottomRight(final Integer yBottomRight) {
		this.yBottomRight = yBottomRight;
	}

	public Integer getZIndex() {
		return zIndex;
	}

	public void setZIndex(final Integer zIndex) {
		this.zIndex = zIndex;
	}

	public SourceDto getSource() {
		return source;
	}

	public void setSource(final SourceDto source) {
		this.source = source;
	}

	public Boolean isModified() {
		return modified;
	}

	public void setModified(final Boolean modified) {
		this.modified = modified;
	}

	public Boolean isSelected() {
		return selected;
	}

	public void setSelected(final Boolean selected) {
		this.selected = selected;
	}

}
