package codingcompetition2019.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.Histogram;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler.LegendPosition;

import codingcompetition2019.CodingCompCSVUtil;

public class ChartView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	private Map<String, Map<String, Integer>> yearCatIncidents;
	private int[] years;
	private List<String> categories;
	
	private JPanel currentXPanel;
	
	public ChartView() {
		mapYearCategoryIncidents();
		update(new ControlFilters()
				.setChartType(ControlFilters.CHART_BAR)
		);
	}
	
	private void mapYearCategoryIncidents() {
		// Load records
		List<List<String>> records = null;
		try {
			records = new CodingCompCSVUtil().readCSVFileWithoutHeaders(GUIMain.fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		yearCatIncidents = new HashMap<String, Map<String, Integer>>();
		
		categories = new ArrayList<String>();
		
		for (List<String> line : records) {
			String category = line.get(0);
			if (!categories.contains(category)) {
				categories.add(category);
			}
			String year = line.get(2);
			int incidents;
			try {
				incidents = Integer.parseInt(line.get(3));
			} catch (Exception e) {
				System.out.println("Bad line: " + line);
				continue;
			}
			Map<String, Integer> categoryIncidents = yearCatIncidents.getOrDefault(year, new HashMap<String, Integer>());
			categoryIncidents.put(category, categoryIncidents.getOrDefault(category, 0) + incidents);
			yearCatIncidents.put(year, categoryIncidents);
		}
		
		String[] sYears = new ArrayList<String>(yearCatIncidents.keySet()).toArray(new String[yearCatIncidents.size()]);
		Arrays.sort(sYears);
		years = new int[sYears.length];
		for (int i = 0; i < sYears.length; i++) {
			years[i] = Integer.parseInt(sYears[i]);
		}
	}

	public void update(ControlFilters filters) {
		int chartType = filters.getChartType();
		
		Chart chart = null;
		if (chartType == ControlFilters.CHART_BAR) {
			chart = buildBarChart(filters);
		} else if (chartType == ControlFilters.CHART_PIE) {
			chart = buildPieChart(filters);
		}
		
		if (chart == null) {
			return;
		}
		
		if (currentXPanel != null) {
			this.remove(currentXPanel);
		}
		
		SwingWrapper wrapper = new SwingWrapper(chart);
		JFrame frame = wrapper.displayChart();
		frame.setVisible(false);
		currentXPanel = wrapper.getXChartPanel();
		this.add(currentXPanel);
	}
	
	private Chart buildBarChart(ControlFilters filters) {
		CategoryChart chart = new CategoryChartBuilder()
				.width(WIDTH)
				.height(HEIGHT)
				.title("Bar chart")
				.xAxisTitle("Year")
				.yAxisTitle("Incidents")
				.build();
		
		chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
		chart.getStyler().setAvailableSpaceFill(0.96);
		chart.getStyler().setOverlapped(false);
		
		for (String cat : categories) {
			int[] incidents = new int[years.length];
			for (int i = 0; i < years.length; i++) {
				incidents[i] = yearCatIncidents.getOrDefault(years[i] + "", new HashMap<String, Integer>())
						.getOrDefault(cat, 0);
			}
			chart.addSeries(cat, years, incidents);
		}
		
		return chart;
	}
	
	private Chart buildPieChart(ControlFilters filters) {
		PieChart chart = new PieChartBuilder()
				.width(WIDTH)
				.height(HEIGHT)
				.title("Pie chart")
				.build();
		
		for (String cat : categories) {
			int incidents = 0;
			for (int year : years) {
				incidents += yearCatIncidents.getOrDefault(year + "", new HashMap<String, Integer>())
				.getOrDefault(cat, 0);
			}
			chart.addSeries(cat, incidents);
		}
		
		return chart;
	}
}
