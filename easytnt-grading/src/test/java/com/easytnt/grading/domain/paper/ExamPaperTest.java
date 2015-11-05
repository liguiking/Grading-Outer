package com.easytnt.grading.domain.paper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.easytnt.grading.domain.share.Area;

public class ExamPaperTest {

	

	
	@Before
	public void before()throws Exception{
	}
	
	@Test
	public void testSave()throws Exception{
		ExamPaper examPaper = new ExamPaper();
		examPaper.setName("考卷1");
		examPaper.setPaperOid(1l);
		examPaper.setFullScore(6f);
		
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
		
	}
	
	@Test
	public void testAddSection()throws Exception{
		ExamPaper examPaper = new ExamPaper();
		examPaper.setName("考卷1");
		examPaper.setPaperOid(1000l);
		examPaper.setFullScore(10f);
		
		Section section1 = new Section();
		section1.setFullScore(5f);
		examPaper.addSections(section1);
		assertNotNull(examPaper.getSections());
		assertTrue(section1.getPaper().equals(examPaper));
		assertTrue(section1.getSectionOid()==100001l);
	}
}
