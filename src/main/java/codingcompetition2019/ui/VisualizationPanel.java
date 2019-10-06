package codingcompetition2019.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class VisualizationPanel extends JPanel {
	private static final long serialVersionUID = 521685048389218424L;
	
	private ControlsPanel controls;
	private ChartView chartView;
	
	public VisualizationPanel() {
		super();
		this.setLayout(new BorderLayout());
		chartView = new ChartView();
		this.add(chartView, BorderLayout.CENTER);
		controls = new ControlsPanel(chartView);
		JPanel east = new JPanel();
		east.add(controls);
		this.add(east, BorderLayout.EAST);
		
		JPanel north = new JPanel();
		north.setLayout(new BorderLayout());
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					@Override
					public void run() {
						GUIMain.switchToPane(new FileSelectPanel());
					}
				}.start();
			}
		});
		north.add(back, BorderLayout.WEST);
		this.add(north, BorderLayout.NORTH);
	}

}
