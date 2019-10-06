package codingcompetition2019;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class Gui {
	private String naturalDisasterByTypeFile = "src/main/resources/natural-disasters-by-type.csv";
	
	private String significantEarthquakeFileName = "src/main/resources/significant-earthquakes.csv";
	
	private String significantVolcanicEruptionsFileName = "src/main/resources/significant-volcanic-eruptions.csv";
	
	private JFrame frmNaturalDisasterData;
	private JComboBox dataSource;
	private JComboBox parseMethod;
	private JButton btnGo;
	private JTextPane outputPane;
	private JTextField txtParam;
	private JLabel lblParam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmNaturalDisasterData.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
		addEventListeners();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNaturalDisasterData = new JFrame();
		frmNaturalDisasterData.setTitle("Natural Disaster Data Parser");
		frmNaturalDisasterData.setBounds(100, 100, 500, 290);
		frmNaturalDisasterData.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dataSource = new JComboBox();
		dataSource.setModel(new DefaultComboBoxModel(new String[] {"natural-disasters-by-type.csv", "significant-earthquakes.csv", "significant-volcanic-eruptions.csv"}));
		
		JLabel lblDataSource = new JLabel("Data Source");
		
		JLabel lblParseMethod = new JLabel("Parse Method");
		
		parseMethod = new JComboBox();
		parseMethod.setModel(new DefaultComboBoxModel(new String[] {"Find Most Impactful Year", "Find Most Impactful Year by Category", "Find Most Common Disaster Type in Given Year", "Get Total Disasters of a Certain Category/Type"}));
		
		btnGo = new JButton("Go!");
		
		outputPane = new JTextPane();
		
		lblParam = new JLabel("Parameter:");
		lblParam.setVisible(false);
		
		txtParam = new JTextField();
		txtParam.setVisible(false);
		txtParam.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frmNaturalDisasterData.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(outputPane, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
									.addComponent(btnGo))
								.addComponent(parseMethod, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(dataSource, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(196))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDataSource)
							.addContainerGap(415, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblParseMethod)
							.addContainerGap(408, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblParam)
							.addContainerGap(419, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtParam, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDataSource)
					.addGap(8)
					.addComponent(dataSource, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblParseMethod)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(parseMethod, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblParam)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtParam, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnGo)
						.addComponent(outputPane, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		frmNaturalDisasterData.getContentPane().setLayout(groupLayout);
	}
	
	private void addEventListeners() {
		
		parseMethod.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String method = (String)parseMethod.getSelectedItem();
				lblParam.setVisible(true);
				txtParam.setVisible(true);
				if(method.equals("Find Most Impactful Year")) {
					lblParam.setVisible(false);
					txtParam.setVisible(false);
				} else if(method.equals("Find Most Impactful Year by Category")) {
					lblParam.setText("Parameter: Category");
					txtParam.setToolTipText("Categories: Earthquake, Extreme temperature, Extreme weather, Flood, Landslide, Mass movement, Volcanic activity, Wildfire");
					lblParam.setToolTipText("Categories: Earthquake, Extreme temperature, Extreme weather, Flood, Landslide, Mass movement, Volcanic activity, Wildfire");
				} else if(method.equals("Find Most Common Disaster Type in Given Year")) {
					lblParam.setText("Parameter: Year");
					lblParam.setToolTipText("Enter a the year in the format YYYY");
				} else if(method.equals("Get Total Disasters of a Certain Category/Type")) {
					lblParam.setText("Parameter: Category");
					txtParam.setToolTipText("Categories: Earthquake, Extreme temperature, Extreme weather, Flood, Landslide, Mass movement, Volcanic activity, Wildfire");
					lblParam.setToolTipText("Categories: Earthquake, Extreme temperature, Extreme weather, Flood, Landslide, Mass movement, Volcanic activity, Wildfire");
				} 
				/*
				else if(method.equals("Get Years with Certain Range of Disaster Occurrances")){
					lblParam.setText("Parameters: min, max");
					txtParam.setText("min, max");
					txtParam.setToolTipText("Enter in the format: <min>, <max>");
					lblParam.setToolTipText("Enter in the format: <min>, <max>");
				}
				*/
			}
			
		});
		
		
		btnGo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String filePath;
				if(dataSource.getSelectedItem().equals("natural-disasters-by-type.csv")){
					filePath = naturalDisasterByTypeFile;
				} else if(dataSource.getSelectedItem().equals("significant-earthquakes.csv")) {
					filePath = significantEarthquakeFileName;
				} else {
					filePath = significantVolcanicEruptionsFileName;
				}
				//get the arraylist representation
				CodingCompCSVUtil util = new CodingCompCSVUtil();
				List<List<String>> data;
				try {
					data = util.readCSVFileWithoutHeaders(filePath);
				} catch (IOException e1) {
					data = null;
					e1.printStackTrace();
				}
				String method = (String)parseMethod.getSelectedItem();
				if(method.equals("Find Most Impactful Year")){
					outputPane.setText("Year: " + util.getMostImpactfulYear(data).getYear());
				} 
				else if(method.equals("Find Most Impactful Year by Category")) {
					DisasterDescription dd = util.getMostImpactfulYearByCategory(txtParam.getText(), data);
					String ans = String.format("Year: %s", dd.getYear());
					outputPane.setText(ans);
				} 
				else if(method.equals("Find Most Common Disaster Type in Given Year")) {
					DisasterDescription dd = util.getMostImpactfulDisasterByYear(txtParam.getText(), data);
					String ans = String.format("Category: %s \nYear: %s", dd.getCategory(), dd.getYear());
					outputPane.setText(ans);
				} 
				else if(method.equals("Get Total Disasters of a Certain Category/Type")) {
					DisasterDescription dd = util.getTotalReportedIncidentsByCategory(txtParam.getText(), data);
					String ans = String.format("Category: %s \nNumber of Incidents: %s", dd.getCategory(), dd.getReportedIncidentsNum());
					outputPane.setText(ans);
				} 
				
			}
			
		});
	}
}
