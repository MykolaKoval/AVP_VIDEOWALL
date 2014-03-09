package com.atanor.vwserver.admin.mvp;

import com.atanor.vwserver.admin.mvp.activity.preview.DefaultPreviewPresetActivity;
import com.atanor.vwserver.admin.mvp.activity.preview.PreviewPresetActivity;
import com.atanor.vwserver.admin.mvp.place.DefaultPresetPlace;
import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class PreviewMapper implements ActivityMapper {

	public PreviewMapper() {
	}

	@Override
	public Activity getActivity(Place place) {

		if (place instanceof PresetPlace) {
			return new PreviewPresetActivity(((PresetPlace) place).getPresetId());
		} else if (place instanceof DefaultPresetPlace) {
			return new DefaultPreviewPresetActivity();
		}

		return null;
	}

}
