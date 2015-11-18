package com.easytnt.grading.repository.impl;

import java.sql.Date;

import org.hibernate.Session;

import com.easytnt.grading.service.ListDataMapper;
import com.easytnt.grading.service.ListDataSourceReader;

public class ExamineeDataImpoirtor{
    
	private ListDataMapper mapper;
	private ListDataSourceReader reader;
	private Session session;
	
	private int colindex = 0;
	private String sql = "";
	ExamineeDataImpoirtor(Session session,ListDataMapper mapper, ListDataSourceReader reader){
		
	}
	public void doImport(){
		try {
			reader.open();
			for(int i=1;i<50;i++){
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
			String  bday = reader.get(i, colindex);
			Date birthday = java.sql.Date.valueOf(bday);
			
			//获取district表字段列
			colindex = mapper.getColIndex("parent_id");		//上级
			String  parentid = reader.get(i, colindex);
			Long parent_id = Long.valueOf(parentid);
			
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
			String  roomnumber = reader.get(i, colindex);
			int room_number =Integer.parseInt(roomnumber);
			
		    //获取examinne表字段列
			colindex = mapper.getColIndex("seating_number");
			String  seatingnumber = reader.get(i, colindex);
			int seating_number =Integer.parseInt(seatingnumber);
			
		    colindex = mapper.getColIndex("examinne_name");	
			String  examinne_name = reader.get(i, colindex);
		    colindex = mapper.getColIndex("examinne_uuid");
			String  examinne_uuid = reader.get(i, colindex);
		    colindex = mapper.getColIndex("uuid_type");
			String  uuid_type = reader.get(i, colindex);
		    colindex = mapper.getColIndex("arts");	
		    
			String  art = reader.get(i, colindex);
			int arts =Integer.parseInt(art);
			
		    colindex = mapper.getColIndex("clazz_name");
			String  clazz_name = reader.get(i, colindex);
		    colindex = mapper.getColIndex("clazz_code");
			String  clazz_code = reader.get(i, colindex);
		    colindex = mapper.getColIndex("absence");
			String  absen = reader.get(i, colindex);
			int absence =Integer.parseInt(absen);
			
		    colindex = mapper.getColIndex("total_score");
			String  totalscore = reader.get(i, colindex);
			Float total_score = Float.valueOf(totalscore);
			
			//测试测试
			//插入student表
			sql = "insert into student(student_number,student_name,gender,nation,birthday) "
					+ " values('"+student_number+"','"+student_name+"','"+gender+"','"+nation+"','"+birthday+"');";
			
			//插入district表
			sql +="insert into district(parent_id,district_number,district_name) "
					+ " values("+parent_id+",'"+district_number+"','"+district_name+"');";

			//插入school表
			sql += "insert into school(district_id,school_name,school_code) "
					+ " values("+i+",'"+school_name+"','"+school_code+"');";
			
			//插入room表
			sql += "insert into room (parent_id,room_number)"
					+ " values("+parent_id+",'"+room_number+"');";

			//插入examinne表
			sql += "insert into examinne(student_id,term_test_id,school_id,room_id,seating_number,examinne_name,examinne_uuid,uuid_type,arts,clazz_name,clazz_code,absence,total_score) "
					+ " values("+i+","+i+","+i+","+i+","+seating_number+",'"+examinne_name+"','"+examinne_uuid+"','"+uuid_type+"',"+arts+",'"+clazz_name+"','"+clazz_code+"',"+absence+","+total_score+");";

					}
				} catch(Exception e){
				throw new IndexOutOfBoundsException("行标已越界溢出");
			}
	}
	
	//插入区县表数据district
	private Long importDistric(){
		
		return null;
	}
	
	//插入学校表数据school
	private Long importSchool(){
		
		return null;
	}
	
	//插入学生表数据student
	private Long importStudent(){
		
		return null;
	}
	
	//插入考场表数据room
	private Long importRoom(){
		
		return null;
	}
	
	//插入考试表数据Examinne
	private Long importExaminne(){
		
		return null;
	}	
}
