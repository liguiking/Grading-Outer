package com.easytnt.grading.repository.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.easytnt.commons.entity.repository.HibernateRepository;
import com.easytnt.grading.repository.ExamineeRepository;
import com.easytnt.grading.service.ListDataMapper;
import com.easytnt.grading.service.ListDataSourceReader;

@SuppressWarnings("rawtypes")
@Repository
public class ExamineeRepositoryHibernateImpl extends HibernateRepository implements ExamineeRepository {
	private static Query query = null;
	//读取数据
	@Override
	public int insertImports(ListDataMapper mapper, ListDataSourceReader reader) {
		try {
			int colindex = 0;
			String sql = "";
			reader.open();
		   
			for(int i=1;;i++){
				//获取student表字段列
				colindex = mapper.getColIndex("student_number");
				String  student_number = reader.get(i, colindex);
				colindex = mapper.getColIndex("student_name");
				String  student_name = reader.get(i, colindex);
				colindex = mapper.getColIndex("gender");
				String  gender = reader.get(i, colindex);
				colindex = mapper.getColIndex("nation");
				String  nation = reader.get(i, colindex);
				colindex = mapper.getColIndex("birthday");
				String  birthday = reader.get(i, colindex);
				
				//获取district表字段列
				colindex = mapper.getColIndex("parent_id");
				String  parent_id = reader.get(i, colindex);
				colindex = mapper.getColIndex("district_number");
				String  district_number = reader.get(i, colindex);
				colindex = mapper.getColIndex("district_name");
				String  district_name = reader.get(i, colindex);
				
				//获取school表字段列
				colindex = mapper.getColIndex("school_name");
				String  school_name = reader.get(i, colindex);
			    colindex = mapper.getColIndex("school_code");
				String  school_code = reader.get(i, colindex);
				
			    //获取room表字段列
				colindex = mapper.getColIndex("room_number");
				String  room_number = reader.get(i, colindex);
				
			    //获取examinne表字段列
				colindex = mapper.getColIndex("seating_number");
				String  seating_number = reader.get(i, colindex);
			    colindex = mapper.getColIndex("examinne_name");	
				String  examinne_name = reader.get(i, colindex);
			    colindex = mapper.getColIndex("examinne_uuid");
				String  examinne_uuid = reader.get(i, colindex);
			    colindex = mapper.getColIndex("uuid_type");
				String  uuid_type = reader.get(i, colindex);
			    colindex = mapper.getColIndex("arts");	
				String  arts = reader.get(i, colindex);
			    colindex = mapper.getColIndex("clazz_name");
				String  clazz_name = reader.get(i, colindex);
			    colindex = mapper.getColIndex("clazz_code");
				String  clazz_code = reader.get(i, colindex);
			    colindex = mapper.getColIndex("absence");
				String  absence = reader.get(i, colindex);
			    colindex = mapper.getColIndex("total_score");
				String  total_score = reader.get(i, colindex);
				
				//测试测试
				sql = "insert into student values("+student_number+","+student_name+","+gender+","+nation+","+birthday+");";
				sql += "insert into district values("+parent_id+","+district_number+","+district_name+");";
				sql += "insert into school values(1,"+school_name+","+school_code+");";
				sql += "insert into room values(1,"+room_number+");";
				sql += "insert into examinne values(1,1,1,1,"+seating_number+","+examinne_name+","+examinne_uuid+","+uuid_type+","+arts+","+clazz_name+","+clazz_code+","+absence+","+total_score+");";
				
				query = this.getCurrentSession().createSQLQuery(sql);
				Integer num = (Integer) query.executeUpdate();
				if(num>0){
					return num;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	protected Class getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}
}
