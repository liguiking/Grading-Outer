/**
 * 
 * 
 **/

package com.easytnt.grading.domain.paper;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.easytnt.commons.entity.share.Entity;
import com.easytnt.grading.domain.exam.SubjectExam;

/** 
 * <pre>
 * 考卷，就是一类试卷，如语文试卷，数学试卷，理科综合试卷
 * </pre>
 *  
 * @author 李贵庆2015年10月14日
 * @version 1.0
 **/
public class ExamPaper implements Entity<ExamPaper> {

	private String name;
	
	private String paperType;
	
	private Long paperId;
	
	private Long paperOid;
	
	private List<Section> sections;
	
	private List<SubjectExam> subjectExam;
	
	private Float fullScore;
	
	public ExamPaper(String name,Float fullScore) {
		this.name = name;
		this.fullScore = fullScore;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.paperId).toHashCode();
	}
	
    @Override
	public boolean equals(Object o) {
		if(!(o instanceof ExamPaper))
			return false;
		ExamPaper other = (ExamPaper)o;
		
		return new EqualsBuilder().append(this.paperId,other.paperId).isEquals();
	}
	
    @Override
	public String toString() {
		return new ToStringBuilder(this).append(this.name).append(this.paperId).build();
	}
    
	@Override
	public boolean sameIdentityAs(ExamPaper other) {
		return this.equals(other);
	}
	
	//以下功能为ORM或者自动构造使用，非此慎用
	public ExamPaper() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPaperId() {
		return paperId;
	}

	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Float getFullScore() {
		return fullScore;
	}

	public void setFullScore(Float fullScore) {
		this.fullScore = fullScore;
	}

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public Long getPaperOid() {
		return paperOid;
	}

	public void setPaperOid(Long paperOid) {
		this.paperOid = paperOid;
	}

	public List<SubjectExam> getSubjectExam() {
		return subjectExam;
	}

	public void setSubjectExam(List<SubjectExam> subjectExam) {
		this.subjectExam = subjectExam;
	}
	
}

