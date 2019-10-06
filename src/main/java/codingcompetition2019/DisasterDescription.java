package codingcompetition2019;

import java.util.List;

public class DisasterDescription {

    private String entity;
    private String code;
    private String year;
    private int reportedIncidentsNum;

    public DisasterDescription(List<String> record) {
        this(record.get(0), record.get(1), record.get(2), Integer.parseInt(record.get(3)));
    }

    public DisasterDescription(String entity, String code, String year, int reportedIncidentsNum) {
        this.entity = entity;
        this.code = code;
        this.year = year;
        this.reportedIncidentsNum = reportedIncidentsNum;
    }

    public DisasterDescription() {

    }

    public String getYear() {
        return year;
    }

    public String getCategory() {
        return entity;
    }

    public int getReportedIncidentsNum() {
        return reportedIncidentsNum;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setCategory(String category) {
        this.entity = category;
    }

    public void setReportedIncidentsNum(int reportedIncidentsNum) {
        this.reportedIncidentsNum = reportedIncidentsNum;
    }
}
