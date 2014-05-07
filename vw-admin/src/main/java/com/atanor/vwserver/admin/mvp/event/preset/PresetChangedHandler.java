package com.atanor.vwserver.admin.mvp.event.preset;

import com.google.gwt.event.shared.EventHandler;

public interface PresetChangedHandler extends EventHandler {

	void onPresetChanged(PresetChangedEvent event);
}
