package com.atanor.vwserver.services;

import java.util.List;

import com.atanor.vwserver.domain.entity.Layout;

public interface ILayoutService {

	Long createLayout(Layout layout);

	void removeLayout(Long id);

	List<Layout> getLayouts();

	Layout getLayout(Long id);
}
