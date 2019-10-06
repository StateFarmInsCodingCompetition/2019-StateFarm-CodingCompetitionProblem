package codingcompetition2019;

/**
 * A class representing the DisasterDescription object
 * @author Sidhartha Chaganti, schaganti@gatech.edu
 * @version 1.0.0
 */
public class DisasterDescription {
    private String year;
    private String category;
    private int reportedIncidentsNum;

    /**
     * Constructs a DisasterDescription object
     * @param category The category of the disaster(s)
     * @param year The year the disaster(s) took place
     * @param reportedIncidentsNum The number of disasters of this type occured in this year
     */
    public DisasterDescription(String category, String year, int reportedIncidentsNum) {
        this.year = year;
        this.category = category;
        this.reportedIncidentsNum = reportedIncidentsNum;
    }

    /**
     * Returns the year of this instance
     * @return The year of this instance
     */
    public String getYear() {
        return year;
    }

    /**
     * Returns the category of this instance
     * @return The category of this instance
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns the number of reported incidents
     * @return The number of reported incidents
     */
    public int getReportedIncidentsNum() {
        return reportedIncidentsNum;
    }
}
