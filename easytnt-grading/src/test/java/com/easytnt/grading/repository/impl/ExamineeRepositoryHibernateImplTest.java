package com.easytnt.grading.repository.impl;
import org.hibernate.Query;
import org.junit.Before;
import org.junit.Test;

import com.easytnt.test.repository.AbstractHibernateTest;

public class ExamineeRepositoryHibernateImplTest extends AbstractHibernateTest{

	private ExamineeRepositoryHibernateImpl repository;
	
	@Before
	public void before()throws Exception{
		repository =  new ExamineeRepositoryHibernateImpl();
		repository.setSessionFactory01(sessionFactory);
	}

	@Test
	public void testSave()throws Exception{
		ListDataMapperMocker mapper = new ListDataMapperMocker();
		ListDataSourceReaderMocker reader = new ListDataSourceReaderMocker();
		int num =0;
		reader.open();
		num = mapper.getColIndex("district_name");
		String district_name =  reader.get(1, num);
		num = mapper.getColIndex("district_number");
		String district_number =  reader.get(1, num);
		
		System.out.println("-----------------------");
		System.out.println(district_name);
		System.out.println(district_number);
		System.out.println(num);
		if(num>0){
			String sql ="insert into district(parent_id,district_number,district_name) "
				+ " values(1,'"+district_number+"','"+district_name+"');";
			//获取插入后的id
			num = mapper.getColIndex("district_id");
			String districtid =  reader.get(1, num);
			int district_id = Integer.parseInt(districtid);
			System.out.println(district_id);
			
			for(int i =0;i<3;i++){
				num = mapper.getColIndex("school_name");
				String school_name =  reader.get(i, num);
				System.out.println(school_name);
				num = mapper.getColIndex("school_code");
				String school_code =  reader.get(i, num);
				System.out.println(school_code);
				sql += "insert into school(district_id,school_name,school_code) "
						+ " values("+district_id+",'"+school_name+"','"+school_code+"');";
				this.beginTransaction();
				Query q = this.getSession().createSQLQuery(sql);
			    int nums =	q.executeUpdate();
			     
			     if(nums>0){
			    	 System.out.println("我去进去了");
			     }else{
			    	 System.out.println("什么鬼");
			     }
				this.commit();
			}

			
		}
		//saveOrUpdate(null);
		
		//clear(null);
	}
}
