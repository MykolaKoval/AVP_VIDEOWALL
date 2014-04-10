package com.atanor.vwserver.services;

import java.util.List;

import com.atanor.vwserver.domain.entity.Source;

public interface ISourceService {

	Long createSource(Source source);

	void removeSources(List<Long> ids);

	Source getSource(Long id);

	List<Source> getSources();
}
