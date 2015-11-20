/**
 * 
 * 
 **/

package com.easytnt.grading.domain.room;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.easytnt.commons.entity.share.Entity;

/** 
 * <pre>
 * 考生
 * </pre>
 *  
 * @author 李贵庆2015年10月14日
 * @version 1.0
 **/
public class Examinee implements Entity<Examinee>{
	public static Map<String,String> valueMap;
	static{
		valueMap = new LinkedHashMap<String,String>();
		valueMap.put("student_number", "学籍号");
		valueMap.put("student_name", "学生姓名");
		valueMap.put("gender", "性别");
		valueMap.put("nation", "民族");
		valueMap.put("birthday", "出生日期");
		valueMap.put("seating_number", "座位号");
		valueMap.put("examinne_uuid", "准考证号");
		valueMap.put("uuid_type", "身份证");
		valueMap.put("arts", "文理科标志");
		valueMap.put("clazz_name", "班级名称");
		valueMap.put("clazz_code", "班级代码");
		valueMap.put("room_number", "考场号");
		valueMap.put("school_name", "学校名称");
		valueMap.put("school_code", "学校代码");
		valueMap.put("district_number", "行政区编号");
		valueMap.put("district_name", "行政区名称");
		
	}
	
	private String uuid;
	
	private Room room;
	
	public Examinee(String uuid,Room room) {
		this.uuid = uuid;
		this.room = room;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.uuid).toHashCode();
	}
	
    @Override
	public boolean equals(Object o) {
		if(!(o instanceof Examinee))
			return false;
		Examinee other = (Examinee)o;
		
		return new EqualsBuilder().append(this.uuid,other.uuid).isEquals();
	}
	
    @Override
	public String toString() {
		return new ToStringBuilder(this).append(this.uuid).build();
	}
    
	@Override
	public boolean sameIdentityAs(Examinee other) {
		return this.equals(other);
	}
	
	public Examinee(){
		
	}
	
	private Long id;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}

