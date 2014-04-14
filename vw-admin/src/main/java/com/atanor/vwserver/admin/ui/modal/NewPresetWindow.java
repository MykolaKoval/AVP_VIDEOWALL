package com.atanor.vwserver.admin.ui.modal;

import java.util.Collection;
import java.util.LinkedHashMap;

import javax.inject.Inject;

import com.atanor.vwserver.admin.ui.modal.ModalCallbacks.NewPresetCallback;
import com.atanor.vwserver.admin.ui.modal.presenter.NewPresetPresenter;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.google.common.collect.Maps;
import com.google.web.bindery.event.shared.EventBus;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.events.ItemChangedEvent;
import com.smartgwt.client.widgets.form.events.ItemChangedHandler;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class NewPresetWindow extends ModalWindow implements NewPresetCallback {

	@Inject
	private EventBus eventBus;

	@Inject
	private NewPresetPresenter presenter;

	private CheckboxItem useLayout;
	private SelectItem layout;
	private SelectItem display;

	public NewPresetWindow() {
		super("New Preset");
		setHeight(170);
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

		useLayout = new CheckboxItem();
		useLayout.setTitle("Use Layout");
		useLayout.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				final CheckboxItem item = (CheckboxItem) event.getItem();
				final boolean checked = item.getValueAsBoolean();
				if (checked) {
					layout.enable();
				} else {
					layout.clearValue();
					layout.disable();
				}
			}
		});

		layout = createModalSelectItem("Select Layout");
		layout.setDisabled(true);

		display = createModalSelectItem("Select Display");

		form.setFields(useLayout, layout, display);

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
		createButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

			}
		});

		getForm().addItemChangedHandler(new ItemChangedHandler() {

			@Override
			public void onItemChanged(ItemChangedEvent event) {
				if (isChecked(useLayout) && isAllValuesValid(getForm())) {
					createButton.enable();
				} else if (!isChecked(useLayout) && isValid(display)) {
					createButton.enable();
				} else {
					createButton.disable();
				}
			}
		});

		layout.addMembers(cancelButton, createButton);
		return layout;
	}

	@Override
	public void show() {
		presenter.prepopulate(getCallback());
		super.show();
	}

	private NewPresetCallback getCallback() {
		return this;
	}

	@Override
	public void initDisplays(final Collection<DisplayDto> displays) {
		final LinkedHashMap<String, String> values = createDisplayValues(displays);
		display.setValueMap(values);
	}

	@Override
	public void initLayouts(final Collection<LayoutDto> layouts) {
		final LinkedHashMap<String, String> values = createLayoutValues(layouts);
		layout.setValueMap(values);
	}

	private static LinkedHashMap<String, String> createDisplayValues(final Collection<DisplayDto> displays) {
		final LinkedHashMap<String, String> result = Maps.newLinkedHashMap();
		for (final DisplayDto dto : displays) {
			result.put(dto.getName(), dto.getName());
		}
		return result;
	}

	private static LinkedHashMap<String, String> createLayoutValues(final Collection<LayoutDto> layouts) {
		final LinkedHashMap<String, String> result = Maps.newLinkedHashMap();
		for (final LayoutDto dto : layouts) {
			result.put(dto.getName(), dto.getName());
		}
		return result;
	}

}
