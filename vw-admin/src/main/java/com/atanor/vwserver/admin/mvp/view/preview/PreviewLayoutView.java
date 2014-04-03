package com.atanor.vwserver.admin.mvp.view.preview;

import java.util.Collection;
import java.util.Map;

import com.atanor.vwserver.admin.mvp.view.HeaderView;
import com.atanor.vwserver.admin.ui.LayoutImg;
import com.atanor.vwserver.admin.ui.Utils;
import com.atanor.vwserver.common.rpc.dto.LayoutDto;
import com.google.common.collect.Maps;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class PreviewLayoutView extends HLayout implements HeaderView {

	private final VLayout vLayout;
	private final Map<Long, LayoutImg> content = Maps.newHashMap();

	public PreviewLayoutView() {
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

	@Override
	public void clean() {
		unselect();
	}

	private void unselect() {
		for (final Map.Entry<Long, LayoutImg> entry : content.entrySet()) {
			entry.getValue().unselect();
		}
	}

	public void setLayouts(final Collection<LayoutDto> layouts) {
		remove();
		for (final LayoutDto layout : layouts) {
			final LayoutImg img = new LayoutImg(layout);
			content.put(layout.getId(), img);
			vLayout.addMember(img);
		}
	}

	private void remove() {
		for (final Map.Entry<Long, LayoutImg> entry : content.entrySet()) {
			vLayout.removeMember(entry.getValue());
		}
		content.clear();
	}

	public void selectLayout(final Long id) {
		unselect();
		if (content.containsKey(id)) {
			content.get(id).select();
		}
	}
}
