package com.atanor.vwserver.admin.mvp.event;

import com.google.gwt.event.shared.GwtEvent;

public class SetModelEvent extends GwtEvent<SetModelHandler> {

	private static Type<SetModelHandler> TYPE;

	public static Type<SetModelHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<SetModelHandler>();
		}
		return TYPE;
	}

	@Override
	public final Type<SetModelHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(SetModelHandler handler) {
		handler.onSetModel(this);
	}
}