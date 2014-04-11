package com.atanor.vwserver.domain.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.atanor.vwserver.domain.entity.Preset;
import com.atanor.vwserver.domain.entity.PresetWindow;
import com.atanor.vwserver.domain.entity.Source;
import com.google.common.collect.Lists;

public class PresetDaoTest extends BaseDaoTest<Preset> {

	private static final Date CREATE_TS = new Date();

	@Test
	public void testInsertRecord() throws Exception {
		Preset preset = new Preset();
		preset.setName("Preset1");
		preset.setCreateTS(CREATE_TS);

		PresetWindow w1 = new PresetWindow("Window 1", 1, 2, 1, 2, 1);
		w1.setSource(new Source("input1", "VIDEO"));
		PresetWindow w2 = new PresetWindow("Window 2", 3, 1, 1, 1, 2);
		w2.setSource(new Source("input2", "CAMERA1"));
		PresetWindow w3 = new PresetWindow("Window 2", 3, 1, 2, 1, 3);
		w3.setSource(new Source("input3", "CAMERA2"));
		populatePreset(preset, Lists.newArrayList(w1, w2, w3));
		preset.setWindows(Lists.newArrayList(w1, w2, w3));

		Assert.assertNotNull(dao.insert(preset));
	}

	@Test
	public void testDeleteRecord() throws Exception {
		Preset preset = new Preset();

		Long id = dao.insert(preset);
		preset = dao.find(id);
		dao.delete(preset);
	}

	@Test
	public void testSelect() throws Exception {
		Preset preset = new Preset();
		preset.setName("Preset1");
		preset.setCreateTS(CREATE_TS);

		PresetWindow w1 = new PresetWindow("Window 1", 1, 2, 1, 2, 1);
		w1.setSource(new Source("input1", "VIDEO"));
		PresetWindow w2 = new PresetWindow("Window 2", 3, 1, 1, 1, 2);
		w2.setSource(new Source("input2", "CAMERA1"));
		PresetWindow w3 = new PresetWindow("Window 3", 3, 1, 2, 1, 3);
		w3.setSource(new Source("input3", "CAMERA2"));
		populatePreset(preset, Lists.newArrayList(w1, w2, w3));
		preset.setWindows(Lists.newArrayList(w1, w2, w3));

		Long id = dao.insert(preset);

		Preset presetFromDB = dao.find(id);
		Assert.assertNotNull(presetFromDB);
		Assert.assertEquals("Preset1", presetFromDB.getName());
		Assert.assertEquals(CREATE_TS, presetFromDB.getCreateTS());
		
		Assert.assertEquals(3, presetFromDB.getWindows().size());

		Assert.assertEquals("Window 1", presetFromDB.getWindows().get(0).getName());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(0).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(2), presetFromDB.getWindows().get(0).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(0).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(2), presetFromDB.getWindows().get(0).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(0).getZIndex());
		Assert.assertNotNull(presetFromDB.getWindows().get(0).getSource());
		Assert.assertEquals("input1", presetFromDB.getWindows().get(0).getSource().getCode());
		Assert.assertEquals("VIDEO", presetFromDB.getWindows().get(0).getSource().getDescription());

		Assert.assertEquals("Window 2", presetFromDB.getWindows().get(1).getName());
		Assert.assertEquals(Integer.valueOf(3), presetFromDB.getWindows().get(1).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(1).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(1).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(1).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(2), presetFromDB.getWindows().get(1).getZIndex());
		Assert.assertNotNull(presetFromDB.getWindows().get(1).getSource());
		Assert.assertEquals("input2", presetFromDB.getWindows().get(1).getSource().getCode());
		Assert.assertEquals("CAMERA1", presetFromDB.getWindows().get(1).getSource().getDescription());
		
		Assert.assertEquals("Window 3", presetFromDB.getWindows().get(2).getName());
		Assert.assertEquals(Integer.valueOf(3), presetFromDB.getWindows().get(2).getXTopLeft());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(2).getYTopLeft());
		Assert.assertEquals(Integer.valueOf(2), presetFromDB.getWindows().get(2).getXBottomRight());
		Assert.assertEquals(Integer.valueOf(1), presetFromDB.getWindows().get(2).getYBottomRight());
		Assert.assertEquals(Integer.valueOf(3), presetFromDB.getWindows().get(2).getZIndex());
		Assert.assertNotNull(presetFromDB.getWindows().get(2).getSource());
		Assert.assertEquals("input3", presetFromDB.getWindows().get(2).getSource().getCode());
		Assert.assertEquals("CAMERA2", presetFromDB.getWindows().get(2).getSource().getDescription());
	}

	@Test
	public void testUpdate() throws Exception {
		Preset preset = new Preset();
		PresetWindow w0 = new PresetWindow();
		w0.setPreset(preset);
		preset.setWindows(Arrays.asList(w0));

		Long id = dao.insert(preset);
		Preset presetFromDB = dao.find(id);
		Assert.assertNotNull(presetFromDB);
		Assert.assertEquals(1, presetFromDB.getWindows().size());

		presetFromDB.setWindows(null);
		dao.update(presetFromDB);

		presetFromDB = dao.find(id);
		Assert.assertNotNull(presetFromDB);
		Assert.assertNull(presetFromDB.getWindows());
	}

	@Test
	public void testUpdateByNewRecord() throws Exception {
		Preset preset = new Preset();
		PresetWindow w0 = new PresetWindow();
		w0.setPreset(preset);
		preset.setWindows(Arrays.asList(w0));

		Long id = dao.insert(preset);

		preset = new Preset(id);
		dao.update(preset);
		Preset presetFromDB = dao.find(id);
		Assert.assertNotNull(presetFromDB);
		Assert.assertNull(presetFromDB.getWindows());
	}

	@Test
	public void testGetAll() {
		Preset preset1 = new Preset();
		Preset preset2 = new Preset();
		Preset preset3 = new Preset();

		dao.insert(preset1);
		dao.insert(preset2);
		dao.insert(preset3);

		List<Preset> allDisplay = dao.findAll();
		Assert.assertEquals(3, allDisplay.size());
	}

	private void populatePreset(Preset preset, List<PresetWindow> screens) {
		for (PresetWindow screen : screens) {
			screen.setPreset(preset);
		}
	}
}
