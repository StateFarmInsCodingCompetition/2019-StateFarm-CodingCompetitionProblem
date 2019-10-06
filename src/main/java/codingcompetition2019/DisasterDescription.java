package codingcompetition2019;

public class DisasterDescription {
	// TODO finish this class
	String year;
	String category;
	int reportedIncidentsNum;
	
	public DisasterDescription() {
		
	}
	
	public DisasterDescription(String category, int reportedIncidentsNum) {
		super();
		this.category = category;
		this.reportedIncidentsNum = reportedIncidentsNum;
	}

	public DisasterDescription(String category, int reportedIncidentsNum, String year) {
		super();
		this.year = year;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getReportedIncidentsNum() {
		return reportedIncidentsNum;
	}
	public void setReportedIncidentsNum(int reportedIncidentsNum) {
		this.reportedIncidentsNum = reportedIncidentsNum;
	}
	

	
}
