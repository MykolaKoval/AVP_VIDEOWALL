package com.atanor.vwserver.admin.mvp.view.preview;

import java.util.Collection;
import java.util.Map;

import com.atanor.vwserver.admin.ui.DisplayImg;
import com.atanor.vwserver.admin.ui.Utils;
import com.atanor.vwserver.common.rpc.dto.DisplayDto;
import com.google.common.collect.Maps;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class PreviewDisplayView extends HLayout {

	private final VLayout vLayout;
	private final Map<Long, DisplayImg> content = Maps.newHashMap();

	public PreviewDisplayView() {
		setWidth(Utils.PREVIEW_DISPLAY_WIDTH);
		setHeight(Utils.getMainAreaHeight());

		vLayout = new VLayout();
		vLayout.setWidth100();
		vLayout.setHeight100();
		vLayout.setPadding(10);
		vLayout.setMembersMargin(10);
		vLayout.setOverflow(Overflow.AUTO);
		vLayout.setBackgroundColor("lightgrey");

		addMember(vLayout);
	}

	public void setDisplays(final Collection<DisplayDto> displays) {
		remove();
		for (DisplayDto display : displays) {
			final DisplayImg img = new DisplayImg(display);
			content.put(display.getId(), img);
			vLayout.addMember(img);
		}
	}

	private void remove() {
		for (Map.Entry<Long, DisplayImg> entry : content.entrySet()) {
			vLayout.removeMember(entry.getValue());
		}
		content.clear();
	}

	public void selectDisplay(final Long id) {
		unselect();
		if (content.containsKey(id)) {
			content.get(id).select();
		}
	}

	private void unselect() {
		for (Map.Entry<Long, DisplayImg> entry : content.entrySet()) {
			entry.getValue().unselect();
		}
	}

	public void clean() {
		unselect();
	}
}
