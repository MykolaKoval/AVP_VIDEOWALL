package com.atanor.vwserver.admin.mvp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SourcePlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<SourcePlace> {

		public SourcePlace getPlace(String token) {
			return new SourcePlace();
		}

		public String getToken(SourcePlace place) {
			return "";
		}
	}
}
