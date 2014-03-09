package com.atanor.vwserver.admin.mvp.place;

import com.atanor.vwserver.admin.ui.Utils;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class LayoutPlace extends Place {

	private final Long layoutId;

	public LayoutPlace() {
		this(null);
	}

	public LayoutPlace(Long layoutId) {
		this.layoutId = layoutId;
	}

	public Long getLayoutId() {
		return layoutId;
	}

	public static class Tokenizer implements PlaceTokenizer<LayoutPlace> {

		public LayoutPlace getPlace(String token) {
			return new LayoutPlace(Utils.fromToken(token));
		}

		public String getToken(LayoutPlace place) {
			return Utils.toToken(place.getLayoutId());
		}
	}
}
