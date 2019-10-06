package codingcompetition2019;

import java.util.List;

public class DisasterDescription {
    private String category;
    private String countryCode;
    private String year;
    private int numIncidents;

    public DisasterDescription(String category, String countryCode, String year, int numIncidents) {
        this.category = category;
        this.countryCode = countryCode;
        this.year = year;
        this.numIncidents = numIncidents;
    }

    public DisasterDescription(List<String> csvRow) {
        this(csvRow.get(0), csvRow.get(1), csvRow.get(2), Integer.parseInt(csvRow.get(3)));
    }

    public String getCategory() {
        return this.category;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getYear() {
        return this.year;
    }

    public int getReportedIncidentsNum() {
        return this.numIncidents;
    }
}
