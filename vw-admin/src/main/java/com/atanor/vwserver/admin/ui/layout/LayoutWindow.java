package com.atanor.vwserver.admin.ui.layout;

import com.atanor.vwserver.common.rpc.dto.LayoutWindowDto;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

public class LayoutWindow extends Label {

	private final LayoutWindowDto dto;
	private boolean isSelected;

	public LayoutWindow(final LayoutWindowDto dto, final LayoutWindowChanged callback) {
		this.dto = dto;

		setLeft(dto.getLeft());
		setTop(dto.getTop());
		setWidth(dto.getWidth());
		setHeight(dto.getHeight());
		setBackgroundColor("yellow");
		setBorder("1px inset black");
		setAlign(Alignment.CENTER);
		setValign(VerticalAlignment.CENTER);
		
		setCanDragResize(true);
		setCanDragReposition(true);
		setDragAppearance(DragAppearance.TARGET);

		setName(dto.getName());
		bringToFront();

		addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final LayoutWindow win = (LayoutWindow) event.getSource();
				win.select();
				callback.onLayoutWindowSelected(win);
			}
		});

		select();
	}

	public void updateDto() {
		dto.setLeft(getLeft());
		dto.setTop(getTop());
		dto.setHeight(getHeight());
		dto.setWidth(getWidth());
	}

	public String getName() {
		return dto.getName();
	}

	public void setName(final String name) {
		dto.setName(name);
		setContents("<font size=\"30\" face=\"Arial\">" + name + "</font>");
	}

	public void select() {
		setOpacity(100);
		isSelected = true;
	}

	public void unselect() {
		setOpacity(50);
		isSelected = false;
	}

	public boolean selected() {
		return isSelected;
	}

	public LayoutWindowDto getDto() {
		return dto;
	}
}
