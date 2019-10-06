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
	
	public ChartView() {
		CodingCompCSVUtil util = new CodingCompCSVUtil();
		List<List<String>> records = null;
		try {
			records = util.readCSVFileWithoutHeaders("src/main/resources/natural-disasters-by-type.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
		CategoryChart chart = this.buildYearBarChart(records);
		SwingWrapper wrapper = new SwingWrapper(chart);
		JFrame frame = wrapper.displayChart();
		frame.setVisible(false);
		add(wrapper.getXChartPanel());
	}
	
	private CategoryChart buildYearBarChart(List<List<String>> records) {
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
		
		Map<String, Map<String, Integer>> yearCatIncidents = new HashMap<String, Map<String, Integer>>();
		
		for (List<String> line : records) {
			String category = line.get(0);
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
		
		String[] years = new ArrayList<String>(yearCatIncidents.keySet()).toArray(new String[yearCatIncidents.size()]);
		Arrays.sort(years);
		
		//chart.addSeries("test", new [] {1, 2, 3, 4, 5}, new double[] {10, 20, 30, 40, 50});
		
		return chart;
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
