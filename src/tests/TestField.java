package tests;

import field.*;
import junit.framework.*;

public class TestField extends TestCase{
	Field field;
	Field field2;
	
	protected void setUp() {
		field = new Field(100, 100);
	}
	
	public void testMoveFishToBarrier() {
		Barrier b = new Barrier(10, 10, 30, 30);
		field.setBarrier(b);
		field.moveFish(29, 30);
		for(int i = 30; i < 40; i++) {
			for(int j = 30; j < 40; j++) {
				field.moveFish(i, j);
			}
		}
		assertTrue(field.getFish().getX() == 29);
	}
	
	public void testMoveFishToDoor() {
		Door d = new Door(5, 5, 5, 10, false);
		Key k = new Key(20, 20);
		int x = field.getFish().getX();
		int y = field.getFish().getY();
		field.setLock(d, k);
		for(int i = 5; i <= 10; i++) {
			field.moveFish(5, i);
		}
		assertTrue(field.getFish().getX() == x && field.getFish().getY() == y);
		field.moveFish(20, 20);
		field.moveFish(5, 5);
		assertTrue(field.getFish().getX() == 5 && field.getFish().getY() == 5);
	}
	
	public void testMoveFishToBorder() {
		int x = field.getFish().getX();
		int y = field.getFish().getY();
		for(int i = 0; i < field.getBarriers().size(); i++) {
			Barrier b = field.getBarriers().get(i);
			for(int k = b.getX(); k < (b.getX()+b.getLength()); k++) {
				for(int j = b.getY(); j < (b.getY()+b.getWidth()); j++) {
					field.moveFish(k, j);
				}
			}
		}
		assertTrue(field.getFish().getX() == x && field.getFish().getY() == y);
	}
	
	public void testIsWin() {
		Key k = new Key(20, 20);
		field.setLastLock(k);
		field.moveFish(field.getLastDoor().getX1(), field.getLastDoor().getY1());
		assertFalse(field.isWin());
		field.moveFish(20, 20);
		field.moveFish(field.getLastDoor().getX1(), field.getLastDoor().getY1());
		assertTrue(field.isWin());
	}
	
	public void testMoveFishOutOfField() {
		int x = field.getFish().getX();
		int y = field.getFish().getY();
		field.moveFish(field.getLength(), field.getHeight());
		assertTrue(x == field.getFish().getX() && y == field.getFish().getY());
		field.moveFish(0, 0);
		assertTrue(x == field.getFish().getX() && y == field.getFish().getY());
		field.moveFish(0, field.getHeight());
		assertTrue(x == field.getFish().getX() && y == field.getFish().getY());
		field.moveFish(-3, -1);
		assertTrue(x == field.getFish().getX() && y == field.getFish().getY()); 
	}
}
