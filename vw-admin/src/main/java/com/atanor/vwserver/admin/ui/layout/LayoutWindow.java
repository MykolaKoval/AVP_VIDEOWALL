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

	public LayoutWindow(final String name, final LayoutWindowChanged callback) {
		dto = new LayoutWindowDto();
		dto.setName(name);

		setLeft(0);
		setTop(0);
		setWidth(200);
		setHeight(200);
		setBackgroundColor("yellow");
		setBorder("1px inset black");
		setAlign(Alignment.CENTER);
		setValign(VerticalAlignment.CENTER);

		setCanDragResize(true);
		setCanDragReposition(true);
		setDragAppearance(DragAppearance.TARGET);

		setContents(name);
		bringToFront();

		addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final LayoutWindow win = (LayoutWindow)event.getSource();
				win.setOpacity(100);
				callback.onLayoutWindowSelected(win.getName());
			}
		});
	}

	public void updateDto() {
		dto.setLeft(getLeft());
		dto.setTop(getTop());
		dto.setHeight(getHeight());
		dto.setWidth(getWidth());
	}

	public String getName() {
		return getContents();
	}
}
