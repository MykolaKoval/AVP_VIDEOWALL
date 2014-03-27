package com.atanor.vwserver.admin.mvp.presenter;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.view.edit.EditLayoutView;

public class EditPresenter {

	@Inject
	public EditLayoutView layoutView;

	public void addLayoutWindow() {
		layoutView.addLayoutWindow();
	}

}
