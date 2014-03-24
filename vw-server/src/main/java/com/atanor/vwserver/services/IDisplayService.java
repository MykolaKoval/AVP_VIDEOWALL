package com.atanor.vwserver.services;

import java.util.List;

import com.atanor.vwserver.domain.entity.Display;

public interface IDisplayService {

	Long createDisplay(Display display);

	void removeDisplay(Long id);
	
	Display getDisplay(Long id);
	
	List<Display> getDisplays();
}
