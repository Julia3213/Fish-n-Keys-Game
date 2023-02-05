package field;

public class Key {
	private int x;
	private int y;
	private int color;//0-red 1-green, 2-yellow, 3-orange,4-blue,5-violet
	public Key(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setColor(int i) {
		color = i;
	}
	
	public int getColor() {
		return color;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Key) {
			Key k = (Key)o;
			return((x == k.getX()) && (y == k.getY()));
		}
		return false;
	}
	
	public String toString() {
		return x+" "+y;
	}
}
