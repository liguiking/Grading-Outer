/**
 * 
 * 
 **/

package com.easytnt.grading.domain.paper;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.easytnt.commons.entity.share.Entity;
import com.easytnt.grading.domain.exam.Subject;

/** 
 * <pre>
 * 考试
 * </pre>
 *  
 * @author 李贵庆2015年10月14日
 * @version 1.0
 **/
public class Test implements Entity<Test> {
	private Long testId;
	
	private Long testOid;
	
	private Subject subject;
	
	private String testName;
	
	private Date testFrome;
	
	private Date testTo;
	
	private Integer testYear;
	
	private Integer testMonth;
	
	private Integer testWeek;
	
	private List<PaperInfo> paperInfos;
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.testId).toHashCode();
	}
	
    @Override
	public boolean equals(Object o) {
		if(!(o instanceof Test))
			return false;
		Test other = (Test)o;
		
		return new EqualsBuilder().append(this.testId,other.testId).isEquals();
	}
	
    @Override
	public String toString() {
		return new ToStringBuilder(this).append(this.testName).append(this.testId).build();
	}
    
	@Override
	public boolean sameIdentityAs(Test other) {
		return this.equals(other);
	}
	
	//以下功能为ORM或者自动构造使用，非此慎用
	public Test() {}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public Long getTestOid() {
		return testOid;
	}

	public void setTestOid(Long testOid) {
		this.testOid = testOid;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Date getTestFrome() {
		return testFrome;
	}

	public void setTestFrome(Date testFrome) {
		this.testFrome = testFrome;
	}

	public Date getTestTo() {
		return testTo;
	}

	public void setTestTo(Date testTo) {
		this.testTo = testTo;
	}

	public Integer getTestYear() {
		return testYear;
	}

	public void setTestYear(Integer testYear) {
		this.testYear = testYear;
	}

	public Integer getTestMonth() {
		return testMonth;
	}

	public void setTestMonth(Integer testMonth) {
		this.testMonth = testMonth;
	}

	public Integer getTestWeek() {
		return testWeek;
	}

	public void setTestWeek(Integer testWeek) {
		this.testWeek = testWeek;
	}

	public List<PaperInfo> getPaperInfos() {
		return paperInfos;
	}

	public void setPaperInfos(List<PaperInfo> paperInfos) {
		this.paperInfos = paperInfos;
	}
	
}

