package com.atanor.vwserver.admin.mvp.event.layout;

import com.google.gwt.event.shared.GwtEvent;

public class LayoutChangedEvent extends GwtEvent<LayoutChangedHandler> {

	private static Type<LayoutChangedHandler> TYPE;

	public static Type<LayoutChangedHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<LayoutChangedHandler>();
		}
		return TYPE;
	}

	private final LayoutAction action;

	public LayoutChangedEvent(final LayoutAction action) {
		this.action = action;
	}

	public LayoutAction getAction() {
		return action;
	}

	@Override
	public final Type<LayoutChangedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LayoutChangedHandler handler) {
		handler.onLayoutChanged(this);
	}
}