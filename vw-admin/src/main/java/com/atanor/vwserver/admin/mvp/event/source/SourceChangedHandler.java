package com.atanor.vwserver.admin.mvp.event.source;

import com.google.gwt.event.shared.EventHandler;

public interface SourceChangedHandler extends EventHandler {

	void onSourceChanged(SourceChangedEvent event);
}
