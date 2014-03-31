package com.atanor.vwserver.domain.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import junit.framework.Assert;

import org.junit.Test;

import com.atanor.vwserver.domain.entity.Display;

public class DisplayDaoTest extends BaseDaoTest<Display> {

	private static final String DISPLAY_IMG = "asdfsdfgsdfgdsgh12345145dsfhwdghwghfghfgh";
	private static final Integer SEGMENT_NUM_WIDTH = 3;
	private static final Integer SEGMENT_NUM_HEIGHT = 2;
	private static final String RESOLUTION = "1920x1080";
	private static final String ORIENTATION = "Landscape";
	private static final Date CREATE_TS = new Date();

	@Test
	public void testInsertRecord() throws Exception {
		Display display = new Display();
		display.setName("Preset1");

		Assert.assertNotNull(dao.insert(display));
	}

	@Test(expected = PersistenceException.class)
	public void testInsertRecordNameDuplicate() throws Exception {
		Display display1 = createDisplay();
		dao.insert(display1);

		Display display2 = createDisplay();
		dao.insert(display2);
	}

	@Test
	public void testDeleteRecord() throws Exception {
		Display display = new Display();

		Long id = dao.insert(display);
		display = dao.find(id);
		dao.delete(display);
	}

	@Test
	public void testSelect() throws Exception {
		Display display = createDisplay();

		Long id = dao.insert(display);

		Display displayFromDB = dao.find(id);
		Assert.assertNotNull(displayFromDB);
		Assert.assertEquals("Display1", displayFromDB.getName());
		Assert.assertEquals(ORIENTATION, displayFromDB.getOrientation());
		Assert.assertEquals(RESOLUTION, displayFromDB.getResolution());
		Assert.assertEquals(SEGMENT_NUM_HEIGHT, displayFromDB.getSegmentNumHeight());
		Assert.assertEquals(SEGMENT_NUM_WIDTH, displayFromDB.getSegmentNumWidth());
		Assert.assertEquals(DISPLAY_IMG, displayFromDB.getImgBlob());
		Assert.assertEquals(CREATE_TS, displayFromDB.getCreateTS());
	}

	@Test
	public void testUpdate() throws Exception {
		Display display = createDisplay();

		Long id = dao.insert(display);

		Display displayFromDB = dao.find(id);
		Assert.assertNotNull(displayFromDB);
		Assert.assertEquals("Display1", displayFromDB.getName());

		displayFromDB.setName("Display2");
		dao.update(displayFromDB);

		displayFromDB = dao.find(id);
		Assert.assertNotNull(displayFromDB);
		Assert.assertEquals("Display2", displayFromDB.getName());
	}

	@Test
	public void testGetAll() {
		Display display1 = new Display();
		display1.setName("Display1");
		display1.setCreateTS(new Date(1000));
		
		Display display2 = new Display();
		display2.setName("Display2");
		display2.setCreateTS(new Date(5000));
		
		Display display3 = new Display();
		display3.setName("Display3");
		display3.setCreateTS(new Date(2000));
		
		dao.insert(display1);
		dao.insert(display2);
		dao.insert(display3);

		List<Display> allDisplays = dao.findAll();
		Assert.assertEquals(3, allDisplays.size());
		Assert.assertEquals("Display2", allDisplays.get(0).getName());
		Assert.assertEquals("Display3", allDisplays.get(1).getName());
		Assert.assertEquals("Display1", allDisplays.get(2).getName());
	}

	private Display createDisplay() {
		Display display = new Display();
		display.setName("Display1");
		display.setResolution(RESOLUTION);
		display.setOrientation(ORIENTATION);
		display.setSegmentNumHeight(SEGMENT_NUM_HEIGHT);
		display.setSegmentNumWidth(SEGMENT_NUM_WIDTH);
		display.setImgBlob(DISPLAY_IMG);
		display.setCreateTS(CREATE_TS);
		return display;
	}
}
