package field;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import io.FieldWriter;

public class Field {
	private int length;
	private int height;
	
	public int[][] coordinates;
	private static final int BARRIER = 1;
	private static final int EMPTY = 0;
	private static final int DOOR = 9;
	private static final int KEY = 8;
	
	private ArrayList<Barrier> barriers;
	private ArrayList<Key> keys;
	private ArrayList<Door> doors;
	private HashMap<Key, Door> locks;
	
	private Lastdoor lastDoor;
	private Fish fish;
	private Border border;
	
	
	public Field(int l, int h) {
		length = l;
		height = h;
		coordinates = new int[l][h];
		for(int i = 0; i < l; i++) {
			for(int j = 0; j < h; j++) {
				coordinates[i][j] = 0;
			}
		}
		barriers = new ArrayList<>();
		keys = new ArrayList<>();
		doors = new ArrayList<>();
		locks = new HashMap<>();
		lastDoor = new Lastdoor(l,h);
		border = new Border(l,h);
		//writer = new FieldWriter()
		setBorder();
		setFish();
	}
	
	public ArrayList<Barrier> getBarriers(){
		return barriers;
	}
	public ArrayList<Door> getDoors(){
		return doors;
	}
	public ArrayList<Key> getKeys(){
		return keys;
	}
	public HashMap<Key, Door> getLocks(){
		return locks;
	}
	public Lastdoor getLastDoor() {
		return lastDoor;
	}
	public Fish getFish() {
		if(fish == null)
			throw new RuntimeException("no fish");
		return fish;
	}
	public int getHeight() {
		if(height <= 0)
			return -1;
		return height;
	}
	public int getLength() {
		if(length <= 0)
			return -1;
		return length;
	}
	private void setBorder() {
		ArrayList<Barrier> bs = border.returnBarriers();
		for(int i = 0; i < bs.size(); i++) {
			setBarrier(bs.get(i));
		}
	}
	public void setBarrier(Barrier b) {
		if(isSetBarrier(b)) {
			for(int i = b.getX(); i < (b.getLength() + b.getX()); i++) {
				for(int j = b.getY(); j < (b.getWidth() + b.getY()); j++) {
					coordinates[i][j] = 1;
				}
			}
			barriers.add(b);
		}
		else {
			System.out.println(b);
			throw new RuntimeException("cannot set barrier");
		}
	}
	
	private boolean isSetBarrier(Barrier b) {
		if(b.getX() > length || b.getY() > height)
			return false;
		if((b.getX() + b.getLength()) > length || (b.getY() + b.getWidth()) > height)
			return false;
		if(b.getX() < 0 || b.getY() < 0)
			return false;
		for(int i = b.getX(); i < b.getX()+b.getLength(); i++) {
			for(int j = b.getY(); j < b.getY()+b.getWidth(); j++) {
				if(coordinates[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void setKey(Key k) {
		if(isSetKey(k)) {
			coordinates[k.getX()][k.getY()] = 8;
			keys.add(k);
		}
		else
			throw new RuntimeException("cannot set key");
			//System.out.println("cannot set key"+k);
	}
	
	private boolean isSetKey(Key k) {
		if(k.getX() >= length || k.getY() >= height)
			return false;
		if(k.getX() < 0 || k.getY() < 0)
			return false;
		if(coordinates[k.getX()][k.getY()]!=0)
			return false;
		return true;
	}
	
	public void removeBarrier(Barrier b) {
		if(!barriers.contains(b))
			throw new RuntimeException("no such barrier");
		for(int i = b.getX(); i < (b.getLength() + b.getX()); i++) {
			for(int j = b.getY(); j < (b.getWidth() + b.getY()); j++) {
				coordinates[i][j] = 0;
			}
		}
		barriers.remove(b);
	}
	
	
	private void setFish() {
		if(length<=((int)(length*0.03) +2) || height <=(height - (int)(height*0.18)))
			throw new RuntimeException("cannot set fish");
		fish = new Fish((int)(length*0.03) +2, height - (int)(height*0.18));
	}
	
	private Key findKey(int x, int y) {
		Key k = new Key(x,y);
		Iterator<Entry<Key,Door>> i = locks.entrySet().iterator();
		while(i.hasNext()) {
			Map.Entry<Key, Door> set = i.next();
			Key kt = set.getKey();
			if(kt.equals(k))
				return kt;
		}
		return null;
	}
	
	private void getKey(int x, int y) {
		Key k = findKey(x,y);
		locks.get(k).open();
		keys.remove(k);
		coordinates[x][y] = 0;
	}
	
	private Door whichDoor(int x, int y) {
		for(int i = 0; i < doors.size(); i++) {
			if(doors.get(i).isThisDoor(x, y))
				return doors.get(i);
		}
		return null;
	}
	
	private void removeKey(Key k) {
		keys.remove(k);
		coordinates[k.getX()][k.getY()] = 0;
		Door d = locks.get(k);
		removeDoor(d);
		locks.remove(k);
	}
	
	private void openDoor(int x, int y) {
		Door d = whichDoor(x,y);
		if(d!=null) {
			if(d.getIsOpen()) {
				if(d instanceof Lastdoor) {
					lastDoor.setWin();
				}
				fish.setCoordinates(x, y);
				removeDoor(d);
			}
			else {
				return;
			}
		}
		else
			System.out.println("null");
	}
	
	public void moveFish(int x, int y) {
		int isMove = isMoveFish(x,y);
		switch(isMove) {
		case(0): 
			fish.setCoordinates(x, y);
			break;
		case(-1):
			break;
		case(1):
			getKey(x,y);
			fish.setCoordinates(x, y);
			break;
		case(2):
			openDoor(x,y);
			break;
		}
	}
	
	private int isMoveFish(int x, int y) {
		if(x<0||y<0)
			return -1;
		if(x>=length || y >= height)
			return -1;
		//if(x + 45>=length || y +45>= height)
			//return -1;
		if(coordinates[x][y]==BARRIER)
			return -1;
		if(x+45<length && y+45 < height) {
			if(coordinates[x+45][y+45]==BARRIER) {
				return -1;
			}
		}
		if(coordinates[x][y]==EMPTY)
			return 0;
		if(coordinates[x][y]==KEY) 
			return 1;
		/*if(coordinates[x+45][y+45]==DOOR) {
			right = true;
			return 2;
		}*/
		if(coordinates[x][y]==DOOR) {
			return 2;
		}
		return 0;
	}
	
	private boolean isSetDoor(Door d) {
			if(d.getOrientation()) {
				for(int i = d.getX1(); i < d.getX2()+1; i++) {
					for(int j = d.getY1(); j <= d.getY2(); j++) {
						if(coordinates[i][j] !=0) return false ;
					}
				}
			}
			else {
				for(int i = d.getX1(); i <= d.getX2(); i++) {
					for(int j = d.getY1(); j < d.getY2() + 1; j++) {
						if(coordinates[i][j] != 0) {System.out.println(i+" "+j); return false;}
					}
				}
			}
		return true;
	}
	
	private void setDoor(Door d) {
		if(isSetDoor(d)) {
			if(d.getOrientation()) {
				for(int i = d.getX1(); i < d.getX2()+1; i++) {
					for(int j = d.getY1(); j <= d.getY2(); j++) {
						coordinates[i][j] = 9;
					}
				}
			}
			else {
				for(int i = d.getX1(); i <= d.getX2(); i++) {
					for(int j = d.getY1(); j < d.getY2() + 1; j++) {
						coordinates[i][j] = 9;
					}
				}
			}
			doors.add(d);
		}
		else {throw new RuntimeException("cannot set door");}
	}
	
	public void setLastLock(Key k) {
		if(lastDoor == null)
			throw new RuntimeException("cannot set last lock");
		k.setColor(6);
		setLock(lastDoor, k);
	}
	
	private void removeDoor(Door d) {
		if(doors.contains(d)) {
			doors.remove(d);
			if(d.getOrientation()) {
				for(int i = d.getX1(); i < d.getX2()+1; i++) {
					for(int j = d.getY1(); j <= d.getY2(); j++) {
						coordinates[i][j] = 0;
					}
				}
			}
			else {
				for(int i = d.getX1(); i <= d.getX2(); i++) {
					for(int j = d.getY1(); j < d.getY2() + 1; j++) {
						coordinates[i][j] = 0;
					}
				}
			}
		}
		else
			throw new RuntimeException("no such door");
	}
	
	public boolean isWin() {
		if(lastDoor == null)
			throw new RuntimeException("no last door");
		return lastDoor.getWin();
	}
	
	public void setLock(Door d, Key k) {
		setDoor(d);
		setKey(k);
		locks.put(k,d);
	}
	
	public void removeLock(Door d, Key k) {
		if(!locks.containsValue(d))
			throw new RuntimeException("no such lock");
		removeKey(k);
		locks.remove(d,k);
	}
}
