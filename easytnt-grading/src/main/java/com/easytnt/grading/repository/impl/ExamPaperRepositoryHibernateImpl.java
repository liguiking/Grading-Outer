package com.easytnt.grading.repository.impl;

import org.springframework.stereotype.Repository;

import com.easytnt.commons.entity.repository.HibernateRepository;
import com.easytnt.grading.domain.paper.ExamPaper;
import com.easytnt.grading.repository.ExamPaperRepository;

@Repository
public class ExamPaperRepositoryHibernateImpl extends HibernateRepository<ExamPaper,Long> implements ExamPaperRepository {

}
