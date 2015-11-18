package com.easytnt.grading.service.impl;

import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.easytnt.grading.service.ListDataSourceReader;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

public class ListDataSourceReaderDBFImpl implements ListDataSourceReader {
	
	private InputStream is;
	
	public ListDataSourceReaderDBFImpl(){}
	public ListDataSourceReaderDBFImpl(InputStream is){
		this.is = is;
	}
	DBFReader reader;
	private List<String[]> dataList;
	//打开数据源
	@Override
	public void open() throws Exception {
		reader = new DBFReader(is); 
		dataList = new ArrayList<String[]>();
	}

	//获取某行数据
	@Override
	public String[] get(int row) throws Exception {
		if(reader.getRecordCount()<=row){
			throw new IndexOutOfBoundsException();
		}
		if(row/100!=0){
			dataList.clear();
		}
		int size = row%100-1;
		while(dataList.size()<=size){
			String[] str = reader.nextRecord();
			dataList.add(str);
		}
		return dataList.get(size);
	}
	
	//获取某行某列数据
	@Override
	public String get(int row, int col) throws Exception {
		String[] str = get(row);
		if(str.length<=col){
			throw new IndexOutOfBoundsException();
		}
		return str[col];
	}
	public String[] getTitle() throws DBFException{
		  int fieldsCount = reader.getFieldCount();
		  String[] titles = new String[fieldsCount];
          // 取出字段信息  
          for (int i = 0; i < fieldsCount; i++) {  
              DBFField field = reader.getField(i); 
              titles[i] = field.getName();
          }
          return titles;
	}
	//关闭数据源
	@Override
	public void close() throws Exception {
		is.close();
		dataList = null;
	}
}
