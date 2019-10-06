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
        DisasterDescription description = new DisasterDescription();

        Map<String, Integer> incidentsByYear = new HashMap<String, Integer>();

        String mostImpactfulYear;
        int max = Integer.MIN_VALUE;

        for (List<String> line : records) {
            String year = line.get(2);
            final int incidents = Integer.parseInt(line.get(3));

            Integer value = incidentsByYear.get(year);

            incidentsByYear.put(year, value == null ? incidents : value + incidents);
        }


        return description;
    }

    public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
        // TODO implement this method
        return null;
    }

    public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
        // TODO implement this method
        return null;
    }

    public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
        // TODO implement this method
        return null;
    }

    /**
     * This method will return the count if the number of incident falls within the provided range.
     * To simplify the problem, we assume:
     * + A value of -1 is provided if the max range is NOT applicable.
     * + A min value can be provided, without providing a max value (which then has to be -1 like indicated above).
     * + If a max value is provided, then a max value is also needed.
     */
    public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {
        // TODO implement this method
        return -1;
    }

    public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
        // TODO implement this method
        return false;
    }
}
