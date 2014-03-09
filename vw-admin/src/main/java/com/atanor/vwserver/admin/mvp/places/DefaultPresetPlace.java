package com.atanor.vwserver.admin.mvp.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DefaultPresetPlace extends Place {

	public DefaultPresetPlace() {
	}

	public static class Tokenizer implements PlaceTokenizer<DefaultPresetPlace> {

		public DefaultPresetPlace getPlace(String token) {
			return new DefaultPresetPlace();
		}

		public String getToken(DefaultPresetPlace place) {
			return "empty";
		}
	}
}
