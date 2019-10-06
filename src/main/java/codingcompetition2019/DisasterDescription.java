package codingcompetition2019;

import java.util.List;

public class DisasterDescription {
	
	private String entity, code;
	private int year, statistic;
	
	public DisasterDescription(List<String> data) {
		if (data.size() < 4) {
			throw new IllegalArgumentException("Expected data to have at least 4 values");
		}
		
		entity = data.get(0);
		code = data.get(1);
		try {
			year = Integer.parseInt(data.get(2));
			statistic = Integer.parseInt(data.get(3));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid year or statistic number", e);
		}
	}
	
	public String getCode() {
		return code;
	}
	
	public String getCategory() {
		return entity;
	}
	
	public String getCountry() {
		return entity;
	}
	
	public int getReportedIncidentsNum() {
		return statistic;
	}
	
	public int getYear() {
		return year;
	}
	
	
	
}
