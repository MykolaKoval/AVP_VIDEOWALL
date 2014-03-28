package com.atanor.vwserver.admin.ui.modal;

import javax.inject.Inject;

import com.atanor.vwserver.admin.mvp.event.LayoutWindowChangedEvent;
import com.atanor.vwserver.admin.mvp.event.WindowAction;
import com.atanor.vwserver.common.rpc.dto.LayoutWindowDto;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.events.ItemChangedEvent;
import com.smartgwt.client.widgets.form.events.ItemChangedHandler;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.layout.HLayout;

public class NewLayoutWindow extends ModalWindow {

	@Inject
	private EventBus eventBus;

	private IntegerItem left;
	private IntegerItem top;
	private IntegerItem width;
	private IntegerItem height;

	public NewLayoutWindow() {
		super("New Layout Window");
		setHeight(200);
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

		left = createModalIntegerItem("Left, %");
		top = createModalIntegerItem("Top, %");
		width = createModalIntegerItem("Width, %");
		height = createModalIntegerItem("Height, %");

		setDefaultValues();
		form.setFields(left, top, width, height);

		return form;
	}

	private void setDefaultValues() {
		left.setValue(0);
		top.setValue(0);
		width.setValue(50);
		height.setValue(50);
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
		createButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LayoutWindowChangedEvent(WindowAction.CREATE, buildWindowDto()));
				destroy();
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

	private LayoutWindowDto buildWindowDto() {
		final LayoutWindowDto dto = new LayoutWindowDto();
		dto.setLeft(Integer.parseInt(left.getValueAsString()));
		dto.setTop(Integer.parseInt(top.getValueAsString()));
		dto.setHeight(Integer.parseInt(height.getValueAsString()));
		dto.setWidth(Integer.parseInt(width.getValueAsString()));
		return dto;
	}
}
