package com.atanor.vwserver.admin.mvp.activity.preview;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.model.DisplayStorage;
import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.mvp.view.preview.PreviewDisplayView;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PreviewDisplayActivity extends AbstractActivity {

	private Long displayId;
	private Boolean refreshDisplays;
	
	@Inject
	private DisplayStorage storage;
	
	@Inject
	private PreviewDisplayView view;

	public PreviewDisplayActivity withPlace(final DisplayPlace place) {
		this.displayId = place.getDisplayId();
		this.refreshDisplays = place.isRefreshDisplays();
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
		
//		if(refreshDisplays){
//			view.setDisplays(storage.getDisplays());
//		}
	}

}
