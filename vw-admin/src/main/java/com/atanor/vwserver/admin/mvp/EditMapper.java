package com.atanor.vwserver.admin.mvp;

import javax.inject.Inject;
import javax.inject.Provider;

import com.atanor.vwserver.admin.mvp.activity.edit.DefaultEditPresetActivity;
import com.atanor.vwserver.admin.mvp.activity.edit.EditPresetActivity;
import com.atanor.vwserver.admin.mvp.place.DefaultPresetPlace;
import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class EditMapper implements ActivityMapper {

	@Inject
	private Provider<EditPresetActivity> presetProvider;
	@Inject
	private Provider<DefaultEditPresetActivity> defaultPresetProvider;

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
