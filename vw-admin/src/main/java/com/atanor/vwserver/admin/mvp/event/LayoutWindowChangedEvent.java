package com.atanor.vwserver.admin.mvp.event;

import com.atanor.vwserver.common.rpc.dto.LayoutWindowDto;
import com.google.gwt.event.shared.GwtEvent;

public class LayoutWindowChangedEvent extends GwtEvent<LayoutWindowChangedHandler> {

	private static Type<LayoutWindowChangedHandler> TYPE;

	public static Type<LayoutWindowChangedHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<LayoutWindowChangedHandler>();
		}
		return TYPE;
	}

	private final WindowAction action;
	private final LayoutWindowDto dto;

	public LayoutWindowChangedEvent(final WindowAction action) {
		this(action, null);
	}

	public LayoutWindowChangedEvent(final WindowAction action, final LayoutWindowDto dto) {
		this.action = action;
		this.dto = dto;
	}

	public WindowAction getAction() {
		return action;
	}

	public LayoutWindowDto getDto() {
		return dto;
	}

	@Override
	public final Type<LayoutWindowChangedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LayoutWindowChangedHandler handler) {
		handler.onLayoutWindowChanged(this);
	}
}