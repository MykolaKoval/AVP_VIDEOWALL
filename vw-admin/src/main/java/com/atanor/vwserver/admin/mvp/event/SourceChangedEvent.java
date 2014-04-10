package com.atanor.vwserver.admin.mvp.event;

import com.google.gwt.event.shared.GwtEvent;

public class SourceChangedEvent extends GwtEvent<SourceChangedHandler> {

	private static Type<SourceChangedHandler> TYPE;

	public static Type<SourceChangedHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<SourceChangedHandler>();
		}
		return TYPE;
	}

	private final SourceAction action;

	public SourceChangedEvent(final SourceAction action) {
		this.action = action;
	}

	public SourceAction getAction() {
		return action;
	}

	@Override
	public final Type<SourceChangedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SourceChangedHandler handler) {
		handler.onSourceChanged(this);
	}
}