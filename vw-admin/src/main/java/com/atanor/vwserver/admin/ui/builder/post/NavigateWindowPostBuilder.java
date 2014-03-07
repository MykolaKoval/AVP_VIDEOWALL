package com.atanor.vwserver.admin.ui.builder.post;

import com.atanor.vwserver.admin.ui.WindowLabel;

public class NavigateWindowPostBuilder implements WindowPostBuilder {

	@Override
	public void doPostBuild(WindowLabel preset) {
		preset.setBorder("1px inset black");
		preset.setBackgroundColor("darkgrey");
	}

}
