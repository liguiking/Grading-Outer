package com.easytnt.grading.repository.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SQLQuery;
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
		examPaper.setName("考卷1");
		examPaper.setPaperOid(1l);
		examPaper.setFullScore(1f);
		
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
		
		Item item1 = new Item();
		item1.setSection(section1);
		item1.setAnswerArea(area);
		item1.setTitle("给分点1");
		item1.setCaption("给分内容1");
		item1.setFullScore(5f);
		item1.setValidValues(new Float[]{1f,2f});
		section1.addItem(item1);
		
		Item item2 = new Item();
		item2.setSection(section1);
		item2.setAnswerArea(area);
		item2.setTitle("给分点2");
		item2.setCaption("给分内容2");
		item2.setFullScore(5f);
		item2.setValidValues(new Float[]{1f,2f});
		section1.addItem(item2);
		
		Item item3 = new Item();
		item3.setSection(section1);
		item3.setAnswerArea(area);
		item3.setTitle("给分点3");
		item3.setCaption("给分内容3");
		item3.setFullScore(5f);
		item3.setValidValues(new Float[]{1f,2f});
		section1.addItem(item3);
		
		Section section2 = new Section();
		section2.setPaper(examPaper);
		section2.setArea(area);
		section2.setTitle("大题 2");
		section2.setCaption("内容2");
		section2.setMaxPinci("5");
		section2.setFullScore(5f);
		section2.setMaxerror(5f);
		examPaper.addSections(section2);
		
		Item item4 = new Item();
		item4.setSection(section2);
		item4.setAnswerArea(area);
		item4.setTitle("给分点4");
		item4.setCaption("给分内容4");
		item4.setFullScore(5f);
		item4.setValidValues(new Float[]{1f,2f});
		section2.addItem(item4);
		
		Item item5 = new Item();
		item5.setSection(section2);
		item5.setAnswerArea(area);
		item5.setTitle("给分点5");
		item5.setCaption("给分内容5");
		item5.setFullScore(5f);
		item5.setValidValues(new Float[]{1f,2f});
		section2.addItem(item5);
		
		Item item6 = new Item();
		item6.setSection(section2);
		item6.setAnswerArea(area);
		item6.setTitle("给分点6");
		item6.setCaption("给分内容6");
		item6.setFullScore(5f);
		item6.setValidValues(new Float[]{1f,2f});
		section2.addItem(item6);
		
		Section section3 = new Section();
		section3.setPaper(examPaper);
		section3.setArea(area);
		section3.setTitle("大题 3");
		section3.setCaption("内容3");
		section3.setMaxPinci("5");
		section3.setFullScore(5f);
		section3.setMaxerror(5f);
		examPaper.addSections(section3);
		
		Item item7 = new Item();
		item7.setSection(section3);
		item7.setAnswerArea(area);
		item7.setTitle("给分点7");
		item7.setCaption("给分内容7");
		item7.setFullScore(5f);
		item7.setValidValues(new Float[]{1f,2f});
		section3.addItem(item7);
		
		Item item8 = new Item();
		item8.setSection(section3);
		item8.setAnswerArea(area);
		item8.setTitle("给分点5");
		item8.setCaption("给分内容5");
		item8.setFullScore(5f);
		item8.setValidValues(new Float[]{1f,2f});
		section3.addItem(item8);
		
		Item item9 = new Item();
		item9.setSection(section3);
		item9.setAnswerArea(area);
		item9.setTitle("给分点9");
		item9.setCaption("给分内容9");
		item9.setFullScore(5f);
		item9.setValidValues(new Float[]{1f,2f});
		section3.addItem(item9);
		
		Section section4 = new Section();
		section4.setPaper(examPaper);
		section4.setArea(area);
		section4.setTitle("大题 4");
		section4.setCaption("内容4");
		section4.setMaxPinci("5");
		section4.setFullScore(5f);
		section4.setMaxerror(5f);
		examPaper.addSections(section4);
		
		Item item10 = new Item();
		item10.setSection(section4);
		item10.setAnswerArea(area);
		item10.setTitle("给分点10");
		item10.setCaption("给分内容10");
		item10.setFullScore(5f);
		item10.setValidValues(new Float[]{1f,2f});
		section4.addItem(item10);
		
		Item item11 = new Item();
		item11.setSection(section4);
		item11.setAnswerArea(area);
		item11.setTitle("给分点11");
		item11.setCaption("给分内容11");
		item11.setFullScore(5f);
		item11.setValidValues(new Float[]{1f,2f});
		section4.addItem(item11);
		
		Item item12 = new Item();
		item12.setSection(section4);
		item12.setAnswerArea(area);
		item12.setTitle("给分点12");
		item12.setCaption("给分内容12");
		item12.setFullScore(5f);
		item12.setValidValues(new Float[]{1f,2f});
		section4.addItem(item12);
		
		Section section5 = new Section();
		section5.setPaper(examPaper);
		section5.setArea(area);
		section5.setTitle("大题 5");
		section5.setCaption("内容5");
		section5.setMaxPinci("5");
		section5.setFullScore(5f);
		section5.setMaxerror(5f);
		
		
		this.beginTransaction();
		saveOrUpdate(examPaper);
		this.commit();
		examPaper.removeSections(section4);
		clear(section4);
		
		examPaper.addSections(section5);
		this.beginTransaction();
		Item item13 = new Item();
		item13.setSection(section5);
		item13.setAnswerArea(area);
		item13.setTitle("给分点13");
		item13.setCaption("给分内容13");
		item13.setFullScore(5f);
		item13.setValidValues(new Float[]{1f,2f});
		section5.addItem(item13);
		
		Item item14 = new Item();
		item14.setSection(section5);
		item14.setAnswerArea(area);
		item14.setTitle("给分点14");
		item14.setCaption("给分内容14");
		item14.setFullScore(5f);
		item14.setValidValues(new Float[]{1f,2f});
		section5.addItem(item14);
		
		Item item15 = new Item();
		item15.setSection(section5);
		item15.setAnswerArea(area);
		item15.setTitle("给分点12");
		item15.setCaption("给分内容12");
		item15.setFullScore(5f);
		item15.setValidValues(new Float[]{1f,2f});
		section5.addItem(item15);
		
		saveOrUpdate(examPaper);
		
		section5.setTitle("修改大题 5");
		section5.setCaption("修改内容5");
		examPaper.updateSections(section5);
		
		saveOrUpdate(examPaper);
		this.commit();
//		clear(examPaper);
	}
}
