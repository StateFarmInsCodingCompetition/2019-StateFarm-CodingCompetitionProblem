package codingcompetition2019.ui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

public class FileSelectPanel extends JPanel implements ItemListener {
	private static final long serialVersionUID = -1499590361669350298L;
	
	private File otherFile;
	
	private String naturalDisasterByTypeFile = "src/main/resources/natural-disasters-by-type.csv";
	private String significantEarthquakeFileNameName = "src/main/resources/significant-earthquakes.csv";
	private String significantVolcanicEruptionsFileName = "src/main/resources/significant-volcanic-eruptions.csv";
	
	private JComboBox<String> options;

	public FileSelectPanel() { 
		super();
		JPanel inner = new JPanel();
		inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
		this.setLayout(new GridBagLayout());
		this.add(inner);
		JLabel title = new JLabel("Disaster Visualization");
		title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 32));
		title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		inner.add(title);
		inner.add(Box.createVerticalStrut(75));
		JLabel info = new JLabel("Please select a file to open below, then press \"Open\" to continue.");
		info.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		inner.add(info);
		inner.add(Box.createVerticalStrut(20));
		JPanel inner2 = new JPanel();
		inner2.setLayout(new FlowLayout());
		options = new JComboBox<String>();
		options.addItem("natural-disasters-by-type.csv");
		options.addItem("significant-earthquakes.csv");
		options.addItem("significant-volcanic-eruptions.csv");
		options.addItem("Other...");
		options.addItemListener(this);
		options.setMaximumSize(options.getPreferredSize());
		inner2.add(options);
		JButton submit = new JButton("Open");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = options.getSelectedIndex();
				if (option == 3 && otherFile == null) {
					JOptionPane.showMessageDialog(null, "Please select a file", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				switch (option) {
				case 0:
					GUIMain.fileName = naturalDisasterByTypeFile;
					break;
				case 1:
					GUIMain.fileName = significantEarthquakeFileNameName;
					break;
				case 2:
					GUIMain.fileName = significantVolcanicEruptionsFileName;
					break;
				case 3:
					GUIMain.fileName = otherFile.getAbsolutePath();
					break;
				}
				new Thread() {
					@Override
					public void run() {
						GUIMain.switchToPane(new VisualizationPanel());
					}
				}.start();
			}
		});
		submit.setAlignmentX(JButton.CENTER_ALIGNMENT);
		inner2.add(submit);
		inner.add(inner2);
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED && e.getItem().equals("Other...")) {
			this.chooseFile();
		}
	}
	
	private void chooseFile() {
		final JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileFilter() {

			@Override
			public boolean accept(File f) {
				return f.getName().endsWith(".csv");
			}

			@Override
			public String getDescription() {
				return "Comma Separated Value (CSV) Files";
			}
		});
		chooser.setMultiSelectionEnabled(false);
		int res = chooser.showOpenDialog(this);
		if (res == JFileChooser.APPROVE_OPTION) {
			otherFile = chooser.getSelectedFile();
			options.setSelectedItem(otherFile.getName());
		} else {
			options.setSelectedIndex(0);
		}
	}
}
