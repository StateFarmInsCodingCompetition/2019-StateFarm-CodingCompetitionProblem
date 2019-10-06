package codingcompetition2019;
/**
 * Object that has information detailing natural disaster(s)
 * @author --
 * @version 1.42
 *
 */
public class DisasterDescription {
	// TODO finish this class
	private int year;
	private String category;
	private int reportedIncident;
	
	/**
	 * Constructs a DisasterDescription object to describe some natrual disaster(s)
	 * @param year
	 * @param category
	 * @param reportedIncident
	 */
	public DisasterDescription(int year, String category, int reportedIncident) {
		this.year = year;
		this.category = category;
		this.reportedIncident = reportedIncident;
	}
	
	/**
	 * @return a String representation the category/type of natural disaster
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @return a String representation of the year of the natural disaster(s)
	 */
	public String getYear() {
		return Integer.toString(year);
	}
	/**
	 * @return the number of reported natural disasters there were
	 */
	public int getReportedIncidentsNum() {
		return reportedIncident;
	}
}
