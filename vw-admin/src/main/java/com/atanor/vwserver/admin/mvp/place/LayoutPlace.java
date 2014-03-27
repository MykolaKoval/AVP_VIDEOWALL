package com.atanor.vwserver.admin.mvp.place;

import com.atanor.vwserver.admin.ui.Utils;
import com.google.gwt.place.shared.PlaceTokenizer;

public class LayoutPlace extends ActionPlace {

	private final Long layoutId;

	public LayoutPlace() {
		this(null, Action.NOTHING);
	}

	public LayoutPlace(final Action action) {
		this(null, action);
	}

	public LayoutPlace(Long layoutId) {
		this(layoutId, Action.NOTHING);
	}

	public LayoutPlace(final Long layoutId, final Action action) {
		super(action);
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
