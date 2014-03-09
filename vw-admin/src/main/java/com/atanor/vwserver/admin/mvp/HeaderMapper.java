package com.atanor.vwserver.admin.mvp;

import com.atanor.vwserver.admin.mvp.activity.header.DefaultHeaderPresetActivity;
import com.atanor.vwserver.admin.mvp.activity.header.HeaderPresetActivity;
import com.atanor.vwserver.admin.mvp.place.DefaultPresetPlace;
import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class HeaderMapper implements ActivityMapper {

	public HeaderMapper() {
	}

	@Override
	public Activity getActivity(Place place) {

		if (place instanceof PresetPlace) {
			return new HeaderPresetActivity(((PresetPlace) place).getPresetId());
		} else if (place instanceof DefaultPresetPlace) {
			return new DefaultHeaderPresetActivity();
		}

		return null;
	}

}
