package com.atanor.vwserver.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "sources")
@NamedQuery(name = "Source.GetAll", query = "SELECT s from Source s")
public class Source extends AbstractEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "code", unique = true, length = 32)
	private String code;

	@Column(name = "description", unique = true, length = 32)
	private String description;

	public Source() {
	}

	public Source(final Long id) {
		this.id = id;
	}

	public Source(final String code, final String description) {
		this.code = code;
		this.description = description;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

}
