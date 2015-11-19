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
			rows.add(new String[] {"a区","a","学校1","school1","student1","学生1","男","民族","2015-12-10",null,"1","考生1","123456","1","1","考场1","code1","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校2","school2","student2","学生2","男","民族","2015-12-10",null,"2","考生2","123457","1","1","考场2","code2","1","1","数学","2015-11-19","2015-11-19"});
			rows.add(new String[] {"a区","a","学校3","school3","student3","学生3","男","民族","2015-12-10",null,"3","考生3","123458","1","1","考场3","code3","1","1","数学","2015-11-19","2015-11-19"});
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
