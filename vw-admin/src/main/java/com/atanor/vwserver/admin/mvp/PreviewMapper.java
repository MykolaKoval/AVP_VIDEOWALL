package com.atanor.vwserver.admin.mvp;

import javax.inject.Inject;
import javax.inject.Provider;

import com.atanor.vwserver.admin.mvp.activity.preview.PreviewDisplayActivity;
import com.atanor.vwserver.admin.mvp.activity.preview.PreviewLayoutActivity;
import com.atanor.vwserver.admin.mvp.activity.preview.PreviewPresetActivity;
import com.atanor.vwserver.admin.mvp.activity.preview.PreviewSourceActivity;
import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.atanor.vwserver.admin.mvp.place.SourcePlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class PreviewMapper implements ActivityMapper {

	@Inject
	private Provider<PreviewPresetActivity> presetProvider;
	@Inject
	private Provider<PreviewLayoutActivity> layoutProvider;
	@Inject
	private Provider<PreviewDisplayActivity> displayProvider;
	@Inject
	private Provider<PreviewSourceActivity> sourceProvider;

	@Override
	public Activity getActivity(Place place) {

		if (place instanceof PresetPlace) {
			return presetProvider.get().withPlace((PresetPlace) place);
		} else if (place instanceof LayoutPlace) {
			return layoutProvider.get().withPlace((LayoutPlace) place);
		} else if (place instanceof DisplayPlace) {
			return displayProvider.get().withPlace((DisplayPlace) place);
		} else if (place instanceof SourcePlace) {
			return sourceProvider.get().withPlace((SourcePlace) place);
		}

		return null;
	}

}
