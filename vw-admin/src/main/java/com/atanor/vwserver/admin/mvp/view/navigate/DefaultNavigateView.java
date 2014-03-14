package com.atanor.vwserver.admin.mvp.view.navigate;

import com.atanor.vwserver.admin.Client;
import com.atanor.vwserver.admin.mvp.place.DisplayPlace;
import com.atanor.vwserver.admin.mvp.place.LayoutPlace;
import com.atanor.vwserver.admin.mvp.place.PresetPlace;
import com.atanor.vwserver.admin.mvp.view.Control;
import com.atanor.vwserver.admin.mvp.view.NavigateView;
import com.atanor.vwserver.admin.ui.Utils;
import com.google.gwt.place.shared.Place;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Cursor;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

public class DefaultNavigateView extends HLayout implements NavigateView {

	private final Canvas presetControl;
	private final Canvas layoutControl;
	private final Canvas displayControl;

	public DefaultNavigateView() {
		setHeight(Utils.NAVIGATION_DISPLAY_HEIGHT);
		setWidth100();
		setBackgroundColor("grey");
		setMembersMargin(20);
		setDefaultLayoutAlign(Alignment.CENTER);
		setLayoutLeftMargin(20);
		
		presetControl = createNavigateControl("presets.png", "Presets", new PresetPlace());
		layoutControl = createNavigateControl("layouts.png", "Layouts", new LayoutPlace());
		displayControl = createNavigateControl("displays.png", "Displays", new DisplayPlace());

		addMembers(presetControl, layoutControl, displayControl, new LayoutSpacer());
	}

	private Canvas createNavigateControl(final String imgSource, final String tooltip, final Place place) {
		final Img img = new Img();
		img.setSrc(imgSource);
		img.setCursor(Cursor.HAND);
		img.setTooltip(tooltip);
		img.setWidth(Utils.NAVIGATE_ICON_SIZE);
		img.setHeight(Utils.NAVIGATE_ICON_SIZE);
		img.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Client.goTo(place);
			}
		});

		return img;
	}

	@Override
	public void clean() {
		enableAllControls();
	}

	private void enableAllControls() {
		presetControl.enable();
		layoutControl.enable();
		displayControl.enable();
	}

	@Override
	public void select(final Control control) {

		switch (control) {
		case Presets:
			enableAllControls();
			presetControl.disable();
			break;
		case Layouts:
			enableAllControls();
			layoutControl.disable();
			break;
		case Displays:
			enableAllControls();
			displayControl.disable();
			break;
		default:
			break;
		}
	}
}
