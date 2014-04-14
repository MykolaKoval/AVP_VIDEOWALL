package com.atanor.vwserver.admin.ui.modal;

import com.atanor.vwserver.admin.ui.Utils;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public abstract class ModalWindow extends Window {

	private final DynamicForm form;
	private final HLayout controlLayout;

	public ModalWindow(final String title) {
		setWidth(Utils.MODAL_WINDOW_WIDTH);
		setHeight(220);
		setTitle(title);
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClickEvent event) {
				destroy();
			}
		});

		final VLayout layout = new VLayout();
		layout.setWidth100();
		layout.setHeight100();

		form = createForm();
		controlLayout = createControlLayout();
		layout.addMembers(form, controlLayout);

		addItem(layout);
	}

	protected abstract DynamicForm createForm();

	protected abstract HLayout createControlLayout();

	protected IntegerItem createModalIntegerItem(final String title) {
		final IntegerItem item = new IntegerItem();
		item.setTitle(title);
		item.setValidateOnChange(true);
		return item;
	}

	protected TextItem createModalTextItem(final String title) {
		return createModalTextItem(title, false);
	}

	protected TextItem createModalTextItem(final String title, final boolean skipValidation) {
		final TextItem item = new TextItem();
		item.setAttribute(Utils.ITEM_SKIPVALIDATION, skipValidation);
		item.setTitle(title);
		return item;
	}

	protected SelectItem createModalSelectItem(final String title) {
		final SelectItem item = new SelectItem();
		item.setTitle(title);
		return item;
	}

	protected IButton createModalButton(final String name) {
		final IButton button = new IButton(name);
		button.setWidth(80);
		return button;
	}

	protected HLayout createModalControlsLayout() {
		final HLayout layout = new HLayout();
		layout.setHeight(30);
		layout.setWidth100();
		layout.setPadding(5);
		layout.setMembersMargin(5);
		layout.setDefaultLayoutAlign(VerticalAlignment.CENTER);
		layout.setAlign(Alignment.RIGHT);
		return layout;
	}

	protected static boolean isValueValid(final IntegerItem item) {
		return item.getValue() != null && item.getValue() instanceof Integer;
	}

	protected static boolean isAllValuesValid(final DynamicForm form) {
		return form.valuesAreValid(false) && hasFieldValues(form);
	}

	private static boolean hasFieldValues(final DynamicForm form) {
		for (FormItem item : form.getFields()) {
			if (skipValidation(item)) {
				continue;
			}
			if (!isValid(item)) {
				return false;
			}
		}
		return true;
	}

	private static boolean skipValidation(final FormItem item) {
		return item.getAttributeAsObject(Utils.ITEM_SKIPVALIDATION) != null
				&& item.getAttributeAsBoolean(Utils.ITEM_SKIPVALIDATION);
	}

	protected static boolean isValid(final FormItem item) {
		return item.getValue() != null;
	}

	protected static boolean isChecked(final CheckboxItem item){
		return item != null && item.getValueAsBoolean();
	}
	
	public DynamicForm getForm() {
		return form;
	}

	public HLayout getControlLayout() {
		return controlLayout;
	}

}
