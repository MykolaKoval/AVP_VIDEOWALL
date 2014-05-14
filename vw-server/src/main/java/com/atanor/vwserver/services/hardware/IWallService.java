package com.atanor.vwserver.services.hardware;

import java.util.List;
import java.util.Set;

import com.atanor.vwserver.services.hardware.vo.LayoutVo;
import com.atanor.vwserver.services.hardware.vo.SourceVo;

public interface IWallService {

	List<LayoutVo> getLayouts();

	void createLayouts(List<LayoutVo> layouts);

	void removeLayouts(Set<String> ids);

	void activateLayout(String layoutId);

	List<SourceVo> getSources();
}
