package com.easytnt.grading.repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.easytnt.commons.entity.repository.HibernateRepository;
import com.easytnt.grading.domain.grade.Teacher;
import com.easytnt.grading.repository.TeacherRepository;

@Repository
public class TeacherRepositoryHibernateImpl extends HibernateRepository<Teacher,Long> implements TeacherRepository {

	
	//获取所有teacher信息
	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> tlist() {
		String hql = "from Teacher where 1=1";
		Query q = this.getCurrentSession().createQuery(hql);
		List<Teacher> tlist = q.list();
		return tlist;
	}
	//获取所选科目的
	@Override
	public int getSeq() {
		String sql = "select max(subject_id) from teacher_info";
		Query q = this.getCurrentSession().createQuery(sql);
		int seq = (int) q.uniqueResult();
		return seq;
	}

	//修改密码
	@Override
	public void updatePass(Long teacherid,String password) {
		// TODO Auto-generated method stub
		Teacher teacher = load(teacherid);
		teacher.setTeacherPassord(password);
		update(teacher);
	}

}
