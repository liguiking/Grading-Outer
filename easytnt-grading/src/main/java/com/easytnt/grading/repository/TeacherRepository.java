package com.easytnt.grading.repository;

import java.util.List;

import com.easytnt.commons.entity.repository.Repository;
import com.easytnt.grading.domain.exam.Subject;
import com.easytnt.grading.domain.grade.Teacher;

/**
 * 科目的具体操作接口类
 * 
 * @author 钟水林 20151109
 *
 */
public interface TeacherRepository extends Repository<Teacher, Long> {
	
	//获取所有的Teacher信息
	public List<Teacher> tlist();
	
	//计算普通帐号的seq
	public String getSeq(Long subjectid);
	
	//计算组长账号的seq
	public String getSeqL(Long subjectid);
	
	
	//修改密码
	public void updatePass(Long teacherid,String password);
}
