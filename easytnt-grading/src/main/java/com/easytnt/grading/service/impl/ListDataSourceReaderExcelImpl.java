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

public class ListDataSourceReaderExcelImpl implements ListDataSourceReader {
	
	private InputStream is;
	
	public ListDataSourceReaderExcelImpl(){}
	public ListDataSourceReaderExcelImpl(InputStream is){
		this.is = is;
	}
	private Workbook workBook = null;
	private Sheet sheet = null;
	//打开数据源
	@Override
	public void open() throws Exception {
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

	//获取某行数据
	@Override
	public String[] get(int row) throws Exception {
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

	//获取某行某列数据
	@Override
	public String get(int row, int col) throws Exception {
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

	//关闭数据源
	@Override
	public void close() throws Exception {
		is.close();
	}
}
