package com.atanor.vwserver.common.rpc.dto;

@SuppressWarnings("serial")
public class SourceDto extends AbstractDto {

	private Long id;

	private String code;
	private String description;
	
	public SourceDto() {
	}

	public SourceDto(final Long id) {
		this.id = id;
	}

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
