package com.easytnt.grading.repository.impl;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations= {"classpath:applicationContext.xml","classpath*:applicationContext-*.xml"})
public class HiberConfiguationTest  extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Test
	public void testMappings() throws Exception{
		logger.debug(sessionFactory.getAllClassMetadata());
	}

}
