package com.easytnt.grading.service.impl;

import java.io.IOException;
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

public class ListDataSourceReaderImpl implements ListDataSourceReader {
	
	private InputStream is;
	private String fileName;
	public ListDataSourceReaderImpl(){}
	public ListDataSourceReaderImpl(InputStream is,String fileName){
		this.is = is;
		this.fileName = fileName;
	}
	private Workbook workBook = null;
	private Sheet sheet = null;
	private DBFReader reader;
	private List<String[]> dataList;
	//打开数据源
	@Override
	public void open() throws Exception {
		if(fileName.toLowerCase().endsWith("dbf")){
			openDBF();
		}else{
			openExcel();
		}
	}
	private void openExcel() throws IOException{
		//判断能否正确解析文件，是否是想要的文件解析内容
		if(!is.markSupported()){
			is = new PushbackInputStream(is,8);
		}
		if (POIFSFileSystem.hasPOIFSHeader(is)) {//2003  
			workBook = new HSSFWorkbook(is);  
       }else if (POIXMLDocument.hasOOXMLHeader(is)) {//2007  
    	   //各种出错，所以手动进行装箱操作
    	   XSSFWorkbook temp = new XSSFWorkbook(is);  
    	   workBook = temp;
    	   sheet = workBook.getSheetAt(0);
       }
	}
	private void openDBF() throws IOException{
		reader = new DBFReader(is); 
		dataList = new ArrayList<String[]>();
	}
	//获取某行数据
	@Override
	public String[] get(int row) throws Exception {
		if(fileName.toLowerCase().endsWith("dbf")){
			return getDBF(row);
		}else{
			return getExcel(row);
		}
	}
	private String[] getExcel(int row){
		if(sheet.getPhysicalNumberOfRows()<=row){
			throw new IndexOutOfBoundsException();
		}
		List<String> result = new ArrayList<String>();
		Row thisRow = sheet.getRow(row);
		Iterator<Cell> cells =  thisRow.iterator();
		while(cells.hasNext()){
			Cell cell = cells.next();
			result.add(cell.getStringCellValue());
		}
		String[] str=new String[]{};
		str = result.toArray(str);
		return str;
	}
	private String[] getDBF(int row) throws DBFException{
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
		if(fileName.toLowerCase().endsWith("dbf")){
			return getDBF(row,col);
		}else{
			return getExcel(row,col);
		}
	}
	private String getExcel(int row, int col) throws Exception {
		if(sheet.getPhysicalNumberOfRows()<=row){
			throw new IndexOutOfBoundsException();
		}
		Row thisRow = sheet.getRow(row);
		if(thisRow.getPhysicalNumberOfCells()<=col){
			throw new IndexOutOfBoundsException();
		}
		Cell cell = thisRow.getCell(col);
		return cell.getStringCellValue();
	}
	private String getDBF(int row, int col) throws Exception {
		String[] str = get(row);
		if(str.length<=col){
			throw new IndexOutOfBoundsException();
		}
		return str[col];
	}
	public  String[] getTitle() throws DBFException{
		if(fileName.toLowerCase().endsWith("dbf")){
			return getDBFTitle();
		}else{
			return getExcelTitle();
		}
	}
	
	private  String[] getExcelTitle() throws DBFException{
		 return getExcel(0);
	}
	private  String[] getDBFTitle() throws DBFException{
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
	}
	public InputStream getIs() {
		return is;
	}
	public void setIs(InputStream is) {
		this.is = is;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
