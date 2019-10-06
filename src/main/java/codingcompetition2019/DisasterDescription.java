package codingcompetition2019;

public class DisasterDescription {
	// TODO finish this class
	private int year;
	private String category;
	private int reportedIncident;
	
	public DisasterDescription(int year, String category, int reportedIncident) {
		this.year = year;
		this.category = category;
		this.reportedIncident = reportedIncident;
	}
	
	public String getCategory() {
		return category;
	}
	public int getYear() {
		return year;
	}
	public int getReportedIncidentsNum() {
		return reportedIncident;
	}
}
