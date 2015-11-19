package com.easytnt.grading.repository.impl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.easytnt.grading.service.ListDataMapper;
import com.easytnt.grading.service.ListDataSourceReader;

public class ExamineeDataImpoirtor{
    
	private ListDataMapper mapper;
	private ListDataSourceReader reader;
	private Session session;
	private StringBuffer sql = new StringBuffer();
	private int colindex = 0;
	private Map<String,Long> longMap = new HashMap<String,Long>();
	private static SimpleDateFormat sdf;
	static{
		sdf = new SimpleDateFormat("yyyyMMdd");
	}
	public ExamineeDataImpoirtor(Session session,ListDataMapper mapper, ListDataSourceReader reader){
		this.session = session;
		this.mapper = mapper;
		this.reader = reader;
	}
	public void doImport() throws Exception{
		try {
			reader.open();
			for(int i=1;;i++){
				Map<String,Object> paramMap = getParam(i,mapper,reader);
				importExaminne(session,paramMap);
			}
		}catch(IndexOutOfBoundsException e){
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	private void clearSql(){
		sql=sql.delete(0, sql.length());
	}
	/**
	 * 插入并得到区的ID
	 * @param session
	 * @param paramMap
	 * @return
	 */
	private Long importDistrict(Session session,Map<String,Object> paramMap){
		String key = paramMap.get("district_number")+"_"+paramMap.get("district_name");
		if(longMap.get(key)==null){
			getDistrict(paramMap,key);
		}
		return longMap.get(key);
	}
	/**
	 * 查询内存或数据库中是否存在，否则进行插入
	 * @param paramMap
	 */
	private void getDistrict(Map<String,Object> paramMap,String key){
		clearSql();
		sql.append(" select district_id from district where district_name ='").append(paramMap.get("district_name"))
		.append("' and district_number ='").append(paramMap.get("district_number")).append("'");
		String str = sql.toString();
		List list = session.createSQLQuery(str).list();
		clearSql();
		if(list.size()>0){
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
		}else{
			clearSql();
			sql.append(" insert into district(district_number,district_name) values (")
			   .append("'").append(paramMap.get("district_number")).append("','").append(paramMap.get("district_name")).append("');");
			session.createSQLQuery(sql.toString()).executeUpdate();
			list = session.createSQLQuery(str).list();
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
			clearSql();
		}
	}
	//插入区县表数据district
	private Long importSchool(Session session,Map<String,Object> paramMap){
		Long districtId = importDistrict(session,paramMap);
		String key = districtId+"_"+paramMap.get("school_code")+"_"+paramMap.get("school_name");
		if(longMap.get(key)==null){
			getSchool(paramMap,districtId,key);
		}
		return longMap.get(key);
	}
	/**
	 * 查询内存或数据库中是否存在，否则进行插入
	 * @param paramMap
	 */
	private void getSchool(Map<String,Object> paramMap,Long districtId,String key){
		clearSql();
		sql.append(" select school_id from school where school_name ='").append(paramMap.get("school_name"))
		.append("' and school_code ='").append(paramMap.get("school_code")).append("' and district_id = ").append(districtId);
		String str = sql.toString();
		List list = session.createSQLQuery(str).list();
		clearSql();
		if(list.size()>0){
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
		}else{
			clearSql();
			sql.append(" insert into school(district_id,school_name,school_code) values (")
			   .append(districtId).append(",'").append(paramMap.get("school_name")).append("','").append(paramMap.get("school_code")).append("');");
			session.createSQLQuery(sql.toString()).executeUpdate();
			list = session.createSQLQuery(str).list();
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
			clearSql();
		}
	}
	
	//插入学生表数据student
	private Long importStudent(Session session,Map<String,Object> paramMap){
		String key =paramMap.get("student_number")+"_"+paramMap.get("student_name");
		if(longMap.get(key)==null){
			getStudent(paramMap,key);
		}
		return longMap.get(key);
	}
	/**
	 * 查询内存或数据库中是否存在，否则进行插入
	 * @param paramMap
	 */
	private void getStudent(Map<String,Object> paramMap,String key){
		clearSql();
		sql.append(" select student_id from student where student_number ='").append(paramMap.get("student_number"))
		.append("' and student_name ='").append(paramMap.get("student_name")).append("'");
		String str = sql.toString();
		List list = session.createSQLQuery(str).list();
		clearSql();
		if(list.size()>0){
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
		}else{
			clearSql();
			sql.append(" insert into student(student_number,student_name,gender,nation,birthday) values (")
			   .append("'").append(paramMap.get("student_number")).append("','").append(paramMap.get("student_name")).append("','")
			   .append(paramMap.get("gender")).append("','").append(paramMap.get("nation")).append("','").append(paramMap.get("birthday")).append("');");
			session.createSQLQuery(sql.toString()).executeUpdate();
			list = session.createSQLQuery(str).list();
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
			clearSql();
		}
	}
	//插入考场表数据room
	private Long importRoom(Session session,Map<String,Object> paramMap){
		if(paramMap.get("room_number")==null||(Integer.valueOf((String)paramMap.get("room_number"))==0)){
			return null;
		}
		String key = "room_"+paramMap.get("room_number");
		if(longMap.get(key)==null){
			getRoom(paramMap,key);
		}
		return  longMap.get(key);
	}
	/**
	 * 查询内存或数据库中是否存在，否则进行插入
	 * @param paramMap
	 */
	private void getRoom(Map<String,Object> paramMap,String key){
		clearSql();
		sql.append(" select room_id from room where room_number = ").append(paramMap.get("room_number"));
		String str = sql.toString();
		List list = session.createSQLQuery(str).list();
		clearSql();
		if(list.size()>0){
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
		}else{
			clearSql();
			sql.append(" insert into room (room_number) values (")
			   .append(paramMap.get("room_number")).append(")");
			session.createSQLQuery(sql.toString()).executeUpdate();
			list = session.createSQLQuery(str).list();
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
			clearSql();
		}
	}
	private Long importTermTest(Session session,Map<String,Object> paramMap){
		String key = paramMap.get("term_test_name")+"_"+paramMap.get("term_test_from")+"_"+paramMap.get("term_test_to");
		if(longMap.get(key)==null){
			getTermTest(paramMap,key);
		}
		return  longMap.get(key);
	}
	/**
	 * 查询内存或数据库中是否存在，否则进行插入
	 * @param paramMap
	 */
	private void getTermTest(Map<String,Object> paramMap,String key){
		clearSql();
		sql.append(" select term_test_id from term_test where term_test_name = '").append(paramMap.get("term_test_name"))
		.append("' and term_test_from ='").append(paramMap.get("term_test_from"))
		.append("' and term_test_to ='").append(paramMap.get("term_test_to")).append("'");
		String str = sql.toString();
		List list = session.createSQLQuery(str).list();
		clearSql();
		if(list.size()>0){
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
		}else{
			clearSql();
			sql.append(" insert into term_test (term_test_oid,term_test_name,term_test_from,term_test_to) values (")
			   .append(sdf.format(new Date())).append(",'").append(paramMap.get("term_test_name")).append("','")
			   .append(paramMap.get("term_test_from")).append("','").append(paramMap.get("term_test_to")).append("')");
			session.createSQLQuery(sql.toString()).executeUpdate();
			list = session.createSQLQuery(str).list();
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
			clearSql();
		}
	}
	//插入考试表数据Examinne
	private void importExaminne(Session session,Map<String,Object> paramMap){
		StringBuffer sqlsb = new StringBuffer();
		sqlsb.append(" insert into examinne(school_id,student_id,term_test_id,room_id,seating_number,examinne_name,examinne_uuid,uuid_type,arts,clazz_name,clazz_code,absence,total_score) values (")
			.append(importSchool(session,paramMap)).append(",").append(importStudent(session,paramMap)).append(",")
			.append(importTermTest(session,paramMap)).append(",").append(importRoom(session,paramMap)).append(",")
			.append(paramMap.get("seating_number")).append(",'").append(paramMap.get("examinne_name")).append("','")
			.append(paramMap.get("examinne_uuid")).append("','").append(paramMap.get("uuid_type")).append("',")
			.append(paramMap.get("arts")).append(",'").append(paramMap.get("clazz_name")).append("','")
			.append(paramMap.get("clazz_code")).append("',").append(paramMap.get("absence")).append(",").append(paramMap.get("total_score")).append(");");
		session.createSQLQuery(sqlsb.toString()).executeUpdate();
	}	
	/**
	 * 获取文件映射字段的内容
	 * @param i
	 * @param mapper
	 * @param reader
	 * @return
	 * @throws Exception
	 */
	private Map<String,Object> getParam(int i,ListDataMapper mapper, ListDataSourceReader reader) throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		//获取district表字段列
		colindex = mapper.getColIndex("district_number");
		paramMap.put("district_number", reader.get(i, colindex));
		colindex = mapper.getColIndex("district_name");
		paramMap.put("district_name", reader.get(i, colindex));
		
		//获取school表字段列
		colindex = mapper.getColIndex("school_name");
		paramMap.put("school_name", reader.get(i, colindex));
	    colindex = mapper.getColIndex("school_code");
		paramMap.put("school_code", reader.get(i, colindex));
		
		//获取student表字段列
		colindex = mapper.getColIndex("student_number");
		paramMap.put("student_number", reader.get(i, colindex));
		colindex = mapper.getColIndex("student_name");
		paramMap.put("student_name", reader.get(i, colindex));
		colindex = mapper.getColIndex("gender");
		paramMap.put("gender", reader.get(i, colindex));
		colindex = mapper.getColIndex("nation");
		paramMap.put("nation", reader.get(i, colindex));
		colindex = mapper.getColIndex("birthday");
		paramMap.put("birthday", reader.get(i, colindex));
		
	    //获取room表字段列
		colindex = mapper.getColIndex("room_number");
		paramMap.put("room_number", reader.get(i, colindex));
		
	    //获取examinne表字段列
		colindex = mapper.getColIndex("seating_number");
		paramMap.put("seating_number", reader.get(i, colindex));
	    colindex = mapper.getColIndex("examinne_name");	
	    paramMap.put("examinne_name", reader.get(i, colindex));
	    colindex = mapper.getColIndex("examinne_uuid");
	    paramMap.put("examinne_uuid", reader.get(i, colindex));
	    colindex = mapper.getColIndex("uuid_type");
	    paramMap.put("uuid_type", reader.get(i, colindex));
	    colindex = mapper.getColIndex("arts");	
	    paramMap.put("arts", reader.get(i, colindex));
	    colindex = mapper.getColIndex("clazz_name");
	    paramMap.put("clazz_name", reader.get(i, colindex));
	    colindex = mapper.getColIndex("clazz_code");
	    paramMap.put("clazz_code", reader.get(i, colindex));
	    colindex = mapper.getColIndex("absence");
	    paramMap.put("absence", reader.get(i, colindex));
	    colindex = mapper.getColIndex("total_score");
	    paramMap.put("total_score", reader.get(i, colindex));
	    
	   //获取term_test表字段列
	    colindex = mapper.getColIndex("term_test_name");
	    paramMap.put("term_test_name", reader.get(i, colindex));
	    colindex = mapper.getColIndex("term_test_from");
	    paramMap.put("term_test_from", reader.get(i, colindex));
	    colindex = mapper.getColIndex("term_test_to");
	    paramMap.put("term_test_to", reader.get(i, colindex));
	    return paramMap;
	}
}
