package com.easytnt.grading.repository.impl;

import org.springframework.stereotype.Repository;

import com.easytnt.commons.entity.repository.HibernateRepository;
import com.easytnt.grading.domain.exam.Exam;
import com.easytnt.grading.domain.grade.Teacher;
import com.easytnt.grading.repository.ExamRepository;
import com.easytnt.grading.repository.TeacherRepository;

@Repository
public class TeacherRepositoryHibernateImpl extends HibernateRepository<Teacher,Long> implements TeacherRepository {

}
