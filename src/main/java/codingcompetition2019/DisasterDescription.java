package codingcompetition2019;

public class DisasterDescription {

    private String category;
    private int reportedIncidentsNum;
    private int year;

    public DisasterDescription() {

    }

    public String getCategory() {
        return category;
    }

    public int getReportedIncidentsNum() {
        return reportedIncidentsNum;
    }

    public String getYear() {
        return year + "";
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setReportedIncidentsNum(int reportedIncidentsNum) {
        this.reportedIncidentsNum = reportedIncidentsNum;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
