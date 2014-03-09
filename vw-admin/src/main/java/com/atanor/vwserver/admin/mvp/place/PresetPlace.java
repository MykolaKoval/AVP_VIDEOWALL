package com.atanor.vwserver.admin.mvp.place;

import com.atanor.vwserver.admin.ui.Utils;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PresetPlace extends Place {

	private final Long presetId;

	public PresetPlace() {
		this(null);
	}

	public PresetPlace(Long presetId) {
		this.presetId = presetId;
	}

	public Long getPresetId() {
		return presetId;
	}

	public static class Tokenizer implements PlaceTokenizer<PresetPlace> {

		public PresetPlace getPlace(String token) {
			return new PresetPlace(Utils.fromToken(token));
		}

		public String getToken(PresetPlace place) {
			return Utils.toToken(place.getPresetId());
		}
	}
}
