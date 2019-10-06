package codingcompetition2019;

import java.util.List;

public class DisasterDescription {
	
	private String entity, code, year;
	private int statistic;
	
	public DisasterDescription(List<String> data) {
		if (data.size() < 4) {
			throw new IllegalArgumentException("Expected data to have at least 4 values");
		}
		
		entity = data.get(0);
		code = data.get(1);
		year = data.get(2);
		try {
			statistic = Integer.parseInt(data.get(3));
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid statistic number", e);
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
	
	public String getYear() {
		return year;
	}
	
	
	
}
