package codingcompetition2019.ui;

import java.util.List;

public class ControlFilters {
	
	/**
	 * The type of the chart
	 */
	private int chartType;

	public static final int CHART_PIE = 1;
	public static final int CHART_BAR = 2;
	public static final int CHAR_LINE = 3;
	
	/**
	 * The data to display on the x-axis
	 */
	private int xAxis;
	
	public static final int XAXIS_YEAR = 1;
	public static final int XAXIS_CATEGORY = 2;
	
	/**
	 * A list of categories to include
	 */
	private List<String> categoryFilters;
	
	/**
	 * The minimum year when providing a year range filter
	 */
	private int minYear;
	
	/**
	 * The maximum year when providing a year range filter
	 */
	private int maxYear;
	
	/**
	 * Whether or not to display categories on the bar chart
	 */
	private boolean barChartDisplayCategories;
	
	public int getChartType() {
		return chartType;
	}
	
	public ControlFilters setChartType(int chartType) {
		this.chartType = chartType;
		return this;
	}
	
	public int getXAxis() {
		return xAxis;
	}
	
	public ControlFilters setXAxis(int xAxis) {
		this.xAxis = xAxis;
		return this;
	}
	
	public List<String> getCategoryFilters() {
		return categoryFilters;
	}
	
	public ControlFilters setCategoryFilters(List<String> categoryFilters) {
		this.categoryFilters = categoryFilters;
		return this;
	}
	
	public int getMinYear() {
		return minYear;
	}
	
	public ControlFilters setMinYear(int minYear) {
		this.minYear = minYear;
		return this;
	}
	
	public int getMaxYear() {
		return maxYear;
	}
	
	public ControlFilters setMaxYear(int maxYear) {
		this.maxYear = maxYear;
		return this;
	}
	
	public boolean getBarChartDisplayCategories() {
		return barChartDisplayCategories;
	}
	
	public ControlFilters setBarChartDisplayCategories(boolean barChartDisplayCategories) {
		this.barChartDisplayCategories = barChartDisplayCategories;
		return this;
	}
}
