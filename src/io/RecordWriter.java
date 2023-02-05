package io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import view.MainFrame;

public class RecordWriter {
	MainFrame mf;
	Writer writer;
	public RecordWriter(String filename, MainFrame m) {
		mf = m;
		try {
			writer = new FileWriter(filename, true);
		}
		catch(IOException e) {
			System.out.println("no such file");
		}
	}
	public void write() {
		try {
			int time = (int)(mf.getTimeEnd() - mf.getTimeStart())/1000;
			writer.write(mf.getLevel()+" "+time+"\n");
			writer.flush();
			writer.close();
		}
		catch(IOException ioe) {
			System.out.println("no such file");
		}
	}
}
