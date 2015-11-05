package com.easytnt.grading.repository.impl;


import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.easytnt.grading.domain.exam.Subject;
import com.easytnt.grading.domain.paper.ExamPaper;
import com.easytnt.grading.domain.paper.Item;
import com.easytnt.grading.domain.paper.PaperType;
import com.easytnt.grading.domain.paper.Section;
import com.easytnt.grading.domain.share.Area;
import com.easytnt.test.repository.AbstractHibernateTest;

public class ExamPaperRepositoryHibernateImplTest extends AbstractHibernateTest{

	private ExamPaperRepositoryHibernateImpl repository;
	

	
	@Before
	public void before()throws Exception{
		initHibernate("hibernate/mapping/paper/ExamPaper.hbm.xml","hibernate/mapping/paper/item.hbm.xml","hibernate/mapping/paper/PaperType.hbm.xml","hibernate/mapping/paper/Section.hbm.xml","hibernate/mapping/exam/Subject.hbm.xml","hibernate/mapping/exam/SubjectExam.hbm.xml");
		repository =  new ExamPaperRepositoryHibernateImpl();
		repository.setSessionFactory01(sessionFactory);
	}
	
	@Test
	public void testSave()throws Exception{
		ExamPaper examPaper = new ExamPaper();
		examPaper.setName("abc");
		examPaper.setPaperOid(1l);
		examPaper.setFullScore(1f);
		
		PaperType type= new PaperType();
		type.setTypeName("123");
		examPaper.setPaperType(type);
		
		Area area = new Area(1,1,1,1);
		
		Subject sub = new Subject();
		sub.setName("小红");
		sub.setCode(1);
		Section section = new Section();
		section.setPaper(examPaper);
		section.setSectionOid(1l);
		section.setArea(area);
		section.setTitle("abc");
		section.setCaption("123");
		section.setMaxPinci("zxc");
		section.setFullScore(1f);
		section.setMaxerror(2f);
		section.setSubject(sub);
		
		Item item = new Item();
		item.setItemOid(1l);
		item.setTitle("qwe");
		item.setCaption("asd");
		item.setAnswerArea(area);
		item.setFullScore(2f);
		item.setValidValues(new Float[]{1f,2f});
		item.setSection(section);
		Set<Item> items = new HashSet<Item>();
		items.add(item);
		section.setItems(items);
		
		Set<Section> sections = new HashSet<Section>();
		sections.add(section);
		examPaper.setSections(sections);
		
		this.beginTransaction();
		saveOrUpdate(examPaper);
		
		this.commit();
//		clear(examPaper);
	}
}
