package com.easytnt.grading.service;

import java.util.List;

import com.easytnt.commons.entity.service.EntityService;
import com.easytnt.grading.domain.exam.Exam;
import com.easytnt.grading.domain.grade.Teacher;
/**
 * 科目的具体操作服务类
 * 
 * @author 钟水林 20151109
 *
 */
public interface TeacherService extends EntityService<Teacher, Long> {
	
	//获取所有的Teacher信息
	public List<Teacher> tlist();
	
	//返回账号计算
	public int getSeq();
	
	//修改密码
	public void updatePass(Long teacheckid,String pass);
}
