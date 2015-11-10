/**
 * 
 * 
 **/

package com.easytnt.grading.domain.paper;

import com.easytnt.commons.entity.share.Entity;

/** 
 * <pre>
 * 考卷，就是一类试卷，如语文试卷，数学试卷，理科综合试卷
 * </pre>
 *  
 * @author 李贵庆2015年10月14日
 * @version 1.0
 **/
public class PaperCard implements Entity<PaperCard> {
	private Long cardId;
	
	private ExamPaper paper;
	
	private Integer cardSeq;
	
	private String path;
	
	public PaperCard(ExamPaper paper){
		this.paper = paper;
	}
	public PaperCard(ExamPaper paper,Integer cardSeq,String path){
		this.paper = paper;
		this.cardSeq = cardSeq;
		this.path = path;
	}
	@Override
	public boolean sameIdentityAs(PaperCard other) {
		return this.equals(other);
	}
	
	//以下功能为ORM或者自动构造使用，非此慎用
	
	private PaperCard(){
		
	}
	
	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public ExamPaper getPaper() {
		return paper;
	}

	public void setPaper(ExamPaper paper) {
		this.paper = paper;
	}

	public Integer getCardSeq() {
		return cardSeq;
	}

	public void setCardSeq(Integer cardSeq) {
		this.cardSeq = cardSeq;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}

