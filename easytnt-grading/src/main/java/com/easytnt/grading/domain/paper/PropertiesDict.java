/**
 * <p><b>© </b></p>
 * 
 **/
package com.easytnt.grading.domain.paper;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.easytnt.commons.entity.share.ValueObject;

/** 
 * <pre>
 * 
 * </pre>
 * 
 * @author 李贵庆 2015年10月24日
 * @version 1.0
 **/
public class PropertiesDict implements ValueObject<PropertiesDict>{
	
	private Long prodictId;
	
	private String prodictCatagory;
	
	private String  prodictCode;
	
	private String prodictName;
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.prodictCode).toHashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof PropertiesDict))
			return false;
		
		PropertiesDict other = (PropertiesDict) o;
		return new EqualsBuilder().append(this.prodictCode, other.prodictCode).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(this.prodictName).append(this.prodictCode).build();
	}
	
	@Override
	public boolean sameValueAs(PropertiesDict other) {
		return this.equals(other);
	}
	
	
	//以下功能为ORM或者自动构造使用，非此慎用
	public PropertiesDict() {
		
	}

	public Long getProdictId() {
		return prodictId;
	}

	public void setProdictId(Long prodictId) {
		this.prodictId = prodictId;
	}

	public String getProdictCatagory() {
		return prodictCatagory;
	}

	public void setProdictCatagory(String prodictCatagory) {
		this.prodictCatagory = prodictCatagory;
	}

	public String getProdictCode() {
		return prodictCode;
	}

	public void setProdictCode(String prodictCode) {
		this.prodictCode = prodictCode;
	}

	public String getProdictName() {
		return prodictName;
	}

	public void setProdictName(String prodictName) {
		this.prodictName = prodictName;
	}

}


