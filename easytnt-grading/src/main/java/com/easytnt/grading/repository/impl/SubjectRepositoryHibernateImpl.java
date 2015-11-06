package com.easytnt.grading.repository.impl;

import org.springframework.stereotype.Repository;

import com.easytnt.commons.entity.repository.HibernateRepository;
import com.easytnt.grading.domain.exam.Subject;
import com.easytnt.grading.repository.SubjectRepository;
/**
 * 科目的具体操作实现接口类
 * 
 * @author 钟水林 20151103
 *
 */
@Repository
public class SubjectRepositoryHibernateImpl extends HibernateRepository<Subject,Long> implements SubjectRepository {
}