package com.atanor.vwserver.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "displays")
@NamedQuery(name = "Display.GetAll", query = "SELECT d from Display d order by d.createTS desc")
public class Display extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", unique = true, length = 32)
	private String name;

	@Column(name = "create_ts")
	private Date createTS;
	
	@Column(name = "segment_num_height")
	private Integer segmentNumHeight;

	@Column(name = "segment_num_width")
	private Integer segmentNumWidth;

	@Column(name = "resolution")
	private String resolution;

	@Column(name = "orientation")
	private String orientation;

	@Lob
	@Column(name = "display_blob", length = 300000)
	private String imgBlob;
	
	public Display() {
	}

	public Display(final Long id) {
		this.id = id;
	}

	@Override
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

	public String getImgBlob() {
		return imgBlob;
	}

	public void setImgBlob(final String imgBlob) {
		this.imgBlob = imgBlob;
	}

	public Date getCreateTS() {
		return createTS;
	}

	public void setCreateTS(final Date createTS) {
		this.createTS = createTS;
	}
	
}
