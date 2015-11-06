package com.easytnt.grading.repository.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.junit.Before;
import org.junit.Test;

import com.easytnt.grading.domain.exam.Subject;
import com.easytnt.test.repository.AbstractHibernateTest;

public class SubjectRepositoryHibernateImplTest extends AbstractHibernateTest{

	private SubjectRepositoryHibernateImpl repository;
	

	@Before
	public void before()throws Exception{
		initHibernate("hibernate/mapping/exam/Subject.hbm.xml");
		repository =  new SubjectRepositoryHibernateImpl();
		repository.setSessionFactory01(sessionFactory);
	}
	
	@Test
	public void testSave()throws Exception{
		Subject subject = new Subject();
		//科目新增
		subject.setSubject_id(8l);
		subject.setSubject_name("十年级语文");
		subject.setSubject_code(8);
		
		//科目删除
		//clear(subject);
		
		//科目修改
		Query q = getSession().createQuery(" from Subject where subject_id ="+9);
		ArrayList<Subject> su = (ArrayList<Subject>) q.list();
		for(Subject ssu : su){
			subject = ssu;
			subject.setSubject_name("修改成功");
		}
		
		//科目分页
		Query q1 = getSession().createQuery(" from Subject where 1=1").setFirstResult(2).setMaxResults(3);
		q1.list();
		System.out.println(q1.list());
		
		this.beginTransaction();
		saveOrUpdate(subject);
		this.commit();
		//clear(subject);
	}
}
