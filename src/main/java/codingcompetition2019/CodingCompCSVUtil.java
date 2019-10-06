package codingcompetition2019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CodingCompCSVUtil {

    private BufferedReader bufferedReader;

    private static final String ALL_NATURAL_DISASTERS = "All natural disasters";

    public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
        List<List<String>> result = new LinkedList<List<String>>();
        bufferedReader = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] values = line.split(",");

            if (values[0].equalsIgnoreCase(countryName)) {
                LinkedList<String> lineEntry = new LinkedList();

                for (String value : values) {
                    lineEntry.add(value);
                }

                result.add(lineEntry);
            }
        }

        return result;
    }

    public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
        List<List<String>> result = new LinkedList<List<String>>();
        bufferedReader = new BufferedReader(new FileReader(fileName));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] values = line.split(",");

            LinkedList<String> lineEntry = new LinkedList();

            for (String value : values) {
                lineEntry.add(value);
            }

            result.add(lineEntry);
        }

        return result;
    }

    public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
        List<List<String>> result = new LinkedList<List<String>>();
        bufferedReader = new BufferedReader(new FileReader(fileName));

        String line;
        boolean firstLine = true;

        while ((line = bufferedReader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }
            String[] values = line.split(",");

            LinkedList<String> lineEntry = new LinkedList();

            for (String value : values) {
                lineEntry.add(value);
            }

            result.add(lineEntry);
        }

        return result;
    }

    public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
        Map<String, Integer> incidentsByYear = new HashMap<String, Integer>();

        DisasterDescription result = new DisasterDescription();

        int max = Integer.MIN_VALUE;

        for (List<String> line : records) {
            DisasterDescription description = new DisasterDescription(line);

            // maintains accuracy of total reported incidents in significant-XXX.csv files due to lack of ALL_NATURAL_DISASTERS
            if (description.getCategory().equalsIgnoreCase(ALL_NATURAL_DISASTERS)) continue;

            int incidents = description.getReportedIncidentsNum();

            Integer currentMapValue = incidentsByYear.get(description.getYear());
            Integer value = currentMapValue == null ? incidents : currentMapValue + incidents;

            if (value > max) {
                max = value;
                result.setYear(description.getYear());
            }

            incidentsByYear.put(description.getYear(), value);
        }

        return result;
    }

    public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
        Map<String, Integer> incidentsByYear = new HashMap<String, Integer>();

        DisasterDescription result = new DisasterDescription();

        int max = Integer.MIN_VALUE;

        for (List<String> line : records) {
            DisasterDescription description = new DisasterDescription(line);

            if (!description.getCategory().equalsIgnoreCase(category)) continue;

            int incidents = description.getReportedIncidentsNum();

            Integer currentMapValue = incidentsByYear.get(description.getYear());
            Integer value = currentMapValue == null ? incidents : currentMapValue + incidents;

            if (value > max) {
                max = value;
                result.setYear(description.getYear());
            }

            incidentsByYear.put(description.getYear(), value);
        }

        return result;
    }

    public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
        Map<String, Integer> incidentsByDisaster = new HashMap<String, Integer>();

        DisasterDescription result = new DisasterDescription();
        result.setYear(year);

        int max = Integer.MIN_VALUE;

        for (List<String> line : records) {
            DisasterDescription description = new DisasterDescription(line);

            if (!description.getYear().equals(year)) continue;
            if (description.getCategory().equalsIgnoreCase(ALL_NATURAL_DISASTERS)) continue;

            int incidents = description.getReportedIncidentsNum();

            Integer currentMapValue = incidentsByDisaster.get(description.getCategory());
            Integer value = currentMapValue == null ? incidents : currentMapValue + incidents;

            if (value > max) {
                max = value;
                result.setCategory(description.getCategory());
                result.setReportedIncidentsNum(value);
            }

            incidentsByDisaster.put(description.getCategory(), value);
        }

        return result;
    }

    public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
        DisasterDescription result = new DisasterDescription();

        int totalReportedIncidents = 0;

        for (List<String> line : records) {
            DisasterDescription description = new DisasterDescription(line);

            if (!description.getCategory().equals(category)) continue;

            totalReportedIncidents += description.getReportedIncidentsNum();
        }

        result.setCategory(category);
        result.setReportedIncidentsNum(totalReportedIncidents);

        return result;
    }

    /**
     * This method will return the count if the number of incident falls within the provided range.
     * To simplify the problem, we assume:
     * + A value of -1 is provided if the max range is NOT applicable.
     * + A min value can be provided, without providing a max value (which then has to be -1 like indicated above).
     * + If a max value is provided, then a max value is also needed.
     */
    public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {
        int totalReportedIncidents = 0;

        for (List<String> line : records) {
            DisasterDescription description = new DisasterDescription(line);

            if (description.getReportedIncidentsNum() >= min) {
                if (max == -1) {
                    totalReportedIncidents++;
                } else {
                    if (description.getReportedIncidentsNum() <= max) {
                        totalReportedIncidents++;
                    }
                }
            }
        }

        return totalReportedIncidents;
    }

    public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
        return getTotalReportedIncidentsInRange(records1) > getTotalReportedIncidentsInRange(records2);
    }

    private int getTotalReportedIncidentsInRange(List<List<String>> records) {
        int totalReportedIncidents = 0;

        for (List<String> line : records) {
            DisasterDescription description = new DisasterDescription(line);

            totalReportedIncidents += description.getReportedIncidentsNum();
        }

        return totalReportedIncidents;
    }
}
