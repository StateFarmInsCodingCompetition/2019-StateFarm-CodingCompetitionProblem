package codingcompetition2019;

/**
 * Stores information regarding disasters
 */

public class DisasterDescription {
    private String category; //Category or Country Name
    private String code; //Code associated with country (optional)
    private String year; //Year of disaster
    private int significant; //Number of significant events during a year
    
    public DisasterDescription(String category, String code, String year, int significant) {
        this.category = category;
        this.code = code;
        this.year = year;
        this.significant = significant;
    }
    
    /**
	 * Sets the category of incident
	 * @param category the new category
	 */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
	 * Sets the code of incident
	 * @param category the new code
	 */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
	 * Sets the year of incident
	 * @param category the new year
	 */
    public void setYear(String year) {
        this.year = year;
    }
    
    /**
	 * Sets the number of significant incidents of incident
	 * @param category the new number of incidents
	 */
    public void setSignificant(int significant) {
        this.significant = significant;
    }
    
    /** Returns the category/country name */
    public String getCategory() {
        return category;
    }
    
    /** Returns the code */
    public String getCode() {
        return code;
    }
    
    /** Returns the year of incident */
    public String getYear() {
        return year;
    }
    
    /** Returns the number of reported incidents */
    public int getReportedIncidentsNum() {
        return significant;
    }
    // TODO finish this class
}
