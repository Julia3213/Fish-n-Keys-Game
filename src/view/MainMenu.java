package view;

import javax.swing.*;

import model.RecordTable;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainMenu extends JFrame{
	JMenu menu;
	JFrame frame = this;
	MainFrame game;
	public MainMenu(String title) {
		super(title);
		Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)sSize.getHeight();
		int width = (int)sSize.getWidth();
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.cyan);
		panel.setLayout(null);
		
		JButton play = new JButton("Играть");
		play.setBounds(width/2 - 150, height/2 - 200, 350, 50);
		play.setFont(new Font("Arial Black", Font.BOLD, 35));
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game = new MainFrame("Game", 1, null);
			}
		});
		JButton recordTable = new JButton("Таблица рекордов");
		recordTable.setBounds(width/2-150, height/2 - 100, 350, 50);
		recordTable.setFont(new Font("Arial Black", Font.BOLD, 25));
		recordTable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				RecordTableView table = new RecordTableView(frame, new RecordTable());
			}
		});
		JButton levelEditor = new JButton("Редактор уровней");
		levelEditor.setBounds(width/2-150,height/2,350,50);
		levelEditor.setFont(new Font("Arial Black", Font.BOLD, 25));
		levelEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditorView ev = new EditorView();
			}
		});
		
		JButton myLevels = new JButton("Мои уровни");
		myLevels.setBounds(width/2-150, height/2 +100, 350, 50);
		myLevels.setFont(new Font("Arial Black", Font.BOLD, 25));
		myLevels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreatedLevels cl = new CreatedLevels();
				
			}
		});
		
		panel.add(play);
		panel.add(levelEditor);
		panel.add(recordTable);
		panel.add(myLevels);
		
		Container c = getContentPane();
		c.add(panel);
		setSize (sSize);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		MainMenu mm = new MainMenu("menu");
	}
}
