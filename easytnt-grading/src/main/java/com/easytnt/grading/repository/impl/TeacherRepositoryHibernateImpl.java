package com.easytnt.grading.repository.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.easytnt.commons.entity.repository.HibernateRepository;
import com.easytnt.grading.domain.grade.Teacher;
import com.easytnt.grading.repository.TeacherRepository;

@Repository
public class TeacherRepositoryHibernateImpl extends HibernateRepository<Teacher,Long> implements TeacherRepository {

	//根据subjectid 返回seq值
	@Override
	public String getSeq(Long subjectid) {
		String sql = "select case when count(t.teacher_account)=0 then 1 else max(CONVERT(teacher_account ,SIGNED))-s.subject_code*100 + 1 end as seq from teacher_info t left"
				+ " join subject s on t.subject_id = s.subject_id where t.subject_id="+subjectid;
		Query q = this.getCurrentSession().createSQLQuery(sql);
		String seq1 =  q.uniqueResult().toString();
		String[] seq = seq1.split("\\.");
		return seq[0];
	}

	//计算组长的seq
	@Override
	public String getSeqL(Long subjectid) {
		String sql = "select case when count(t.teacher_account)=0 then 1 else max(CONVERT(teacher_account ,SIGNED))-s.subject_code*10 + 1 end as seq from teacher_info t left"
				+ " join subject s on t.subject_id = s.subject_id where t.subject_id="+subjectid;
		Query q = this.getCurrentSession().createSQLQuery(sql);
		String seq1 =  q.uniqueResult().toString();
		String[] seq = seq1.split("\\.");
		return seq[0];
	}
	
	//修改密码
	@Override
	public void updatePass(Long teacherid,String password) {
		String hql = "from Teacher where teacherId="+teacherid;
		Query q = this.getCurrentSession().createQuery(hql);
		Teacher teacher = (Teacher) q.list().get(0);
		teacher.setTeacherPassord(password);
		teacher.setSubject(teacher.getSubject());
		update(teacher);
	}
	
	//根据科目查询教师信息
	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> getTeacherSname(Long subject_id) {
		String hql = " from Teacher t where t.subject.id= "+subject_id;
		Query q = this.getCurrentSession().createQuery(hql);
		List<Teacher> tlist = q.list();
		if(tlist.size()>0){
			return tlist;
		}
		return null;
	}
	
	
	@Override
	protected Class<Teacher> getEntityClass() {
		return Teacher.class;
	}
}
