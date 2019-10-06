package codingcompetition2019;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CodingCompCSVUtil {

    /**
     * This method reads the CSV file and returns the data about the specified country
     *
     * @param fileName    filePath of the CSV file with data
     * @param countryName name of the country to filter the records by
     * @return Data about the disaster for that country
     * @throws IOException error on reading the file
     */
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

    /**
     * This method reads the CSV file including the header.
     * Each line of the CSV has data about the disaster separated by comma. Store it in a list
     * Store all the data in the list and return it
     *
     * @param fileName filePath of the CSV file with data
     * @return list with a list of data related to the disaster
     * @throws IOException error on reading the file
     */
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

                input.close();
            } catch (IOException ex) {
                throw ex;
            } catch (Exception ex) {
                // Other unexpected error occurred
                ex.printStackTrace();
                return null;
            }
        }

        return csvEntriesWithHeader;
    }

    /**
     * This method reads the CSV file without the header.
     * Each line of the CSV has data about the disaster separated by comma. Store it in a list
     * Store all the data in the list and return it
     *
     * @param fileName filePath of the CSV file with data
     * @return list with a list of data related to the disaster
     * @throws IOException error on reading the file
     */
    public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
        // Get all records with the header
        List<List<String>> allCSVEntries = readCSVFileWithHeaders(fileName);

        //Remove the first entry of header
        allCSVEntries.remove(0);

        return allCSVEntries;
    }

    /**
     * This method finds the most impactful year
     *
     * @param records list of data related to a disaster event
     * @return info about the most impactful Disaster based on year
     */
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
            disaster.setCategory(entity);

            return disaster;
        }

        return new DisasterDescription();
    }

    /**
     * This method filters the records by the given category and
     * returns the info about the most impactful disaster for that given category.
     *
     * @param category Category of disaster
     * @param records  List of data about the disaster
     * @return Info about the most impactful disaster for the given category
     */
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

    /**
     * This method filters the list by the given year and
     * returns info about the most impactful disaster for that given year.
     *
     * @param year    Filter the record based on this year
     * @param records List of data about the disaster
     * @return Info about the most impactful disaster for the given year
     */
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

    /**
     * This method filters the records by given category and
     * calculates the total number of reported incidents for that category
     *
     * @param category Disaster category to filter the records
     * @param records  List of data about disaster
     * @return Total number of reported incidents for the given category
     */
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

    /**
     * This method checks if the first list has more reported incidents than the second list.
     * It finds the total reported incidents for both records and compares them
     *
     * @param records1 List of data about disaster
     * @param records2 List of data about disaster
     * @return Boolean value indicating record1 has more reported incidents than record2
     */
    public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
        return getTotalReportedIncidents(records1) > getTotalReportedIncidents(records2);
    }

    /**
     * This method iterates through the records and calculates the total reported incidents for that given data.
     *
     * @param records List of data about the disaster
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
