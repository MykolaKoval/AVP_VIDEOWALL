package com.atanor.vwserver.admin.ui.modal;

public interface ModalCallbacks {

	interface NewDisplayCallback {
		void onDisplayCreated();
	}
	
	interface SaveLayoutCallback {
		void onLayoutSaved();
	}
	
	interface NewSourceCallback {
		void onSourceCreated();
	}
}
