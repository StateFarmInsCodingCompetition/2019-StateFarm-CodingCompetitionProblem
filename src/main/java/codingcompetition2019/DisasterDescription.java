package codingcompetition2019;

/**
 * This class holds basic information about a given disaster
 * It has the year the disaster occurred
 * It has the total number of reported incidents for that given disaster
 * It has the category of the disaster
 * It has the country the description occurred
 */
public class DisasterDescription {

    private String category;
    private String country;
    private int reportedIncidentsNum;
    private int year;

    /**
     * No-arg constructor
     */
    public DisasterDescription() {

    }


    // Accessors for data field
    public String getCategory() {
        return category;
    }

    public int getReportedIncidentsNum() {
        return reportedIncidentsNum;
    }

    public String getYear() {
        return year + "";
    }

    public String getCountry() {
        return country;
    }

    // Mutator for data field
    public void setCategory(String category) {
        this.category = category;
    }

    public void setReportedIncidentsNum(int reportedIncidentsNum) {
        this.reportedIncidentsNum = reportedIncidentsNum;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
