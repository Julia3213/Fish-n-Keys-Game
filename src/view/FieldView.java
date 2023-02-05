package view;

import java.awt.*;
import javax.swing.*;

import java.util.HashMap;

import field.Barrier;
import field.Door;
import field.Field;
import field.Fish;
import field.Key;

public class FieldView extends JPanel{
	Field field;
	HashMap<Integer, Color> colors;
	
	public FieldView(Field f) {
		field = f;
		colors = new HashMap<>();
		colors.put(0, Color.red);
		colors.put(1, Color.green);
		colors.put(2, new Color(253, 229, 7));
		colors.put(3, new Color(255, 144, 33));
		colors.put(4, Color.blue);
		colors.put(5, Color.cyan);
		colors.put(6, Color.black);
	}
	
	public Field getField() {
		return field;
	}
	
	public void setNewField(Field f) {
		field = f;
		repaint();
	}
	
	public HashMap<Integer,Color> getColors(){
		return colors;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		for(int i = 0; i < field.getBarriers().size(); i++) {
			Barrier b = field.getBarriers().get(i);
			g.fillRect(b.getX(), b.getY(), b.getLength(), b.getWidth());
		}
		for(int i = 0; i < field.getKeys().size(); i++) {
			Key k = field.getKeys().get(i);
			g.setColor(colors.get(k.getColor()));
			g.fillOval(k.getX(), k.getY(), 10, 10);
			g.drawLine(k.getX()+5, k.getY(), k.getX()+5, k.getY()-7);
			g.drawLine(k.getX()+5, k.getY()-7, k.getX()+8, k.getY()-7);
			g.drawLine(k.getX()+5, k.getY()-4, k.getX()+8, k.getY()-4);
			g.setColor(Color.black);
		}
		for(int i = 0; i < field.getDoors().size(); i++) {
			Door d = field.getDoors().get(i);
			g.setColor(colors.get(d.getColor()));
			g.drawLine(d.getX1(), d.getY1(), d.getX2(), d.getY2());
			g.setColor(Color.black);
		}
		
		g.setColor(Color.blue);
		Fish f = field.getFish();
		g.fillOval(f.getX(), f.getY(), 45, 45);
		g.drawLine(f.getX(),f.getY()+21, f.getX() -10, f.getY());
		g.drawLine(f.getX(),f.getY()+21, f.getX() -10, f.getY()+45);
		g.drawLine(f.getX()-10, f.getY(), f.getX()-10, f.getY()+45);
		
		g.setColor(new Color(46, 209, 250));
		g.drawOval(f.getX(), f.getY(), 10, 10);
		g.setColor(new Color(158, 227, 245, 90));
		g.fillOval(f.getX(), f.getY(), 10, 10);
		g.setColor(Color.black);
		g.fillOval(f.getX() + 30, f.getY() + 15, 9, 9);
		g.setColor(Color.white);
		g.fillOval(f.getX() + 32, f.getY() + 16, 3, 3);
		
	}
}
