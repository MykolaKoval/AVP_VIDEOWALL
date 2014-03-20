package com.atanor.vwserver.admin.mvp.place;

import com.atanor.vwserver.admin.ui.Utils;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class DisplayPlace extends Place {

	private final Long displayId;
	private final Boolean refreshDisplays;
	
	public DisplayPlace() {
		this(null, false);
	}

	public DisplayPlace(Long displayId, Boolean refreshDisplays) {
		this.displayId = displayId;
		this.refreshDisplays = refreshDisplays;
	}

	public Long getDisplayId() {
		return displayId;
	}
	
	public Boolean isRefreshDisplays() {
		return refreshDisplays;
	}

	public static class Tokenizer implements PlaceTokenizer<DisplayPlace> {

		public DisplayPlace getPlace(String token) {
			return new DisplayPlace(Utils.fromToken(token), false);
		}

		public String getToken(DisplayPlace place) {
			return Utils.toToken(place.getDisplayId());
		}
	}
}
