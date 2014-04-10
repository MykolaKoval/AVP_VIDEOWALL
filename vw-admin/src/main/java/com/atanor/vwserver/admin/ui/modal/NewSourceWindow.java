package com.atanor.vwserver.admin.ui.modal;

import javax.inject.Inject;

import com.atanor.vwserver.admin.ui.modal.ModalCallbacks.NewSourceCallback;
import com.atanor.vwserver.admin.ui.modal.presenter.NewSourcePresenter;
import com.atanor.vwserver.common.rpc.dto.SourceDto;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.events.ItemChangedEvent;
import com.smartgwt.client.widgets.form.events.ItemChangedHandler;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;

public class NewSourceWindow extends ModalWindow implements NewSourceCallback {

	@Inject
	private NewSourcePresenter presenter;

	private TextItem code;
	private TextItem description;

	public NewSourceWindow() {
		super("New Source");
		setHeight(150);
	}

	@Override
	protected DynamicForm createForm() {
		final DynamicForm form = new DynamicForm();
		form.setHeight100();
		form.setWidth100();
		form.setPadding(5);
		form.setNumCols(2);
		form.setColWidths("70%", "*");
		form.setLayoutAlign(VerticalAlignment.BOTTOM);

		code = createModalTextItem("Source ID");
		description = createModalTextItem("Source Description");

		form.setFields(code, description);

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

		final IButton createButton = createModalButton("Create");
		createButton.setDisabled(true);
		createButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				presenter.createSource(buildSourceDto(), getCallback());
			}
		});

		getForm().addItemChangedHandler(new ItemChangedHandler() {

			@Override
			public void onItemChanged(ItemChangedEvent event) {
				if (isAllValuesValid(getForm())) {
					createButton.enable();
				} else {
					createButton.disable();
				}
			}
		});

		layout.addMembers(cancelButton, createButton);
		return layout;
	}

	private SourceDto buildSourceDto() {
		final SourceDto dto = new SourceDto();
		dto.setCode(code.getValueAsString());
		dto.setDescription(description.getValueAsString());
		return dto;
	}

	@Override
	public void onSourceCreated() {
		destroy();
	}

	private NewSourceCallback getCallback() {
		return this;
	}
}
