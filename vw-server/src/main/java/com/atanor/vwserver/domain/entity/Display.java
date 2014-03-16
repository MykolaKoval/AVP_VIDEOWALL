package com.atanor.vwserver.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "displays")
@NamedQuery(name = "Display.GetAll", query = "SELECT d from Display d")
public class Display extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", unique = true, length = 32)
	private String name;

	@Column(name = "segment_num_height")
	private Integer segmentNumHeight;

	@Column(name = "segment_num_width")
	private Integer segmentNumWidth;

	@Column(name = "segment_height")
	private Integer segmentHeight;

	@Column(name = "segment_width")
	private Integer segmentWidth;

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

}
