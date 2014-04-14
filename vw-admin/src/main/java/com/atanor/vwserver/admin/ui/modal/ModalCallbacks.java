package com.atanor.vwserver.admin.ui.modal;

import java.util.Collection;

import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;

public interface ModalCallbacks {

	interface NewPresetCallback {
		void initDisplays(Collection<DisplayDto> displays);
		void initLayouts(Collection<LayoutDto> layouts);
	}

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
