package main.java.codingcompetition2019;

public class DisasterDescription {

    private String category;
    private String code;
    private String year;
    private String numReports;

    public DisasterDescription(String category, String code, String year, String numReports) {

        this.category = category;
        this.code = code;
        this.year = year;
        this.numReports = numReports;
    }

    public String getCategory() {
        return this.category;
    }

    public String getCode() {
        return this.code;
    }

    public String getYear() {
        return this.year;
    }

    public int getReportedIncidentsNum() {
        return Integer.parseInt(this.numReports);
    }


}
