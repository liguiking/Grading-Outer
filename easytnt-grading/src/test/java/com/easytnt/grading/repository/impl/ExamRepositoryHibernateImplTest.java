package com.easytnt.grading.repository.impl;


import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import com.easytnt.test.repository.AbstractHibernateTest;

public class ExamRepositoryHibernateImplTest extends AbstractHibernateTest{

	private ExamRepositoryHibernateImpl repository;
	
	private Transaction tx;
	
	@Before
	public void before()throws Exception{
		initHibernate("hibernate/mapping/a.hbm");
		repository =  new ExamRepositoryHibernateImpl();
		repository.setSessionFactory01(sessionFactory);
		tx = session.beginTransaction();
	}
	
	@Test
	public void testSave()throws Exception{
		tx.rollback();
	}
}
