package com.atanor.vwserver.admin.mvp.event.display;

import com.google.gwt.event.shared.EventHandler;

public interface DisplayChangedHandler extends EventHandler {

	void onSourceChanged(DisplayChangedEvent event);
}
