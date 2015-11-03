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

/** 
 * <pre>
 * 考卷，就是一类试卷，如语文试卷，数学试卷，理科综合试卷
 * </pre>
 *  
 * @author 李贵庆2015年10月14日
 * @version 1.0
 **/
public class PaperInfo implements Entity<PaperInfo> {
	private Long paperId;
	
	private Long paperOid;
	
	private String paperName;
	
	private String  paperType;
	
	private List<PaperSectionInfo> sections;
	
	private List<Test> tests;
	
	private Float fullScore;
	
	public PaperInfo(String name,Float fullScore) {
		this.paperName = name;
		this.fullScore = fullScore;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.paperId).toHashCode();
	}
	
    @Override
	public boolean equals(Object o) {
		if(!(o instanceof PaperInfo))
			return false;
		PaperInfo other = (PaperInfo)o;
		
		return new EqualsBuilder().append(this.paperId,other.paperId).isEquals();
	}
	
    @Override
	public String toString() {
		return new ToStringBuilder(this).append(this.paperName).append(this.paperId).build();
	}
    
	@Override
	public boolean sameIdentityAs(PaperInfo other) {
		return this.equals(other);
	}
	
	//以下功能为ORM或者自动构造使用，非此慎用
	public PaperInfo() {}

	public Long getPaperId() {
		return paperId;
	}

	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}

	
	public Long getPaperOid() {
		return paperOid;
	}

	public void setPaperOid(Long paperOid) {
		this.paperOid = paperOid;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	

	public String getPaperType() {
		return paperType;
	}

	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}

	public List<PaperSectionInfo> getSections() {
		return sections;
	}

	public void setSections(List<PaperSectionInfo> sections) {
		this.sections = sections;
	}

	public Float getFullScore() {
		return fullScore;
	}

	public void setFullScore(Float fullScore) {
		this.fullScore = fullScore;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
	
}

