package com.atanor.vwserver.admin.mvp;

import javax.inject.Inject;
import javax.inject.Provider;

import com.atanor.vwserver.admin.mvp.activity.preview.DefaultPreviewPresetActivity;
import com.atanor.vwserver.admin.mvp.activity.preview.PreviewPresetActivity;
import com.atanor.vwserver.admin.mvp.place.DefaultPresetPlace;
import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class PreviewMapper implements ActivityMapper {

	@Inject
	private Provider<PreviewPresetActivity> presetProvider;
	@Inject
	private Provider<DefaultPreviewPresetActivity> defaultPresetProvider;

	@Override
	public Activity getActivity(Place place) {

		if (place instanceof PresetPlace) {
			return presetProvider.get().withPlace((PresetPlace) place);
		} else if (place instanceof DefaultPresetPlace) {
			return defaultPresetProvider.get().withPlace((DefaultPresetPlace) place);
		}

		return null;
	}

}
