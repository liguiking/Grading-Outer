/**
 * 
 * 
 **/

package com.easytnt.grading.domain.paper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.easytnt.commons.entity.share.ValueObject;
import com.easytnt.grading.domain.exam.Subject;
import com.easytnt.grading.domain.share.Area;

/**
 * <pre>
 * 试卷中的大题，如：一、选择题；二、填空题等
 * 在试卷中，不能直接作答的题目，都定义为大题
 * 如语文卷中，
 * 一、选择题；
 * 二、填空题
 * 三、阅读题目
 *   1、古诗词
 *   2、文言文阅读 
 *   3、现代文阅读
 *   3.1 请阅读下方
 *       ......
 *   3.1.1 本文主要内容是什么？
 *   3.1.2 
 *   
 * 其中,一、二、三，1、 2、3，3.1均定义为大题
 * 3.1.1、3.1.2不是大题，是小题{@link Item}}
 * </pre>
 * 
 * @author 李贵庆2015年10月14日
 * @version 1.0
 **/
public class Section implements ValueObject<Section>{

	private ExamPaper paper;

	private Long sectionOid;
	
	private Section parentSection;

	private Set<Section> subSection;
	
	private Subject subject;

	private String title;

	private String caption;
	
	private String maxPinci;
	
	private Float fullScore;

	private Set<Item> items;
	
	private Float maxerror;
	
	private Area area;

	public Section(ExamPaper examPaper){
		this.paper = examPaper;
		setOid();
	}
	public Section(ExamPaper examPaper,Float fullScore,String caption,String title){
		this.paper = examPaper;
		setOid();
		this.fullScore = fullScore;
		this.caption = caption;
		this.title = title;
	}
	
	public void addItem(Item item) {
		init();
		if (item.getFullScore() == null) {
			throw new UnsupportedOperationException("给分点为空");
		}
		item.setSection(this);
		this.items.add(item);
		finish();
	}
	private void init() {
		if (this.items == null) {
			this.items = new HashSet<Item>();
		}
	}
	public void updateItems(Item item){
		init();
		this.items.remove(item);
		addItem(item);
	}
	public void removeItems(Item item){
		init();
		item.setSection(null);
		this.items.remove(item);
	}
	public void clearItems(){
		init();
		this.items.clear();
	}
	public void finish(){
		Iterator<Item> iterItem =  items.iterator();
		float itemFullScores=0;
		while(iterItem.hasNext()){
			Item item = iterItem.next();
			itemFullScores+=item.getFullScore();
		}
		if(itemFullScores>this.getFullScore()){
			throw new UnsupportedOperationException("给分点大于试题分数");
		}
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.sectionOid)
				.toHashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Section))
			return false;
		Section other = (Section) o;

		return new EqualsBuilder()
				.append(this.sectionOid, other.sectionOid)
				.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(this.paper)
				.append(this.title).build();
	}
	
	@Override
	public boolean sameValueAs(Section other) {
		return this.equals(other);
	}
	private void setOid(){
		if(paper!=null &&paper.getPaperOid()!=null){
			if(paper.getSections()==null){
				this.sectionOid = paper.getPaperOid()*100+1;
			}else{
				this.sectionOid = paper.getPaperOid()*100+paper.getSections().size()+1;
			}
		}
			
	}
	//以下功能为ORM或者自动构造使用，非此慎用
	public Section() {
		
	}
	
	private Long sectionId;
	
	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public ExamPaper getPaper() {
		return paper;
	}

	public void setPaper(ExamPaper paper) {
		this.paper = paper;
		setOid();
	}

	public Section getParentSection() {
		return parentSection;
	}

	public void setParentSection(Section parentSection) {
		this.parentSection = parentSection;
	}

	public Set<Section> getSubSection() {
		return subSection;
	}

	public void setSubSection(Set<Section> subSection) {
		this.subSection = subSection;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
		finish();
	}

	public Float getFullScore() {
		return fullScore;
	}

	public void setFullScore(Float fullScore) {
		this.fullScore = fullScore;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Long getSectionOid() {
		return sectionOid;
	}

	public void setSectionOid(Long sectionOid) {
		this.sectionOid = sectionOid;
	}

	public String getMaxPinci() {
		return maxPinci;
	}

	public void setMaxPinci(String maxPinci) {
		this.maxPinci = maxPinci;
	}

	public Float getMaxerror() {
		return maxerror;
	}

	public void setMaxerror(Float maxerror) {
		this.maxerror = maxerror;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
}
