package com.easytnt.grading.repository.impl;

import java.util.ArrayList;

import com.easytnt.grading.service.ListDataSourceReader;

public class ListDataSourceReaderMocker implements ListDataSourceReader {
	//private HashMap<String,String[]> rows = new HashMap<>();
	
		private ArrayList<String[]> rows = new ArrayList<>();
		
		public ListDataSourceReaderMocker() {
			
		}
		
		
		
		@Override
		public void open() throws Exception {
			rows.add(new String[] {"a区","a","学校1","school1","a1student1","学生1","男","民族","2015-12-10",null,"1","考生1","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校1","school1","a1student2","学生2","男","民族","2015-12-10",null,"1","考生2","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校1","school1","a1student3","学生3","男","民族","2015-12-10",null,"1","考生3","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校1","school1","a1student4","学生4","男","民族","2015-12-10",null,"1","考生4","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校1","school1","a1student5","学生5","男","民族","2015-12-10",null,"1","考生5","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校1","school1","a1student6","学生6","男","民族","2015-12-10",null,"1","考生6","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校1","school1","a1student7","学生7","男","民族","2015-12-10",null,"1","考生7","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校1","school1","a1student8","学生8","男","民族","2015-12-10",null,"1","考生8","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校1","school1","a1student9","学生9","男","民族","2015-12-10",null,"1","考生9","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校1","school1","a1student10","学生10","男","民族","2015-12-10",null,"1","考生10","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			
			rows.add(new String[] {"a区","a","学校2","school2","a2student1","学生1","男","民族","2015-12-10",null,"1","考生1","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校2","school2","a2student2","学生2","男","民族","2015-12-10",null,"1","考生2","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校2","school2","a2student3","学生3","男","民族","2015-12-10",null,"1","考生3","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校2","school2","a2student4","学生4","男","民族","2015-12-10",null,"1","考生4","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校2","school2","a2student5","学生5","男","民族","2015-12-10",null,"1","考生5","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校2","school2","a2student6","学生6","男","民族","2015-12-10",null,"1","考生6","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校2","school2","a2student7","学生7","男","民族","2015-12-10",null,"1","考生7","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校2","school2","a2student8","学生8","男","民族","2015-12-10",null,"1","考生8","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校2","school2","a2student9","学生9","男","民族","2015-12-10",null,"1","考生9","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校2","school2","a2student10","学生10","男","民族","2015-12-10",null,"1","考生10","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			
			rows.add(new String[] {"a区","a","学校3","school3","a3student1","学生1","男","民族","2015-12-10",null,"1","考生1","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校3","school3","a3student2","学生2","男","民族","2015-12-10",null,"1","考生2","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校3","school3","a3student3","学生3","男","民族","2015-12-10",null,"1","考生3","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校3","school3","a3student4","学生4","男","民族","2015-12-10",null,"1","考生4","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校3","school3","a3student5","学生5","男","民族","2015-12-10",null,"1","考生5","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校3","school3","a3student6","学生6","男","民族","2015-12-10",null,"1","考生6","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校3","school3","a3student7","学生7","男","民族","2015-12-10",null,"1","考生7","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校3","school3","a3student8","学生8","男","民族","2015-12-10",null,"1","考生8","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校3","school3","a3student9","学生9","男","民族","2015-12-10",null,"1","考生9","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校3","school3","a3student10","学生10","男","民族","2015-12-10",null,"1","考生10","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			
			rows.add(new String[] {"b区","b","学校1","school1","b1student1","学生1","男","民族","2015-12-10",null,"1","考生1","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校1","school1","b1student2","学生2","男","民族","2015-12-10",null,"1","考生2","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校1","school1","b1student3","学生3","男","民族","2015-12-10",null,"1","考生3","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校1","school1","b1student4","学生4","男","民族","2015-12-10",null,"1","考生4","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校1","school1","b1student5","学生5","男","民族","2015-12-10",null,"1","考生5","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校1","school1","b1student6","学生6","男","民族","2015-12-10",null,"1","考生6","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校1","school1","b1student7","学生7","男","民族","2015-12-10",null,"1","考生7","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校1","school1","b1student8","学生8","男","民族","2015-12-10",null,"1","考生8","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校1","school1","b1student9","学生9","男","民族","2015-12-10",null,"1","考生9","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校1","school1","b1student10","学生10","男","民族","2015-12-10",null,"1","考生10","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			
			rows.add(new String[] {"b区","b","学校2","school2","b2student1","学生1","男","民族","2015-12-10",null,"1","考生1","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校2","school2","b2student2","学生2","男","民族","2015-12-10",null,"1","考生2","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校2","school2","b2student3","学生3","男","民族","2015-12-10",null,"1","考生3","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校2","school2","b2student4","学生4","男","民族","2015-12-10",null,"1","考生4","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校2","school2","b2student5","学生5","男","民族","2015-12-10",null,"1","考生5","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校2","school2","b2student6","学生6","男","民族","2015-12-10",null,"1","考生6","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校2","school2","b2student7","学生7","男","民族","2015-12-10",null,"1","考生7","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校2","school2","b2student8","学生8","男","民族","2015-12-10",null,"1","考生8","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校2","school2","b2student9","学生9","男","民族","2015-12-10",null,"1","考生9","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"b区","b","学校2","school2","b2student10","学生10","男","民族","2015-12-10",null,"1","考生10","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"c区","c",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null});
		}

		@Override
		public String[] get(int row) throws Exception {
			return this.rows.get(row-1);
		}

		@Override
		public String get(int row, int col) throws Exception {
			return this.rows.get(row-1)[col];
		}

		@Override
		public void close() throws Exception {
			rows.clear();

		}
}
