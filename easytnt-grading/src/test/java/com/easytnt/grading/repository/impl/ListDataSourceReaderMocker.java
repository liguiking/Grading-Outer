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
			rows.add(new String[] {"a区","a","school1","学校1"});
			rows.add(new String[] {"a区","a","school2","学校2"});
			rows.add(new String[] {"a区","a","school3","学校3"});
		}

		@Override
		public String[] get(int row) throws Exception {
			return this.rows.get(row);
		}

		@Override
		public String get(int row, int col) throws Exception {
			return this.rows.get(row)[col-1];
		}

		@Override
		public void close() throws Exception {
			rows.clear();

		}
}
