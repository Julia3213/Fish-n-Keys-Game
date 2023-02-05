package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import field.Barrier;
import field.Door;
import field.Field;
import field.Key;
import field.Lastdoor;
import io.LevelReader;

public class CreatedLevels extends JFrame{
	private ArrayList<Field> levels;
	private File file;
	int height;
	int width;
	LevelReader reader;
	public CreatedLevels() {
		super("My levels");
		Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
		height = (int)sSize.getHeight();
		width = (int)sSize.getWidth();
		levels = new ArrayList<>();
		reader = new LevelReader("mylvls.txt", this);
		reader.read();
		addButtons();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(sSize);
		setVisible(true);
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void addLevel(Field f) {
		levels.add(f);
	}
	public void addButtons() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		for(int i = 0; i < levels.size(); i++) {
			JButton lev = new JButton("������� "+(i+1));
			lev.setBorder(new LineBorder(Color.BLUE, 2));
			lev.setBackground(new Color(153,204,255));
			lev.setFocusPainted(false);
			lev.setPreferredSize(new Dimension(200,100));
			int ii = i;
			lev.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainFrame m = new MainFrame(("My level"+(ii+1)), (-1), levels.get(ii));
				}
			});
			panel.add(lev);
		}
		Container c = getContentPane();
		c.add(panel);
	}
}
