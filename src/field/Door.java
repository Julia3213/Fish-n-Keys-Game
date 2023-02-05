package field;

public class Door {
	protected int x1;
	protected int y1;
	protected int x2;
	protected int y2;
	protected int color;
	protected boolean isOpen;
	protected boolean orientation;//false-ver true-hor
	
	public Door(int x1, int y1, int x2, int y2, boolean o) {
		if(!o) {
			if(y1<y2) {
				this.y1 = y1;
				this.y2 = y2;
				this.x1 = x1;
				this.x2 = x2;
			}
			else {
				this.y1 = y2;
				this.y2 = y1;
				this.x1 = x2;
				this.x2 = x1;
			}
		}
		else {
			if(x1<x2) {
				this.y1 = y1;
				this.y2 = y2;
				this.x1 = x1;
				this.x2 = x2;
			}
			else {
				this.y1 = y2;
				this.y2 = y1;
				this.x1 = x2;
				this.x2 = x1;
			}
		}
		orientation = o;
		isOpen = false;
	}
	public int getX1() {
		return x1;
	}
	
	public int getX2() {
		return x2;
	}
	
	public int getY1() {
		return y1;
	}
	
	public int getY2() {
		return y2;
	}
	
	public boolean getOrientation() {
		return orientation;
	}
	
	public boolean isThisDoor(int x, int y) {
		if(orientation) {
			for(int i = x1; i < x2 + 1; i++) {
				for(int j = y1; j <= y2; j++) {
					if(i==x && j==y)
						return true;
				}
			}
			return false;
		}
		else {
			for(int i = x1; i <= x2; i++) {
				for(int j = y1; j < y2 + 1; j++) {
					if(i==x && j==y)
						return true;
				}
			}
			return false;
		}
	}
	
	public void open() {
		isOpen = true;
	}
	
	public boolean getIsOpen() {
		return isOpen;
	}
	
	public void setColor(int i) {
		color = i;
	}
	
	public int getColor() {
		return color;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Door) {
			Door d = (Door) o;
			return ((x1 == d.getX1()) && (y1 == d.getY1()) && (x2 == d.getX2()) && (y2 == d.getY2()) && (orientation == d.getOrientation()));
		}
		return false;
	}
	@Override
	public String toString() {
		return (x1+" "+y1+" "+x2+" "+y2);
	}
}
