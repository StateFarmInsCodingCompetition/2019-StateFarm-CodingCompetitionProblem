package codingcompetition2019.ui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIMain {
	
	private static JFrame frame;
	private static final Dimension WINDOW_SIZE = new Dimension(1200, 800);
	
	public static String fileName;

	
	public static void main(String[] args) {
		frame = new JFrame("Disaster Visualizer GUI - Robert Pooley and Jeremy Schonfeld - Data from Our World Data");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		Point topLeft = new Point(size.width / 2, size.height / 2);
		topLeft.translate(WINDOW_SIZE.width / -2, WINDOW_SIZE.height / -2);
		frame.setBounds(new Rectangle(topLeft.x, topLeft.y, WINDOW_SIZE.width, WINDOW_SIZE.height));
		switchToPane(new FileSelectPanel());
		frame.setVisible(true);
	}
	
	public static void switchToPane(JPanel panel) {
		frame.setContentPane(panel);
		frame.getContentPane().revalidate();
		frame.getContentPane().repaint();
	}
	
}
