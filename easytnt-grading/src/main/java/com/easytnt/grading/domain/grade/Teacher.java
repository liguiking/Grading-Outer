package com.easytnt.grading.domain.grade;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.easytnt.grading.domain.exam.Subject;

/**
 * 20151109
 * 
 * @author 钟水林
 *
 */
public class Teacher {
	private Long teacherId;
	private Subject subject;
	private String teacherAccount;
	private String teacherName;
	private String teacherPassord;
	private int leader;

	// 无参
	public Teacher() {
		
	}

	//本类方法生成单个教师账号
	public Teacher allocatedTo(Subject subject,int seq) {
		this.subject  = subject;
		this.teacherAccount =  String.valueOf(this.subject.getSubjectCode() * 100 +seq);
		this.teacherPassord = this.teacherAccount;
		return this;
	}
	//本类方法批量生成组长账号
	public void alladdleader(Teacher tea ,Subject subject,int seq){
		this.subject  = subject;
		this.teacherAccount =  String.valueOf(this.subject.getSubjectCode() * 10 +seq);
		this.teacherPassord = this.teacherAccount;
		tea.setSubject(new Subject(subject.getName(),subject.getSubjectCode()));
		tea.setTeacherName(this.teacherName);
		tea.setTeacherAccount(this.teacherAccount);
		tea.setTeacherPassord(this.teacherPassord);
	}

	public List<Teacher> cloneTimes(int times){
		ArrayList<Teacher> ts = new ArrayList<>();
		Teacher t = new Teacher();
		t.subject = this.subject;
	    //t.teacherAccount = Integer.parseInt(this.teacherAccount)++;
		t.leader = this.leader;
		ts.add(t);
		return ts;
	}
	
	//重写
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.teacherAccount).toHashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Teacher))
			return false;
		Teacher other = (Teacher) o;
		return new EqualsBuilder().append(this.teacherAccount, other.teacherAccount).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(this.teacherAccount).append(this.teacherName).build();
	}
	
	//set  get
	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	public String getTeacherAccount() {
		return teacherAccount;
	}

	public void setTeacherAccount(String teacherAccount) {
		this.teacherAccount = teacherAccount;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherPassord() {
		return teacherPassord;
	}

	public void setTeacherPassord(String teacherPassord) {
		this.teacherPassord = teacherPassord;
	}
	
	public int getLeader() {
		return leader;
	}

	public void setLeader(int leader) {
		this.leader = leader;
	}
}
