package com.atanor.vwserver.domain.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import junit.framework.Assert;

import org.junit.Test;

import com.atanor.vwserver.domain.entity.Display;

public class DisplayDaoTest extends BaseDaoTest<Display> {

	private static final String DISPLAY_IMG = "asdfsdfgsdfgdsgh12345145dsfhwdghwghfghfgh";
	private static final Integer SEGMENT_NUM_WIDTH = 3;
	private static final Integer SEGMENT_NUM_HEIGHT = 2;
	private static final Integer SEGMENT_WIDTH = 800;
	private static final Integer SEGMENT_HEIGHT = 600;

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
		Assert.assertEquals(SEGMENT_HEIGHT, displayFromDB.getSegmentHeight());
		Assert.assertEquals(SEGMENT_WIDTH, displayFromDB.getSegmentWidth());
		Assert.assertEquals(SEGMENT_NUM_HEIGHT, displayFromDB.getSegmentNumHeight());
		Assert.assertEquals(SEGMENT_NUM_WIDTH, displayFromDB.getSegmentNumWidth());
		Assert.assertEquals(DISPLAY_IMG, displayFromDB.getImgBlob());
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

	public void testGetAll() {
		Display display1 = new Display();
		Display display2 = new Display();
		Display display3 = new Display();

		dao.insert(display1);
		dao.insert(display2);
		dao.insert(display3);

		List<Display> allDisplays = dao.findAll();
		Assert.assertEquals(3, allDisplays.size());
	}

	private Display createDisplay() {
		Display display = new Display();
		display.setName("Display1");
		display.setSegmentHeight(SEGMENT_HEIGHT);
		display.setSegmentWidth(SEGMENT_WIDTH);
		display.setSegmentNumHeight(SEGMENT_NUM_HEIGHT);
		display.setSegmentNumWidth(SEGMENT_NUM_WIDTH);
		display.setImgBlob(DISPLAY_IMG);
		return display;
	}
}
