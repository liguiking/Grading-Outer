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
	//根据subjectid 返回seq值
	@Override
	public String getSeq(Long subjectid) {
		String sql = "select case when count(t.teacher_account)=0 then 1 else max(t.teacher_account)-s.subject_code*100 + 1 end as seq from teacher_info t left"
				+ " join subject s on t.subject_id = s.subject_id where t.subject_id="+subjectid;
		Query q = this.getCurrentSession().createSQLQuery(sql);
		String seq1 =  q.uniqueResult().toString();
		String[] seq = seq1.split("\\.");
		return seq[0];
	}

	//计算组长的seq
	@Override
	public String getSeqL(Long subjectid) {
		String sql = "select case when count(t.teacher_account)=0 then 1 else max(t.teacher_account)-s.subject_code*10 + 1 end as seq from teacher_info t left"
				+ " join subject s on t.subject_id = s.subject_id where t.subject_id="+subjectid;
		Query q = this.getCurrentSession().createSQLQuery(sql);
		String seq1 =  q.uniqueResult().toString();
		String[] seq = seq1.split("\\.");
		return seq[0];
	}
	
	//修改密码
	@Override
	public void updatePass(Long teacherid,String password) {
		Teacher teacher = load(teacherid);
		teacher.setTeacherPassord(password);
		update(teacher);
	}
	
	@Override
	public Teacher load(Long id) {
		String hql ="from Teacher where teacherId="+id;
		Query q = this.getCurrentSession().createQuery(hql);
		Teacher t = (Teacher) q.list().get(0);
		return t;
	}
	

}
