package com.easytnt.grading.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easytnt.commons.entity.cqrs.Query;
import com.easytnt.commons.entity.service.AbstractEntityService;
import com.easytnt.grading.domain.exam.Exam;
import com.easytnt.grading.domain.grade.Teacher;
import com.easytnt.grading.repository.ExamRepository;
import com.easytnt.grading.repository.TeacherRepository;
import com.easytnt.grading.service.ExamService;
import com.easytnt.grading.service.TeacherService;

/**
 * 科目的具体操作服务实现类
 * 
 * @author 钟水林 20151109
 *
 */
@Service
public class TeacherServiceImpl extends AbstractEntityService<Teacher, Long>
		implements TeacherService {

	private TeacherRepository teacherRepository;

	public TeacherServiceImpl() {

	}

	@Autowired
	public void setRepository(TeacherRepository repository) {
		this.teacherRepository = repository;
		super.setRepository(repository);
	}

	@Override
	public Teacher load(Long pk) {
		Teacher teacher = new Teacher();
		// exam.setName("Exam");
		return teacher;
	}

	@Transactional(readOnly = true)
	@Override
	public void query(Query<Teacher> query) {
		// TODO Auto-generated method stub
	}

	// 获取所有的Teacher信息
	public List<Teacher> tlist() {
		List<Teacher> tlist = teacherRepository.tlist();
		return tlist;
	}

	// 查询
	@Override
	public int getSeq() {
		// TODO Auto-generated method stub
		return 0;
	}

	// 修改密码
	@Override
	public void updatePass(Long teacheckid, String pass) {
		// TODO Auto-generated method stub

	}
}
