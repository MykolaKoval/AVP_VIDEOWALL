package com.atanor.vwserver.admin.mvp.activity;

import com.atanor.vwserver.admin.mvp.place.Action;
import com.google.common.base.Preconditions;
import com.google.gwt.activity.shared.AbstractActivity;

public abstract class ActionActivity extends AbstractActivity {

	protected void execute(Action action) {
		Preconditions.checkNotNull(action, "action can not be null");

		switch (action) {
		case CLEAN:
			doClean();
			break;
		default:
			break;
		}
	}

	protected void doClean() {
	}

}
