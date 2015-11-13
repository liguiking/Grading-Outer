package com.easytnt.grading.service.impl;

import java.util.ArrayList;
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


	
	@Transactional(readOnly=true)
	public void query(Query<Teacher> query){
		//TODO 
		query.result(this.list());
		query.rows(101);
	}

	@Override
	@Transactional
	public void create(Teacher teacher, int amount) {
		String seq = "1";
		if(teacher.isManager()){
			seq = teacherRepository.getSeqL(teacher.getSubject().getId());
		}else {
			seq = teacherRepository.getSeq(teacher.getSubject().getId());
		}
		
		teacher.genAccount(Integer.valueOf(seq));
		List<Teacher> teachers = teacher.cloneTimes(amount);
		for(Teacher t:teachers) {
			this.create(t);
		}
	}

	@Transactional
	@Override
	public void resetPassword(Teacher teacher) {
		teacher.resetPassord();
	}
}
