package codingcompetition2019;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CodingCompCSVUtil {

    public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
        List<List<String>> countries = readCSVFileWithoutHeaders(fileName);

        // Filter the csv values by country
        List<List<String>> countryEntries = new ArrayList<List<String>>();
        for (List<String> entry : countries) {
            // Check if the name of the country matches
            if (entry.get(0).trim().toLowerCase().equals(countryName.trim().toLowerCase())) {
                countryEntries.add(entry);
            }
        }
        return countryEntries;
    }

    public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
        // Get all entries without the header
        File inputFile = new File(fileName);

        List<List<String>> csvEntriesWithHeader = new ArrayList<List<String>>();

        if (inputFile.exists()) {
            try {
                Scanner input = new Scanner(inputFile);
                // Read each line an add it to the list
                while (input.hasNext()) {
                    String currentLine = input.nextLine();
                    // Split the line by comma and store it as array
                    List<String> values = Arrays.asList(currentLine.split(","));
                    csvEntriesWithHeader.add(values);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }

        return csvEntriesWithHeader;
    }

    public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
        // Get all records with the header
        List<List<String>> allCSVEntries = readCSVFileWithHeaders(fileName);

        //Remove the first entry of header
        allCSVEntries.remove(0);

        return allCSVEntries;
    }

    public DisasterDescription getMostImpactfulYear(List<List<String>> records) {

        // See if there is at least one record
        if (records.size() > 0) {

            //Assume the first entry is the most impactful
            int year = Integer.parseInt(records.get(0).get(2));
            int maxImpact = Integer.parseInt(records.get(0).get(3));
            String entity = records.get(0).get(0);

            //Iterate through each entry and get the find the most impact
            for (List<String> entry : records) {
                int currentImpact = Integer.parseInt(entry.get(3));
                if (currentImpact > maxImpact) {
                    maxImpact = currentImpact;
                    year = Integer.parseInt(entry.get(2));
                    entity = entry.get(0);
                }
            }

            DisasterDescription disaster = new DisasterDescription();
            disaster.setYear(year);
            disaster.setReportedIncidentsNum(maxImpact);
            disaster.setCategory(entity); // Could be country name or category

            return disaster;
        }

        return new DisasterDescription();
    }

    public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {

        //Filter the list by category
        List<List<String>> recordByCategory = new LinkedList<List<String>>();
        for (List<String> record : records) {
            // Check if the category matches
            if (record.get(0).toLowerCase().trim().equals(category.toLowerCase().trim())) {
                recordByCategory.add(record);
            }
        }

        return getMostImpactfulYear(recordByCategory);
    }

    public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {

        // Filter the record by year
        List<List<String>> recordsByYear = new LinkedList<List<String>>();
        for (List<String> record : records) {
            if (record.get(2).trim().toLowerCase().equals(year.trim().toLowerCase())) {
                // Ignore for all natural disasters
                if (!record.get(0).trim().toLowerCase().equals("all natural disasters")) {
                    recordsByYear.add(record);
                }
            }
        }

        return getMostImpactfulYear(recordsByYear);
    }


    public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {

        int totalIncidents = 0;
        for (List<String> record : records) {
            // Check if the category matches
            if (record.get(0).toLowerCase().trim().equals(category.toLowerCase().trim())) {
                totalIncidents += Integer.parseInt(record.get(3));
            }
        }

        DisasterDescription disaster = new DisasterDescription();
        disaster.setCategory(category);
        disaster.setReportedIncidentsNum(totalIncidents);

        return disaster;
    }


    /**
     * This method will return the count if the number of incident falls within the provided range.
     * To simplify the problem, we assume:
     * + A value of -1 is provided if the max range is NOT applicable.
     * + A min value can be provided, without providing a max value (which then has to be -1 like indicated above).
     * + If a max value is provided, then a max value is also needed.
     */
    public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {
        boolean hasMaximumRange = max != -1;
        int count = 0;

        for (List<String> record : records) {
            int reportedIncident = Integer.parseInt(record.get(3));

            if (hasMaximumRange) {
                if (reportedIncident >= min && reportedIncident <= max) {
                    count++;
                }
            } else { // No maximum range
                if (reportedIncident >= min) {
                    count++;
                }
            }
        }

        return count;
    }

    public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
        return getTotalReportedIncidents(records1) > getTotalReportedIncidents(records2);
    }

    /**
     * This method iterates through the records and calculates the total reported incidents.
     *
     * @param records List of disaster with their metadata
     * @return int total reported incidents
     */
    private int getTotalReportedIncidents(List<List<String>> records) {
        int total = 0;
        for (List<String> entry : records) {
            total += Integer.parseInt(entry.get(3));
        }
        return total;
    }


}
