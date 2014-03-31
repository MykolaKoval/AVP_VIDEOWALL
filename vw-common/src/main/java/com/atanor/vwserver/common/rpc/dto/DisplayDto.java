package com.atanor.vwserver.common.rpc.dto;

@SuppressWarnings("serial")
public class DisplayDto extends AbstractDto {

	private Long id;

	private String name;
	private Integer segmentNumHeight;
	private Integer segmentNumWidth;
	private String resolution;
	private String orientation;
	private String image;

	public DisplayDto() {
	}

	public DisplayDto(final Long id) {
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

	public Integer getSegmentNumHeight() {
		return segmentNumHeight;
	}

	public void setSegmentNumHeight(final Integer segmentNumHeight) {
		this.segmentNumHeight = segmentNumHeight;
	}

	public Integer getSegmentNumWidth() {
		return segmentNumWidth;
	}

	public void setSegmentNumWidth(final Integer segmentNumWidth) {
		this.segmentNumWidth = segmentNumWidth;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(final String resolution) {
		this.resolution = resolution;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(final String orientation) {
		this.orientation = orientation;
	}

	public String getImage() {
		return image;
	}

	public void setImage(final String image) {
		this.image = image;
	}

}
