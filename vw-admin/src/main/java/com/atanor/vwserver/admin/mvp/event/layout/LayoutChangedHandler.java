package com.atanor.vwserver.admin.mvp.event.layout;

import com.google.gwt.event.shared.EventHandler;

public interface LayoutChangedHandler extends EventHandler {

	void onLayoutChanged(LayoutChangedEvent event);
}
