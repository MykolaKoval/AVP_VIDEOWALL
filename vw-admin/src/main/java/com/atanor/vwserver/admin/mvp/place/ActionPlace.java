package com.atanor.vwserver.admin.mvp.place;

import com.google.gwt.place.shared.Place;

public abstract class ActionPlace extends Place {

	private final Action action;

	public ActionPlace(final Action action) {
		this.action = action;
	}

	public Action getAction() {
		return action;
	}

}
