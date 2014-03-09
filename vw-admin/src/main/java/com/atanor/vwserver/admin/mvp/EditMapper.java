package com.atanor.vwserver.admin.mvp;

import com.atanor.vwserver.admin.mvp.activities.edit.DefaultEditPresetActivity;
import com.atanor.vwserver.admin.mvp.activities.edit.EditPresetActivity;
import com.atanor.vwserver.admin.mvp.places.DefaultPresetPlace;
import com.atanor.vwserver.admin.mvp.places.PresetPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class EditMapper implements ActivityMapper {

	public EditMapper() {
	}

	@Override
	public Activity getActivity(Place place) {

		if (place instanceof PresetPlace) {
			return new EditPresetActivity(((PresetPlace) place).getPresetId());
		} else if (place instanceof DefaultPresetPlace) {
			return new DefaultEditPresetActivity();
		}

		return null;
	}

}
