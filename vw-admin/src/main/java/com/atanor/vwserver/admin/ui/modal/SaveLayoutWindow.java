package com.atanor.vwserver.admin.ui.modal;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.presenter.EditPresenter;
import com.atanor.vwserver.admin.ui.modal.ModalCallbacks.SaveLayoutCallback;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.events.ItemChangedEvent;
import com.smartgwt.client.widgets.form.events.ItemChangedHandler;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;

public class SaveLayoutWindow extends ModalWindow implements SaveLayoutCallback {

	@Inject
	private EditPresenter presenter;

	private TextItem name;

	public SaveLayoutWindow() {
		super("Save Layout");
		setHeight(120);
	}

	@Override
	protected DynamicForm createForm() {
		final DynamicForm form = new DynamicForm();
		form.setHeight100();
		form.setWidth100();
		form.setPadding(5);
		form.setNumCols(2);
		form.setColWidths("30%", "*");
		form.setLayoutAlign(VerticalAlignment.BOTTOM);

		name = new TextItem();
		name.setTitle("Name");
		name.setWidth("100%");

		form.setFields(name);

		return form;
	}

	@Override
	protected HLayout createControlLayout() {
		final HLayout layout = createModalControlsLayout();

		final IButton cancelButton = createModalButton("Cancel");
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				destroy();
			}
		});

		final IButton saveButton = createModalButton("Save");
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				presenter.saveLayout(name.getValueAsString(), getCallback());
			}
		});

		getForm().addItemChangedHandler(new ItemChangedHandler() {

			@Override
			public void onItemChanged(ItemChangedEvent event) {
				if (isAllValuesValid(getForm())) {
					saveButton.enable();
				} else {
					saveButton.disable();
				}
			}
		});

		layout.addMembers(cancelButton, saveButton);
		return layout;
	}

	private SaveLayoutCallback getCallback() {
		return this;
	}

	@Override
	public void onLayoutSaved() {
		destroy();
	}
}
