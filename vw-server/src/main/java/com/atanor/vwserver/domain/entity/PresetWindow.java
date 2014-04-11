package com.atanor.vwserver.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "preset_windows")
@NamedQuery(name = "PresetWindow.GetAll", query = "SELECT w FROM PresetWindow w")
public class PresetWindow extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", length = 32)
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "source_id")
	private Source source;

	@Column(name = "x_top_left")
	private Integer xTopLeft;

	@Column(name = "y_top_left")
	private Integer yTopLeft;

	@Column(name = "x_bottom_right")
	private Integer xBottomRight;

	@Column(name = "y_bottom_right")
	private Integer yBottomRight;

	@Column(name = "z_index")
	private Integer zIndex;

	@ManyToOne
	@JoinColumn(name = "preset_id", nullable = false)
	private Preset preset;
	
	@Transient
	private Boolean modified;
	
	@Transient
	private Boolean selected;
	
	public PresetWindow() {
	}

	public PresetWindow(final Long id){
		this.id = id;
	}
	
	public PresetWindow(final String name, final Integer xTopLeft, final Integer yTopLeft,
			final Integer xBottomRight, final Integer yBottomRight, final Integer zIndex) {
		this.name = name;
		this.xTopLeft = xTopLeft;
		this.yTopLeft = yTopLeft;
		this.xBottomRight = xBottomRight;
		this.yBottomRight = yBottomRight;
		this.zIndex = zIndex;
	}

	@Override
	public Long getId() {
		return id;
	}

	public Preset getPreset() {
		return preset;
	}

	public void setPreset(final Preset preset) {
		this.preset = preset;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(final Source source) {
		this.source = source;
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
