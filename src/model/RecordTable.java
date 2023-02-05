package model;

import java.io.*;
import java.util.*;
import java.util.Map.*;

import io.RecordReader;

public class RecordTable {
	private HashMap<Integer, Integer> times;
	private File file;
	RecordReader reader;
	
	public RecordTable() {
		times = new HashMap<>();
		file = new File("time.txt");
		reader = new RecordReader("time.txt", this);
		reader.load();
	}
	
	public HashMap<Integer, Integer> getMap(){
		return times;
	}
	
	public void putTime(int level, int time) {
		if(times.containsKey(level)) {
			if(times.get(level) > time)
				times.put(level, time);
		}
		else
			times.put(level, time);
	}
	
	public Object[][] toObjectArray(){
		Object[][] data = new Integer[times.size()][2];
		Iterator<Entry<Integer, Integer>> i = times.entrySet().iterator();
		for(int j = 0 ; j < times.size(); j++) {
			Map.Entry<Integer, Integer> t = i.next();
			data[j][0] = t.getKey();
			data[j][1] = t.getValue();
		}
		return data;
	}
}
