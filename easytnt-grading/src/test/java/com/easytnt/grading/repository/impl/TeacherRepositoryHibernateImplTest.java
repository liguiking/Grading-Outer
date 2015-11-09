package com.easytnt.grading.repository.impl;

import org.junit.Before;
import org.junit.Test;

import com.easytnt.grading.domain.exam.Subject;
import com.easytnt.grading.domain.grade.Teacher;
import com.easytnt.test.repository.AbstractHibernateTest;

public class TeacherRepositoryHibernateImplTest extends AbstractHibernateTest{

	private TeacherRepositoryHibernateImpl repository;
	

	@Before
	public void before()throws Exception{
		initHibernate("hibernate/mapping/grade/Teacher.hbm.xml","hibernate/mapping/exam/Subject.hbm.xml");
		repository =  new TeacherRepositoryHibernateImpl();
		repository.setSessionFactory01(sessionFactory);
	}
	
	@Test
	public void testSave()throws Exception{
//		
		Subject su = new Subject();
		su.setName("十年级大法");
		su.setSubjectCode(10);
		
		Teacher teacher = new Teacher();
		teacher.setTeacherName("小紫");
		teacher.setTeacherPassord("22222");
		teacher.setSubject(su);
		
		this.beginTransaction();
		//this.saveOrUpdate(su);
		this.saveOrUpdate(teacher);
		this.commit();

		//clear(teacher);
	}
}
