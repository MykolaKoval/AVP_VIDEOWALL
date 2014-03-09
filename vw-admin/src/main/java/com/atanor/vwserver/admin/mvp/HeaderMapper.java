package com.atanor.vwserver.admin.mvp;

import javax.inject.Inject;
import javax.inject.Provider;

import com.atanor.vwserver.admin.mvp.activity.header.DefaultHeaderPresetActivity;
import com.atanor.vwserver.admin.mvp.activity.header.HeaderPresetActivity;
import com.atanor.vwserver.admin.mvp.place.DefaultPresetPlace;
import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class HeaderMapper implements ActivityMapper {

	@Inject
	private Provider<HeaderPresetActivity> presetProvider;
	@Inject
	private Provider<DefaultHeaderPresetActivity> defaultPresetProvider;
	
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
