package com.easytnt.grading.repository.impl;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import com.easytnt.commons.util.SpringContextUtil;
import com.easytnt.grading.service.impl.ListDataMapperImpl;
import com.easytnt.grading.service.impl.ListDataSourceReaderImpl;
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
		ExamineeDataImpoirtor tor=null;
		try{
			ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml","classpath*:applicationContext-*.xml"});
			SpringContextUtil sp = new SpringContextUtil();
			sp.setApplicationContext(context);
			JdbcTemplate jt = SpringContextUtil.getBean("jdbcTemplate");
			tor = new ExamineeDataImpoirtor(jt,getSession(),mapper,reader);
			tor.doImport();
		}catch(Exception e){
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		assertTrue(tor.getErrorDatas().size()==1);
		this.commit();
	}
	@Test
	public void testFileSave()throws Exception{
		Map<String,String> valueMap = new LinkedHashMap<String,String>();
		valueMap.put("student_number", "学号");
		valueMap.put("student_name", "姓名");
		valueMap.put("gender", "性别");
		valueMap.put("nation", "民族");
		valueMap.put("birthday", "出生日期");
		valueMap.put("seating_number", "座位");
		valueMap.put("examinne_uuid", "准考证号");
		valueMap.put("uuid_type", "身份证");
		valueMap.put("arts", "文理科标志");
		valueMap.put("clazz_name", "班级名称");
		valueMap.put("clazz_code", "班级代码");
		valueMap.put("room_number", "考场编号");
		valueMap.put("school_name", "学校名称");
		valueMap.put("school_code", "学校代码");
		valueMap.put("district_number", "区县代码");
		valueMap.put("district_name", "区县名称");
		File f = new File(this.getClass().getResource("").getPath());
		File f1 = new File(f.toPath()+"\\学生导入.xls");
		File f2 = new File(f.toPath()+"\\学生导入.xls");
		ListDataMapperImpl mapper = new ListDataMapperImpl(new FileInputStream(f1),f1.getName(),valueMap);
		ListDataSourceReaderImpl reader = new ListDataSourceReaderImpl(new FileInputStream(f2),f2.getName());
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml","classpath*:applicationContext-*.xml"});
		SpringContextUtil sp = new SpringContextUtil();
		sp.setApplicationContext(context);
		JdbcTemplate jt = SpringContextUtil.getBean("jdbcTemplate");
		
		this.beginTransaction();
		try{
			new ExamineeDataImpoirtor(jt,getSession(),mapper,reader).doImport();
		}catch(Exception e){
			e.printStackTrace();
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
		this.commit();
	}
}
