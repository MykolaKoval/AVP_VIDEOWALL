package com.atanor.vwserver.services.impl;

import java.util.List;
import java.util.Set;

import com.atanor.vwserver.services.hardware.IWallService;
import com.atanor.vwserver.services.hardware.vo.LayoutVo;
import com.atanor.vwserver.services.hardware.vo.SourceVo;

public class MockWallService implements IWallService {

	@Override
	public List<LayoutVo> getLayouts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createLayouts(List<LayoutVo> layouts) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeLayouts(Set<String> ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void activateLayout(String layoutId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SourceVo> getSources() {
		// TODO Auto-generated method stub
		return null;
	}

}
