package codingcompetition2019.ui;

import javax.swing.JPanel;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

public class ChartView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public ChartView() {
		
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
	}
}
