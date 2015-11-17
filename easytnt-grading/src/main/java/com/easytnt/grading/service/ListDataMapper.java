package com.easytnt.grading.service;


/** 
 * <pre>
 * 行列数据源映射配置
 * </pre>
 * 
 * @author 李贵庆 2015年11月17日
 * @version 1.0
 **/
public interface ListDataMapper {

	/**
	 * 读取数据源到目标数据列映射
	 * @param targetIndex
	 * @param sourceIndex
	 * @return
	 * @throws IndexOutOfBoundException sourceIndex大于数据源列数时抛出
	 */
	public int getColIndex(String targetName) throws Exception; 
	 
}
