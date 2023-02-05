package io;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import model.RecordTable;

public class RecordReader {
	RecordTable recordTable;
	Reader reader;
	public RecordReader(String filename, RecordTable rt) {
		recordTable = rt;
		try {
			reader = new FileReader(filename);
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public void load() {
		try {
			int level;
			int time;
			StringBuffer str = new StringBuffer();
			char[] buf = new char[1024];
			int c = 0;
			String s = "";
			while((c = reader.read(buf))>=0) {
				s = String.valueOf(buf, 0, c);
				str.append(s);
			}
			s = str.toString();
			reader.close();
			Scanner scan = new Scanner(s);
			while(scan.hasNextLine()) {
				String t = scan.nextLine();
				Scanner scant = new Scanner(t);
				if(scant.hasNextInt()) {
					level = scant.nextInt();
				}
				else {
					scant.close();
					throw new RuntimeException("false format");
				}
				if(scant.hasNextInt()) {
					time = scant.nextInt();
				}
				else {
					scant.close();
					throw new RuntimeException("false format");
				}
				scant.close();
				recordTable.putTime(level, time);
			}
			for(int i = 1; i < 4; i++) {
				recordTable.putTime(i, 4000);
			}
			scan.close();
		}
		catch(IOException ioe) {
			System.out.println("file error");
		}
	}
}
