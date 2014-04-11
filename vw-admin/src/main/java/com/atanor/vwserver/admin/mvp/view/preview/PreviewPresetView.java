package com.atanor.vwserver.admin.mvp.view.preview;

import java.util.Collection;
import java.util.List;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.atanor.vwserver.admin.ui.PresetLabel;
import com.atanor.vwserver.admin.ui.Utils;
import com.atanor.vwserver.admin.ui.builder.UiBuilder;
import com.atanor.vwserver.admin.ui.builder.post.NavigatePresetPostBuilder;
import com.atanor.vwserver.admin.ui.builder.post.NavigateWindowPostBuilder;
import com.atanor.vwserver.common.rpc.dto.PresetDto;
import com.google.common.collect.Lists;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class PreviewPresetView extends VLayout implements ClickHandler {

	private final List<PresetLabel> layouts = Lists.newArrayList();

	private Double scaleFactor;
	private Long presetWidth;
	private Long presetHeight;

	public PreviewPresetView() {
		setWidth(Utils.PREVIEW_DISPLAY_WIDTH);
		setHeight100();
		setOverflow(Overflow.HIDDEN);
		setShowResizeBar(true);
		setMembersMargin(40);
		setBackgroundColor("lightgrey");
	}

	private void createPresetWindow(PresetDto preset, Long presetWidth, Long presetHeight, Double scaleFactor) {

		PresetLabel lab = UiBuilder.buildPresetLayout(preset, presetWidth, presetHeight, scaleFactor,
				new NavigatePresetPostBuilder(), new NavigateWindowPostBuilder());
		lab.addClickHandler(this);
		layouts.add(lab);

		HLayout labelLayout = new HLayout();
		labelLayout.setAlign(Alignment.CENTER);
		labelLayout.addMember(lab);
		labelLayout.setHeight(10);

		addMember(labelLayout);
	}

	@Override
	public void onClick(ClickEvent event) {
		PresetLabel preset = (PresetLabel) event.getSource();

		if (!preset.isSelected()) {
			Client.goTo(new PresetPlace(preset.getDto().getId()));
		}
	}

	private void selectPreset(final PresetLabel preset) {
		preset.setSelected(true);
		preset.setOpacity(20);
	}

	private void resetPresetLayouts() {
		for (PresetLabel layout : layouts) {
			layout.setSelected(false);
			layout.setOpacity(100);
		}
	}

	public void clean() {
		resetPresetLayouts();
	}

	public void setPreset(final Long presetId) {
//		resetPresetLayouts();
//		selectPreset(getPreset(presetId));
	}

	private PresetLabel getPreset(final Long presetId) {
		for (PresetLabel preset : layouts) {
			if (presetId == preset.getId()) {
				return preset;
			}
		}
		throw new IllegalStateException("Preset with following id: " + id + " not found");
	}

	public void setPresetConfiguration(final PresetDto newPreset) {
		List<PresetDto> presets = Lists.newArrayList();
		for (PresetLabel playout : layouts) {
			presets.add(playout.getDto().getId() != newPreset.getId() ? playout.getDto() : newPreset);
		}

		layouts.clear();
		cleanPresetLayouts();

		for (PresetDto preset : presets) {
			createPresetWindow(preset, presetWidth, presetHeight, scaleFactor);
		}
	}

	private void cleanPresetLayouts() {
		for (Canvas child : getChildren()) {
			if (child instanceof HLayout) {
				child.destroy();
			}
		}
	}

	public void setPresets(final Collection<PresetDto> presets) {

	}
}
