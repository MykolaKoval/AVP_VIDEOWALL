package com.atanor.vwserver.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "layout_windows")
@NamedQuery(name = "LayoutWindow.GetAll", query = "SELECT w FROM LayoutWindow w")
public class LayoutWindow extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", length = 32)
	private String name;

	@Column(name = "left_pct")
	private Integer left;

	@Column(name = "top_pct")
	private Integer top;

	@Column(name = "width_pct")
	private Integer width;

	@Column(name = "height_pct")
	private Integer height;

	@ManyToOne
	@JoinColumn(name = "layout_id", nullable = false)
	private Layout layout;

	public LayoutWindow() {
	}

	public LayoutWindow(final String name, final Integer left, final Integer top, final Integer width,
			final Integer height) {
		this.name = name;
		this.left = left;
		this.top = top;
		this.width = width;
		this.height = height;
	}

	public LayoutWindow(final Long id) {
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

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(final Layout layout) {
		this.layout = layout;
	}

}
