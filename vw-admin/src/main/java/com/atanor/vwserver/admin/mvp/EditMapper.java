package com.atanor.vwserver.admin.mvp;

import javax.inject.Inject;
import javax.inject.Provider;

import com.atanor.vwserver.admin.mvp.activity.edit.EditDisplayActivity;
import com.atanor.vwserver.admin.mvp.activity.edit.EditLayoutActivity;
import com.atanor.vwserver.admin.mvp.activity.edit.EditPresetActivity;
import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class EditMapper implements ActivityMapper {

	@Inject
	private Provider<EditPresetActivity> presetProvider;
	@Inject
	private Provider<EditLayoutActivity> layoutProvider;
	@Inject
	private Provider<EditDisplayActivity> displayProvider;

	@Override
	public Activity getActivity(Place place) {

		if (place instanceof PresetPlace) {
			return presetProvider.get().withPlace((PresetPlace) place);
		} else if (place instanceof LayoutPlace) {
			return layoutProvider.get().withPlace((LayoutPlace) place);
		} else if (place instanceof DisplayPlace) {
			return displayProvider.get().withPlace((DisplayPlace) place);
		}

		return null;
	}

}
