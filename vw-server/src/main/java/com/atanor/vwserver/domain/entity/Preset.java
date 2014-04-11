package com.atanor.vwserver.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "presets")
@NamedQuery(name = "Preset.GetAll", query = "SELECT p FROM Preset p")
public class Preset extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", unique = true, length = 32)
	private String name;

	@Column(name = "create_ts")
	private Date createTS;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "preset")
	private List<PresetWindow> windows;

	@Lob
	@Column(name = "preset_blob", length = 300000)
	private String imgBlob;

	public Preset() {
	}

	public Preset(Long id) {
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

	public Date getCreateTS() {
		return createTS;
	}

	public void setCreateTS(final Date createTS) {
		this.createTS = createTS;
	}

	public List<PresetWindow> getWindows() {
		return windows;
	}

	public void setWindows(final List<PresetWindow> windows) {
		this.windows = windows;
	}

	public String getImgBlob() {
		return imgBlob;
	}

	public void setImgBlob(final String imgBlob) {
		this.imgBlob = imgBlob;
	}

}
