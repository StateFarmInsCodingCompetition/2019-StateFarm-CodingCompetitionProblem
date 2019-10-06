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
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler.LegendPosition;

import codingcompetition2019.CodingCompCSVUtil;

public class ChartView extends JPanel {

	private static final long serialVersionUID = 1L;
	
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
		
		CategoryChart chart = null;
		if (chartType == ControlFilters.CHART_BAR) {
			chart = buildBarChart(filters);
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
	
	private CategoryChart buildBarChart(ControlFilters filters) {
		CategoryChart chart = createBaseChart()
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
	
	private CategoryChartBuilder createBaseChart() {
		return new CategoryChartBuilder()
				.width(800)
				.height(600);
	}
	
	private CategoryChart getChart() {
		CategoryChart chart = new CategoryChartBuilder()
				.width(800)
				.height(600)
				.title("My Chart")
				.xAxisTitle("My X axis")
				.yAxisTitle("My Y axis")
				.build();
		
		chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
		chart.getStyler().setAvailableSpaceFill(0.96);
		chart.getStyler().setOverlapped(false);
		 Histogram histogram1 = new Histogram(getGaussianData(10000), 20, -20, 20);
		   Histogram histogram2 = new Histogram(getGaussianData(5000), 20, -20, 20);
		    chart.addSeries("histogram 1", histogram1.getxAxisData(), histogram1.getyAxisData());
		    chart.addSeries("histogram 2", histogram2.getxAxisData(), histogram2.getyAxisData());
		 
		return chart;
	}
	
	private List<Double> getGaussianData(int count) {
	    List<Double> data = new ArrayList<Double>(count);
	    Random r = new Random();
	    for (int i = 0; i < count; i++) {
	      data.add(r.nextGaussian() * 10);
	    }
	    return data;
	  }
}
