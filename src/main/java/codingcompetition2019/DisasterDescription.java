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
    private double avgIncidents;

    /**
     * Constructs a DisasterDescription object with the fields for the basic requirements
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
     * Constructs a DisasterDescription object with the fields for the bonus feature of average number of incidents per year by category
     * @param category The category of the disaster(s)
     * @param avgIncidents The average number of incidents per year for this category
     */
    public DisasterDescription(String category, double avgIncidents) {
        this.category = category;
        this.avgIncidents = avgIncidents;
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

    /**
     * Returns the average number of incidents per year for this category
     * @return The average number of incidents
     */
    public double getAvgIncidents() {
        return avgIncidents;
    }
}
