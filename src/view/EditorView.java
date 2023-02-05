package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;

import field.*;
import io.FieldWriter;

public class EditorView extends JFrame{
	int height;
	int width;
	Barrier b;
	Door d;
	Key k;
	Object obj;
	int color = 0;
	FieldView fv;
	Field field;
	JFrame frame = this;
	ArrayList<Integer> notUsedColors;
	public EditorView() {
		super("Level editor");
		
		Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
		height = (int)sSize.getHeight();
		width = (int)sSize.getWidth();
		
		field = new Field(width, height);
		notUsedColors = new ArrayList<>();
		for(int i = 0; i < 6; i++) {
			notUsedColors.add(i);
		}
		JButton barrier = new JButton("Barrier");
		barrier.setBounds(width/2 + 100, 3, 100, 30);
		barrier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b = new Barrier(300,100,300,300);
				obj = b;
				field.setBarrier(b);
				fv.setNewField(field);
				fv.repaint();
			}
		});
		
		JButton vertLock = new JButton("Vertical lock");
		vertLock.setBounds(width/2 - 100, 3, 200, 30);
		vertLock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton key = new JButton("key");
				key.setBounds(10,height/2, 70,30);
				key.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						obj = k;
					}
				});
				fv.add(key);
				JButton door = new JButton("door");
				door.setBounds(10,height/2 - 30, 70,30);
				door.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						obj = d;
					}
				});
				fv.add(door);
				JButton ok = new JButton("ok");
				ok.setBounds(10,height/2 - 60, 70,30);
				ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						obj = null;
						fv.remove(key);
						fv.remove(door);
						fv.remove(ok);
						fv.repaint();
					}
				});
				fv.add(ok);
				fv.repaint();
				d = new Door(40,40,40,140,false);
				if(notUsedColors.isEmpty()) {
					JOptionPane.showMessageDialog(new JDialog(),"only 6 locks");
					return;
				}
				if(!notUsedColors.contains(color)) {
					color = notUsedColors.get(0);
					notUsedColors.remove(notUsedColors.indexOf(color));
				}
				else {
					notUsedColors.remove(color);
				}
				d.setColor(color);
				k = new Key(45,45);
				k.setColor(color);
				field.setLock(d,k);
				fv.setNewField(field);
				fv.repaint();
			}
		});
		
		JButton horLock = new JButton("Horizontal lock");
		horLock.setBounds(width/2-300, 3, 200, 30);
		horLock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton key = new JButton("key");
				key.setBounds(10,height/2, 70,30);
				key.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						obj = k;
					}
				});
				fv.add(key);
				JButton door = new JButton("door");
				door.setBounds(10,height/2 - 30, 70,30);
				door.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						obj = d;
					}
				});
				fv.add(door);
				JButton ok = new JButton("ok");
				ok.setBounds(10,height/2 - 60, 70,30);
				ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						obj = null;
						fv.remove(key);
						fv.remove(door);
						fv.remove(ok);
						fv.repaint();
					}
				});
				fv.add(ok);
				fv.repaint();
				d = new Door(30,30,130,30,true);
				if(!notUsedColors.contains(color)) {
					color = notUsedColors.get(0);
					notUsedColors.remove(color);
				}	
				else {
					notUsedColors.remove(color);
				}
				d.setColor(color);
				k = new Key(30,35);
				k.setColor(color);
				field.setLock(d,k);
				fv.setNewField(field);
				fv.repaint();
			}
		});
		
		JButton endLock = new JButton("Last lock");
		endLock.setBounds(width/2-500,3, 200, 30);
		endLock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fv.remove(vertLock);
				fv.remove(horLock);
				fv.remove(endLock);
				d = new Lastdoor(width, height);
				color = 6;
				k = new Key(100,100);
				k.setColor(6);
				obj = k;
				field.setLock(d,k);
				fv.setNewField(field);
				fv.repaint();
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if(obj instanceof Barrier) {
					if(field.getBarriers().contains(b)) {
						field.removeBarrier(b);
						fv.setNewField(field);
					}
					Barrier bb = new Barrier(b.getLength(), b.getWidth(), e.getX(), e.getY());
					if(e.getX()>=width||e.getY()>=height)
						return;
					b = bb;
					field.setBarrier(bb);
					fv.setNewField(field);
					fv.getGraphics().setColor(Color.black);
				}
				else if(obj instanceof Key) {
					Door dd = new Door(d.getX1(), d.getY1(), d.getX2(), d.getY2(), d.getOrientation());
					dd.setColor(d.getColor());
					if(field.getDoors().contains(d) && field.getKeys().contains(k)) {
						field.removeLock(d, k);
						fv.setNewField(field);
					}
					Key kk = new Key(e.getX(), e.getY());
					kk.setColor(color);
					d = dd;
					k = kk;
					field.setLock(d,kk);
					fv.setNewField(field);
				}
				else if(obj instanceof Door) {
					boolean or = d.getOrientation();
					Key kk = new Key(k.getX(), k.getY());
					kk.setColor(color);
					if(field.getDoors().contains(d) && field.getKeys().contains(k)) {
						field.removeLock(d, k);
						fv.setNewField(field);
					}
					Door dd;
					if(or) {
						dd = new Door(e.getX() - 50, e.getY(), e.getX() + 50, e.getY(), true);
					}
					else {
						dd = new Door(e.getX(), e.getY() - 50, e.getX(), e.getY()+50,false);
					}
					dd.setColor(color);
					d = dd;
					k = kk;
					field.setLock(dd,k);
					fv.setNewField(field);
				}
			}
		});
		
		
		JButton oK = new JButton("OK");
		oK.setBounds(width/2+200, 3, 100, 30);
		oK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fv.setNewField(field);
				FieldWriter writer = new FieldWriter("mylvls.txt", field);
				writer.write();
				fv.remove(barrier);
				fv.remove(vertLock);
				fv.remove(horLock);
				fv.remove(endLock);
				fv.remove(oK);
			}
		});
		fv = new FieldView(field);
		fv.add(barrier);
		fv.add(vertLock);
		fv.add(horLock);
		fv.add(endLock);
		fv.add(oK);
		fv.setLayout(null);
		
		Container container = getContentPane();
		container.add(fv);
		setSize(sSize);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
