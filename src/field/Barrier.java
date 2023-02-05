package field;

public class Barrier {
	private int length;
	private int width;
	private int x;
	private int y;
	public Barrier(int l, int w, int x, int y) {
		length = l;
		width = w;
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getLength() {
		return length;
	}
	public int getWidth() {
		return width;
	}
	public String toString() {
		String s = getLength() + " " + getWidth()+" "+getX()+" "+getY();
		return s;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Barrier) {
			Barrier b = (Barrier)o;
			if(length!=b.getLength())
				return false;
			if(width !=b.getWidth())
				return false;
			if(x!= b.getX())
				return false;
			if(y!=b.getY())
				return false;
			return true;
		}
		return false;
	}
}
