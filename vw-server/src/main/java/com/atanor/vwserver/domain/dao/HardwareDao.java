package com.atanor.vwserver.domain.dao;

import com.atanor.vwserver.domain.entity.Hardware;


public class HardwareDao extends GenericDaoImpl<Hardware, Long> {

	@Override
	public Class<Hardware> getEntityClass() {
		return Hardware.class;
	}

}
