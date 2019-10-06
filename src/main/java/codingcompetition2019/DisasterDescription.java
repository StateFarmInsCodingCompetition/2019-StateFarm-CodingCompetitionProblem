package codingcompetition2019;

/**
 * This class represents a description for a natural disaster
 * @author Mudit Gupta and Ryan Thomas Lynch
 * @version 1.0
 */
public class DisasterDescription {


	private String category;
	private int reportedIncidents;
	private int year;

	/**
	* Initializes a description of some summary of disasters in a
	* given year and category with the number of incidents reported.
	* @param category The category of incidents in this summary.
	* @param reportedIncidents The number of incidents in the category and year.
	* @param year The year to which this summary applies.
	*/
	public DisasterDescription(String category, int reportedIncidents, int year) {
		this.category = category;
		this.reportedIncidents = reportedIncidents;
		this.year = year;
	}

	/**
	* Initializes a description of some summary of disasters in a
	* given category with the number of incidents reported with either an unknown
	* year or no proper year (sets year to -1).
	* @param category The category of incidents in this summary.
	* @param reportedIncidents The number of incidents in the category.
	*/
	public DisasterDescription(String category, int reportedIncidents) {
		this(category, reportedIncidents, -1);
	}

	/**
	* Initializes a description of some summary of disasters in a
	* given year with the number of incidents reported with either an unknown
	* category or no proper category (sets category to null).
	* @param reportedIncidents The number of incidents in the category.
	* @param year The year of the incidents in this summary.
	*/
	public DisasterDescription(int reportedIncidents, int year) {
		this(null, reportedIncidents, year);
	}

	/**
	* Returns the category.
	* @return the category.
	*/
	public String getCategory() {
		return category;
	}

	/**
	* Returns the number of reported incidents in this summary.
	* @return the number of reported incidents in this summary.
	*/
	public int getReportedIncidentsNum() {
		return reportedIncidents;
	}

	/**
	* Returns the year as a String if it exists (is not -1). If the year is -1,
	* returns "NoYr".
	* @return the year as a String if it exists (is not -1). If the year is -1,
	* returns "NoYr".
	*/
	public String getYear() {
		return year == -1 ? "NoYr" : String.valueOf(year);
	}
}
