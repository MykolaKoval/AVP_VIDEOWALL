package com.atanor.vwserver.admin.mvp;

import javax.inject.Inject;
import javax.inject.Provider;

import com.atanor.vwserver.admin.mvp.activity.navigate.DisplayControlActivity;
import com.atanor.vwserver.admin.mvp.activity.navigate.LayoutControlActivity;
import com.atanor.vwserver.admin.mvp.activity.navigate.PresetControlActivity;
import com.atanor.vwserver.admin.mvp.place.DefaultPresetPlace;
import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class NavigateMapper implements ActivityMapper {

	@Inject
	private Provider<PresetControlActivity> presetProvider;
	@Inject
	private Provider<LayoutControlActivity> layoutProvider;
	@Inject
	private Provider<DisplayControlActivity> displayProvider;

	@Override
	public Activity getActivity(Place place) {

		if (place instanceof PresetPlace || place instanceof DefaultPresetPlace) {
			return presetProvider.get();
		} else if (place instanceof LayoutPlace) {
			return layoutProvider.get();
		} else if (place instanceof DisplayPlace) {
			return displayProvider.get();
		}

		return null;
	}

}
