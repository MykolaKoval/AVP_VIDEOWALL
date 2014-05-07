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
		final HLayout hLayout = createModalControlsLayout();

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
				presenter.createTemplate((Long) layout.getValue(), (Long) display.getValue());
				destroy();
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

		hLayout.addMembers(cancelButton, createButton);
		return hLayout;
	}

	@Override
	public void show() {
		presenter.prepopulate(this);
		super.show();
	}

	@Override
	public void initDisplays(final Collection<DisplayDto> displays) {
		final LinkedHashMap<Long, String> values = createDisplayValues(displays);
		display.setValueMap(values);
	}

	@Override
	public void initLayouts(final Collection<LayoutDto> layouts) {
		final LinkedHashMap<Long, String> values = createLayoutValues(layouts);
		layout.setValueMap(values);
	}

	private static LinkedHashMap<Long, String> createDisplayValues(final Collection<DisplayDto> displays) {
		final LinkedHashMap<Long, String> result = Maps.newLinkedHashMap();
		for (final DisplayDto dto : displays) {
			result.put(dto.getId(), dto.getName());
		}
		return result;
	}

	private static LinkedHashMap<Long, String> createLayoutValues(final Collection<LayoutDto> layouts) {
		final LinkedHashMap<Long, String> result = Maps.newLinkedHashMap();
		for (final LayoutDto dto : layouts) {
			result.put(dto.getId(), dto.getName());
		}
		return result;
	}

}
