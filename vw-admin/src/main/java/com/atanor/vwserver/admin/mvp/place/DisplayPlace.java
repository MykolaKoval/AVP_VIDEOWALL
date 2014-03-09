package com.atanor.vwserver.admin.mvp.place;

import com.atanor.vwserver.admin.ui.Utils;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DisplayPlace extends Place {

	private final Long displayId;

	public DisplayPlace() {
		this(null);
	}

	public DisplayPlace(Long displayId) {
		this.displayId = displayId;
	}

	public Long getDisplayId() {
		return displayId;
	}

	public static class Tokenizer implements PlaceTokenizer<DisplayPlace> {

		public DisplayPlace getPlace(String token) {
			return new DisplayPlace(Utils.fromToken(token));
		}

		public String getToken(DisplayPlace place) {
			return Utils.toToken(place.getDisplayId());
		}
	}
}
