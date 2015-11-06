package com.easytnt.grading.repository.impl;


import java.util.List;

import org.hibernate.Query;
import org.junit.Before;
import org.junit.Test;

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
	public void testSelect()throws Exception{
		this.sessionFactory.openSession();
		Query q =  this.getSession().createQuery(" from ExamPaper");
		List<ExamPaper> examList = q.list();
		System.out.println(1);
		for(ExamPaper ep:examList){
			System.out.println(ep.getPaperOid());
			System.out.println(ep.getSections().size());
			for(Section sec:ep.getSections()){
				System.out.println(sec.getSectionOid());
			}
		}
		this.sessionFactory.close();
	}
	
	@Test
	public void testSave()throws Exception{
		ExamPaper examPaper = new ExamPaper();
		examPaper.setName("考卷1");
		examPaper.setPaperOid(1l);
		examPaper.setFullScore(100f);
		
		PaperType type= new PaperType();
		type.setTypeName("A卷");
		examPaper.setPaperType(type);
		
		Area area = new Area(1,1,1,1);
		
		Section section1 = new Section();
		section1.setPaper(examPaper);
		section1.setArea(area);
		section1.setTitle("大题 1");
		section1.setCaption("内容1");
		section1.setMaxPinci("5");
		section1.setFullScore(5f);
		section1.setMaxerror(5f);
		examPaper.addSections(section1);
		
		Section section2 = new Section();
		section2.setPaper(examPaper);
		section2.setArea(area);
		section2.setTitle("大题 2");
		section2.setCaption("内容2");
		section2.setMaxPinci("5");
		section2.setFullScore(5f);
		section2.setMaxerror(5f);
		examPaper.addSections(section2);
		
		Section section3 = new Section();
		section3.setPaper(examPaper);
		section3.setArea(area);
		section3.setTitle("大题 3");
		section3.setCaption("内容3");
		section3.setMaxPinci("5");
		section3.setFullScore(5f);
		section3.setMaxerror(5f);
		examPaper.addSections(section3);
		
		Section section4 = new Section();
		section4.setPaper(examPaper);
		section4.setArea(area);
		section4.setTitle("大题 4");
		section4.setCaption("内容4");
		section4.setMaxPinci("5");
		section4.setFullScore(5f);
		section4.setMaxerror(5f);
		examPaper.addSections(section4);
		
		
		this.beginTransaction();
		saveOrUpdate(examPaper);
		this.commit();
		this.beginTransaction();
		section4.setTitle("修改大题 4");
		examPaper.removeSections(section4);
		saveOrUpdate(examPaper);
		this.commit();
//		clear(examPaper);
	}
}
