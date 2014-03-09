package com.atanor.vwserver.admin.mvp.event;

import com.google.gwt.event.shared.EventHandler;

public interface CleanGridActivationHandler extends EventHandler {

    void onCleanGridActivation(CleanGridActivationEvent event);
}
