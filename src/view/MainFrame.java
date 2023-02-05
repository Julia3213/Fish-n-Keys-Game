package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

import field.Field;
import io.RecordWriter;
import model.Level;

public class MainFrame extends JFrame{
	Field field;
	JFrame frame = this;
	Graphics g;
	int lvl;
	int height;
	int width;
	long timeStart;
	long timeEnd;
	RecordWriter writer;
	public MainFrame(String title, int lev, Field f) {
		super(title);
		Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
		height = (int)sSize.getHeight();
		width = (int)sSize.getWidth();
		lvl = lev;
		if(lev!=-1) {
	
			Level level = new Level(lev,width,height);
			field = level.getField();
		}
		else {
			field = f;
		}
		timeStart = System.currentTimeMillis();
		FieldView fw = new FieldView(field);
		addKeyListener(new FishKeyAdapter());
		
		writer = new RecordWriter("time.txt", this);
		
		
		Container c = getContentPane();
		c.add(fw);
		setSize (sSize);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public class FishKeyAdapter extends KeyAdapter{
		public FishKeyAdapter() {
			super();
		}
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case(KeyEvent.VK_LEFT):
				for(int i = 0; i < 10; i++) {
					field.moveFish(field.getFish().getX() - 1, field.getFish().getY());
				}
				break;
			case(KeyEvent.VK_RIGHT):
				for(int i = 0; i < 10; i++) {
					field.moveFish(field.getFish().getX() + 1, field.getFish().getY());
				}
				if(field.isWin()) {
					timeEnd = System.currentTimeMillis();
					if(lvl!=-1) {
						writer.write();
						if(lvl == 3) {
							setVisible(false);
							dispose();
							return;
						}
						lvl++;
						MainFrame m = new MainFrame(("Level "+lvl), lvl, null);
						setVisible(false);
						dispose();
					}
					else {
						setVisible(false);
						dispose();
					}
				}
				break;
			case(KeyEvent.VK_UP):
				for(int i = 0; i < 5; i++) {
					field.moveFish(field.getFish().getX(), field.getFish().getY() - 1);
				}
				break;
			case(KeyEvent.VK_DOWN):
				for(int i = 0; i < 5; i++) {
					field.moveFish(field.getFish().getX(), field.getFish().getY() + 1);
				}
				break;
			}
			repaint();
		}
	}
	
	public long getTimeStart() {
		return timeStart;
	}
	
	public long getTimeEnd() {
		return timeEnd;
	}
	
	public int getLevel() {
		return lvl;
	}
}
