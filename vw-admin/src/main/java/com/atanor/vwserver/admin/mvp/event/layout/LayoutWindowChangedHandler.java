package com.atanor.vwserver.admin.mvp.event.layout;

import com.google.gwt.event.shared.EventHandler;

public interface LayoutWindowChangedHandler extends EventHandler {

	void onLayoutWindowChanged(LayoutWindowChangedEvent event);
}
