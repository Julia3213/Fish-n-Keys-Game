package model;

import field.Barrier;
import field.Door;
import field.Field;
import field.Key;

public class Level {
	private Field field;
	private int width;
	private int height;
	
	public Level(int i, int w, int h) {
		width = w;
		height = h;
		field = new Field(width, height);
		if(i == 1) {
			createLevel1();
		}
		else if(i == 2) {
			createLevel2();
		}
		else if(i == 3) {
			createLevel3();
		}
	}
	
	public void createLevel1() {
		field.setBarrier(new Barrier(300, 100, 20, height*6/10+35));//
		field.setBarrier(new Barrier(200, 100, width/3 + 65, height*6/10+35));
		field.setBarrier(new Barrier(400, 100, width - 421, height*6/10+35));
		field.setBarrier(new Barrier(400, 100, width - 421, height*3/10+55));
		field.setBarrier(new Barrier(500, 100, width - 921, height*3/10+55));
		field.setBarrier(new Barrier(535, 100, 20, height/10 + 15));//
		field.setBarrier(new Barrier(550, 100, width - 571, height/10 + 15));
		
		Door d = new Door(width - 401, height*6/10+34, width - 401, height*3/10 + 55 +100,false);
		d.setColor(0);
		Key k = new Key(width - 100, height - 127);
		k.setColor(0);
		field.setLock(d, k);
		
		Door d2 = new Door(555, height/10+15,width - 572, height/10+15,true);
		d2.setColor(1);
		Key k2 = new Key(width - 200, height*3/10+200);
		k2.setColor(1);
		field.setLock(d2, k2);
		
		Key lk = new Key(480,40);
		lk.setColor(6);
		field.setLastLock(lk);
	}
	
	public void createLevel2() {
		field.setBarrier(new Barrier(300, 70, 20, height*6/10+65));
		field.setBarrier(new Barrier(300, 70, 20, height*6/10-70));
		field.setBarrier(new Barrier(70, 250, 20, height*6/10-320));
		field.setBarrier(new Barrier(120, 280, 200, 19));
		field.setBarrier(new Barrier(110, 548, 400, 19));//
		Door d1 = new Door(320, height*6/10+65,320, height*6/10, false);
		Key k1 = new Key(35, 51);
		field.setLock(d1, k1);
		
		Door d2 = new Door(400, 567, 400, height - 81, false);
		d2.setColor(1);
		Key k2 = new Key(40, height*6/10 + 19);
		k2.setColor(1);
		field.setLock(d2, k2);
		
		field.setBarrier(new Barrier(180, 70, 510, height*6/10+65));
		field.setBarrier(new Barrier(180, 70, 510, height*5/10));
		field.setBarrier(new Barrier(180, 275, 510, 19));
		
		Door d3 = new Door(690,height*6/10+65+70, 800, height*6/10+65+70, true);
		d3.setColor(2);
		Key k3 = new Key(1200,height*5/10+85);
		k3.setColor(2);
		field.setLock(d3, k3);
		
		field.setBarrier(new Barrier(210, 70, 800, height*6/10+65));
		field.setBarrier(new Barrier(459, 70, 800, 87));
		field.setBarrier(new Barrier(100,67,800,430));
		field.setBarrier(new Barrier(210, 70, 800, height*5/10));
		field.setBarrier(new Barrier(459, 70, 800, 222));
		field.setBarrier(new Barrier(100,68,800,292));
		
		field.setBarrier(new Barrier(159,70,1100,height*5/10));
		field.setBarrier(new Barrier(159,70,1100,height*6/10+65));
		
		Door d4 = new Door(1010,height*5/10+70,1100, height*5/10+70, true);
		d4.setColor(3);
		Key k4 = new Key(550, height*5/10+85);
		k4.setColor(3);
		field.setLock(d4, k4);
		
		Door d5 = new Door(800, 19, 800, 86, false);
		d5.setColor(4);
		Key k5 = new Key(550, height*5/10 - 48);
		k5.setColor(4);
		field.setLock(d5, k5);
		
		Key lk = new Key(1190, height*5/10 - 48);
		field.setLastLock(lk);
	}
	
	public void createLevel3() {
		field.setBarrier(new Barrier(200, 70, 20, height*6/10+65));
		field.setBarrier(new Barrier(200, 70, 120, height*6/10-70));
		field.setBarrier(new Barrier(200, 70, 20, height*4/10 - 60));
		field.setBarrier(new Barrier(200, 70, 120, height*3/10 - 120));
		
		field.setBarrier(new Barrier(100,548,320,19));
		
		field.setBarrier(new Barrier(200, 70, 420, height*6/10+65));
		field.setBarrier(new Barrier(200, 70, 520, height*6/10-70));
		field.setBarrier(new Barrier(200, 70, 420, height*4/10 - 60));
		field.setBarrier(new Barrier(200, 70, 520, height*3/10-120));
		
		field.setBarrier(new Barrier(100,544,720,96));
		
		field.setBarrier(new Barrier(150, 70, 820, height*6/10+65));
		field.setBarrier(new Barrier(150, 70, 890, height*6/10-70));
		field.setBarrier(new Barrier(150, 70, 820, height*4/10 - 60));
		field.setBarrier(new Barrier(150, 70, 890, height*3/10-120));
		
		field.setBarrier(new Barrier(100, 548, 890+150, 19));
		
		Door d = new Door(320, 19+548, 320, height - 81, false);
		Key k = new Key(260,43);
		field.setLock(d, k);
		
		Door d2 = new Door(720 + 100, 96, 820, 19, false);
		d2.setColor(1);
		Key k2 = new Key(674,584);
		k2.setColor(1);
		field.setLock(d2, k2);
		
		Door d3 = new Door(1040,568,1040, height - 81, false);
		d3.setColor(2);
		Key k3 = new Key(963, 41);
		k3.setColor(2);
		field.setLock(d3, k3);
		
		Key lk = new Key(840,586);
		field.setLastLock(lk);
		
		field.setBarrier(new Barrier(50,50, width - 71, 87));
	}
	
	public void createLevel(Field f) {
		field = f;
	}
	
	public Field getField() {
		return field;
	}
}
