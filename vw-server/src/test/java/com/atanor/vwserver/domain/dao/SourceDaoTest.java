package com.atanor.vwserver.domain.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import junit.framework.Assert;

import org.junit.Test;

import com.atanor.vwserver.domain.entity.Source;

public class SourceDaoTest extends BaseDaoTest<Source> {

	private static final String SOURCE_CODE = "code";
	private static final String SOURCE_DESC = "description";

	@Test
	public void testInsertRecord() throws Exception {
		Source source = createSource();

		Assert.assertNotNull(dao.insert(source));
	}

	@Test(expected = PersistenceException.class)
	public void testInsertRecordCodeDuplicate() throws Exception {
		Source source1 = createSource();
		dao.insert(source1);

		Source source2 = new Source();
		source2.setCode(SOURCE_CODE);
		dao.insert(source2);
	}

	@Test(expected = PersistenceException.class)
	public void testInsertRecordDescriptionDuplicate() throws Exception {
		Source source1 = createSource();
		dao.insert(source1);

		Source source2 = new Source();
		source2.setDescription(SOURCE_DESC);
		dao.insert(source2);
	}

	@Test
	public void testDeleteRecord() throws Exception {
		Source source = createSource();

		Long id = dao.insert(source);
		source = dao.find(id);
		dao.delete(source);
	}

	@Test
	public void testSelect() throws Exception {
		Source source = createSource();

		Long id = dao.insert(source);

		Source sourceFromDB = dao.find(id);
		Assert.assertNotNull(sourceFromDB);
		Assert.assertEquals(SOURCE_CODE, sourceFromDB.getCode());
		Assert.assertEquals(SOURCE_DESC, sourceFromDB.getDescription());
	}

	@Test
	public void testUpdate() throws Exception {
		Source source = createSource();

		Long id = dao.insert(source);

		Source sourceFromDB = dao.find(id);
		Assert.assertNotNull(sourceFromDB);
		Assert.assertEquals(SOURCE_CODE, sourceFromDB.getCode());

		sourceFromDB.setCode("code1");
		dao.update(sourceFromDB);

		sourceFromDB = dao.find(id);
		Assert.assertNotNull(sourceFromDB);
		Assert.assertEquals("code1", sourceFromDB.getCode());
	}

	@Test
	public void testGetAll() {
		Source source1 = new Source();
		source1.setCode("code1");
		source1.setDescription("description1");

		Source source2 = new Source();
		source2.setCode("code2");
		source2.setDescription("description2");

		Source source3 = new Source();
		source3.setCode("code3");
		source3.setDescription("description3");

		dao.insert(source1);
		dao.insert(source2);
		dao.insert(source3);

		List<Source> allSources = dao.findAll();
		Assert.assertEquals(3, allSources.size());
		Assert.assertEquals("code1", allSources.get(0).getCode());
		Assert.assertEquals("code2", allSources.get(1).getCode());
		Assert.assertEquals("code3", allSources.get(2).getCode());
	}

	private Source createSource() {
		Source source = new Source();
		source.setCode(SOURCE_CODE);
		source.setDescription(SOURCE_DESC);
		return source;
	}
}
