package com.atanor.vwserver.admin.mvp.event.display;

import com.google.gwt.event.shared.GwtEvent;

public class DisplayChangedEvent extends GwtEvent<DisplayChangedHandler> {

	private static Type<DisplayChangedHandler> TYPE;

	public static Type<DisplayChangedHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<DisplayChangedHandler>();
		}
		return TYPE;
	}

	private final DisplayAction action;

	public DisplayChangedEvent(final DisplayAction action) {
		this.action = action;
	}

	public DisplayAction getAction() {
		return action;
	}

	@Override
	public final Type<DisplayChangedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DisplayChangedHandler handler) {
		handler.onSourceChanged(this);
	}
}