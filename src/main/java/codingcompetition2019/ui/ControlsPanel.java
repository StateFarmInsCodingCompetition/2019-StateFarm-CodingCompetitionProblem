package codingcompetition2019.ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlsPanel extends JPanel implements ItemListener {
	private static final long serialVersionUID = 4645758996733040911L;
	
	private ChartView chart;
	private ControlFilters filters;
	
	private JComboBox<String> chartTypeSelector;

	public ControlsPanel(ChartView chart) {
		this.chart = chart;
		this.filters = new ControlFilters();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createTitledBorder("Options"));
		JLabel typeLabel = new JLabel("Chart Type");
		typeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		this.add(typeLabel);
		chartTypeSelector = new JComboBox<String>();
		chartTypeSelector.addItem("Pie Chart");
		chartTypeSelector.addItem("Bar Graph");
		chartTypeSelector.addItem("Line Graph");
		chartTypeSelector.addItemListener(this);
		this.add(chartTypeSelector);
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			this.filters.setChartType(chartTypeSelector.getSelectedIndex());
			this.chart.update(this.filters);
		}
	}
}
