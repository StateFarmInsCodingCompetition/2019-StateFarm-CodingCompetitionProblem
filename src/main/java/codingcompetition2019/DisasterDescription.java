package codingcompetition2019;

public class DisasterDescription {
	
	
	private String category;
	private int reportedIncidents;
	private int year;
	
	//big boy constructor
	public DisasterDescription(String category, int reportedIncidents, int year) {
		this.category = category;
		this.reportedIncidents = reportedIncidents;
		this.year = year;
	}
	
	//like main constructor, but for year as a string
	public DisasterDescription(String category, int reportedIncidents, String year) {
		this(category, reportedIncidents, Integer.parseInt(year));
	}
	
	//for when the year is irrelevant
	public DisasterDescription(String category, int reportedIncidents) {
		this.category = category;
		this.reportedIncidents = reportedIncidents;
	}
    /**
     * [getCategory description]
     * @return [description]
     */
    public String getCategory() {
		return category;
	}

    /**
     * [getReportedIncidentsNum description]
     * @return [description]
     */
	public int getReportedIncidentsNum() {
		return reportedIncidents;
	}

    /**
     * [getYear description]
     * @return [description]
     */
	public String getYear() {
		return String.valueOf(year);
	}
}
