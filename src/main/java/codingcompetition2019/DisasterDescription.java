package codingcompetition2019;

public class DisasterDescription {
	private String year = "";
	private String category = "";
	private int numInYear = 0;
	public DisasterDescription(String year) {
	    this(year, "", 0);
    }
    public DisasterDescription(String year, String type, int num) {
	    this.year = year;
	    this.category = type;
	    this.numInYear = num;
    }

    public int getReportedIncidentsNum() {
	    return numInYear;
    }

    public String getYear() {
        return year;
    }
}
