package io;

import java.io.*;
import java.util.Scanner;

import field.Barrier;
import field.Door;
import field.Field;
import field.Key;
import field.Lastdoor;
import view.CreatedLevels;

public class LevelReader {
	Reader reader;
	CreatedLevels cLevels;
	public LevelReader(String filename, CreatedLevels cl) {
		cLevels = cl;
		try {
			reader = new FileReader(filename);
		}
		catch(IOException e) {}
	}
	public void read() {
		int c = 0;
		char []buffer = new char[1024];
		StringBuilder str = new StringBuilder();
		String s = "";
		try {
			while((c=reader.read(buffer)) >= 0) {
				s = String.valueOf(buffer, 0 , c);
				str.append(s);
			}
			s = str.toString();
			reader.close();
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		Scanner scan = new Scanner(s);
		while(scan.hasNextLine()) {
			StringBuilder stmp = new StringBuilder();
			String tmp = scan.nextLine();
			while(!tmp.equals("end")) {
				stmp.append(tmp + "\n");
				tmp = scan.nextLine();
			}
			tmp = stmp.toString();
			scanLevel(tmp);
		}
		scan.close();
	}
	
	public void scanLevel(String l) {
		Scanner scan = new Scanner(l);
		Field field = new Field(cLevels.getWidth(), cLevels.getHeight());
		while(scan.hasNextLine()) {
			Scanner tt = new Scanner(scan.nextLine());
			switch(tt.next()) {
			case("b:"):
				tt.next();
				Barrier b = new Barrier(tt.nextInt(),tt.nextInt(),tt.nextInt(),tt.nextInt());
				field.setBarrier(b);
				break;
			case("l:"):
				tt.next();
				Key k = new Key(tt.nextInt(), tt.nextInt());
				Door d = new Door(tt.nextInt(), tt.nextInt(), tt.nextInt(), tt.nextInt(), tt.nextBoolean());
				int color = tt.nextInt();
				k.setColor(color);
				d.setColor(color);
				field.setLock(d,k);
				break;
			case("el:"):
				tt.next();
				Key ek = new Key(tt.nextInt(), tt.nextInt());
				Lastdoor ed = new Lastdoor(cLevels.getWidth(), cLevels.getHeight());
				ek.setColor(6);
				field.setLock(ed,ek);
				break;
			}
		}
		scan.close();
		cLevels.addLevel(field);
	}
}
