package io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import field.Barrier;
import field.Door;
import field.Field;
import field.Key;
import field.Lastdoor;

public class FieldWriter {
	Field field;
	Writer writer;
	public FieldWriter(String filename, Field f) {
		field = f;
		try {
			writer = new FileWriter(filename, true);
		}
		catch(IOException e) {
			System.out.println("no sush file");
		}
	}
	
	public void write() {
		try {
			writer.write("begin\n");
			for(int i = 4; i < field.getBarriers().size(); i++) {
				Barrier b = field.getBarriers().get(i);
				String s = "b: ( "+b.getLength()+" "+b.getWidth()+" "+b.getX()+" "+b.getY()+" )\n";
				writer.write(s);
			}
			Iterator<Entry<Key, Door>> i = field.getLocks().entrySet().iterator();
			while(i.hasNext()) {
				Map.Entry<Key, Door> entry = i.next();
				Key k = entry.getKey();
				Door d = entry.getValue();
				String s;
				if(d instanceof Lastdoor) {
					s = "el: ( "+k.getX()+" "+k.getY()+" "+d.getX1()+" "+d.getY1()+" "+d.getX2()+" "+d.getY2()+" "+d.getOrientation()+" "+k.getColor()+" )\n";
				}
				else {
					s = "l: ( "+k.getX()+" "+k.getY()+" "+d.getX1()+" "+d.getY1()+" "+d.getX2()+" "+d.getY2()+" "+d.getOrientation()+" "+k.getColor()+" )\n";
				}
				writer.write(s);
			}
			writer.write("end\n");
			writer.flush();
			writer.close();
		}catch(IOException e) {System.out.println("no such file");}
	}
}
