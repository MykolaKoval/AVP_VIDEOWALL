package com.atanor.vwserver.admin.mvp.event;

import com.google.gwt.event.shared.EventHandler;

public interface SetModelHandler extends EventHandler {

	void onSetModel(SetModelEvent event);
}
