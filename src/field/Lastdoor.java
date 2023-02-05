package field;

public class Lastdoor extends Door{
	private boolean win;
	public Lastdoor(int length, int height) {
		super(length - (int)(length*0.045), (int)(height*0.027), length - (int)(length*0.045), (int)(height*0.12), false);//door 65
		color = 6;//black
		win = false;
	}
	public void setWin() {
		win = true;
	}
	public boolean getWin() {
		return win;
	}
}
