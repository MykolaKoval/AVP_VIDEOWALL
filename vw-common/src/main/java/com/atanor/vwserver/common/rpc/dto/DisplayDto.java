package com.atanor.vwserver.common.rpc.dto;

@SuppressWarnings("serial")
public class DisplayDto extends AbstractDto {

	private Long id;

	private String name;
	private Integer segmentNumHeight;
	private Integer segmentNumWidth;
	private Integer segmentHeight;
	private Integer segmentWidth;
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

	public Integer getSegmentHeight() {
		return segmentHeight;
	}

	public void setSegmentHeight(final Integer segmentHeight) {
		this.segmentHeight = segmentHeight;
	}

	public Integer getSegmentWidth() {
		return segmentWidth;
	}

	public void setSegmentWidth(final Integer segmentWidth) {
		this.segmentWidth = segmentWidth;
	}

	public String getImage() {
		return image;
	}

	public void setImage(final String image) {
		this.image = image;
	}

}
