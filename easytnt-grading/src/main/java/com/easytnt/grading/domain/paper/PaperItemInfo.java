/**
 * 
 * 
 **/

package com.easytnt.grading.domain.paper;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.NumberUtils;

import com.easytnt.commons.entity.share.ValueObject;
import com.easytnt.grading.domain.share.Area;

/**
 * <pre>
 * 给分点
 * </pre>
 * 
 * @author 李贵庆2015年10月14日
 * @version 1.0
 **/
public class PaperItemInfo implements ValueObject<PaperItemInfo> {
	private Long itemId;
	
	private PaperSectionInfo section;
	
	private Long itemOid;
	
	private String itemName;

	private String itemCaption;

	private Integer  left;
	
	private Integer top;
	
	private Integer width;
	
	private Integer height;
	
	private Float fullScore;
	
	private Float validscoredot;
	
	private Float[] validValues;
	
	public PaperItemInfo(String title,Float fullScore) {
		this.itemName = title;
		this.fullScore = fullScore;
	}

	public boolean isEffectiveScore(Float score) {
		return score.compareTo(this.getMinPoint()) >= 0
				&& score.compareTo(getMaxPoint()) <= 0;
	}
	
	public void genValidValues(String validscoredot) {
		String[] values = validscoredot.split(",");
		if(values.length > 0) {
			Float[] scores = new Float[values.length];
			int i = 0;
			for(String value:values) {
				scores[i++] = NumberUtils.parseNumber(value, Float.class);
			}
			if(!this.isEffectiveScore(scores[scores.length-1])) {
				throw new IllegalArgumentException(validscoredot + "不在有效分范围内");
			}
			
			this.validValues = scores;
		}
		
	}

	public Float getMinPoint() {
		Float[] allPoints = getAllPoints();
		return allPoints[0];
	}

	public Float getMaxPoint() {
		return fullScore;
	}

	public Float[] getAllPoints() {
		// TODO
		return new Float[] { 0f, 1f, 2f };
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.itemName).append(this.fullScore).toHashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof PaperItemInfo))
			return false;
		
		PaperItemInfo other = (PaperItemInfo) o;
		return new EqualsBuilder().append(this.itemName, other.itemName)
				.append(this.fullScore, other.fullScore).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(this.itemName).append(this.fullScore).build();
	}

	@Override
	public boolean sameValueAs(PaperItemInfo other) {
		return this.equals(other);
	}
	
	public static class Builder{
		private PaperItemInfo item;
		
		public Builder(String itemName) {
			this.item = new PaperItemInfo();
			this.item.itemName = itemName;
		}
		
		public Builder itemCaption(String itemCaption) {
			this.item.itemCaption = itemCaption;
			return this;
		}
		
		public Builder validValues(Float[] validValues) {
			this.item.validValues = validValues;
			return this;
		}
		
		public Builder fullScore(Float fullScore) {
			this.item.fullScore = fullScore;
			return this;
		}
		
		public PaperItemInfo create() {
			return this.item;
		}
	}

	//以下功能为ORM或者自动构造使用，非此慎用
	public PaperItemInfo() {
		
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public PaperSectionInfo getSection() {
		return section;
	}

	public void setSection(PaperSectionInfo section) {
		this.section = section;
	}

	public Long getItemOid() {
		return itemOid;
	}

	public void setItemOid(Long itemOid) {
		this.itemOid = itemOid;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCaption() {
		return itemCaption;
	}

	public void setItemCaption(String itemCaption) {
		this.itemCaption = itemCaption;
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

	public Float getValidscoredot() {
		return validscoredot;
	}

	public void setValidscoredot(Float validscoredot) {
		this.validscoredot = validscoredot;
	}

	public Float[] getValidValues() {
		return validValues;
	}

	public void setValidValues(Float[] validValues) {
		this.validValues = validValues;
	}
	
	
}
