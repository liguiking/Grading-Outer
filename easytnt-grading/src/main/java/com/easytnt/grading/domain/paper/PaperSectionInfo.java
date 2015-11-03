/**
 * 
 * 
 **/

package com.easytnt.grading.domain.paper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.easytnt.commons.entity.share.ValueObject;
import com.easytnt.grading.domain.exam.Subject;

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
 * 3.1.1、3.1.2不是大题，是小题{@link PaperItemInfo}}
 * </pre>
 * 
 * @author 李贵庆2015年10月14日
 * @version 1.0
 **/
public class PaperSectionInfo implements ValueObject<PaperSectionInfo>{
	private Long sectionId;
	
	private PaperInfo paper;
	
	private Long sectionOid;

	private String sectionName;

	private String sectionCaption;
	
	private String maxPinci;
	
	private Integer left;
	
	private Integer top;
	
	private Integer width;
	
	private Integer height;
	
	private Float fullScore;
	
	private Float maxerror;

	private List<PaperItemInfo> items;

	public void addItem(PaperItemInfo item) {
		init();
		this.items.add(item);
	}
	
	public void addAllItems(Collection<PaperItemInfo> items) {
		init();
		this.items.addAll(items);
	}
	
	private void init() {
		if (this.items == null) {
			this.items = new ArrayList<>();
		}
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.sectionName)
				.append(this.paper)
//				.append(this.parentSection)
				.toHashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof PaperSectionInfo))
			return false;
		PaperSectionInfo other = (PaperSectionInfo) o;

		return new EqualsBuilder().append(this.sectionName, other.sectionName)
				.append(this.paper,other.paper)
//				.append(this.parentSection, other.parentSection)
				.isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(this.paper)
				.append(this.sectionName).build();
	}
	
	@Override
	public boolean sameValueAs(PaperSectionInfo other) {
		return this.equals(other);
	}

	//以下功能为ORM或者自动构造使用，非此慎用
	public PaperSectionInfo() {
		
	}

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public PaperInfo getPaper() {
		return paper;
	}

	public void setPaper(PaperInfo paper) {
		this.paper = paper;
	}
	

	public Long getSectionOid() {
		return sectionOid;
	}

	public void setSectionOid(Long sectionOid) {
		this.sectionOid = sectionOid;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getSectionCaption() {
		return sectionCaption;
	}

	public void setSectionCaption(String sectionCaption) {
		this.sectionCaption = sectionCaption;
	}

	public String getMaxPinci() {
		return maxPinci;
	}

	public void setMaxPinci(String maxPinci) {
		this.maxPinci = maxPinci;
	}

	public Integer getLeft() {
		return left;
	}

	public void setLeft(Integer left) {
		this.left = left;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Float getFullScore() {
		return fullScore;
	}

	public void setFullScore(Float fullScore) {
		this.fullScore = fullScore;
	}

	public Float getMaxerror() {
		return maxerror;
	}

	public void setMaxerror(Float maxerror) {
		this.maxerror = maxerror;
	}

	public List<PaperItemInfo> getItems() {
		return items;
	}

	public void setItems(List<PaperItemInfo> items) {
		this.items = items;
	}
	
	
}
