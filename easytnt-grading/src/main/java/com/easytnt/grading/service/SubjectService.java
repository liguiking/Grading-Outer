package com.easytnt.grading.service;

import java.util.List;

import com.easytnt.commons.entity.service.EntityService;
import com.easytnt.grading.domain.exam.Subject;
/**
 * 科目的具体操作服务类
 * 
 * @author 钟水林 20151103
 *
 */
public interface SubjectService extends EntityService<Subject, Long> {
	//科目新增
	public void insertSubser();
	//科目删除
	public void deleteSubser();
	//科目修改
	public void updateSubser();
	//获取科目
	public List<Subject> getSubser();
}
