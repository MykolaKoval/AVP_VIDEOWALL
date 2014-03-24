package com.atanor.vwserver.admin.mvp.event;

import com.atanor.vwserver.admin.mvp.model.ModelType;
import com.google.gwt.event.shared.GwtEvent;

public class SetModelEvent extends GwtEvent<SetModelHandler> {

	private static Type<SetModelHandler> TYPE;

	public static Type<SetModelHandler> getType() {
		if (TYPE == null) {
			TYPE = new Type<SetModelHandler>();
		}
		return TYPE;
	}

	private final ModelType modelType;

	public SetModelEvent() {
		this(ModelType.All);
	}

	public SetModelEvent(final ModelType modelType) {
		this.modelType = modelType;
	}

	public ModelType getModelType() {
		return modelType;
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