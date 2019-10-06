package codingcompetition2019;

public class DisasterDescription {
	
	private String entity, code;
	private int year, statistic;
	
	public DisasterDescription(String[] data) {
		if (data.length < 4) {
			throw new IllegalArgumentException("Expected data to have at least 4 values");
		}
		
		entity = data[0];
		code = data[1];
		try {
			year = Integer.parseInt(data[2]);
			statistic = Integer.parseInt(data[3]);
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
