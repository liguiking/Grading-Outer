package com.easytnt.grading.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private BufferedReader br = null;
	//打开数据源
	@Override
	public void open() throws Exception {
		if(fileName.toLowerCase().endsWith("dbf")){
			openDBF();
		}else if(fileName.toLowerCase().endsWith("csv")){
			openCSV();
		}else{
			openExcel();
		}
	}
	//获取某行数据
	@Override
	public String[] get(int row) throws Exception {
		if(fileName.toLowerCase().endsWith("dbf")){
			return getDBF(row);
		}else if(fileName.toLowerCase().endsWith("csv")){
			return getCSV(row);
		}else{
			return getExcel(row);
		}
	}
	//获取某行某列数据
	@Override
	public String get(int row, int col) throws Exception {
		if(col==-1){
			return null;
		}
		if(fileName.toLowerCase().endsWith("dbf")){
			return getDBF(row,col);
		}else if(fileName.toLowerCase().endsWith("csv")){
			return getCSV(row,col);
		}else{
			return getExcel(row,col);
		}
	}
	
	public  String[] getTitle() throws IOException{
		if(fileName.toLowerCase().endsWith("dbf")){
			return getDBFTitle();
		}else if(fileName.toLowerCase().endsWith("csv")){
			return getCSVTitle();
		}else{
			return getExcelTitle();
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
       }
		sheet = workBook.getSheetAt(0);
	}
	private void openDBF() throws IOException{
		reader = new DBFReader(is); 
		dataList = new ArrayList<String[]>();
	}
	private void openCSV() throws IOException{
		br = new BufferedReader(new InputStreamReader(is,"GBK")); 
		dataList = new ArrayList<String[]>();
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
			cell.setCellType(Cell.CELL_TYPE_STRING);
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
	private String[] getCSV(int row) throws IOException{
        dataList = new ArrayList<String[]>();
        while(dataList.size()<=row){
        	csvReadLine();
        }
		return dataList.get(row);
	}
	private void csvReadLine() throws IOException{
		String rec = null;//一行   
        String str;//一个单元格 
		if((rec = br.readLine()) != null) {   
            Pattern pCells = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");   
            Matcher mCells = pCells.matcher(rec);   
            List<String> cells = new ArrayList<String>();//每行记录一个list   
            //读取每个单元格   
            while (mCells.find()) {   
                str = mCells.group();   
                str = str.replaceAll("(?sm)/?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");   
                str = str.replaceAll("(?sm)(\"(\"))", "$2");   
                cells.add(str);   
            }
            String[] data=new String[]{};
            data = cells.toArray(data);
            dataList.add(data);
        }else{
        	throw new IndexOutOfBoundsException();
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
		cell.setCellType(Cell.CELL_TYPE_STRING);
		return cell.getStringCellValue();
	}
	private String getDBF(int row, int col) throws Exception {
		return getString(row,col);
	}
	private String getCSV(int row, int col) throws Exception{
		return getString(row,col);
	}
	private String getString(int row, int col) throws Exception{
		String[] str = get(row);
		if(str.length<=col){
			throw new IndexOutOfBoundsException();
		}
		return str[col];
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
	private  String[] getCSVTitle() throws IOException{
		  return getCSV(0);
	}
	
	//关闭数据源
	@Override
	public void close() throws Exception {
		if(!(br == null)){
			br.close();
		}
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
