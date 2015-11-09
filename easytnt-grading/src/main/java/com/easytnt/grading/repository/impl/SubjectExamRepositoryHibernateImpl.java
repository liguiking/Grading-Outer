package com.easytnt.grading.repository.impl;

import org.springframework.stereotype.Repository;

import com.easytnt.commons.entity.repository.HibernateRepository;
import com.easytnt.grading.domain.exam.SubjectExam;
import com.easytnt.grading.repository.SubjectExamRepository;

@Repository
public class SubjectExamRepositoryHibernateImpl extends HibernateRepository<SubjectExam,Long> implements SubjectExamRepository {

}
