package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import model.RecordTable;

public class RecordTableView extends JDialog{
	RecordTable recordTable;
	int height;
	int width;
	
	public RecordTableView(Frame f, RecordTable rt) {
		super(f,"Record table");
		recordTable = rt;
		Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
		height = (int)sSize.getHeight();
		width = (int)sSize.getWidth();
		
		Container c = getContentPane();
		c.add(createTable());
		
		setSize (200,200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public JPanel createTable() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(recordTable.getMap().size() + 1,2));
		Object[][] data = recordTable.toObjectArray();
		JLabel[][] labelData = new JLabel[data.length][2];
		JLabel level = new JLabel("Level");
		level.setBorder(new LineBorder(Color.red));
		panel.add(level);
		JLabel time = new JLabel("Time");
		time.setBorder(new LineBorder(Color.red));
		panel.add(time);
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < 2; j++) {
				labelData[i][j] = new JLabel(data[i][j].toString());
				labelData[i][j].setBorder(new LineBorder(Color.blue));
				if((int)data[i][j]==4000) {
					JLabel nothing = new JLabel("-");
					nothing.setBorder(new LineBorder(Color.blue));
					panel.add(nothing);
				}
				else
					panel.add(labelData[i][j]);
			}
		}
		return panel;
	}
}
