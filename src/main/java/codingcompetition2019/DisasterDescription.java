package codingcompetition2019;

public class DisasterDescription {
	private String year = "";
	private String category = "";
	private int reportedIncidents = 0;

	/**
	 * Creates a new Disaster Description object with just a year
	 * @param year the year of the disaster
	 */
	public DisasterDescription(String year) {
	    this(year, "", 0);
    }

	/**
	 * Creates a new Disaster Description object with specified parameters
	 * @param year year of disaster or N/A if over multiple years
	 * @param type the category of the disaster
	 * @param reportedIncidents the number of reported incidents
	 */
    public DisasterDescription(String year, String type, int reportedIncidents) {
	    this.year = year;
	    this.category = type;
	    this.reportedIncidents = reportedIncidents;
    }

	/**
	 * Getter method for the number of reported incidents.
	 * @return the number of reported incidents.
	 */
	public int getReportedIncidentsNum() {
	    return reportedIncidents;
    }

	/**
	 * Adds number of new incidents to the current number of reported incidents.
	 * @param numIncidents the number of incidents
	 */
	public void addReportedIncidents(int numIncidents) {
        reportedIncidents += numIncidents;
    }

	/**
	 * Getter method for the year
	 * @return the year
	 */
	public String getYear() {
        return year;
    }

	/**
	 * Getter method for the category
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
}
