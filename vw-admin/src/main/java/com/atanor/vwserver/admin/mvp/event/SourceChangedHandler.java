package com.atanor.vwserver.admin.mvp.event;

import com.google.gwt.event.shared.EventHandler;

public interface SourceChangedHandler extends EventHandler {

	void onSourceChanged(SourceChangedEvent event);
}
