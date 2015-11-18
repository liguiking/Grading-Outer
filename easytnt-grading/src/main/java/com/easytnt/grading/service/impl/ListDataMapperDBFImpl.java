package com.easytnt.grading.service.impl;

import java.io.InputStream;
import java.util.Map;

import com.easytnt.grading.service.ListDataMapper;

public class ListDataMapperDBFImpl implements ListDataMapper {
	
	private InputStream is;
	
	private String[] titleList;
	
	private Map<String,String> Map;
	
	public ListDataMapperDBFImpl(){};
	
	public ListDataMapperDBFImpl(InputStream is,Map<String,String> map){
		this.is = is;
		this.Map = map;
	}
	private void init() throws Exception{
		if(is!=null && (titleList ==null || titleList.length==0)){
			ListDataSourceReaderDBFImpl reader = new ListDataSourceReaderDBFImpl(is);
			 	reader.open();
				titleList = reader.getTitle();
				reader.close();
		}
	}
	//读取数据源到目标数据列映射
	@Override
	public int getColIndex(String targetName) throws Exception {
		init();
		for(int i=0,j=titleList.length;i<j;i++){
			if(titleList[i].equals(Map.get(targetName))){
				return i;
			}
		}
		return 0;
	}
	
	
	public String[] getTitleList() throws Exception {
		init();
		return titleList;
	}
	public InputStream getIs() {
		return is;
	}
	public void setIs(InputStream is) {
		this.is = is;
	}
	public Map<String, String> getMap() {
		return Map;
	}
	public void setMap(Map<String, String> map) {
		Map = map;
	}
	
	
}
