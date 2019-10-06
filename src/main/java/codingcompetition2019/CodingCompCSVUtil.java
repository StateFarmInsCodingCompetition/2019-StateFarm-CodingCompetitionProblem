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

    private enum SearchCriteria {
        CATEGORY, YEAR;
    }

    private enum ReturnResult {
        YEAR, DISASTER, REPORTED_INCIDENTS;
    }

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

    private DisasterDescription getDisasterDescriptionByCritera(SearchCriteria searchCriteria, String relevant, ReturnResult returnResult, List<List<String>> records) {
        DisasterDescription description = new DisasterDescription();

        if (returnResult == ReturnResult.YEAR) {
            Map<String, Integer> incidentsByYear = new HashMap<String, Integer>();
            String mostImpactfulYear = "";
            int max = Integer.MIN_VALUE;

            for (List<String> line : records) {
                String category = line.get(0);
                String year = line.get(2);

                if (searchCriteria == SearchCriteria.CATEGORY) {
                    if (!category.equalsIgnoreCase(relevant)) continue;
                } else if (searchCriteria == SearchCriteria.YEAR) {
                    if (!year.equalsIgnoreCase(relevant)) continue;
                }

                int incidents = Integer.parseInt(line.get(3));
                Integer currentValue = incidentsByYear.get(year);
                Integer value = currentValue == null ? incidents : currentValue + incidents;

                if (value > max) {
                    max = value;
                    mostImpactfulYear = year;
                }

                incidentsByYear.put(year, value);
            }
            if (searchCriteria == SearchCriteria.CATEGORY) {
                description.setCategory(relevant);
            } else {
                description.setCategory(ALL_NATURAL_DISASTERS);
            }
            description.setYear(mostImpactfulYear);
            description.setReportedIncidentsNum(max);

            return description;
        } else if (returnResult == ReturnResult.DISASTER) {
            Map<String, Integer> incidentsByDisaster = new HashMap<String, Integer>();

            String mostImpactfulDisaster = "";
            int max = Integer.MIN_VALUE;

            for (List<String> line : records) {
                String category = line.get(0);
                if (category.equalsIgnoreCase(ALL_NATURAL_DISASTERS)) continue;

                String year = line.get(2);

                if (searchCriteria == SearchCriteria.CATEGORY) {
                    if (!category.equalsIgnoreCase(relevant)) continue;
                } else if (searchCriteria == SearchCriteria.YEAR) {
                    if (!year.equalsIgnoreCase(relevant)) continue;
                }

                int incidents = Integer.parseInt(line.get(3));
                Integer currentValue = incidentsByDisaster.get(category);
                Integer value = currentValue == null ? incidents : currentValue + incidents;

                if (value > max) {
                    max = value;
                    mostImpactfulDisaster = category;
                }

                incidentsByDisaster.put(year, value);
            }
            description.setCategory(mostImpactfulDisaster);
            description.setReportedIncidentsNum(max);

            return description;
        } else if (returnResult == ReturnResult.REPORTED_INCIDENTS) {
            int totalReportedIncidents = 0;

            for (List<String> line : records) {
                String category = line.get(0);
                String year = line.get(2);

                if (!category.equalsIgnoreCase(relevant)) continue;

                totalReportedIncidents += Integer.parseInt(line.get(3));
            }

            description.setCategory(relevant);
            description.setReportedIncidentsNum(totalReportedIncidents);

            return description;
        }

        throw new IllegalArgumentException("Invalid return result");
    }

    public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
        return getDisasterDescriptionByCritera(SearchCriteria.CATEGORY, ALL_NATURAL_DISASTERS, ReturnResult.YEAR, records);
    }

    public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
        return getDisasterDescriptionByCritera(SearchCriteria.CATEGORY, category, ReturnResult.YEAR, records);
    }

    public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
        return getDisasterDescriptionByCritera(SearchCriteria.YEAR, year, ReturnResult.DISASTER, records);
    }

    public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
        return getDisasterDescriptionByCritera(SearchCriteria.CATEGORY, category, ReturnResult.REPORTED_INCIDENTS, records);
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
            int reportedIncidents = Integer.parseInt(line.get(3));
            if (reportedIncidents >= min) {
                if (max == -1) {
                    totalReportedIncidents++;
                } else {
                    if (reportedIncidents <= max) {
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
            int reportedIncidents = Integer.parseInt(line.get(3));

            totalReportedIncidents += reportedIncidents;
        }

        return totalReportedIncidents;
    }
}
