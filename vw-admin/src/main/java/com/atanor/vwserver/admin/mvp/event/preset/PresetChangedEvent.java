package com.atanor.vwserver.admin.mvp.event.preset;

import com.google.gwt.event.shared.GwtEvent;

public class PresetChangedEvent extends GwtEvent<PresetChangedHandler> {

	private static Type<PresetChangedHandler> TYPE;

	public static Type<PresetChangedHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<PresetChangedHandler>();
		}
		return TYPE;
	}

	private final PresetAction action;
	private final Long layoutId;
	private final Long displayId;
	
	public PresetChangedEvent(final PresetAction action) {
		this(action, null, null);
	}

	public PresetChangedEvent(final PresetAction action, final Long layoutId, final Long displayId) {
		this.action = action;
		this.layoutId = layoutId;
		this.displayId = displayId;
	}
	
	public PresetAction getAction() {
		return action;
	}

	public Long getLayoutId() {
		return layoutId;
	}

	public Long getDisplayId() {
		return displayId;
	}

	@Override
	public final Type<PresetChangedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PresetChangedHandler handler) {
		handler.onPresetChanged(this);
	}
}