package field;

import java.util.ArrayList;

public class Border {
	Barrier up;
	Barrier down;
	Barrier right;
	Barrier left;
	private int length;
	private int height;
	public Border(int l, int h) {
		length = l;
		height = h;
		up = new Barrier(l - 1, (int)(h*0.027), 0, 0);
		down = new Barrier(l - 1, (int)(h*0.1), 0, height - (int)(h*0.11) - 1);
		left = new Barrier((int)(h*0.027) + 1, h - (int)(h*0.11) - (int)(h*0.027) - 1, 0, (int)(h*0.027));
		right = new Barrier((int)(h*0.027), h - (int)(height*0.14)- 1 - (int)(h*0.092), length - (int)(h*0.028) - 1, (int)(h*0.028) + (int)(h*0.092) + 1);//door 80
	}

	public ArrayList<Barrier> returnBarriers(){
		ArrayList<Barrier> barriers = new ArrayList<>();
		if(length!=0 && height!=0) {
			barriers.add(up);
			barriers.add(down);
			barriers.add(left);
			barriers.add(right);
		}
		return barriers;
	}
}
