/**
 * 
 * 
 **/

package com.easytnt.grading.domain.paper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
	
	private PaperType paperType;
	
	private Long paperId;
	
	private Long paperOid;
	
	private Set<Section> sections;
	
	private Set<SubjectExam> subjectExam;
	
	private Set<PaperCard> paperCards;
	
	private Float fullScore;
	
	private Float objectivityScore; //客观题满分
	
	private Float subjectivityScore; //主观题满分
	
	public ExamPaper(String name,Float fullScore) {
		this.name = name;
		this.fullScore = fullScore;
	}
	private void init() {
		if (this.sections == null) {
			this.sections = new LinkedHashSet<Section>();
		}
		if (this.subjectExam == null) {
			this.subjectExam = new LinkedHashSet<SubjectExam>();
		}
		if (this.paperCards == null) {
			this.paperCards = new LinkedHashSet<PaperCard>();
		}
	}
	public void addPaperCard(PaperCard paperCard){
		init();
		paperCard.setPaper(this);
		this.paperCards.add(paperCard);
	}
	public void removePaperCard(PaperCard paperCard){
		init();
		paperCard.setPaper(null);
		this.paperCards.remove(paperCard);
	}
	private Integer index=1;
	public void addSections(Section section){
		init();
		if(section.getFullScore()==null){
			throw new UnsupportedOperationException("试题分数为空");
		}
		section.setPaper(this,index);
		this.sections.add(section);
		validate();
		index++;
	}
	public void addSubjectExams(SubjectExam subjectExam){
		init();
		this.subjectExam.add(subjectExam);
	}
	public void addSections(Integer position,Section section){
		init();
		if(section.getFullScore()==null){
			throw new UnsupportedOperationException("试题分数为空");
		}
		List<Section> sectionList = new ArrayList<Section>();
		sectionList.addAll(sections);
		if((sectionList.get(position).getSectionOid()%10)==(position+1)){
			section.setSectionOid(sectionList.get(position).getSectionOid());
			sectionList.set(position, section);
		}else{
			section.setPaper(this,position+1);
			sectionList.add(position, section);
		}
		this.sections.clear();
		this.sections.addAll(sectionList);
		validate();
	}
	public void updateSection(Section oldSection,Section newSection){
		init();
		if(newSection.getFullScore()==null){
			throw new UnsupportedOperationException("试题分数为空");
		}
		newSection.setSectionOid(oldSection.getSectionOid());
		List<Section> sectionList = new ArrayList<Section>();
		sectionList.addAll(sections);
		sectionList.set(sectionList.indexOf(oldSection), newSection);
		sections.clear();
		sections.addAll(sectionList);
		validate();
	}
	public void removeSections(Integer position){
		init();
		List<Section> sectionList = new ArrayList<Section>();
		sectionList.addAll(sections);
		sectionList.remove(index);
		sections.clear();
		sections.addAll(sectionList);
		validate();
	}
	public void removeSections(Section section){
		init();
		section.setPaper(null);
		this.sections.remove(section);
	}
	public void clearSections(){
		init();
		this.sections.clear();
	}
	private void validate(){
		if(this.fullScore==null){
			throw new UnsupportedOperationException("试卷分数为空");
		}
		Iterator<Section> iterSection =  sections.iterator();
		float sectionFullScores=0;
		while(iterSection.hasNext()){
			Section section = iterSection.next();
			sectionFullScores+=section.getFullScore();
		}
		if(sectionFullScores>this.fullScore){
			throw new UnsupportedOperationException("试题分数大于试卷分数");
		}
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.paperOid).toHashCode();
	}
	
    @Override
	public boolean equals(Object o) {
		if(!(o instanceof ExamPaper))
			return false;
		ExamPaper other = (ExamPaper)o;
		
		return new EqualsBuilder().append(this.paperOid,other.paperOid).isEquals();
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

	public Set<Section> getSections() {
		return sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}

	public Float getFullScore() {
		return fullScore;
	}

	public void setFullScore(Float fullScore) {
		this.fullScore = fullScore;
	}

	public PaperType getPaperType() {
		return paperType;
	}

	public void setPaperType(PaperType paperType) {
		this.paperType = paperType;
	}

	public Long getPaperOid() {
		return paperOid;
	}

	public void setPaperOid(Long paperOid) {
		this.paperOid = paperOid;
	}

	public Set<SubjectExam> getSubjectExam() {
		return subjectExam;
	}

	public void setSubjectExam(Set<SubjectExam> subjectExam) {
		this.subjectExam = subjectExam;
	}
	public Float getObjectivityScore() {
		return objectivityScore;
	}
	public void setObjectivityScore(Float objectivityScore) {
		this.objectivityScore = objectivityScore;
	}
	public Float getSubjectivityScore() {
		return subjectivityScore;
	}
	public void setSubjectivityScore(Float subjectivityScore) {
		this.subjectivityScore = subjectivityScore;
	}
	public Set<PaperCard> getPaperCards() {
		return paperCards;
	}
	public void setPaperCards(Set<PaperCard> paperCards) {
		this.paperCards = paperCards;
	}
	
	
}

