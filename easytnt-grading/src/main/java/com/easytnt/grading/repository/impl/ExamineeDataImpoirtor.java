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
				importDistrict(session,paramMap);
				importSchool(session,paramMap);
				importStudent(session,paramMap);
				importRoom(session,paramMap);
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
		if(districtIsNull(paramMap)){
			return null;
		}
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
		sql.append(" select district_id from district where district_name = ? and district_number = ? ");
		String str = sql.toString();
		List list = session.createSQLQuery(str)
				.setString(0, (String)paramMap.get("district_name"))
				.setString(1, (String)paramMap.get("district_number")).list();
		clearSql();
		if(list.size()>0){
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
		}else{
			clearSql();
			sql.append(" insert into district(district_name,district_number) values (?,?);");
			session.createSQLQuery(sql.toString())
					.setString(0, (String)paramMap.get("district_name"))
					.setString(1, (String)paramMap.get("district_number")).executeUpdate();
			list = session.createSQLQuery(str)
					.setString(0, (String)paramMap.get("district_name"))
					.setString(1, (String)paramMap.get("district_number")).list();
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
			clearSql();
		}
	}
	private Long importSchool(Session session,Map<String,Object> paramMap){
		if(schoolIsNull(paramMap)){
			return null;
		}
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
		sql.append(" select school_id from school where school_name =?  and school_code =?  and district_id =? ");
		String str = sql.toString();
		List list = session.createSQLQuery(str)
				.setString(0, (String)paramMap.get("school_name"))
				.setString(1, (String)paramMap.get("school_code"))
				.setBigInteger(2, BigInteger.valueOf(districtId)).list();
		clearSql();
		if(list.size()>0){
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
		}else{
			clearSql();
			sql.append(" insert into school(school_name,school_code,district_id) values (?,?,?);");
			session.createSQLQuery(sql.toString())
				.setString(0, (String)paramMap.get("school_name"))
				.setString(1, (String)paramMap.get("school_code"))
				.setBigInteger(2, BigInteger.valueOf(districtId)).executeUpdate();
			list = session.createSQLQuery(str)
					.setString(0, (String)paramMap.get("school_name"))
					.setString(1, (String)paramMap.get("school_code"))
					.setBigInteger(2, BigInteger.valueOf(districtId)).list();
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
			clearSql();
		}
	}
	
	//插入学生表数据student
	private Long importStudent(Session session,Map<String,Object> paramMap){
		if(studentIsNull(paramMap)){
			return null;
		}
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
		sql.append(" select student_id from student where student_number =? and student_name =? ");
		String str = sql.toString();
		List list = session.createSQLQuery(str)
				.setString(0, (String)paramMap.get("student_number"))
				.setString(1, (String)paramMap.get("student_name")).list();
		clearSql();
		if(list.size()>0){
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
		}else{
			clearSql();
			sql.append(" insert into student(student_number,student_name,gender,nation,birthday) values (?,?,?,?,?);");
			session.createSQLQuery(sql.toString())
				.setString(0, (String)paramMap.get("student_number"))
				.setString(1, (String)paramMap.get("student_name"))
				.setString(2, (String)paramMap.get("gender"))
				.setString(3, (String)paramMap.get("nation"))
				.setString(4, (String)paramMap.get("birthday")).executeUpdate();
			
			list = session.createSQLQuery(str)
					.setString(0, (String)paramMap.get("student_number"))
					.setString(1, (String)paramMap.get("student_name")).list();
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
			clearSql();
		}
	}
	//插入考场表数据room
	private Long importRoom(Session session,Map<String,Object> paramMap){
		if(roomIsNull(paramMap)){
			return null;
		}
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
		sql.append(" select room_id from room where room_number = ? ");
		String str = sql.toString();
		List list = session.createSQLQuery(str).setString(0, (String)paramMap.get("room_number")).list();
		clearSql();
		if(list.size()>0){
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
		}else{
			clearSql();
			sql.append(" insert into room (room_number) values (?)");
			session.createSQLQuery(sql.toString()).setString(0, (String)paramMap.get("room_number")).executeUpdate();
			list = session.createSQLQuery(str).setString(0, (String)paramMap.get("room_number")).list();
			longMap.put(key, ((BigInteger)list.get(0)).longValue());
			clearSql();
		}
	}
//	private Long importTermTest(Session session,Map<String,Object> paramMap){
//		String key = paramMap.get("term_test_name")+"_"+paramMap.get("term_test_from")+"_"+paramMap.get("term_test_to");
//		if(longMap.get(key)==null){
//			getTermTest(paramMap,key);
//		}
//		return  longMap.get(key);
//	}
//	/**
//	 * 查询内存或数据库中是否存在，否则进行插入
//	 * @param paramMap
//	 */
//	private void getTermTest(Map<String,Object> paramMap,String key){
//		clearSql();
//		sql.append(" select term_test_id from term_test where term_test_name = '").append(paramMap.get("term_test_name"))
//		.append("' and term_test_from ='").append(paramMap.get("term_test_from"))
//		.append("' and term_test_to ='").append(paramMap.get("term_test_to")).append("'");
//		String str = sql.toString();
//		List list = session.createSQLQuery(str).list();
//		clearSql();
//		if(list.size()>0){
//			longMap.put(key, ((BigInteger)list.get(0)).longValue());
//		}else{
//			clearSql();
//			sql.append(" insert into term_test (term_test_oid,term_test_name,term_test_from,term_test_to) values (")
//			   .append(sdf.format(new Date())).append(",'").append(paramMap.get("term_test_name")).append("','")
//			   .append(paramMap.get("term_test_from")).append("','").append(paramMap.get("term_test_to")).append("')");
//			session.createSQLQuery(sql.toString()).executeUpdate();
//			list = session.createSQLQuery(str).list();
//			longMap.put(key, ((BigInteger)list.get(0)).longValue());
//			clearSql();
//		}
//	}
	//插入考试表数据Examinne
	private void importExaminne(Session session,Map<String,Object> paramMap){
		if(examinneIsNull(paramMap)){
			return;
		}
		StringBuffer sqlsb = new StringBuffer();

		sqlsb.append(" insert into examinne(school_id,student_id,term_test_id,room_id,seating_number,examinne_name,examinne_uuid,uuid_type,arts,clazz_name,clazz_code,absence,total_score) values (")
		.append("?,?,?,?,?,?,?,?,?,?,?,?,?);");
		session.createSQLQuery(sqlsb.toString())
			.setBigInteger(0, BigInteger.valueOf(importSchool(session,paramMap)))
			.setBigInteger(1, BigInteger.valueOf(importStudent(session,paramMap)))
			.setBigInteger(2, BigInteger.valueOf(1l))
			.setBigInteger(3, longToBigInteger(importRoom(session,paramMap)))
			.setInteger(4, strToInteger(paramMap.get("seating_number")))
			.setString(5, (String)paramMap.get("examinne_name"))
			.setString(6, (String)paramMap.get("examinne_uuid"))
			.setString(7, (String)paramMap.get("uuid_type"))
			.setInteger(8, strToInteger(paramMap.get("arts")))
			.setString(9, (String)paramMap.get("clazz_name"))
			.setString(10, (String)paramMap.get("clazz_code"))
			.setInteger(11, strToInteger(paramMap.get("absence")))
			.setFloat(12, strToFloat(paramMap.get("total_score")))
			.executeUpdate();
	}
	private Float strToFloat(Object o){
		if(o==null){
			return 0f;
		}else{
			return Float.valueOf((String)o);
		}
	}
	private Integer strToInteger(Object o){
		if(o==null){
			return 0;
		}else{
			return Integer.valueOf((String)o);
		}
	}
	private BigInteger longToBigInteger(Long lo){
		if(lo==null){
			return null;
		}else{
			return BigInteger.valueOf(lo);
		}
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
//	    colindex = mapper.getColIndex("term_test_name");
//	    paramMap.put("term_test_name", reader.get(i, colindex));
//	    colindex = mapper.getColIndex("term_test_from");
//	    paramMap.put("term_test_from", reader.get(i, colindex));
//	    colindex = mapper.getColIndex("term_test_to");
//	    paramMap.put("term_test_to", reader.get(i, colindex));
	    return paramMap;
	}
	
	private boolean districtIsNull(Map<String,Object> paramMap){
		boolean isTrue = true;
		isTrue= isTrue && paramMap.get("district_number")==null;
		isTrue= isTrue && paramMap.get("district_name")==null;
		return isTrue;
	}
	private boolean schoolIsNull(Map<String,Object> paramMap){
		boolean isTrue = true;
		isTrue= isTrue && paramMap.get("school_name")==null;
		isTrue= isTrue && paramMap.get("school_code")==null;
		return isTrue;
	}
	private boolean studentIsNull(Map<String,Object> paramMap){
		boolean isTrue = true;
		isTrue= isTrue && paramMap.get("student_number")==null;
		isTrue= isTrue && paramMap.get("student_name")==null;
		isTrue= isTrue && paramMap.get("gender")==null;
		isTrue= isTrue && paramMap.get("nation")==null;
		isTrue= isTrue && paramMap.get("birthday")==null;
		return isTrue;
	}
	private boolean roomIsNull(Map<String,Object> paramMap){
		boolean isTrue = true;
		isTrue= isTrue && paramMap.get("room_number")==null;
		return isTrue;
	}
	private boolean examinneIsNull(Map<String,Object> paramMap){
		boolean isTrue = true;
		isTrue= isTrue && paramMap.get("seating_number")==null;
		isTrue= isTrue && paramMap.get("examinne_name")==null;
		isTrue= isTrue && paramMap.get("examinne_uuid")==null;
		isTrue= isTrue && paramMap.get("uuid_type")==null;
		isTrue= isTrue && paramMap.get("arts")==null;
		isTrue= isTrue && paramMap.get("clazz_name")==null;
		isTrue= isTrue && paramMap.get("clazz_code")==null;
		isTrue= isTrue && paramMap.get("absence")==null;
		isTrue= isTrue && paramMap.get("total_score")==null;
		return isTrue;
	}
}
