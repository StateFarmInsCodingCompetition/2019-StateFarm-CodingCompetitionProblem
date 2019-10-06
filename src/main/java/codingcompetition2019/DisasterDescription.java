package codingcompetition2019;

public class DisasterDescription {
	private String year = "";
	private String category = "";
	private int reportedIncidents = 0;

	public DisasterDescription(String year) {
	    this(year, "", 0);
    }

    public DisasterDescription(String year, String type, int reportedIncidents) {
	    this.year = year;
	    this.category = type;
	    this.reportedIncidents = reportedIncidents;
    }

    public int getReportedIncidentsNum() {
	    return reportedIncidents;
    }

    public void addReportedIncidents(int numIncidents) {
        reportedIncidents += numIncidents;
    }
    public String getYear() {
        return year;
    }
	public String getCategory() {
		return category;
	}
}
