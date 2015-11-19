package com.easytnt.grading.repository.impl;
import org.hibernate.Query;
import org.junit.Before;
import org.junit.Test;

import com.easytnt.test.repository.AbstractHibernateTest;

public class ExamineeRepositoryHibernateImplTest extends AbstractHibernateTest{

	private ExamineeRepositoryHibernateImpl repository;
	
	@Before
	public void before()throws Exception{
		initHibernate("hibernate/mapping/paper/ExamPaper.hbm.xml","hibernate/mapping/paper/Item.hbm.xml","hibernate/mapping/paper/PaperType.hbm.xml","hibernate/mapping/paper/Section.hbm.xml","hibernate/mapping/exam/Subject.hbm.xml","hibernate/mapping/exam/SubjectExam.hbm.xml","hibernate/mapping/paper/PaperCard.hbm.xml");
		repository =  new ExamineeRepositoryHibernateImpl();
		repository.setSessionFactory01(sessionFactory);
	}

	@Test
	public void testSave()throws Exception{
		ListDataMapperMocker mapper = new ListDataMapperMocker();
		ListDataSourceReaderMocker reader = new ListDataSourceReaderMocker();
		this.beginTransaction();
		new ExamineeDataImpoirtor(getSession(),mapper,reader).doImport();
		this.commit();
		
	}
}
