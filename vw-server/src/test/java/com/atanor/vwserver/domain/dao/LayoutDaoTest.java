package com.atanor.vwserver.domain.dao;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.atanor.vwserver.domain.entity.Layout;
import com.atanor.vwserver.domain.entity.LayoutWindow;
import com.google.common.collect.Lists;

public class LayoutDaoTest extends BaseDaoTest<Layout> {

	@Test
	public void testInsertRecord() throws Exception {
		Layout layout = new Layout();
		layout.setName("Layout1");

		LayoutWindow w1 = new LayoutWindow("Window 1", 1, 2, 3, 4);
		LayoutWindow w2 = new LayoutWindow("Window 2", 2, 3, 4, 5);
		LayoutWindow w3 = new LayoutWindow("Window 3", 3, 4, 5, 6);
		populate(layout, Lists.newArrayList(w1, w2, w3));
		layout.setWindows(Lists.newArrayList(w1, w2, w3));

		Assert.assertNotNull(dao.insert(layout));
	}

	@Test
	public void testDeleteRecord() throws Exception {
		Layout layout = new Layout();

		Long id = dao.insert(layout);
		layout = dao.find(id);
		dao.delete(layout);
	}

	@Test
	public void testSelect() throws Exception {
		Layout layout = new Layout();
		layout.setName("Layout1");

		LayoutWindow w1 = new LayoutWindow("Window 1", 1, 2, 3, 4);
		LayoutWindow w2 = new LayoutWindow("Window 2", 2, 3, 4, 5);
		LayoutWindow w3 = new LayoutWindow("Window 3", 3, 4, 5, 6);
		populate(layout, Lists.newArrayList(w1, w2, w3));
		layout.setWindows(Lists.newArrayList(w1, w2, w3));

		Long id = dao.insert(layout);

		Layout layoutFromDB = dao.find(id);
		Assert.assertNotNull(layoutFromDB);
		Assert.assertEquals("Layout1", layoutFromDB.getName());

		Assert.assertEquals(3, layoutFromDB.getWindows().size());

		Assert.assertEquals("Window 1", layoutFromDB.getWindows().get(0).getName());
		Assert.assertEquals(Integer.valueOf(1), layoutFromDB.getWindows().get(0).getLeft());
		Assert.assertEquals(Integer.valueOf(2), layoutFromDB.getWindows().get(0).getTop());
		Assert.assertEquals(Integer.valueOf(3), layoutFromDB.getWindows().get(0).getWidth());
		Assert.assertEquals(Integer.valueOf(4), layoutFromDB.getWindows().get(0).getHeight());

		Assert.assertEquals("Window 2", layoutFromDB.getWindows().get(1).getName());
		Assert.assertEquals(Integer.valueOf(2), layoutFromDB.getWindows().get(1).getLeft());
		Assert.assertEquals(Integer.valueOf(3), layoutFromDB.getWindows().get(1).getTop());
		Assert.assertEquals(Integer.valueOf(4), layoutFromDB.getWindows().get(1).getWidth());
		Assert.assertEquals(Integer.valueOf(5), layoutFromDB.getWindows().get(1).getHeight());

		Assert.assertEquals("Window 3", layoutFromDB.getWindows().get(2).getName());
		Assert.assertEquals(Integer.valueOf(3), layoutFromDB.getWindows().get(2).getLeft());
		Assert.assertEquals(Integer.valueOf(4), layoutFromDB.getWindows().get(2).getTop());
		Assert.assertEquals(Integer.valueOf(5), layoutFromDB.getWindows().get(2).getWidth());
		Assert.assertEquals(Integer.valueOf(6), layoutFromDB.getWindows().get(2).getHeight());
	}

	@Test
	public void testUpdate() throws Exception {
		Layout layout = new Layout();
		LayoutWindow w0 = new LayoutWindow();
		w0.setLayout(layout);
		layout.setWindows(Arrays.asList(w0));

		Long id = dao.insert(layout);
		Layout layoutFromDB = dao.find(id);
		Assert.assertNotNull(layoutFromDB);
		Assert.assertEquals(1, layoutFromDB.getWindows().size());

		layoutFromDB.setWindows(null);
		dao.update(layoutFromDB);

		layoutFromDB = dao.find(id);
		Assert.assertNotNull(layoutFromDB);
		Assert.assertNull(layoutFromDB.getWindows());
	}

	@Test
	public void testUpdateByNewRecord() throws Exception {
		Layout layout = new Layout();
		LayoutWindow w0 = new LayoutWindow();
		w0.setLayout(layout);
		layout.setWindows(Arrays.asList(w0));

		Long id = dao.insert(layout);

		layout = new Layout(id);
		dao.update(layout);
		Layout layoutFromDB = dao.find(id);
		Assert.assertNotNull(layoutFromDB);
		Assert.assertNull(layoutFromDB.getWindows());
	}

	@Test
	public void testGetAll() {
		Layout layout1 = new Layout();
		Layout layout2 = new Layout();
		Layout layout3 = new Layout();

		dao.insert(layout1);
		dao.insert(layout2);
		dao.insert(layout3);

		List<Layout> allLayout = dao.findAll();
		Assert.assertEquals(3, allLayout.size());
	}

	private void populate(Layout layout, List<LayoutWindow> windows) {
		for (LayoutWindow window : windows) {
			window.setLayout(layout);
		}
	}

}
