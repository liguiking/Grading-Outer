/**
 * 
 * 
 **/

package com.easytnt.grading.domain.exam;

import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.easytnt.commons.entity.share.ValueObject;

/**
 * <pre>
 * 考试的科目， 并非教学中的科目，就是指一类试卷 
 * 如七年级语文（只包含教学中的七年级语文），九年级科学（可能包含九年级物理、化学、生物等几个教学中的科目）
 * </pre>
 * 
 * @author 李贵庆2015年10月19日
 * @version 1.0
 **/
public class Subject implements ValueObject<Subject> {

	/**
	 * @author钟水林20151103
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String subject_name;

	private int subject_code;

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.subject_name).append(this.subject_code).toHashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Subject))
			return false;
		Subject other = (Subject) o;

		return new EqualsBuilder().append(this.subject_name, other.subject_name).append(this.subject_code, other.subject_code).append(this.subject_code, other.subject_code).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(this.subject_name).append(this.subject_code).build();
	}

	@Override
	public boolean sameValueAs(Subject other) {
		return this.equals(other);
	}

	// 以下功能为ORM或者自动构造使用，非此慎用
	public Subject() {
	}
	
	private Long subject_id;

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public int getSubject_code() {
		return subject_code;
	}

	public void setSubject_code(int subject_code) {
		this.subject_code = subject_code;
	}

	public Long getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(Long subject_id) {
		this.subject_id = subject_id;
	}

}
