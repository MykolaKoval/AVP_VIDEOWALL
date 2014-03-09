package com.atanor.vwserver.admin.mvp;

import com.atanor.vwserver.admin.mvp.activities.header.DefaultHeaderPresetActivity;
import com.atanor.vwserver.admin.mvp.activities.header.HeaderPresetActivity;
import com.atanor.vwserver.admin.mvp.places.DefaultPresetPlace;
import com.atanor.vwserver.admin.mvp.places.PresetPlace;
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
